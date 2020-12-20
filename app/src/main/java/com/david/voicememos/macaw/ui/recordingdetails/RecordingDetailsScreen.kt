package com.david.voicememos.macaw.ui.recordingdetails

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
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
import androidx.compose.ui.graphics.HorizontalGradient
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.david.voicememos.macaw.R
import com.david.voicememos.macaw.entities.convertDurationToString
import com.david.voicememos.macaw.ui.components.MacawSeekbar
import com.david.voicememos.macaw.ui.components.MacawSurface
import com.david.voicememos.macaw.ui.composebase.blue700
import com.david.voicememos.macaw.ui.composebase.red500
import com.david.voicememos.macaw.ui.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.io.File
import java.lang.Exception
import kotlin.concurrent.timer
import kotlin.math.min


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun RecordingDetailsScreen(
    dayAndTime: String,
    duration: String,
    path: String,
    viewModel: HomeViewModel
) {

    val isPlaying = remember { mutableStateOf(false) }

    val mHandler = Handler(Looper.getMainLooper())
    var currentTime by remember { mutableStateOf(0) }

    viewModel.initMediaPlayer(Uri.fromFile(File(path)))

    if (viewModel.isStreamCompleted.collectAsState().value) {
        isPlaying.value = false
    }

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
                style = MaterialTheme.typography.h5,
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
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(top = 8.dp),
                        fontStyle = FontStyle.Italic
                    )
                }
            }
            MacawSeekbar(
                currentTime = currentTime,
                duration = viewModel.getMediaDuration()
            )
        }
        Row(
            modifier = Modifier.align(Alignment.BottomCenter).padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
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
                    if (isPlaying.value) {
                        viewModel.pauseMedia()
                        mHandler.removeCallbacksAndMessages(null)
                    } else {
                        viewModel.playMedia()

                        mHandler.postDelayed(object : Runnable {

                            override fun run() {

                                if (isPlaying.value) {
                                    mHandler.postDelayed(this, 1000)
                                    currentTime = viewModel.getMediaDuration()
                                }
                            }
                        }, 0)
                    }

                    isPlaying.value = !isPlaying.value
                },
                backgroundColor = colors.secondary,
                modifier = Modifier
                    .defaultMinSizeConstraints(minWidth = 64.dp, minHeight = 64.dp),
            ) {
                Image(
                    colorFilter = ColorFilter.tint(Color.White),
                    imageVector = if (isPlaying.value) {
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

