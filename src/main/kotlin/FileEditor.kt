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
 * This function creates a window that contains a file editor and a markdown preview side by side.
 * The file editor allows the user to edit the content of the file, and the markdown preview
 * displays the rendered markdown content in real-time as the user types.
 *
 * @param file The file to be edited. The content of this file will be loaded and displayed in the editor.
 */
@Composable
fun FileEditorWindow(file: File) {
  var fileContent by remember { mutableStateOf("") }

  LaunchedEffect(file) { file.loadFileContent { content -> fileContent = content } }

  Row(modifier = Modifier.fillMaxSize().padding(16.dp)) {
    FileEditor(
      content = fileContent,
      onContentChanged = {
        fileContent = it
        file.saveFile(it)
      },
      modifier = Modifier.weight(1f).padding(end = 8.dp),
    )

    BasicRichText(modifier = Modifier.weight(1f).padding(start = 8.dp)) { Markdown(fileContent) }
  }
}

/**
 * Composable function that displays a file editor.
 *
 * This function creates an `OutlinedTextField` that allows the user to edit the content of a file.
 * The content of the file is passed as a parameter, and any changes made to the content are
 * propagated back through the `onContentChanged` callback function.
 *
 * @param content The content of the file to be displayed and edited.
 * @param onContentChanged Callback function to handle content changes. This function is called
 *        whenever the content of the text field changes, with the new content as its parameter.
 * @param modifier Modifier to be applied to the editor. This allows for customization of the
 *        layout and appearance of the text field.
 */
@Composable
fun FileEditor(content: String, onContentChanged: (String) -> Unit, modifier: Modifier = Modifier) =
  OutlinedTextField(
    value = content,
    onValueChange = onContentChanged,
    modifier = modifier.fillMaxSize(),
    maxLines = Int.MAX_VALUE,
    singleLine = false,
  )

val ioScope = CoroutineScope(Dispatchers.IO)

/**
 * Loads the content of the file.
 *
 * @param onContentLoaded Lambda function to handle the loaded content.
 */
fun File.loadFileContent(onContentLoaded: (String) -> Unit) = ioScope.launch {
  val content = Files.readString(Paths.get(this@loadFileContent.toURI()))
  onContentLoaded(content)
}

/**
 * Saves the content to the file.
 *
 * @param content The content to save.
 */
fun File.saveFile(content: String) = ioScope.launch { Files.writeString(Paths.get(this@saveFile.toURI()), content) }
