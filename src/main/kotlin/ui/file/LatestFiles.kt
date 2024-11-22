package io.github.omydagreat.ui.file

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.omydagreat.util.PreferencesManager
import java.io.File

@Composable
fun LatestFiles(onFileSelected: (File) -> Unit) {
  val latestFiles = remember { PreferencesManager.loadLatestFiles() }

  Column(modifier = Modifier.padding(16.dp)) {
    latestFiles.forEach { filePath ->
      val file = File(filePath)
      Text(
        text = file.name,
        modifier = Modifier.fillMaxWidth().clickable { onFileSelected(file) }.padding(8.dp),
      )
    }
  }
}
