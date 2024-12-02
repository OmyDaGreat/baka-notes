@file:Suppress("kotlin:S1128")

package io.github.omydagreat.ui.file

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.omydagreat.util.PreferencesManager
import io.github.omydagreat.util.theme.Text.Body1
import java.io.File

/**
 * A composable function that displays a list of the latest files.
 *
 * @param onFileSelected A callback function that is invoked when a file is selected.
 */
@Composable
fun LatestFiles(onFileSelected: (File) -> Unit) {
  val latestFiles = PreferencesManager.latestFilesPref

  LazyColumn(modifier = Modifier.padding(16.dp)) {
    items(latestFiles) { filePath ->
      val file = File(filePath)
      Body1(
        text = file.name,
        modifier = Modifier.fillMaxWidth().clickable { onFileSelected(file) }.padding(8.dp),
      )
    }
  }
}
