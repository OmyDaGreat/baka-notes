package xyz.malefic.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import xyz.malefic.compose.comps.text.typography.Body1
import xyz.malefic.compose.engine.factory.ScaffoldFactory
import xyz.malefic.compose.engine.factory.TextFactory
import xyz.malefic.compose.engine.factory.divAssign
import xyz.malefic.compose.engine.fuel.fuel
import xyz.malefic.util.PreferencesManager.Companion.hideHiddenFoldersPref
import xyz.malefic.util.PreferencesManager.Companion.maxLatestFilesPref

/**
 * Composable function for the Settings page.
 *
 * @param hideHiddenFolders Boolean indicating whether hidden folders should be hidden.
 * @param onToggleHideHiddenFolders Callback function to toggle the hidden folders setting.
 */
@Composable
fun SettingsPage(
    hideHiddenFolders: Boolean,
    onToggleHideHiddenFolders: () -> Unit,
) {
    ScaffoldFactory { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            HideHiddenFolders(hideHiddenFolders, onToggleHideHiddenFolders)
            MaxLatestFiles()
        }
    } /= {
        topBar = fuel { TopAppBar(title = { TextFactory("Settings")() }) }.function
    }
}

/**
 * Composable function for the "Hide Hidden Folders" setting.
 *
 * @param hideHiddenFolders Boolean indicating whether hidden folders should be hidden.
 * @param onToggleHideHiddenFolders Callback function to toggle the hidden folders setting.
 */
@Composable
private fun HideHiddenFolders(
    hideHiddenFolders: Boolean,
    onToggleHideHiddenFolders: () -> Unit,
) {
    Body1("Hide folders that start with a period")
    Switch(
        checked = hideHiddenFolders,
        onCheckedChange = {
            onToggleHideHiddenFolders()
            hideHiddenFoldersPref = it
        },
    )
}

/**
 * Composable function for the "Max Latest Files" setting.
 * Allows the user to set the maximum number of latest files stored.
 */
@Composable
private fun MaxLatestFiles() {
    var maxLatestFiles by remember { mutableStateOf(maxLatestFilesPref) }
    var maxLatestFilesInput by remember { mutableStateOf(TextFieldValue(maxLatestFiles.toString())) }

    TextField(
        value = maxLatestFilesInput,
        onValueChange = {
            maxLatestFilesInput = it
            maxLatestFiles = it.text.toIntOrNull() ?: maxLatestFiles
            maxLatestFilesPref = maxLatestFiles
        },
        label = { Text("Max Latest Files Stored", color = MaterialTheme.colors.onBackground) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
    )
}
