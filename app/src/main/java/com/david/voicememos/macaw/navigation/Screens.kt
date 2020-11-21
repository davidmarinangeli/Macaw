package com.david.voicememos.macaw.navigation

import androidx.annotation.StringRes
import com.david.voicememos.macaw.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen("home", R.string.home)
    object RecordingDetails : Screen("recordingDetails", R.string.recordingDetails)
}