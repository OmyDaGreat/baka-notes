package io.github.omydagreat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.io.File

/**
 * Data class representing a tree node.
 *
 * @param T The type of the value.
 * @param value The value of the node.
 * @param children The children of the node.
 */
data class TreeNode<T>(val value: T, val children: List<TreeNode<T>> = emptyList())

/**
 * Composable function to display the file tree view.
 *
 * @param node The root node of the file tree.
 * @param onFileSelected Lambda function to handle file selection.
 */
@Composable
fun FileTreeView(node: TreeNode<File>, onFileSelected: (File) -> Unit) {
  val flattenedTree = remember { flattenTree(node) }
  val listState = rememberLazyListState()
  val folderPath = node.value.absolutePath

  LaunchedEffect(folderPath) {
    val savedScrollPosition = PreferencesManager.loadScrollPosition(folderPath)
    listState.scrollToItem(savedScrollPosition)
  }

  LazyColumn(state = listState, modifier = Modifier.fillMaxHeight().padding(start = 16.dp)) {
    items(flattenedTree) { (file, depth) ->
      Text(
        text = file.name,
        modifier =
          Modifier.padding(start = (depth * 16).dp).clickable {
            if (file.isFile) onFileSelected(file)
          },
      )
    }
  }

  DisposableEffect(folderPath) {
    onDispose { PreferencesManager.saveScrollPosition(folderPath, listState.firstVisibleItemIndex) }
  }
}

/**
 * Builds a file tree from a given file.
 *
 * @param file The root file.
 * @return The root node of the file tree.
 */
fun buildFileTree(file: File): TreeNode<File> {
  val children = file.listFiles()?.map { buildFileTree(it) } ?: emptyList()
  return TreeNode(file, children)
}

/**
 * Flattens a tree structure into a list of pairs.
 *
 * @param node The root node of the tree.
 * @param depth The current depth of the node.
 * @return A list of pairs containing the file and its depth.
 */
fun flattenTree(node: TreeNode<File>, depth: Int = 0): List<Pair<File, Int>> =
  mutableListOf<Pair<File, Int>>().also {
    it.add(node.value to depth)
    node.children.forEach { child -> it.addAll(flattenTree(child, depth + 1)) }
  }
