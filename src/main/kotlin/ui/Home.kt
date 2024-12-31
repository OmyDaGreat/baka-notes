package xyz.malefic.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.vinceglb.filekit.core.PlatformDirectory
import moe.tlaster.precompose.navigation.Navigator
import xyz.malefic.compose.comps.text.typography.Heading4
import xyz.malefic.compose.engine.factory.ColumnFactory
import xyz.malefic.compose.engine.factory.ScaffoldFactory
import xyz.malefic.compose.engine.factory.divAssign
import xyz.malefic.compose.engine.fuel.fuel
import xyz.malefic.compose.engine.fuel.space
import xyz.malefic.ext.file.buildFileTree
import xyz.malefic.ui.folder.FolderSelector
import xyz.malefic.ui.folder.FolderView

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
fun Home(
    darkTheme: Boolean,
    onToggleTheme: () -> Unit,
    currentFolder: PlatformDirectory?,
    onFolderChange: (PlatformDirectory?) -> Unit,
    hideHiddenFolders: Boolean,
    navi: Navigator,
) {
    val folderContents =
        remember(currentFolder) {
            currentFolder?.file?.buildFileTree()?.filter {
                !hideHiddenFolders || !it.name.startsWith(".")
            }
        }

    ScaffoldFactory { paddingValues ->
        ColumnFactory {
            fuel { FolderSelector(onFolderSelected = onFolderChange, currentFolder) }.space(6.dp)()
            folderContents?.let { contents ->
                key(currentFolder) { FolderView(contents, navi, onFolderSelected = onFolderChange) }
            }
        } /= {
            modifier = Modifier.padding(paddingValues)
        }
    } /= {
        topBar =
            fuel {
                TopAppBar(
                    title = { Heading4("Markdown Explorer") },
                    actions = { Switch(checked = darkTheme, onCheckedChange = { onToggleTheme() }) },
                )
            }.function
    }
}
