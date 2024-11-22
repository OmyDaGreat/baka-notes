package io.github.omydagreat.ui.file

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.omydagreat.util.PreserveTrailingSpacesTransformation

/**
 * Composable function that displays a file editor.
 *
 * This function creates an `OutlinedTextField` that allows the user to edit the content of a file.
 * The content of the file is passed as a parameter, and any changes made to the content are
 * propagated back through the `onContentChanged` callback function.
 *
 * @param content The content of the file to be displayed and edited.
 * @param onContentChanged Callback function to handle content changes. This function is called
 *   whenever the content of the text field changes, with the new content as its parameter.
 * @param modifier Modifier to be applied to the editor. This allows for customization of the layout
 *   and appearance of the text field.
 */
@Composable
fun FileEditor(content: String, onContentChanged: (String) -> Unit, modifier: Modifier = Modifier) =
  OutlinedTextField(
    value = content,
    onValueChange = onContentChanged,
    modifier = modifier.fillMaxSize(),
    maxLines = Int.MAX_VALUE,
    singleLine = false,
    visualTransformation = PreserveTrailingSpacesTransformation(),
  )
