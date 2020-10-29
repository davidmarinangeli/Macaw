package com.david.voicememos.macaw.ui.recordingdetails

import androidx.compose.animation.Transition
import androidx.compose.animation.core.AnimationClockObservable
import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.TransitionDefinition
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.transition
import androidx.compose.foundation.Box
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.align
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.HorizontalGradient
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.david.voicememos.macaw.R
import com.david.voicememos.macaw.ui.components.PlayButton
import com.david.voicememos.macaw.ui.components.RecordButton
import com.david.voicememos.macaw.ui.composebase.*

@Preview(showBackground = true)
@Composable
fun RecordingDetailsScreen() {
    Stack(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
        Column {
            Stack {
                Box(
                    gravity = Alignment.BottomStart,
                    modifier = Modifier.clip(
                        shape = RoundedCornerShape(
                            bottomRight = 32.dp,
                            bottomLeft = 32.dp
                        )
                    ).fillMaxWidth().preferredHeight(180.dp).background(
                        HorizontalGradient(listOf(blue700, blue500), 0f, 500f)
                    )
                )
            }
            Text(
                "Info",
                style = typography.h5,
                modifier = Modifier.padding(top = 16.dp, end = 16.dp, start = 16.dp)
            )
            Card(
                shape = RoundedCornerShape(MaterialTheme.shapes.medium.topLeft),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).background(
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
            Text(
                "Word Standings",
                style = typography.h5,
                modifier = Modifier.padding(top = 8.dp, end = 16.dp, start = 16.dp)
            )
            Card(
                shape = RoundedCornerShape(MaterialTheme.shapes.medium.topLeft),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).background(
                    colors.surface
                ).fillMaxWidth()
            ) {}
        }
        Row(modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp)) {
            Button(
                onClick = {},
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.weight(2.0f, true).padding(end = 8.dp),
                backgroundColor = colors.primary
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