package io.github.omydagreat

import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.omydagreat.ui.BlueYellow
import io.github.omydagreat.ui.NavigationManager
import io.github.omydagreat.util.PreferencesManager.Companion.loadLastOpenedFolder
import io.github.vinceglb.filekit.core.PlatformDirectory
import java.io.File
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.ProvidePreComposeLocals
import moe.tlaster.precompose.navigation.rememberNavigator

/**
 * Entry point of the application.
 *
 * This function sets up the main window of the application and initializes the theme and current
 * folder state. It uses the `application` function to create a window with a specified title and
 * close request handler. The theme state is managed using a `mutableStateOf` variable, and the
 * current folder state is initialized from the last opened folder stored in the preferences.
 *
 * @receiver The application scope.
 */
fun main() = application {
  Window(onCloseRequest = ::exitApplication, title = "baka Markdown Explorer") {
    ProvidePreComposeLocals {
      PreComposeApp {
        val navi = rememberNavigator()
        var darkTheme by remember { mutableStateOf(false) }
        var currentFolder by remember { mutableStateOf<PlatformDirectory?>(null) }

        loadLastOpenedFolder()?.let { currentFolder = PlatformDirectory(File(it)) }

        BlueYellow(darkTheme = darkTheme) {
          NavigationManager(
            navi,
            darkTheme,
            onToggleTheme = { darkTheme = !darkTheme },
            currentFolder,
          )
        }
      }
    }
  }
}
