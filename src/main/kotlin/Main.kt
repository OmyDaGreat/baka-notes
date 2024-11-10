package io.github.omydagreat

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.omydagreat.PreferencesManager.Companion.loadLastOpenedFolder
import io.github.vinceglb.filekit.core.PlatformDirectory
import java.io.File

/** Main entry point of the application. */
fun main() = application {
  Window(onCloseRequest = ::exitApplication, title = "baka Markdown Explorer") {
    var darkTheme by remember { mutableStateOf(false) }
    var currentFolder by remember { mutableStateOf<PlatformDirectory?>(null) }

    loadLastOpenedFolder()?.let { currentFolder = PlatformDirectory(File(it)) }

    MyAppTheme(darkTheme = darkTheme) {
      Baka(darkTheme, onToggleTheme = { darkTheme = !darkTheme }, currentFolder)
    }
  }
}

/**
 * Composable function representing the main application UI.
 *
 * @param darkTheme Boolean indicating if the dark theme is enabled.
 * @param onToggleTheme Lambda function to toggle the theme.
 */
@Preview
@Composable
fun Baka(darkTheme: Boolean, onToggleTheme: () -> Unit, initialFolder: PlatformDirectory?) {
  var currentFolder by remember { mutableStateOf(initialFolder) }

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("Markdown Explorer") },
        actions = { Switch(checked = darkTheme, onCheckedChange = { onToggleTheme() }) },
      )
    }
  ) { paddingValues ->
    Column(modifier = Modifier.padding(paddingValues)) {
      FolderSelector(onFolderSelected = { currentFolder = it })
      currentFolder?.let { folder -> FolderView(folder) }
    }
  }
}
