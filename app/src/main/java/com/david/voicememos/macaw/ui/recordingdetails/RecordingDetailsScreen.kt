package com.david.voicememos.macaw.ui.recordingdetails

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.david.voicememos.macaw.R
import com.david.voicememos.macaw.ui.components.MacawSeekbar
import com.david.voicememos.macaw.ui.components.MacawSurface
import com.david.voicememos.macaw.ui.composebase.blue700
import com.david.voicememos.macaw.ui.composebase.red500
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.io.File


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun RecordingDetailsScreen(
    dayAndTime: String,
    duration: String,
    path: String,
    viewModel: RecordingDetailsViewModel
) {
    val state by viewModel.middlePlayer.collectAsState()

    Box(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
        Column {
            Box(
                contentAlignment = Alignment.BottomStart,
                modifier = Modifier.clip(
                    shape = RoundedCornerShape(
                        bottomRight = 32.dp,
                        bottomLeft = 32.dp
                    )
                ).fillMaxWidth().preferredHeight(180.dp).background(
                    Brush.horizontalGradient(listOf(blue700, colors.primary), 0f, 500f)
                )
            ) {
            }
            Text(
                "Info",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(top = 16.dp, end = 16.dp, start = 16.dp)
            )
            MacawSurface(onClick = null) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = dayAndTime, style = MaterialTheme.typography.h5)
                    Row(
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Image(
                            imageVector = vectorResource(id = R.drawable.ic_baseline_manual_record_24),
                            colorFilter = ColorFilter.tint(red500),
                            modifier = Modifier.padding(4.dp).preferredWidth(12.dp)
                                .align(Alignment.CenterVertically),
                            contentScale = ContentScale.FillWidth
                        )
                        Text(text = duration, style = MaterialTheme.typography.body1)
                    }
                    Text(
                        text = "Saved in $path",
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(top = 8.dp),
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter).padding(24.dp)
        ) {
            MacawSeekbar(
                currentTime = state.currentPosition, duration = state.duration
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                FloatingActionButton(
                    onClick = { viewModel.rewindTenSeconds() },
                    modifier = Modifier.padding(horizontal = 32.dp)
                        .defaultMinSizeConstraints(minWidth = 38.dp, minHeight = 38.dp),
                    backgroundColor = colors.secondaryVariant
                ) {
                    Image(
                        colorFilter = ColorFilter.tint(Color.White),
                        imageVector = vectorResource(id = R.drawable.ic_baseline_replay_10_24),
                        contentScale = ContentScale.FillWidth
                    )
                }
                FloatingActionButton(
                    onClick = {
                        if (state.isPlaying) {
                            viewModel.pauseMedia()
                        } else {
                            viewModel.playMedia()
                        }
                    },
                    backgroundColor = colors.secondary,
                    modifier = Modifier
                        .defaultMinSizeConstraints(minWidth = 64.dp, minHeight = 64.dp),
                ) {
                    Image(
                        colorFilter = ColorFilter.tint(Color.White),
                        imageVector = if (state.isPlaying) {
                            vectorResource(id = R.drawable.ic_baseline_pause_24)
                        } else {
                            vectorResource(id = R.drawable.ic_baseline_play_arrow_24)
                        },
                        modifier = Modifier.preferredWidth(48.dp),
                        contentScale = ContentScale.FillWidth
                    )
                }
                FloatingActionButton(
                    onClick = { viewModel.forwardTenSeconds() },
                    modifier = Modifier.padding(horizontal = 32.dp)
                        .defaultMinSizeConstraints(minWidth = 38.dp, minHeight = 38.dp),
                    backgroundColor = colors.secondaryVariant
                ) {
                    Image(
                        colorFilter = ColorFilter.tint(Color.White),
                        imageVector = vectorResource(id = R.drawable.ic_baseline_forward_10_24),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        }
    }
}

