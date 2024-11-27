package io.github.omydagreat.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import io.github.omydagreat.util.PreferencesManager.Companion.saveHideHiddenFoldersState
import io.github.omydagreat.util.theme.Text.Body1

@Composable
fun SettingsPage(hideHiddenFolders: Boolean, onToggleHideHiddenFolders: () -> Unit) {
  Scaffold(topBar = { TopAppBar(title = { Text("Settings") }) }) { paddingValues ->
    Column(modifier = Modifier.padding(paddingValues)) {
      Body1("Hide folders that start with a period")
      Switch(
        checked = hideHiddenFolders,
        onCheckedChange = {
          onToggleHideHiddenFolders()
          saveHideHiddenFoldersState(it)
        },
      )
    }
  }
}
