package com.david.voicememos.macaw.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.david.voicememos.macaw.R
import com.david.voicememos.macaw.entities.Recording
import com.david.voicememos.macaw.ui.composebase.*

@Composable
fun RecordingCard(
    recording: Recording,
    onClickListener: () -> Unit
) {

    MacawSurface(
        onClick = onClickListener,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .weight(8f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = recording.readableDayTime,
                        style = MaterialTheme.typography.h6,
                    )
                    Text(
                        text = recording.readableDate,
                        style = MaterialTheme.typography.body1,
                        color = grey700
                    )
                }
                Text(
                    text = recording.duration,
                    style = MaterialTheme.typography.body1,
                    color = blue700
                )

            }
            Image(
                colorFilter = ColorFilter.tint(blue200),
                imageVector = ImageVector.vectorResource(id = R.drawable.round_play_circle_24),
                alignment = Alignment.Center,
                modifier = Modifier
                    .padding(end = 16.dp, bottom = 16.dp)
                    .requiredWidth(36.dp)
                    .weight(1f)
                    .align(Alignment.Bottom),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
        }
    }
}