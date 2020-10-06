package com.david.voicememos.macaw.ui.home

import android.Manifest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Box
import androidx.compose.foundation.layout.ColumnScope.weight
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.david.voicememos.macaw.entities.Recording
import com.david.voicememos.macaw.ui.components.RecordButton
import com.david.voicememos.macaw.ui.components.RecordingCard
import com.david.voicememos.macaw.ui.navigation.Actions

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    activity: HomeActivity,
    recordingList: List<Recording>,
    onClick: () -> Unit
) {

    val isRecordingIdle = remember { mutableStateOf(true) }
    Stack(
        modifier = Modifier.padding(top = 16.dp).fillMaxWidth()
    ) {
        LazyColumnFor(
            items = recordingList,
            modifier = Modifier.weight(1f),
            itemContent = { item ->
                if (item == recordingList.last()) {
                    Box(Modifier.height(80.dp))
                } else {
                    RecordingCard(item, onClickListener = onClick)
                }
            })
        RecordButton(
            layoutModifier = Modifier.padding(16.dp).align(Alignment.BottomCenter),
            isRecordingIdle = isRecordingIdle.value,
            onClickListener = onRecordPressed(
                activity = activity,
                homeViewModel = homeViewModel,
                startRecording = isRecordingIdle
            )
        )
    }
}

private fun onRecordPressed(
    activity: HomeActivity,
    homeViewModel: HomeViewModel,
    startRecording: MutableState<Boolean>
): () -> Unit {
    return {
        activity.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                if (startRecording.value) {
                    startRecording(activity)
                } else {
                    stopRecording()
                    homeViewModel.readRecordings(activity.externalCacheDir?.absolutePath)
                }
                startRecording.value = !startRecording.value
            }
        }.launch(Manifest.permission.RECORD_AUDIO)
    }
}