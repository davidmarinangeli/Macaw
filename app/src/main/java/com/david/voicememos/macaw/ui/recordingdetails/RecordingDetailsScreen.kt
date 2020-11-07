package com.david.voicememos.macaw.ui.recordingdetails

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.HorizontalGradient
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.david.voicememos.macaw.R
import com.david.voicememos.macaw.ui.components.PlayButton
import com.david.voicememos.macaw.ui.composebase.blue700
import com.david.voicememos.macaw.ui.composebase.red500
import com.david.voicememos.macaw.ui.composebase.surfaceWhite
import java.io.File

@ExperimentalMaterialApi
@Composable
fun RecordingDetailsScreen(
    dayAndTime: String,
    duration: String,
    path: String,
    applicationContext: Context
) {
    val surfaceColor = if (colors.isLight) {
        surfaceWhite
    } else {
        colors.surface
    }

    val myUri: Uri = Uri.fromFile(File(path))

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
            Surface(
                elevation = 2.dp,
                color = surfaceColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = dayAndTime, style = MaterialTheme.typography.h5)
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
        }
        Row(
            modifier = Modifier.align(Alignment.BottomCenter).padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FloatingActionButton(
                onClick = {},
                modifier = Modifier.padding(horizontal = 32.dp)
                    .defaultMinSizeConstraints(minWidth = 38.dp, minHeight = 38.dp),
                backgroundColor = colors.secondaryVariant
            ) {
                Image(
                    colorFilter = ColorFilter.tint(Color.White),
                    asset = vectorResource(id = R.drawable.ic_baseline_replay_10_24),
                    contentScale = ContentScale.FillWidth
                )
            }
            FloatingActionButton(
                onClick = onPlayButtonPressed(applicationContext, myUri), backgroundColor = colors.secondary,
                modifier = Modifier
                    .defaultMinSizeConstraints(minWidth = 64.dp, minHeight = 64.dp),
            ) {
                Image(
                    colorFilter = ColorFilter.tint(Color.White),
                    asset = vectorResource(id = R.drawable.ic_baseline_play_arrow_24),
                    modifier = Modifier.preferredWidth(48.dp),
                    contentScale = ContentScale.FillWidth
                )
            }
            FloatingActionButton(
                onClick = {},
                modifier = Modifier.padding(horizontal = 32.dp)
                    .defaultMinSizeConstraints(minWidth = 38.dp, minHeight = 38.dp),
                backgroundColor = colors.secondaryVariant
            ) {
                Image(
                    colorFilter = ColorFilter.tint(Color.White),
                    asset = vectorResource(id = R.drawable.ic_baseline_forward_10_24),
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}

@Composable
private fun onPlayButtonPressed(
    applicationContext: Context,
    myUri: Uri
): () -> Unit {
    return {
        val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(applicationContext, myUri)
            prepare()
            start()
        }
    }
}