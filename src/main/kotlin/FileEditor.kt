package io.github.omydagreat

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.BasicRichText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Composable function that displays a file editor window along with a markdown preview.
 *
 * @param file The file to be edited.
 */
@Composable
fun FileEditorWindow(file: File) {
  var fileContent by remember { mutableStateOf("") }

  LaunchedEffect(file) { loadFileContent(file) { content -> fileContent = content } }

  Row(modifier = Modifier.fillMaxSize().padding(16.dp)) {
    FileEditor(
      content = fileContent,
      onContentChanged = {
        fileContent = it
        saveFile(file, it)
      },
      modifier = Modifier.weight(1f).padding(end = 8.dp),
    )

    BasicRichText(modifier = Modifier.weight(1f).padding(start = 8.dp)) { Markdown(fileContent) }
  }
}

/**
 * Composable function that displays a file editor.
 *
 * @param content The content of the file.
 * @param onContentChanged Callback function to handle content changes.
 * @param modifier Modifier to be applied to the editor.
 */
@Composable
fun FileEditor(content: String, onContentChanged: (String) -> Unit, modifier: Modifier = Modifier) {
  OutlinedTextField(
    value = content,
    onValueChange = onContentChanged,
    modifier = modifier.fillMaxSize(),
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
