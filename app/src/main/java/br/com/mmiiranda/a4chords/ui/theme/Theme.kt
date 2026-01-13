package br.com.mmiiranda.a4chords.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF80CBC4),
    secondary = Color(0xFF80CBC4),
    tertiary = Color(0xFF80CBC4),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color(0xFF000000),
    onSecondary = Color(0xFF000000),
    onTertiary = Color(0xFF000000),
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF004D40),
    secondaryContainer = Color(0xFF004D40)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF00897B),
    secondary = Color(0xFF00695C),
    tertiary = Color(0xFF004D40),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFFFFFFFF),
    onTertiary = Color(0xFFFFFFFF),
    onBackground = Color(0xFF000000),
    onSurface = Color(0xFF000000),
    primaryContainer = Color(0xFF80CBC4),
    secondaryContainer = Color(0xFF80CBC4)
)

@Composable
fun A4ChordsTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}

val SwitchColors
    @Composable
    get() = SwitchDefaults.colors(
        checkedThumbColor = DarkColorScheme.primary,
        checkedTrackColor = DarkColorScheme.primaryContainer,
        uncheckedThumbColor = LightColorScheme.primary,
        uncheckedTrackColor = LightColorScheme.primaryContainer
    )