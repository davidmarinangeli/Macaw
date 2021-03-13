package com.david.voicememos.macaw.ui.recordingdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.david.voicememos.macaw.R
import com.david.voicememos.macaw.ui.components.MacawSeekbar
import com.david.voicememos.macaw.ui.components.MacawSurface
import com.david.voicememos.macaw.ui.composebase.*
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun RecordingDetailsScreen(
    dayAndTime: String,
    duration: String,
    path: String,
    viewModel: RecordingDetailsViewModel,
    onBackPressed: () -> Unit
) {
    val state by viewModel.middlePlayer.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(54.dp)
                    .background(
                        Brush.horizontalGradient(listOf(blue700, colors.primary), 0f, 500f)
                    )
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.round_arrow_back_24),
                    tint = black,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .align(Alignment.CenterStart)
                        .clickable(onClick = onBackPressed)
                )
                Text(
                    text = dayAndTime,
                    style = typography.h6,
                    color = black,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Brush.horizontalGradient(listOf(blue700, colors.primary), 0f, 500f))
                    .clip(
                        RoundedCornerShape(
                            topEnd = 32.dp,
                            topStart = 32.dp
                        )
                    )
                    .requiredHeight(32.dp)
                    .background(colors.surface)
            )
            Text(
                "Info",
                style = typography.h5,
                modifier = Modifier.padding(end = 16.dp, start = 16.dp)
            )
            MacawSurface(
                onClick = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = dayAndTime, style = typography.h6
                    )
                    Row(
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_manual_record_24),
                            colorFilter = ColorFilter.tint(red500),
                            modifier = Modifier
                                .padding(4.dp)
                                .requiredWidth(12.dp)
                                .align(Alignment.CenterVertically),
                            contentScale = ContentScale.FillWidth,
                            contentDescription = null
                        )
                        Text(text = duration, style = typography.body1)
                    }
                    Text(
                        text = "Saved in $path",
                        color = grey700,
                        style = typography.body2,
                        modifier = Modifier.padding(top = 8.dp),
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
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
                    onClick = {
                        // TODO: haptic feedback
                        viewModel.rewindTenSeconds()
                    },
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .defaultMinSize(minWidth = 38.dp, minHeight = 38.dp),
                    backgroundColor = colors.secondaryVariant
                ) {
                    Image(
                        colorFilter = ColorFilter.tint(Color.White),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_replay_10_24),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null
                    )
                }
                FloatingActionButton(
                    onClick = {
                        // TODO: haptic feedback

                        if (state.isPlaying) {
                            viewModel.pauseMedia()
                        } else {
                            viewModel.playMedia()
                        }
                    },
                    backgroundColor = colors.secondary,
                    modifier = Modifier
                        .defaultMinSize(minWidth = 64.dp, minHeight = 64.dp),
                ) {
                    Image(
                        colorFilter = ColorFilter.tint(Color.White),
                        imageVector = if (state.isPlaying) {
                            ImageVector.vectorResource(id = R.drawable.ic_baseline_pause_24)
                        } else {
                            ImageVector.vectorResource(id = R.drawable.ic_baseline_play_arrow_24)
                        },
                        modifier = Modifier.requiredWidth(48.dp),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null
                    )
                }
                FloatingActionButton(
                    onClick = {
                        viewModel.forwardTenSeconds()
                        // TODO: haptic feedback
                    },
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .defaultMinSize(minWidth = 38.dp, minHeight = 38.dp),
                    backgroundColor = colors.secondaryVariant
                ) {
                    Image(
                        colorFilter = ColorFilter.tint(Color.White),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_forward_10_24),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

