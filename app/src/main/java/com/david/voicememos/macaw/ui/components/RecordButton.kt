package com.david.voicememos.macaw.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.HorizontalGradient
import androidx.compose.ui.unit.dp
import com.david.voicememos.macaw.ui.red500
import com.david.voicememos.macaw.ui.red800

@Composable
fun RecordButton(onClickListener: () -> Unit) {
    val gradientBackground = HorizontalGradient(
        0.0f to red800,
        1.0f to red500,
        startX = 0f,
        endX = 128f
    )
    val modifier = Modifier
        .clickable(onClick = onClickListener)
        .size(64.dp)
        .clip(CircleShape)
        .background(brush = gradientBackground)


    Surface(color = Color.Transparent, modifier = modifier) {
        Row {}
    }

}