package io.github.omydagreat.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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

  Row(modifier = Modifier.background(MaterialTheme.colors.background).fillMaxSize()) {
    Sidebar(navi, mostRecentFile)
    RoutedNavHost()
  }
}
