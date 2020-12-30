package com.david.voicememos.macaw.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.david.voicememos.macaw.entities.Recording

@Composable
fun RecordingCard(
    recording: Recording,
    onClickListener: () -> Unit
) {

    MacawSurface(
        onClick = onClickListener,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = recording.dayAndTime, style = MaterialTheme.typography.h6)
            Text(text = recording.date, style = MaterialTheme.typography.body1)
            Text(
                text = recording.duration,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.align(Alignment.End)
            )

        }
    }
}