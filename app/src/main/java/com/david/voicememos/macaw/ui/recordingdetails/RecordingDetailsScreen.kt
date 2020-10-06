package com.david.voicememos.macaw.ui.recordingdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.david.voicememos.macaw.R
import com.david.voicememos.macaw.ui.composebase.red500
import com.david.voicememos.macaw.ui.composebase.shapes
import com.david.voicememos.macaw.ui.composebase.typography

@Preview
@Composable
fun RecordingDetailsScreen() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            shape = RoundedCornerShape(shapes.large.bottomRight),
            modifier = Modifier.background(
                colors.surface
            ).fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Title of ", style = typography.h5)
                Row(
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Image(
                        asset = vectorResource(id = R.drawable.ic_baseline_manual_record_24),
                        colorFilter = ColorFilter.tint(red500),
                        modifier = Modifier.padding(4.dp).preferredWidth(12.dp)
                            .align(Alignment.CenterVertically),
                        contentScale = ContentScale.FillWidth
                    )
                    Text(text = "00:32", style = typography.body1)
                }
                Text(
                    text = "Saved in path/this/that/recording.mp4",
                    style = typography.caption,
                    modifier = Modifier.padding(top = 8.dp),
                    fontStyle = FontStyle.Italic
                )
            }
        }

    }
}