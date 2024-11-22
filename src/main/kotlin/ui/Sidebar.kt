package io.github.omydagreat.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.io.File
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun Sidebar(navi: Navigator, mostRecentFile: File?) {
  Column(modifier = Modifier.padding(16.dp)) {
    Text(text = "Home", modifier = Modifier.clickable { navi.navigate("home") }.padding(8.dp))
    Text(
      text = "Latest Files",
      modifier = Modifier.clickable { navi.navigate("latestFiles") }.padding(8.dp),
    )
    mostRecentFile?.let {
      Text(
        text = "File Editor",
        modifier = Modifier.clickable { navi.navigate("fileEditor/${it.path}") }.padding(8.dp),
      )
    } ?: Text(text = "File Editor", modifier = Modifier.padding(8.dp))
  }
}
