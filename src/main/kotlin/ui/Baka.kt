package io.github.omydagreat.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import io.github.omydagreat.ui.folder.FolderSelector
import io.github.omydagreat.ui.folder.FolderView
import io.github.vinceglb.filekit.core.PlatformDirectory
import moe.tlaster.precompose.navigation.Navigator

/**
 * Composable function that sets up the main UI scaffold.
 *
 * This function creates the main structure of the application's user interface, including a top app
 * bar with a title and a theme toggle switch, and a column that contains a folder selector and a
 * folder view. The folder selector allows the user to pick a directory, and the folder view
 * displays the contents of the selected directory.
 *
 * @param darkTheme Boolean indicating if the dark theme is enabled.
 * @param onToggleTheme Lambda function to toggle the theme. This function is called when the user
 *   toggles the theme switch in the top app bar.
 * @param initialFolder Initial folder to be displayed. This is the folder that was last opened by
 *   the user, and it is loaded from the preferences when the application starts.
 */
@Composable
fun Baka(
  darkTheme: Boolean,
  onToggleTheme: () -> Unit,
  initialFolder: PlatformDirectory?,
  navi: Navigator,
) {
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
      currentFolder?.let { folder -> FolderView(folder, navi) }
    }
  }
}
