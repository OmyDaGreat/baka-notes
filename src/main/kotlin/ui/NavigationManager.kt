package xyz.malefic.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.navigation.Navigator
import xyz.malefic.compose.engine.factory.RowFactory
import xyz.malefic.compose.engine.factory.div
import xyz.malefic.compose.engine.factory.timesAssign
import xyz.malefic.compose.engine.fuel.background
import xyz.malefic.compose.nav.RouteManager.RoutedNavHost
import xyz.malefic.util.PreferencesManager
import java.io.File

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

    RowFactory {
        Sidebar(navi, mostRecentFile)
        RoutedNavHost()
    } / {
        modifier = Modifier.fillMaxSize()
    } *= {
        background()
    }
}
