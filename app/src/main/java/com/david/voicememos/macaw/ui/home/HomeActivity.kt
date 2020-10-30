package com.david.voicememos.macaw.ui.home

import android.content.Intent
import android.media.MediaRecorder
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.david.voicememos.macaw.ui.MacawMain
import com.david.voicememos.macaw.ui.composebase.MacawTheme
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


@ExperimentalMaterialApi
class HomeActivity : AppCompatActivity(), RecognitionListener {

    private val homeViewModel by viewModels<HomeViewModel>()
    lateinit var speechRecognizer: SpeechRecognizer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
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
        val data = p0?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        Log.d("tag", data?.get(0) ?: " Empty ")

}

override fun onPartialResults(p0: Bundle?) {
    TODO("Not yet implemented")
}

override fun onEvent(p0: Int, p1: Bundle?) {
    TODO("Not yet implemented")
}
}