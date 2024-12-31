package xyz.malefic.ui.file

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import xyz.malefic.util.PreserveTrailingSpacesTransformation

@Composable
fun FileEditor(
    content: String,
    onContentChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val textColor = MaterialTheme.colors.onSurface
    val backgroundColor = MaterialTheme.colors.surface

    OutlinedTextField(
        value = content,
        onValueChange = onContentChanged,
        modifier = modifier.fillMaxSize(),
        maxLines = Int.MAX_VALUE,
        singleLine = false,
        visualTransformation = PreserveTrailingSpacesTransformation(),
        textStyle = MaterialTheme.typography.body1.copy(color = textColor),
        colors =
            TextFieldDefaults.outlinedTextFieldColors(
                textColor = textColor,
                backgroundColor = backgroundColor,
                cursorColor = textColor,
                focusedBorderColor = MaterialTheme.colors.primary,
                unfocusedBorderColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
            ),
    )
}
