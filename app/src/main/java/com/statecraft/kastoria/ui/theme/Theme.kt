package com.statecraft.kastoria.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val KastoriaColors = darkColorScheme(
    primary = Color(0xFFB08D57),
    secondary = Color(0xFF5C7285),
    background = Color(0xFF14171C),
    surface = Color(0xFF1E232B),
    onPrimary = Color(0xFF14171C),
    onBackground = Color(0xFFE8E6E1),
    onSurface = Color(0xFFE8E6E1)
)

@Composable
fun KastoriaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = KastoriaColors,
        content = content
    )
}
