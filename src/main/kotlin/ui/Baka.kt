package io.github.omydagreat.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.omydagreat.ui.folder.FolderSelector
import io.github.omydagreat.ui.folder.FolderView
import io.github.omydagreat.util.buildFileTree
import io.github.omydagreat.util.filter
import io.github.omydagreat.util.theme.Text.Heading4
import io.github.vinceglb.filekit.core.PlatformDirectory
import moe.tlaster.precompose.navigation.Navigator

/**
 * Composable function that represents the home page of the application, allowing the user to open
 * folders and choose markdown files to edit.
 *
 * @param darkTheme Boolean indicating whether the dark theme is enabled.
 * @param onToggleTheme Lambda function to toggle the theme.
 * @param currentFolder The currently selected folder.
 * @param onFolderChange Lambda function to handle folder change.
 * @param hideHiddenFolders Boolean indicating whether to hide hidden folders.
 * @param navi Navigator instance for handling navigation.
 */
@Composable
fun Baka(
  darkTheme: Boolean,
  onToggleTheme: () -> Unit,
  currentFolder: PlatformDirectory?,
  onFolderChange: (PlatformDirectory?) -> Unit,
  hideHiddenFolders: Boolean,
  navi: Navigator,
) {
  // Remember the contents of the current folder, filtered based on the hideHiddenFolders flag.
  val folderContents =
    remember(currentFolder) {
      currentFolder?.file?.buildFileTree()?.filter {
        !hideHiddenFolders || !it.name.startsWith(".")
      }
    }

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Heading4("Markdown Explorer") },
        actions = { Switch(checked = darkTheme, onCheckedChange = { onToggleTheme() }) },
      )
    }
  ) { paddingValues ->
    Column(modifier = Modifier.padding(paddingValues)) {
      // Folder selector component to choose a folder.
      FolderSelector(onFolderSelected = onFolderChange)
      Spacer(modifier = Modifier.height(6.dp))
      // Display the contents of the selected folder.
      folderContents?.let { contents -> key(currentFolder) { FolderView(contents, navi) } }
    }
  }
}
