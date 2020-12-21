package com.david.voicememos.macaw.ui.home

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MacawPlayer(private val context: Context) {

    val mediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
    }

    suspend fun prepareMediaPlayer(uri: Uri): MediaPlayer {
        return withContext(Dispatchers.IO) {
            mediaPlayer.apply {
                stop()
                reset()
                setDataSource(context, uri)
                prepare()

            }
            mediaPlayer
        }
    }
}