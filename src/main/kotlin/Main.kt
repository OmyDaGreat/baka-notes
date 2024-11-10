package io.github.omydagreat

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

/**
 * Entry point of the application. Sets up the main window and initializes the theme and current
 * folder state.
 */
fun main() = application {
  Window(onCloseRequest = ::exitApplication, title = "baka Markdown Explorer") {
    var darkTheme by remember { mutableStateOf(false) }
    var currentFolder by remember { mutableStateOf<PlatformDirectory?>(null) }

    loadLastOpenedFolder()?.let { currentFolder = PlatformDirectory(File(it)) }

    BlueYellow(darkTheme = darkTheme) {
      Baka(darkTheme, onToggleTheme = { darkTheme = !darkTheme }, currentFolder)
    }
  }
}

/**
 * Composable function that sets up the main UI scaffold.
 *
 * @param darkTheme Boolean indicating if the dark theme is enabled.
 * @param onToggleTheme Lambda function to toggle the theme.
 * @param initialFolder Initial folder to be displayed.
 */
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
