@file:Suppress("kotlin:S1128")

package io.github.omydagreat.ui.file

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
import io.github.omydagreat.util.PreferencesManager
import io.github.omydagreat.util.PreferencesManager.Companion.saveScrollPosition
import io.github.omydagreat.util.TreeNode
import io.github.omydagreat.util.flattenTree
import java.io.File

/**
 * Composable function to display the file tree view.
 *
 * This function creates a view that displays the contents of a folder as a tree structure. Each
 * file and folder is represented as a node in the tree. When a file is selected from the tree, the
 * `onFileSelected` callback is triggered with the selected file.
 *
 * The function also manages the scroll position of the tree view. When the folder path changes, it
 * loads the saved scroll position from the preferences and scrolls to that position. When the
 * composable is disposed, it saves the current scroll position to the preferences.
 *
 * @param node The root node of the file tree, represented as a `TreeNode<File>`.
 * @param onFileSelected Lambda function to handle file selection. This function is called whenever
 *   a file is selected, with the selected file as its parameter.
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
            if (file.isFile) {
              onFileSelected(file)
            }
          },
      )
    }
  }

  DisposableEffect(folderPath) {
    onDispose { saveScrollPosition(folderPath, listState.firstVisibleItemIndex) }
  }
}