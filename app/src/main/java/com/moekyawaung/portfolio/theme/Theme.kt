package com.moekyawaung.portfolio.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

val DarkColorPalette = darkColors(
    primary = NeonCyan,
    secondary = NeonPink,
    background = BackgroundDark,
    surface = SurfaceDark,
    onPrimary = TextPrimary,
    onSecondary = TextPrimary,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

val LightColorPalette = lightColors(
    primary = NeonCyan,
    secondary = NeonPink,
    background = Color(0xFFF5F7FF),
    surface = Color(0xFFFFFFFF),
    onPrimary = BackgroundDark,
    onSecondary = BackgroundDark,
    onBackground = BackgroundDark,
    onSurface = BackgroundDark
)

@Composable
fun PortfolioTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    
    MaterialTheme(
        colors = colors,
        content = content
    )
}
