package com.david.voicememos.macaw.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.david.voicememos.macaw.entities.convertDurationToString

@Composable
fun MacawSeekbar(
    currentTime: Int,
    duration: Int
) {

    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = Modifier.padding(end = 4.dp), text = convertDurationToString(currentTime))
        Box(modifier = Modifier.fillMaxWidth(0.8f)) {
            Box(
                modifier = Modifier.height(6.dp).clip(RoundedCornerShape(3.dp)).fillMaxWidth()
                    .background(colors.primaryVariant).align(Alignment.CenterStart)
            )
            Box(
                modifier = Modifier.height(6.dp).clip(RoundedCornerShape(3.dp))
                    .fillMaxWidth(currentTime / duration.toFloat())
                    .background(colors.secondary).align(Alignment.CenterStart)
            )
        }
        Text(modifier = Modifier.padding(start = 4.dp), text = convertDurationToString(duration))
    }
}