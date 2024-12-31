package xyz.malefic.util.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

/** Defines the light color palette for the application theme. */
val BYLightColorPalette =
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

/** Defines the dark color palette for the application theme. */
val BYDarkColorPalette =
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
