package com.david.voicememos.macaw.ui.home

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri

class LilloPlayer(private val context: Context) {

    val mediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )

    }

    fun prepareMediaPlayer(uri: Uri): MediaPlayer {
        mediaPlayer.setDataSource(context, uri)
        mediaPlayer.prepareAsync()

        return mediaPlayer
    }


}