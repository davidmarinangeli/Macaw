package com.david.voicememos.macaw.ui.home

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.david.voicememos.macaw.R
import com.david.voicememos.macaw.entities.Recording
import com.david.voicememos.macaw.entities.generateRecordingName
import com.david.voicememos.macaw.ui.components.RecordButton
import com.david.voicememos.macaw.ui.components.RecordingCard
import java.util.*

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    activity: HomeActivity,
    recordingList: MutableList<Recording>,
    onClick: () -> Unit
) {
    val isRecordingIdle = remember { mutableStateOf(true) }
    if (recordingList.isNotEmpty()) {
        remember { recordingList.add(0, Recording("", "", "")) }
    }
    Box(
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        if (recordingList.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    asset = vectorResource(id = R.drawable.ic_no_results),
                    modifier = Modifier.padding(horizontal = 32.dp),
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = "No results found",
                    style = MaterialTheme.typography.body1,
                    color = colors.onSurface,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        } else {
            Column {
                LazyColumnFor(
                    items = recordingList,
                    itemContent = { item ->
                        when (item) {
                            recordingList.last() -> {
                                RecordingCard(item, onClickListener = onClick)
                                Box(Modifier.height(80.dp))
                            }
                            recordingList.first() -> {
                                Box {
                                    Image(
                                        asset = vectorResource(id = R.drawable.homescreen_background),
                                        modifier = Modifier.padding(bottom = 16.dp).clip(
                                            shape = RoundedCornerShape(
                                                bottomRight = 32.dp,
                                                bottomLeft = 32.dp
                                            )
                                        ).fillMaxWidth(),
                                        contentScale = ContentScale.Fit
                                    )
                                    Column(
                                        modifier = Modifier.align(Alignment.BottomStart)
                                            .padding(start = 32.dp, bottom = 48.dp)
                                    ) {
                                        Text(
                                            text = "Hey,",
                                            style = MaterialTheme.typography.h5,
                                            color = colors.surface
                                        )
                                        Text(
                                            text = "David",
                                            style = MaterialTheme.typography.h2,
                                            color = colors.surface
                                        )
                                    }
                                }
                            }
                            else -> {
                                RecordingCard(item, onClickListener = onClick)
                            }
                        }
                    })
            }
        }
        RecordButton(
            layoutModifier = Modifier.padding(16.dp).align(Alignment.BottomCenter),
            isRecordingIdle = isRecordingIdle.value,
            onClickListener = onRecordPressed(
                activity = activity,
                homeViewModel = homeViewModel,
                startRecording = isRecordingIdle
            )
        )
    }
}

@ExperimentalMaterialApi
private fun onRecordPressed(
    activity: HomeActivity,
    homeViewModel: HomeViewModel,
    startRecording: MutableState<Boolean>
): () -> Unit {
    return {
        activity.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                if (startRecording.value) {
                    val fileName = generateRecordingName(activity.externalCacheDir?.absolutePath)
                    homeViewModel.startRecording(fileName)
                    start(activity)
                } else {
                    homeViewModel.stopRecording()
                    homeViewModel.readRecordings(activity.externalCacheDir?.absolutePath)
                }
                startRecording.value = !startRecording.value
            }
        }.launch(Manifest.permission.RECORD_AUDIO)
    }
}


@ExperimentalMaterialApi
fun start(activity: HomeActivity) {

    val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(activity.applicationContext)

    val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
    speechRecognizerIntent.putExtra(
        RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
    )
    speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

    speechRecognizer.setRecognitionListener(object : RecognitionListener {
        override fun onReadyForSpeech(p0: Bundle?) {

        }

        override fun onBeginningOfSpeech() {
        }

        override fun onRmsChanged(p0: Float) {
        }

        override fun onBufferReceived(p0: ByteArray?) {
        }

        override fun onEndOfSpeech() {
        }

        override fun onError(p0: Int) {
        }

        override fun onResults(p0: Bundle?) {
        }

        override fun onPartialResults(p0: Bundle?) {
        }

        override fun onEvent(p0: Int, p1: Bundle?) {
        }
    })

    speechRecognizer.startListening(speechRecognizerIntent)
}