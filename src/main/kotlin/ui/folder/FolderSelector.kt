package xyz.malefic.ui.folder

import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import co.touchlab.kermit.Logger
import io.github.vinceglb.filekit.core.FileKit
import io.github.vinceglb.filekit.core.PlatformDirectory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xyz.malefic.compose.comps.text.typography.Body1B
import xyz.malefic.compose.engine.factory.ButtonFactory
import xyz.malefic.compose.engine.factory.divAssign

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
 * @param currentFolder The currently selected folder, if any.
 */
@Composable
fun FolderSelector(
    onFolderSelected: (PlatformDirectory) -> Unit,
    currentFolder: PlatformDirectory?,
) {
    val buttonColors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)

    ButtonFactory { Body1B("Open Folder") } /= {
        onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                val initialDirectory = currentFolder?.file?.absolutePath ?: System.getProperty("user.home")
                val directory =
                    FileKit.pickDirectory(title = "Pick a directory", initialDirectory = initialDirectory)
                directory?.let {
                    onFolderSelected(it)
                    Logger.i("Selected directory: $directory")
                }
            }
        }
        colors = buttonColors
    }
}
