package io.github.omydagreat

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Defines the light color palette for the application theme.
 */
private val LightColorPalette =
  lightColors(
    primary = Color(0xFF1E88E5),
    primaryVariant = Color(0xFF1565C0),
    secondary = Color(0xFFFFEB3B),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFFFFFFF),
    error = Color(0xFFB00020),
    onPrimary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFF000000),
    onBackground = Color(0xFF000000),
    onSurface = Color(0xFF000000),
    onError = Color(0xFFFFFFFF),
  )

/**
 * Defines the dark color palette for the application theme.
 */
private val DarkColorPalette =
  darkColors(
    primary = Color(0xFF1E88E5),
    primaryVariant = Color(0xFF1565C0),
    secondary = Color(0xFFFFEB3B),
    background = Color(0xFF121212),
    surface = Color(0xFF121212),
    error = Color(0xFFCF6679),
    onPrimary = Color(0xFF000000),
    onSecondary = Color(0xFF000000),
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFFFFFFFF),
    onError = Color(0xFF000000),
  )

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
      DarkColorPalette
    } else {
      LightColorPalette
    }

  MaterialTheme(colors = colors, content = content)
}
