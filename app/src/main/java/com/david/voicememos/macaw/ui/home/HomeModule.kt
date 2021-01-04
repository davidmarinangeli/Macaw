package com.david.voicememos.macaw.ui.home

import android.media.MediaRecorder
import com.david.voicememos.macaw.ui.recordingdetails.RecordingDetailsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel() }
    viewModel { RecordingDetailsViewModel(get()) }

    single { MacawPlayer(androidContext()) }
}