package com.david.voicememos.macaw.ui.home

import android.media.MediaRecorder
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.voicememos.macaw.entities.Recording
import com.david.voicememos.macaw.entities.convertFilesToRecordings
import com.david.voicememos.macaw.entities.generateRecordingName
import com.david.voicememos.macaw.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel() : ViewModel() {

    var recordings = MutableStateFlow<List<Recording>>(emptyList())
    var recorder: MediaRecorder? = null
    var homeRepository: HomeRepository = HomeRepository()

    val recordingState = MutableStateFlow(false)
    var folderPath: String = ""

    fun readRecordings() {
        viewModelScope.launch(Dispatchers.IO) {
            recordings.emit(homeRepository.getRecordings(folderPath))
        }
    }

    fun startRecording() {

        val fileName = generateRecordingName(folderPath)

        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB)
            setAudioEncodingBitRate(16 * 44100)
            setAudioSamplingRate(44100)
            setOutputFile(fileName)

            try {
                prepare()
            } catch (e: IOException) {
                viewModelScope.launch {
                    recordingState.emit(false)
                }
            }

            start()
            viewModelScope.launch {
                recordingState.emit(true)
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


    // event: removeItem
//    fun removeRecording(item: String) {
//        recordings = recordings.also {
//
//        }
//    }

}