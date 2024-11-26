package io.github.omydagreat

import androidx.compose.runtime.*
import androidx.compose.ui.window.application
import io.github.omydagreat.ui.Baka
import io.github.omydagreat.ui.BlueYellow
import io.github.omydagreat.ui.NavigationManager
import io.github.omydagreat.ui.file.FileEditorWindow
import io.github.omydagreat.ui.file.LatestFiles
import io.github.omydagreat.util.PreferencesManager.Companion.loadLastOpenedFolder
import io.github.omydagreat.util.gate
import io.github.vinceglb.filekit.core.PlatformDirectory
import java.io.File
import moe.tlaster.precompose.navigation.rememberNavigator
import xyz.malefic.navigate.RouteManager
import xyz.malefic.wrap.NavWindow

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
  NavWindow(onCloseRequest = ::exitApplication, title = "baka Markdown Explorer") {
    val navi = rememberNavigator()
    var darkTheme by remember { mutableStateOf(false) }
    var currentFolder by remember { mutableStateOf<PlatformDirectory?>(null) }

    val composableMap: Map<String, @Composable (List<String?>) -> Unit> =
      mapOf(
        "Baka" to { Baka(darkTheme, { darkTheme = !darkTheme }, currentFolder, navi) },
        "LatestFiles" to { LatestFiles { file -> navi gate "fileEditor/${file.path}" } },
        "FileEditorWindow" to { params -> FileEditorWindow(File(params[0]!!)) },
      )

    RouteManager.initialize(composableMap, this::class.java.getResourceAsStream("/routes.yaml")!!)

    loadLastOpenedFolder()?.let { currentFolder = PlatformDirectory(File(it)) }

    BlueYellow(darkTheme = darkTheme) { NavigationManager(navi) }
  }
}
