package com.david.voicememos.macaw.ui.home

import android.media.MediaRecorder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.voicememos.macaw.entities.Recording
import com.david.voicememos.macaw.entities.generateRecordingName
import com.david.voicememos.macaw.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(private val recorder: MediaRecorder) :
    ViewModel() {

    var recordings = MutableStateFlow<List<Recording>>(emptyList())
    val recordingState = MutableStateFlow(false)
    var homeRepository: HomeRepository = HomeRepository()

    var folderPath: String = ""

    // ** Recording Funs **
    fun readRecordings() {
        viewModelScope.launch(Dispatchers.IO) {
            recordings.emit(homeRepository.getRecordings(folderPath))
        }
    }

    fun sortRecordings() {

        viewModelScope.launch(Dispatchers.IO) {
            recordings.emit(
                recordings.value.minus(recordings.value.first()).sortedBy {
                    it.duration
                })
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