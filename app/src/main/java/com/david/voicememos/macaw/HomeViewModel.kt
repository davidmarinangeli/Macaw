package com.david.voicememos.macaw

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class HomeViewModel : ViewModel() {

    var recordings: List<String> by mutableStateOf(listOf())
        private set

    fun addRecording(item: String) {
        recordings = recordings + listOf(item)
    }

    // event: removeItem
    fun removeRecording(item: String) {
        recordings = recordings.toMutableList().also {
            it.remove(item)
        }
    }

}