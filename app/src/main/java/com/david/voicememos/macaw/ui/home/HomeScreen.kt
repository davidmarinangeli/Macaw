package com.david.voicememos.macaw.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.david.voicememos.macaw.R
import com.david.voicememos.macaw.entities.Recording
import com.david.voicememos.macaw.ui.components.MacawSurface
import com.david.voicememos.macaw.ui.components.RecordButton
import com.david.voicememos.macaw.ui.components.RecordingCard
import com.david.voicememos.macaw.ui.composebase.typography
import com.david.voicememos.macaw.ui.sortmethodchooser.SortMethodListSheetFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    activity: HomeActivity,
    recordingList: MutableList<Recording>,
    onClick: (item: Recording) -> Unit
) {

    val recordingState = homeViewModel.recordingState.collectAsState()

    Box(
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        if (recordingList.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    imageVector = vectorResource(id = R.drawable.ic_no_results),
                    modifier = Modifier.padding(horizontal = 32.dp),
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = "No results found",
                    style = typography.body1,
                    color = colors.onSurface,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        } else {
            Column {
                LazyColumn {
                    items(items = recordingList,
                        itemContent = { item ->
                            when (item) {
                                recordingList.last() -> {
                                    RecordingCard(item, onClickListener = { onClick(item) })
                                    Box(Modifier.height(80.dp))
                                }
                                recordingList.first() -> {
                                    Column {
                                        Box {
                                            Image(
                                                imageVector = vectorResource(id = R.drawable.homescreen_background),
                                                modifier = Modifier.padding(bottom = 16.dp).clip(
                                                    shape = RoundedCornerShape(
                                                        bottomRight = 16.dp,
                                                        bottomLeft = 16.dp
                                                    )
                                                ).fillMaxWidth(),
                                                contentScale = ContentScale.FillBounds
                                            )
                                            Column(
                                                modifier = Modifier.align(Alignment.BottomStart)
                                                    .padding(start = 32.dp, bottom = 48.dp)
                                            ) {
                                                Text(
                                                    text = "Hey,",
                                                    style = typography.h5,
                                                    color = colors.onSurface
                                                )
                                                Text(
                                                    text = "David",
                                                    style = typography.h2,
                                                    color = colors.onSurface
                                                )
                                            }
                                        }
                                        SortButton(
                                            modifier = Modifier.align(Alignment.End),
                                            onClick = {
                                                val bottomSheetFragment = SortMethodListSheetFragment()
                                                bottomSheetFragment.show(
                                                    activity.supportFragmentManager,
                                                    bottomSheetFragment.tag
                                                )
                                            })
                                    }
                                }
                                else -> {
                                    RecordingCard(item, onClickListener = { onClick(item) })
                                }
                            }
                        })
                }
            }
        }

        RecordButton(
            modifier = Modifier.padding(16.dp).align(Alignment.BottomCenter),
            isRecording = recordingState.value,
            onClickListener = onRecordPressed(
                activity = activity,
                homeViewModel = homeViewModel,
            )
        )
    }
}

@Composable
private fun SortButton(modifier: Modifier, onClick: () -> Unit) {
    MacawSurface(
        onClick = onClick,
        modifier = modifier.padding(end = 16.dp, bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(
                vertical = 8.dp,
                horizontal = 12.dp
            )
        ) {
            Text(
                text = "Sort",
                style = typography.button,
                color = colors.primary,
                modifier = Modifier.padding(end = 4.dp)
            )
            Image(
                imageVector = vectorResource(id = R.drawable.ic_sort),
                colorFilter = ColorFilter.tint(colors.primary)
            )
        }
    }
}

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
private fun onRecordPressed(
    activity: HomeActivity,
    homeViewModel: HomeViewModel
): () -> Unit {
    return {

        val isRecording = homeViewModel.recordingState.value

        if (isRecording) {
            homeViewModel.stopRecording()
        } else {
            activity.requestAudioRecording()
        }

    }
}