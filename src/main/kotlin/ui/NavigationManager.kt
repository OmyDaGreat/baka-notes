// NavigationManager.kt
package io.github.omydagreat.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import io.github.omydagreat.util.PreferencesManager
import java.io.File
import moe.tlaster.precompose.navigation.Navigator
import xyz.malefic.navigate.RouteManager.RoutedNavHost

@Composable
fun NavigationManager(navi: Navigator) {
  var mostRecentFile by remember {
    mutableStateOf(PreferencesManager.latestFilesPref.firstOrNull()?.let { File(it) })
  }

  LaunchedEffect(Unit) {
    PreferencesManager.latestFilesFlow.collect { files ->
      mostRecentFile = files.firstOrNull()?.let { File(it) }
    }
  }

  Row(modifier = Modifier.background(MaterialTheme.colors.background).fillMaxSize()) {
    Sidebar(navi, mostRecentFile)
    RoutedNavHost()
  }
}
