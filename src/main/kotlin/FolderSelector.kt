package io.github.omydagreat

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import io.github.omydagreat.PreferencesManager.Companion.saveLastOpenedFolder
import io.github.vinceglb.filekit.core.FileKit
import io.github.vinceglb.filekit.core.PlatformDirectory
import kotlinx.coroutines.launch

/**
 * Composable function for selecting a folder.
 *
 * @param onFolderSelected Lambda function to handle folder selection.
 */
@Composable
fun FolderSelector(onFolderSelected: (PlatformDirectory) -> Unit) {
  Button(
    onClick = {
      ioScope.launch {
        val directory = FileKit.pickDirectory(title = "Pick a directory", initialDirectory = "C:")
        directory?.let {
          onFolderSelected(it)
          saveLastOpenedFolder(it.file.absolutePath)
          Logger.i("Selected directory: $directory")
        }
      }
    }
  ) {
    Text("Open Folder")
  }
  Spacer(modifier = Modifier.height(5.dp))
}
