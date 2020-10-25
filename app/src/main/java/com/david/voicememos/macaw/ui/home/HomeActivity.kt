package com.david.voicememos.macaw.ui.home

import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.david.voicememos.macaw.ui.MacawMain
import com.david.voicememos.macaw.ui.composebase.MacawTheme
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

private const val LOG_TAG = "AudioRecordTest"
private var recorder: MediaRecorder? = null

class HomeActivity : AppCompatActivity() {

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MacawTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MacawMain(homeViewModel, this, onBackPressedDispatcher)
                }
            }
        }
    }
}

fun startRecording(activity: HomeActivity) {

    val fileName =
        "${activity.externalCacheDir?.absolutePath}/Macaw-${
            SimpleDateFormat("ddMMyyyy-HHmmss", Locale.getDefault()).format(
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

fun stopRecording() {
    recorder?.apply {
        stop()
        reset()
        release()
    }
    recorder = null
}