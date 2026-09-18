package com.statecraft.kastoria.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val ParchmentBackground = Color(0xFF16130E)
val CardBackground = Color(0xFF241F17)
val GoldAccent = Color(0xFFC9A24B)
val MutedText = Color(0xFFCFC6B3)

private val KastoriaColors = darkColorScheme(
    primary = GoldAccent,
    secondary = Color(0xFF7A5C3E),
    background = ParchmentBackground,
    surface = CardBackground,
    onPrimary = Color(0xFF14110C),
    onBackground = MutedText,
    onSurface = MutedText
)

private val KastoriaTypography = Typography(
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Serif,
        fontSize = 17.sp,
        lineHeight = 24.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp
    )
)

@Composable
fun KastoriaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = KastoriaColors,
        typography = KastoriaTypography,
        content = content
    )
}
