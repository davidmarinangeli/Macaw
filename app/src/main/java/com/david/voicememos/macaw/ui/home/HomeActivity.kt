package com.david.voicememos.macaw.ui.home

import android.Manifest
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.david.voicememos.macaw.ui.MacawMain
import com.david.voicememos.macaw.ui.composebase.MacawTheme
import com.david.voicememos.macaw.ui.recordingdetails.RecordingDetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
class HomeActivity : AppCompatActivity() {

    private val homeViewModel by inject<HomeViewModel>()
    private val recordingDetailsViewModel by inject<RecordingDetailsViewModel>()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) startRecording()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@HomeActivity)
            modules(homeModule)
        }

        setContent {
            MacawTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MacawMain(homeViewModel, recordingDetailsViewModel, this)
                }
            }
        }

        homeViewModel.folderPath = externalCacheDir?.absolutePath
            ?: throw IllegalStateException("externalCacheDir is null! LILLOOO!")
    }

    fun requestAudioRecording() {
        requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }

    private fun startRecording() {
        homeViewModel.startRecording()
    }

}