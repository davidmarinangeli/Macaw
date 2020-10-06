package com.david.voicememos.macaw.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope.align
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.HorizontalGradient
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.david.voicememos.macaw.R
import com.david.voicememos.macaw.ui.composebase.red500
import com.david.voicememos.macaw.ui.composebase.red800

@Composable
fun RecordButton(layoutModifier: Modifier, isRecordingIdle: Boolean, onClickListener: () -> Unit) {
    val gradientBackground = HorizontalGradient(
        0.0f to red800,
        1.0f to red500,
        startX = 0f,
        endX = 128f
    )
    val componentModifier = layoutModifier.then(
        Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(brush = gradientBackground)
            .clickable(onClick = onClickListener)
    )

    Surface(color = Color.Transparent, modifier = componentModifier) {
        if (!isRecordingIdle) {
            Image(
                colorFilter = ColorFilter.tint(Color.White),
                asset = vectorResource(id = R.drawable.ic_baseline_stop_24),
                modifier = Modifier.preferredWidth(42.dp).align(Alignment.CenterVertically),
                contentScale = ContentScale.FillWidth
            )
        }
    }

}