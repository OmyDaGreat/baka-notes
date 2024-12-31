package xyz.malefic

import androidx.compose.runtime.*
import androidx.compose.ui.window.application
import io.github.vinceglb.filekit.core.PlatformDirectory
import moe.tlaster.precompose.navigation.rememberNavigator
import xyz.malefic.compose.comps.precompose.NavWindow
import xyz.malefic.compose.nav.RouteManager
import xyz.malefic.compose.nav.config.YamlConfigLoader
import xyz.malefic.ext.precompose.gate
import xyz.malefic.ui.BlueYellow
import xyz.malefic.ui.Home
import xyz.malefic.ui.NavigationManager
import xyz.malefic.ui.file.FileEditorWindow
import xyz.malefic.ui.file.LatestFiles
import xyz.malefic.ui.settings.SettingsPage
import xyz.malefic.util.PreferencesManager.Companion.darkThemePref
import xyz.malefic.util.PreferencesManager.Companion.hideHiddenFoldersPref
import xyz.malefic.util.PreferencesManager.Companion.lastOpenedFolderPref
import java.io.File

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
fun main() {
    application {
        NavWindow(onCloseRequest = ::exitApplication, title = "baka Markdown Explorer") {
            val navi = rememberNavigator()
            var darkTheme by remember { mutableStateOf(darkThemePref) }
            var currentFolder by remember {
                mutableStateOf(lastOpenedFolderPref?.let { File(it) }?.let { PlatformDirectory(it) })
            }
            var hideHiddenFolders by remember { mutableStateOf(hideHiddenFoldersPref) }

            val composableMap: Map<String, @Composable (List<String?>) -> Unit> =
                mapOf(
                    "Baka" to
                        {
                            Home(
                                darkTheme = darkTheme,
                                onToggleTheme = {
                                    darkTheme = !darkTheme
                                    darkThemePref = !darkThemePref
                                },
                                currentFolder = currentFolder,
                                onFolderChange = {
                                    currentFolder = it
                                    lastOpenedFolderPref = it!!.file.absolutePath
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
