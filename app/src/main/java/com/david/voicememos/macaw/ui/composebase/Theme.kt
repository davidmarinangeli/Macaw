package com.david.voicememos.macaw.ui.composebase

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = blue500,
    primaryVariant = blue700,
    secondary = green500,
    error = red500,
    onSurface = Color.White
    )

private val LightColorPalette = lightColors(
    primary = blue500,
    primaryVariant = blue700,
    secondary = green500,
    secondaryVariant = green700,
    error = red500,
    onSurface = black
)

@Composable
fun MacawTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}