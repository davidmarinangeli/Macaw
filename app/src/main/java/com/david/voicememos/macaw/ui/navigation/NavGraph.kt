package com.david.voicememos.macaw.ui.navigation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.android.parcel.Parcelize

sealed class Destination : Parcelable {

    @Parcelize
    object Home : Destination()

    @Parcelize
    object RecordingDetails : Destination()
}

class Actions(navigator: Navigator<Destination>) {

    val recordingDetails: () -> Unit = {
        navigator.navigate(Destination.RecordingDetails)
    }

    val pressOnBack: () -> Unit = {
        navigator.back()
    }
}