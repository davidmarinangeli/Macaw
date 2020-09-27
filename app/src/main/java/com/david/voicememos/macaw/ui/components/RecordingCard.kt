package com.david.voicememos.macaw.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.david.voicememos.macaw.R
import com.david.voicememos.macaw.entities.Recording
import com.david.voicememos.macaw.ui.red800
import com.david.voicememos.macaw.ui.shapes
import com.david.voicememos.macaw.ui.typography

@Composable
fun RecordingCard(
    recording: Recording
) {

    Card(Modifier.background(colors.surface, shapes.medium).fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = recording.dayAndTime, style = typography.h6)
                Image(
                    colorFilter = ColorFilter.tint(red800),
                    asset = vectorResource(id = R.drawable.ic_baseline_play_arrow_24),
                    modifier = Modifier.preferredWidth(32.dp).align(Alignment.CenterVertically),
                    contentScale = ContentScale.FillWidth
                )
            }
            Text(text = recording.date, style = MaterialTheme.typography.body1)
            Text(
                text = recording.duration,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.align(Alignment.End)
            )

        }
    }
}