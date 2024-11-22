package io.github.omydagreat.ui

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import io.github.omydagreat.util.BYDarkColorPalette
import io.github.omydagreat.util.BYLightColorPalette

/**
 * A composable function that applies the BlueYellow theme to its content.
 *
 * @param darkTheme A boolean flag indicating whether to use the dark theme. Defaults to false.
 * @param content A composable lambda function representing the content to be themed.
 */
@Composable
fun BlueYellow(darkTheme: Boolean = false, content: @Composable () -> Unit) {
  val colors: Colors =
    if (darkTheme) {
      BYDarkColorPalette
    } else {
      BYLightColorPalette
    }

  MaterialTheme(colors = colors, content = content)
}
