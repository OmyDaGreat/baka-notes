package io.github.omydagreat.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.omydagreat.ui.file.FileEditorWindow
import io.github.omydagreat.ui.file.LatestFiles
import io.github.omydagreat.util.PreferencesManager
import io.github.vinceglb.filekit.core.PlatformDirectory
import java.io.File
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path

@Composable
fun NavigationManager(
  navi: Navigator,
  darkTheme: Boolean,
  onToggleTheme: () -> Unit,
  initialFolder: PlatformDirectory?,
) {
  val latestFiles = remember { PreferencesManager.loadLatestFiles() }
  val mostRecentFile = latestFiles.firstOrNull()?.let { File(it) }

  Row {
    Sidebar(navi, mostRecentFile)
    NavHost(navi, initialRoute = "home") {
      scene("home") { Baka(darkTheme, onToggleTheme, initialFolder, navi) }
      scene("latestFiles") { LatestFiles { file -> navi.navigate("fileEditor/${file.path}") } }
      scene("fileEditor/{filePath}") { backStackEntry ->
        val filePath = backStackEntry.path<String>("filePath")
        filePath?.let { FileEditorWindow(File(it)) }
      }
    }
  }
}
