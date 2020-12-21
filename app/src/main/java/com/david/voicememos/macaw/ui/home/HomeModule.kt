package com.david.voicememos.macaw.ui.home

import android.media.MediaRecorder
import com.david.voicememos.macaw.ui.recordingdetails.RecordingDetailsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { RecordingDetailsViewModel(get()) }

    single { MacawPlayer(androidContext()) }

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