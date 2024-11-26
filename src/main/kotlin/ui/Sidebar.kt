package io.github.omydagreat.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.omydagreat.util.gate
import io.github.omydagreat.util.theme.Text.Body1
import java.io.File
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun Sidebar(navi: Navigator, mostRecentFile: File?) {
  Column(modifier = Modifier.padding(16.dp)) {
    Body1(text = "Home", modifier = Modifier.clickable { navi gate "home" }.padding(8.dp))
    Body1(
      text = "Latest Files",
      modifier = Modifier.clickable { navi gate "latestFiles" }.padding(8.dp),
    )
    mostRecentFile?.let {
      Body1(
        text = "File Editor",
        modifier = Modifier.clickable { navi gate "fileEditor/${it.path}" }.padding(8.dp),
      )
    } ?: Body1(text = "File Editor", modifier = Modifier.padding(8.dp))
  }
}
