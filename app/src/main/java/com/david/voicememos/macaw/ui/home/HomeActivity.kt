package com.david.voicememos.macaw.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.david.voicememos.macaw.ui.MacawMain
import com.david.voicememos.macaw.ui.composebase.MacawTheme


@ExperimentalMaterialApi
class HomeActivity : AppCompatActivity() {

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MacawTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MacawMain(homeViewModel, this)
                }
            }
        }
    }
}