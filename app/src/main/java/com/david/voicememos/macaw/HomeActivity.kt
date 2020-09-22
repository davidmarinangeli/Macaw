package com.david.voicememos.macaw

import android.Manifest
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.david.voicememos.macaw.ui.MacawTheme
import com.david.voicememos.macaw.ui.components.RecordButton
import java.io.IOException

private const val LOG_TAG = "AudioRecordTest"
private var recorder: MediaRecorder? = null
private var fileName: String = ""

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fileName = "${externalCacheDir?.absolutePath}/audiorecordtest.3gp"
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

private fun startRecording() {
    recorder = MediaRecorder().apply {
        setAudioSource(MediaRecorder.AudioSource.MIC)
        setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        setOutputFile(fileName)
        setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

        try {
            prepare()
        } catch (e: IOException) {
            Log.e(LOG_TAG, "prepare() failed")
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


fun onRecord(start: Boolean) = if (start) {
    startRecording()
} else {
    stopRecording()
}

@Composable
fun HomeScreen(homeViewModel: HomeViewModel, activity: HomeActivity) {
    val recordingList: List<String> = homeViewModel.recordings
    val startRecording = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    ) {
        LazyColumnFor(
            items = recordingList,
            modifier = Modifier.weight(1f),
            itemContent = { item ->
                Text(
                    text = item,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Red
                )
            })
        Row(modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp)) {
            RecordButton(onClickListener = {

                activity.registerForActivityResult(
                    ActivityResultContracts.RequestPermission()
                ) { isGranted: Boolean ->
                    if (isGranted) {
                        homeViewModel.addRecording("hi")
                        onRecord(startRecording.value)
                        startRecording.value = !startRecording.value
                    } else {
                        // Explain to the user that the feature is unavailable because the
                        // features requires a permission that the user has denied. At the
                        // same time, respect the user's decision. Don't link to system
                        // settings in an effort to convince the user to change their
                        // decision.
                    }
                }.launch(Manifest.permission.RECORD_AUDIO)


            })
        }

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