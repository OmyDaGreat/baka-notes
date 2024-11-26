package io.github.omydagreat.ui.file

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.material.RichText
import io.github.omydagreat.util.PreferencesManager
import io.github.omydagreat.util.loadFileContent
import io.github.omydagreat.util.saveFile
import java.io.File

/**
 * Composable function that displays a file editor window along with a markdown preview.
 *
 * This function creates a window that contains a file editor and a markdown preview side by side.
 * The file editor allows the user to edit the content of the file, and the markdown preview
 * displays the rendered markdown content in real-time as the user types.
 *
 * @param file The file to be edited. The content of this file will be loaded and displayed in the
 *   editor.
 */
@Composable
fun FileEditorWindow(file: File) {
  var fileContent by remember { mutableStateOf("") }

  LaunchedEffect(file) {
    PreferencesManager.saveLatestFile(file.absolutePath)
    file.loadFileContent { content -> fileContent = content }
  }

  Row(modifier = Modifier.fillMaxSize().padding(16.dp)) {
    FileEditor(
      content = fileContent,
      onContentChanged = {
        fileContent = it
        file.saveFile(it)
      },
      modifier = Modifier.weight(1f).padding(end = 8.dp),
    )

    Column(
      modifier = Modifier.weight(1f).padding(start = 8.dp).verticalScroll(rememberScrollState())
    ) {
      RichText(modifier = Modifier.background(Color.White).padding(16.dp)) { Markdown(fileContent) }
    }
  }
}
