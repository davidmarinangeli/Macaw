package com.david.voicememos.macaw.ui.home

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.david.voicememos.macaw.entities.Recording
import com.david.voicememos.macaw.entities.convertFilesToRecordings
import java.io.File

class HomeViewModel : ViewModel() {

    var recordings: List<Recording> by mutableStateOf(listOf())
        private set

    fun readRecordings(activity: Activity) {
        val directory = activity.externalCacheDir?.absolutePath
        if (directory != null) {
            val file = File(directory)
            recordings = convertFilesToRecordings(file.listFiles()?.toList() ?: listOf())
        } else {
            // display error
        }
    }

    // event: removeItem
    fun removeRecording(item: String) {
        recordings = recordings.toMutableList().also {
            it.remove(item)
        }
    }

}