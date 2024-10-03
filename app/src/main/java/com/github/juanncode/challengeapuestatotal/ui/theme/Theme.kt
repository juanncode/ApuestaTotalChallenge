package com.github.juanncode.challengeapuestatotal.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val DarkColorScheme = darkColorScheme(
    primary = BetBlue,
    background = BetBlack,
    surface = BetDarkGray,
    secondary = BetWhite,
    tertiary = BetWhite,
    primaryContainer = BetBlue30,
    onPrimary = BetBlack,
    onBackground = BetWhite,
    onSurface = BetWhite,
    onSurfaceVariant = BetGray,
    error = BetDarkRed,
    errorContainer = BetDarkRed5
)

@Composable
fun ChallengeApuestaTotalTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}