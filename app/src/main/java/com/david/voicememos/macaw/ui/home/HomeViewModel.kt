package com.david.voicememos.macaw.ui.home

import android.media.MediaRecorder
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david.voicememos.macaw.entities.Recording
import com.david.voicememos.macaw.entities.convertFilesToRecordings
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel : ViewModel() {

    var recordings = MutableLiveData<List<Recording>>()
    var recorder: MediaRecorder? = null

    fun readRecordings(directory: String?) {
        if (directory != null) {
            val file = File(directory)
            recordings.value = convertFilesToRecordings(file.listFiles()?.toList() ?: listOf())
        } else {
            // display error
        }
    }


    fun startRecording(fileName: String) {

        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB)
            setAudioEncodingBitRate(16*44100)
            setAudioSamplingRate(44100)
            setOutputFile(fileName)

            try {
                prepare()
            } catch (e: IOException) {
                // TODO handle preparation fail
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


    // event: removeItem
//    fun removeRecording(item: String) {
//        recordings = recordings.also {
//
//        }
//    }

}