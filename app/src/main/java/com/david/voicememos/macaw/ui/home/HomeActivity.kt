package com.david.voicememos.macaw.ui.home

import android.media.MediaRecorder
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
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



class HomeActivity : AppCompatActivity(), RecognitionListener {

    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var speech: SpeechRecognizer

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

    override fun onReadyForSpeech(p0: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onBeginningOfSpeech() {
        TODO("Not yet implemented")
    }

    override fun onRmsChanged(p0: Float) {
        TODO("Not yet implemented")
    }

    override fun onBufferReceived(p0: ByteArray?) {
        TODO("Not yet implemented")
    }

    override fun onEndOfSpeech() {
        TODO("Not yet implemented")
    }

    override fun onError(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun onResults(p0: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onPartialResults(p0: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onEvent(p0: Int, p1: Bundle?) {
        TODO("Not yet implemented")
    }
}