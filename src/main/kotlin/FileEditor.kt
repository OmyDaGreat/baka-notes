package io.github.omydagreat

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Composable function to display the file editor window.
 *
 * @param file The selected file.
 */
@Composable
fun FileEditorWindow(file: File) {
  var fileContent by remember { mutableStateOf("") }

  LaunchedEffect(file) { loadFileContent(file) { content -> fileContent = content } }

  FileEditor(
    content = fileContent,
    onContentChanged = {
      fileContent = it
      saveFile(file, it)
    },
  )
}

/**
 * Composable function to display the file editor.
 *
 * @param content The content of the file.
 * @param onContentChanged Lambda function to handle content changes.
 */
@Composable
fun FileEditor(content: String, onContentChanged: (String) -> Unit) {
  OutlinedTextField(
    value = content,
    onValueChange = onContentChanged,
    modifier = Modifier.fillMaxSize(),
    maxLines = Int.MAX_VALUE,
    singleLine = false,
  )
}

val ioScope = CoroutineScope(Dispatchers.IO)

/**
 * Loads the content of a file.
 *
 * @param file The file to load.
 * @param onContentLoaded Lambda function to handle the loaded content.
 */
fun loadFileContent(file: File?, onContentLoaded: (String) -> Unit) {
  file?.let {
    ioScope.launch {
      val content = Files.readString(Paths.get(it.toURI()))
      onContentLoaded(content)
    }
  }
}

/**
 * Saves the content to a file.
 *
 * @param file The file to save.
 * @param content The content to save.
 */
fun saveFile(file: File?, content: String) {
  file?.let { ioScope.launch { Files.writeString(Paths.get(it.toURI()), content) } }
}
