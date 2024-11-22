package io.github.omydagreat.ui.folder

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import io.github.omydagreat.util.PreferencesManager.Companion.saveLastOpenedFolder
import io.github.vinceglb.filekit.core.FileKit
import io.github.vinceglb.filekit.core.PlatformDirectory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Composable function for selecting a folder.
 *
 * This function creates a button that, when clicked, opens a directory picker dialog. The user can
 * select a folder from the dialog, and the selected folder is then passed to the `onFolderSelected`
 * callback function. Additionally, the path of the selected folder is saved using the
 * `saveLastOpenedFolder` function, and a log message is generated with the selected directory's
 * information.
 *
 * @param onFolderSelected Lambda function to handle folder selection. This function is called with
 *   the selected `PlatformDirectory` as its parameter.
 */
@Composable
fun FolderSelector(onFolderSelected: (PlatformDirectory) -> Unit) {
  Button(
    onClick = {
      CoroutineScope(Dispatchers.IO).launch {
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
