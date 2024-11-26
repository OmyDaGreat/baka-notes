package io.github.omydagreat.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.omydagreat.util.PreferencesManager
import java.io.File
import moe.tlaster.precompose.navigation.Navigator
import xyz.malefic.navigate.RouteManager.RoutedNavHost

/**
 * A composable function that manages the navigation within the application.
 *
 * @param navi The Navigator instance used for navigation.
 */
@Composable
fun NavigationManager(navi: Navigator) {
  val latestFiles = remember { PreferencesManager.loadLatestFiles() }
  val mostRecentFile = latestFiles.firstOrNull()?.let { File(it) }

  Row {
    Sidebar(navi, mostRecentFile)
    RoutedNavHost()
  }
}
