package com.david.voicememos.macaw.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun MacawSeekbar(
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = Modifier.padding(end = 4.dp), text = "00:04")
        Box(
            modifier = Modifier
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp))
                .fillMaxWidth(0.8f)
                .background(colors.primary)
        ) {
        }
        Text(modifier = Modifier.padding(start = 4.dp), text = "00:58")
    }
}