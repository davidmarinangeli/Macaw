package com.david.voicememos.macaw.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.david.voicememos.macaw.ui.composebase.shapes
import com.david.voicememos.macaw.ui.composebase.surfaceWhite

@Composable
fun MacawSurface(
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val color = if (colors.isLight) {
        surfaceWhite
    } else {
        colors.surface
    }

    Surface(
        elevation = 2.dp,
        color = color,
        modifier = modifier.clip(shapes.medium)
            .let { if (onClick != null) it.clickable(onClick = onClick) else it }
    ) {
        Providers(
            AmbientContentColor provides contentColorFor(color),
            AmbientAbsoluteElevation provides AmbientAbsoluteElevation.current,
            content = content
        )
    }
}