package com.david.voicememos.macaw

import android.Manifest
import android.content.Context
import android.media.MediaRecorder
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.ui.tooling.preview.Preview
import com.david.voicememos.macaw.entities.Recording
import com.david.voicememos.macaw.ui.MacawTheme
import com.david.voicememos.macaw.ui.components.RecordButton
import com.david.voicememos.macaw.ui.components.RecordingCard
import java.io.File
import java.io.IOException
import java.time.LocalDateTime
import java.util.*

private const val LOG_TAG = "AudioRecordTest"
private var recorder: MediaRecorder? = null

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeViewModel by viewModels<HomeViewModel>()

        setContent {
            MacawTheme {
                Surface(color = MaterialTheme.colors.background) {
                    HomeScreen(homeViewModel, this)
                }
            }
        }
    }
}

private fun startRecording(activity: HomeActivity) {
    val fileName =
        "${activity.externalCacheDir?.absolutePath}/${Calendar.getInstance().timeInMillis}.mp4"

    recorder = MediaRecorder().apply {
        setAudioSource(MediaRecorder.AudioSource.MIC)
        setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        setOutputFile(fileName)
        setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

        try {
            prepare()
        } catch (e: IOException) {
            Log.e(LOG_TAG, "prepare() failed $e")
        }

        start()
    }
}

private fun stopRecording() {
    recorder?.apply {
        stop()
        reset()
        release()
    }
    recorder = null
}


fun onRecord(homeViewModel: HomeViewModel, activity: HomeActivity, start: Boolean) = if (start) {
    startRecording(activity)
} else {
    stopRecording()
    homeViewModel.readRecordings(activity)
}

@Composable
fun HomeScreen(homeViewModel: HomeViewModel, activity: HomeActivity) {

    homeViewModel.readRecordings(activity)
    val recordingList: List<Recording> = homeViewModel.recordings
    val startRecording = remember { mutableStateOf(true) }
    Column(
        modifier = Modifier.padding(top = 16.dp).fillMaxWidth()
    ) {
        LazyColumnFor(
            items = recordingList,
            modifier = Modifier.weight(1f),
            itemContent = { item ->
                RecordingCard(item)
            })
    }
}

@Composable
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
                onRecord(homeViewModel, activity, startRecording.value)
                startRecording.value = !startRecording.value
            }
        }.launch(Manifest.permission.RECORD_AUDIO)


    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val homeViewModel = HomeViewModel()
    MacawTheme {
        HomeScreen(homeViewModel, HomeActivity())
    }
}