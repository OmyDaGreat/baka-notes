package io.github.omydagreat

import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import io.github.vinceglb.filekit.core.PlatformDirectory
import java.io.File

/**
 * Composable function to display the folder view.
 *
 * @param folder The selected folder.
 */
@Composable
fun FolderView(folder: PlatformDirectory) {
  val fileTree by remember { mutableStateOf(buildFileTree(folder.file)) }
  var selectedFile by remember { mutableStateOf<File?>(null) }

  FileTreeView(fileTree, onFileSelected = { file -> selectedFile = file })

  selectedFile?.let { file ->
    Window(onCloseRequest = { selectedFile = null }, title = "baka File Editor - ${file.name}") {
      FileEditorWindow(file)
    }
  }
}
