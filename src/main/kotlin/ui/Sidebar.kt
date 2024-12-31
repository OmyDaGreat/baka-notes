package xyz.malefic.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.Navigator
import xyz.malefic.compose.comps.text.typography.Body1
import xyz.malefic.compose.engine.factory.ColumnFactory
import xyz.malefic.compose.engine.factory.timesAssign
import xyz.malefic.compose.engine.fuel.padding
import xyz.malefic.ext.precompose.gate
import java.io.File

/**
 * Composable function that renders a sidebar with navigation items.
 *
 * @param navi The Navigator instance used to handle navigation actions.
 * @param mostRecentFile An optional File object representing the most recently accessed file.
 *
 * The sidebar includes navigation items for "Home", "Latest Files", "File Editor", and "Settings".
 * Each item triggers a navigation action when clicked, with "File Editor" navigating to a specific
 * file path if a recent file is provided.
 */
@Composable
fun Sidebar(
    navi: Navigator,
    mostRecentFile: File?,
) {
    ColumnFactory {
        SidebarItem("Home") { navi gate "home" }
        SidebarItem("Latest Files") { navi gate "latestFiles" }
        SidebarItem("File Editor") { mostRecentFile?.let { navi gate "fileEditor/${it.path}" } }
        SidebarItem("Settings") { navi gate "settings" }
    } *= {
        padding(16.dp)
    }
}

/**
 * A composable function that displays a sidebar item with the specified text.
 *
 * @param text The text to display within the sidebar item.
 * @param onClick A lambda function to be invoked when the sidebar item is clicked.
 */
@Composable
fun SidebarItem(
    text: String,
    onClick: () -> Unit,
) = Body1(text = text, modifier = Modifier.clickable(onClick = onClick).padding(8.dp))
