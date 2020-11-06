package com.david.voicememos.macaw.ui.recordingdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.HorizontalGradient
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.david.voicememos.macaw.R
import com.david.voicememos.macaw.entities.Recording
import com.david.voicememos.macaw.ui.components.PlayButton
import com.david.voicememos.macaw.ui.composebase.blue700
import com.david.voicememos.macaw.ui.composebase.red500

@ExperimentalMaterialApi
@Composable
fun RecordingDetailsScreen(item: Recording?) {
    Box(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
        Column {
            Box(
                alignment = Alignment.BottomStart,
                modifier = Modifier.clip(
                    shape = RoundedCornerShape(
                        bottomRight = 32.dp,
                        bottomLeft = 32.dp
                    )
                ).fillMaxWidth().preferredHeight(180.dp).background(
                    HorizontalGradient(listOf(blue700, colors.primary), 0f, 500f)
                )
            ) {
            }
            Text(
                "Info",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(top = 16.dp, end = 16.dp, start = 16.dp)
            )
            Card(
                shape = RoundedCornerShape(MaterialTheme.shapes.medium.topLeft),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).background(
                    colors.surface
                ).fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = item?.dayAndTime ?: "", style = MaterialTheme.typography.h5)
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
                        Text(text = "00:32", style = MaterialTheme.typography.body1)
                    }
                    Text(
                        text = "Saved in path/this/that/recording.mp4",
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(top = 8.dp),
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }
        Row(modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp)) {
            Button(
                onClick = {},
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.weight(2.0f, true).padding(end = 8.dp),
                colors = ButtonConstants.defaultButtonColors(),
            )
            {
                Image(
                    asset = vectorResource(id = R.drawable.ic_baseline_share_24),
                    modifier = Modifier.preferredWidth(32.dp).padding(vertical = 8.dp)
                )
            }
            PlayButton(Modifier.weight(9.0f, true))
        }
    }
}