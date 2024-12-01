package io.github.omydagreat

import androidx.compose.runtime.*
import androidx.compose.ui.window.application
import io.github.omydagreat.ui.Baka
import io.github.omydagreat.ui.BlueYellow
import io.github.omydagreat.ui.NavigationManager
import io.github.omydagreat.ui.file.FileEditorWindow
import io.github.omydagreat.ui.file.LatestFiles
import io.github.omydagreat.ui.settings.SettingsPage
import io.github.omydagreat.util.PreferencesManager.Companion.hideHiddenFoldersPref
import io.github.omydagreat.util.PreferencesManager.Companion.lastOpenedFolder
import io.github.vinceglb.filekit.core.PlatformDirectory
import java.io.File
import moe.tlaster.precompose.navigation.rememberNavigator
import xyz.malefic.components.precompose.NavWindow
import xyz.malefic.extensions.precompose.gate
import xyz.malefic.navigate.RouteManager
import xyz.malefic.navigate.config.YamlConfigLoader

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
@Suppress("kotlin:S6619")
fun main() {
  application {
    NavWindow(onCloseRequest = ::exitApplication, title = "baka Markdown Explorer") {
      val navi = rememberNavigator()
      var darkTheme by remember { mutableStateOf(false) }
      var currentFolder by remember {
        mutableStateOf(lastOpenedFolder?.let { File(it) }?.let { PlatformDirectory(it) })
      }
      var hideHiddenFolders by remember { mutableStateOf(hideHiddenFoldersPref) }

      val composableMap: Map<String, @Composable (List<String?>) -> Unit> =
        mapOf(
          "Baka" to
            {
              Baka(
                darkTheme = darkTheme,
                onToggleTheme = { darkTheme = !darkTheme },
                currentFolder = currentFolder,
                onFolderChange = {
                  currentFolder = it
                  lastOpenedFolder = it!!.file.absolutePath
                },
                hideHiddenFolders = hideHiddenFolders,
                navi = navi,
              )
            },
          "LatestFiles" to { LatestFiles { file -> navi gate "fileEditor/${file.path}" } },
          "FileEditorWindow" to { params -> FileEditorWindow(File(params[0]!!)) },
          "SettingsPage" to
            {
              SettingsPage(
                hideHiddenFolders = hideHiddenFolders,
                onToggleHideHiddenFolders = { hideHiddenFolders = !hideHiddenFolders },
              )
            },
        )

      RouteManager.initialize(
        composableMap,
        this::class.java.getResourceAsStream("/routes.yaml")!!,
        configLoader = YamlConfigLoader(),
        navi = navi,
      )

      BlueYellow(darkTheme = darkTheme) { NavigationManager(navi) }
    }
  }
}
