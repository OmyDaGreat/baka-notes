package xyz.malefic.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * A `VisualTransformation` that preserves trailing spaces in the text.
 *
 * This transformation replaces all spaces with non-breaking spaces to ensure that trailing spaces
 * are visible in the text field.
 */
class PreserveTrailingSpacesTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText = text.text.replace(" ", "\u00A0")
        return TransformedText(AnnotatedString(transformedText), OffsetMapping.Identity)
    }
}
