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
 * Entry point of the application.
 *
 * This function sets up the main window of the application and initializes the theme and current folder state.
 * It uses the `application` function to create a window with a specified title and close request handler.
 * The theme state is managed using a `mutableStateOf` variable, and the current folder state is initialized
 * from the last opened folder stored in the preferences.
 *
 * @receiver The application scope.
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
 * This function creates the main structure of the application's user interface, including
 * a top app bar with a title and a theme toggle switch, and a column that contains a folder
 * selector and a folder view. The folder selector allows the user to pick a directory, and
 * the folder view displays the contents of the selected directory.
 *
 * @param darkTheme Boolean indicating if the dark theme is enabled.
 * @param onToggleTheme Lambda function to toggle the theme. This function is called when the
 *        user toggles the theme switch in the top app bar.
 * @param initialFolder Initial folder to be displayed. This is the folder that was last opened
 *        by the user, and it is loaded from the preferences when the application starts.
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
