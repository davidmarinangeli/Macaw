package com.david.voicememos.macaw.ui.recordingdetails

import android.net.Uri
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.voicememos.macaw.ui.home.MacawPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.math.min

class RecordingDetailsViewModel(private val macawPlayer: MacawPlayer) : ViewModel() {

    val middlePlayer = MutableStateFlow(MiddlePlayer())
    val mHandler = Handler(Looper.getMainLooper())

    // ** Mediaplayer Funs **

    fun initMediaPlayer(uri: Uri) {


        viewModelScope.launch {
            macawPlayer.prepareMediaPlayer(uri)
            middlePlayer.emit(
                middlePlayer.value.copy(
                    duration = macawPlayer.mediaPlayer.duration,
                    currentPosition = macawPlayer.mediaPlayer.currentPosition,
                    isPlaying = false
                ))
        }

        macawPlayer.mediaPlayer.setOnCompletionListener {
            viewModelScope.launch {
                middlePlayer.emit(
                    middlePlayer.value.copy(isPlaying = false, currentPosition = macawPlayer.mediaPlayer.duration)
                )
            }
        }
    }

    fun rewindTenSeconds() {
        macawPlayer.mediaPlayer.seekTo(macawPlayer.mediaPlayer.currentPosition - 10000)
        updateCurrentPosition()
    }

    fun forwardTenSeconds() {
        macawPlayer.mediaPlayer.seekTo(
            macawPlayer.mediaPlayer.currentPosition + (min(
                10000,
                macawPlayer.mediaPlayer.duration - macawPlayer.mediaPlayer.currentPosition
            ))
        )
        updateCurrentPosition()
    }

    fun playMedia() {
        viewModelScope.launch {
            macawPlayer.mediaPlayer.start()

            middlePlayer.emit(
                middlePlayer.value.copy(
                    isPlaying = true
                )
            )
        }

        mHandler.postDelayed(object : Runnable {

            override fun run() {
                if (middlePlayer.value.isPlaying) {
                    mHandler.postDelayed(this, 1000)
                    updateCurrentPosition()
                }
            }
        }, 0)
    }

    fun pauseMedia() {
        viewModelScope.launch {
            macawPlayer.mediaPlayer.pause()
            mHandler.removeCallbacksAndMessages(null)
            middlePlayer.emit(
                middlePlayer.value.copy(
                    isPlaying = false
                )
            )
        }

    }

    private fun updateCurrentPosition() {
        viewModelScope.launch {
            middlePlayer.emit(
                middlePlayer.value.copy(
                    currentPosition = macawPlayer.mediaPlayer.currentPosition
                )
            )
        }
    }
}

data class MiddlePlayer(
    val isPlaying: Boolean = false,
    val currentPosition: Int = 0,
    val duration: Int = 10
)