package io.github.omydagreat

import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import io.github.vinceglb.filekit.core.PlatformDirectory
import java.io.File

/**
 * Composable function to display the folder view.
 *
 * This function creates a view that displays the contents of a folder as a tree structure.
 * When a file is selected from the tree, it opens a new window with a file editor and markdown preview.
 *
 * @param folder The selected folder represented as a `PlatformDirectory`.
 */
@Composable
fun FolderView(folder: PlatformDirectory) {
  val fileTree by remember { mutableStateOf(folder.file.buildFileTree()) }
  var selectedFile by remember { mutableStateOf<File?>(null) }

  FileTreeView(fileTree, onFileSelected = { file -> selectedFile = file })

  selectedFile?.let { file ->
    Window(onCloseRequest = { selectedFile = null }, title = "baka File Editor - ${file.name}") {
      FileEditorWindow(file)
    }
  }
}
