package com.david.voicememos.macaw.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david.voicememos.macaw.entities.Recording
import com.david.voicememos.macaw.entities.convertFilesToRecordings
import java.io.File

class HomeViewModel : ViewModel() {

    var recordings = MutableLiveData<List<Recording>>()

    fun readRecordings(directory: String?) {
        if (directory != null) {
            val file = File(directory)
            recordings.value = convertFilesToRecordings(file.listFiles()?.toList() ?: listOf())
        } else {
            // display error
        }
    }

    // event: removeItem
//    fun removeRecording(item: String) {
//        recordings = recordings.also {
//
//        }
//    }

}