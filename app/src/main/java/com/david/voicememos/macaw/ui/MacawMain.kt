package com.david.voicememos.macaw.ui

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import com.david.voicememos.macaw.ui.home.HomeActivity
import com.david.voicememos.macaw.ui.home.HomeScreen
import com.david.voicememos.macaw.ui.home.HomeViewModel
import com.david.voicememos.macaw.ui.navigation.*
import com.david.voicememos.macaw.ui.navigation.BackDispatcherAmbient
import com.david.voicememos.macaw.ui.recordingdetails.RecordingDetailsScreen

@Composable
fun MacawMain(
    homeViewModel: HomeViewModel,
    activity: HomeActivity,
    backDispatcher: OnBackPressedDispatcher
) {
    val navigator: Navigator<Destination> = rememberSavedInstanceState(
        saver = Navigator.saver(backDispatcher)
    ) {
        Navigator(Destination.Home, backDispatcher)
    }
    val actions = remember(navigator) { Actions(navigator) }

    Providers(BackDispatcherAmbient provides backDispatcher) {
        ProvideDisplayInsets {
            Crossfade(navigator.current) { destination ->
                when (destination) {
                    is Destination.Home -> {
                        HomeScreen(
                            homeViewModel = homeViewModel,
                            activity = activity,
                            actions = actions
                        )
                    }
                    is Destination.RecordingDetails -> {
                        RecordingDetailsScreen()
                    }
                }
            }
        }
    }
}