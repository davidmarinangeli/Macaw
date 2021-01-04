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

class HomeViewModel :
    ViewModel() {

    var recorder: MediaRecorder? = null
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

    fun sortRecordingsByDuration() {

        viewModelScope.launch(Dispatchers.IO) {
            recordings.emit(
                recordings.value.sortedBy {
                    it.duration
                })
        }

    }

    fun sortRecordingsByDate() {
        viewModelScope.launch(Dispatchers.IO) {
            recordings.emit(
                recordings.value.sortedBy {
                    it.date
                })
        }

    }

    fun startRecording() {

        val fileName = generateRecordingName(folderPath)

        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB)
            setAudioEncodingBitRate(16 * 44100)
            setAudioSamplingRate(44100)
        }

        try {
            recorder?.prepare()
            recorder?.start()
            viewModelScope.launch {
                recordingState.emit(true)
            }
        } catch (e: IOException) {
            viewModelScope.launch {
                recordingState.emit(false)
            }
        }
    }

    fun stopRecording() {
        recorder?.apply {
            stop()
            reset()
            release()
        }
        recorder = null
        viewModelScope.launch {
            recordingState.emit(false)
        }

        readRecordings()
    }
}