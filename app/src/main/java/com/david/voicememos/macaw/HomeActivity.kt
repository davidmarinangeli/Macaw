package com.david.voicememos.macaw

import android.Manifest
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Box
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.weight
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.david.voicememos.macaw.entities.Recording
import com.david.voicememos.macaw.ui.MacawTheme
import com.david.voicememos.macaw.ui.components.RecordButton
import com.david.voicememos.macaw.ui.components.RecordingCard
import java.io.IOException
import java.text.SimpleDateFormat
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

@Composable
fun HomeScreen(homeViewModel: HomeViewModel, activity: HomeActivity) {

    homeViewModel.readRecordings(activity)
    val recordingList: List<Recording> = homeViewModel.recordings
    val isRecordingIdle = remember { mutableStateOf(true) }
    Stack(
        modifier = Modifier.padding(top = 16.dp).fillMaxWidth()
    ) {
        LazyColumnFor(
            items = recordingList,
            modifier = Modifier.weight(1f),
            itemContent = { item ->
                if (item == recordingList.last()) {
                    Box(Modifier.height(80.dp))
                } else {
                    RecordingCard(item)
                }
            })
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
                if (startRecording.value) {
                    startRecording(activity)
                } else {
                    stopRecording()
                    homeViewModel.readRecordings(activity)
                }
                startRecording.value = !startRecording.value
            }
        }.launch(Manifest.permission.RECORD_AUDIO)
    }
}

private fun startRecording(activity: HomeActivity) {

    val fileName =
        "${activity.externalCacheDir?.absolutePath}/MacawRecording-${
            SimpleDateFormat("ddMMyyyy", Locale.getDefault()).format(
                Calendar.getInstance().time
            )
        }.mp4"

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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val homeViewModel = HomeViewModel()
    MacawTheme {
        HomeScreen(homeViewModel, HomeActivity())
    }
}