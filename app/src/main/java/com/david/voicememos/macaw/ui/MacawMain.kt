package com.david.voicememos.macaw.ui

import android.net.Uri
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.david.voicememos.macaw.entities.Recording
import com.david.voicememos.macaw.ui.home.HomeActivity
import com.david.voicememos.macaw.ui.home.HomeScreen
import com.david.voicememos.macaw.ui.home.HomeViewModel
import com.david.voicememos.macaw.navigation.Screen
import com.david.voicememos.macaw.ui.recordingdetails.RecordingDetailsScreen
import com.david.voicememos.macaw.ui.recordingdetails.RecordingDetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.io.File

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun MacawMain(
    homeViewModel: HomeViewModel,
    recordingDetailsViewModel: RecordingDetailsViewModel,
    activity: HomeActivity
) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            homeViewModel.readRecordings()
            val recordingList: List<Recording> =
                homeViewModel.recordings.collectAsState().value
            HomeScreen(
                homeViewModel = homeViewModel,
                activity = activity,
                onClick = { item ->
                    navController.navigate(
                        "${Screen.RecordingDetails.route}/${item.dayAndTime}/${item.duration}/${item.path}"
                    )
                },
                recordingList = recordingList.toMutableList()
            )
        }
        composable(
            "${Screen.RecordingDetails.route}/{dayAndTime}/{duration}/{path}"
        ) { backStackEntry ->

            recordingDetailsViewModel.initMediaPlayer(
                Uri.fromFile(
                    File(backStackEntry.arguments?.getString("path") ?: "")
                )
            )

            RecordingDetailsScreen(
                backStackEntry.arguments?.getString("dayAndTime") ?: "",
                backStackEntry.arguments?.getString("duration") ?: "",
                backStackEntry.arguments?.getString("path") ?: "",
                recordingDetailsViewModel
            )
        }
    }
}