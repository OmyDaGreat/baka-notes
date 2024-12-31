package xyz.malefic.ui.folder

import androidx.compose.runtime.Composable
import io.github.vinceglb.filekit.core.PlatformDirectory
import moe.tlaster.precompose.navigation.Navigator
import xyz.malefic.ext.precompose.gate
import xyz.malefic.ext.tree.TreeNode
import xyz.malefic.ui.file.FileTreeView
import java.io.File

/**
 * Composable function to display the folder view.
 *
 * This function creates a view that displays the contents of a folder as a tree structure. When a
 * file is selected from the tree, it opens a new window with a file editor and markdown preview.
 *
 * @param treeNodes The selected folder represented as a `PlatformDirectory`.
 */
@Composable
fun FolderView(
    treeNodes: TreeNode<File>,
    navi: Navigator,
    onFolderSelected: (PlatformDirectory) -> Unit,
) {
    FileTreeView(
        treeNodes,
        onFileSelected = { file ->
            if (file.isDirectory) {
                onFolderSelected(PlatformDirectory(file))
            } else if (file.isFile) {
                navi gate "fileEditor/${file.path}"
            }
        },
    )
}
