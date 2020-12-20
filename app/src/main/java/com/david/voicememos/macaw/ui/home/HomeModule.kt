package com.david.voicememos.macaw.ui.home

import android.media.AudioAttributes
import android.media.MediaDataSource
import android.media.MediaPlayer
import android.media.MediaRecorder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val homeModule = module {
    factory { HomeViewModel(get(),get()) }

    factory { LilloPlayer(androidContext()) }

    factory {
        MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB)
            setAudioEncodingBitRate(16 * 44100)
            setAudioSamplingRate(44100)
        }
    }
}