package xyz.malefic.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.Navigator
import xyz.malefic.ext.precompose.gate
import xyz.malefic.util.theme.Text.Body1
import java.io.File

@Composable
fun Sidebar(
    navi: Navigator,
    mostRecentFile: File?,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        SidebarItem("Home") { navi gate "home" }
        SidebarItem("Latest Files") { navi gate "latestFiles" }
        SidebarItem("File Editor") { mostRecentFile?.let { navi gate "fileEditor/${it.path}" } }
        SidebarItem("Settings") { navi gate "settings" }
    }
}

@Composable
fun SidebarItem(
    text: String,
    onClick: () -> Unit,
) {
    Body1(text = text, modifier = Modifier.clickable(onClick = onClick).padding(8.dp))
}
