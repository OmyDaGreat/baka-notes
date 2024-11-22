package io.github.omydagreat.ui.folder

import androidx.compose.runtime.*
import io.github.omydagreat.ui.file.FileTreeView
import io.github.omydagreat.util.buildFileTree
import io.github.vinceglb.filekit.core.PlatformDirectory
import moe.tlaster.precompose.navigation.Navigator

/**
 * Composable function to display the folder view.
 *
 * This function creates a view that displays the contents of a folder as a tree structure. When a
 * file is selected from the tree, it opens a new window with a file editor and markdown preview.
 *
 * @param folder The selected folder represented as a `PlatformDirectory`.
 */
@Composable
fun FolderView(folder: PlatformDirectory, navi: Navigator) {
  FileTreeView(
    folder.file.buildFileTree(),
    onFileSelected = { file -> navi.navigate("fileEditor/${file.path}") },
  )
}
