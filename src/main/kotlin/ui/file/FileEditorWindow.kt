package xyz.malefic.ui.file

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.material.RichText
import xyz.malefic.compose.engine.factory.ColumnFactory
import xyz.malefic.compose.engine.factory.RowFactory
import xyz.malefic.compose.engine.factory.div
import xyz.malefic.compose.engine.factory.divAssign
import xyz.malefic.compose.engine.factory.timesAssign
import xyz.malefic.compose.engine.fuel.background
import xyz.malefic.compose.engine.fuel.fuel
import xyz.malefic.compose.engine.fuel.padding
import xyz.malefic.ext.file.loadFileContent
import xyz.malefic.ext.file.saveFile
import xyz.malefic.util.PreferencesManager
import java.io.File

/**
 * Composable function that displays a file editor window along with a markdown preview.
 *
 * This function creates a window that contains a file editor and a markdown preview side by side.
 * The file editor allows the user to edit the content of the file, and the markdown preview
 * displays the rendered markdown content in real-time as the user types.
 *
 * @param file The file to be edited. The content of this file will be loaded and displayed in the
 *   editor.
 */
@Composable
fun FileEditorWindow(file: File) {
    var fileContent by remember { mutableStateOf("") }

    LaunchedEffect(file) {
        PreferencesManager.saveLatestFile(file.absolutePath)
        file.loadFileContent { content -> fileContent = content }
    }

    RowFactory {
        FileEditor(
            content = fileContent,
            onContentChanged = {
                fileContent = it
                file.saveFile(it)
            },
            modifier = Modifier.weight(1f).padding(end = 8.dp),
        )

        ColumnFactory {
            fuel { RichText { Markdown(fileContent) } } *= {
                background(Color.White)
                padding(16.dp)
            }
        } /= {
            modifier = Modifier.weight(1f).padding(start = 8.dp).verticalScroll(rememberScrollState())
        }
    } / {
        modifier = Modifier.fillMaxSize()
    } *= {
        padding(16.dp)
    }
}
