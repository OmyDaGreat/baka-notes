package io.github.omydagreat.ui.folder

import androidx.compose.runtime.*
import io.github.omydagreat.ui.file.FileTreeView
import io.github.omydagreat.util.TreeNode
import io.github.omydagreat.util.gate
import java.io.File
import moe.tlaster.precompose.navigation.Navigator

/**
 * Composable function to display the folder view.
 *
 * This function creates a view that displays the contents of a folder as a tree structure. When a
 * file is selected from the tree, it opens a new window with a file editor and markdown preview.
 *
 * @param treeNodes The selected folder represented as a `PlatformDirectory`.
 */
@Composable
fun FolderView(treeNodes: TreeNode<File>, navi: Navigator) {
  LaunchedEffect(treeNodes) { println("Current folder updated: ${treeNodes.value}") }

  FileTreeView(treeNodes, onFileSelected = { file -> navi gate "fileEditor/${file.path}" })
}
