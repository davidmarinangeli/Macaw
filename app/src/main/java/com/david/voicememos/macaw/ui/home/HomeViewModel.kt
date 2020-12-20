package com.david.voicememos.macaw.ui.home

import android.media.MediaRecorder
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.voicememos.macaw.entities.Recording
import com.david.voicememos.macaw.entities.generateRecordingName
import com.david.voicememos.macaw.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.math.min

class HomeViewModel(private val lilloPlayer: LilloPlayer, private val recorder: MediaRecorder) :
    ViewModel() {

    var recordings = MutableStateFlow<List<Recording>>(emptyList())
    var homeRepository: HomeRepository = HomeRepository()

    val recordingState = MutableStateFlow(false)
    var folderPath: String = ""
    val isStreamCompleted = MutableStateFlow(false)

    // ** Mediaplayer Funs **

    fun initMediaPlayer(uri: Uri) {
        lilloPlayer.prepareMediaPlayer(uri)

        lilloPlayer.mediaPlayer.setOnCompletionListener {
            viewModelScope.launch {
                isStreamCompleted.emit(true)
            }

        }
    }

    fun rewindTenSeconds() {
        lilloPlayer.mediaPlayer.seekTo(lilloPlayer.mediaPlayer.currentPosition - 10000)
    }

    fun forwardTenSeconds() {
        lilloPlayer.mediaPlayer.seekTo(
            lilloPlayer.mediaPlayer.currentPosition + (min(
                10000,
                lilloPlayer.mediaPlayer.duration - lilloPlayer.mediaPlayer.currentPosition
            ))
        )
    }

    fun getMediaDuration(): Int {
        return lilloPlayer.mediaPlayer.duration
    }

    fun playMedia() {
        lilloPlayer.mediaPlayer.start()
    }

    fun pauseMedia() {
        lilloPlayer.mediaPlayer.pause()
    }

    // ** Recording Funs **
    fun readRecordings() {
        viewModelScope.launch(Dispatchers.IO) {
            recordings.emit(homeRepository.getRecordings(folderPath))
        }
    }

    fun startRecording() {

        val fileName = generateRecordingName(folderPath)

        recorder.setOutputFile(fileName)

        try {
            recorder.prepare()
        } catch (e: IOException) {
            viewModelScope.launch {
                recordingState.emit(false)
            }
        }

        recorder.start()
        viewModelScope.launch {
            recordingState.emit(true)
        }
    }

    fun stopRecording() {
        recorder.apply {
            stop()
            reset()
            release()
        }
        viewModelScope.launch {
            recordingState.emit(false)
        }

        readRecordings()
    }
}


// event: removeItem
//    fun removeRecording(item: String) {
//        recordings = recordings.also {
//
//        }
//    }
