package com.david.voicememos.macaw.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.david.voicememos.macaw.R
import com.david.voicememos.macaw.entities.Recording
import com.david.voicememos.macaw.ui.components.RecordButton
import com.david.voicememos.macaw.ui.components.RecordingCard
import com.david.voicememos.macaw.ui.composebase.shapes
import com.david.voicememos.macaw.ui.composebase.surfaceWhite
import com.david.voicememos.macaw.ui.composebase.typography
import com.david.voicememos.macaw.ui.sortbybottomsheet.SortMethodListSheetFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalAnimationApi
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
    val listState = rememberLazyListState()

    val showButton = remember {
        derivedStateOf {
            listState.firstVisibleItemScrollOffset < 1
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        if (recordingList.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_no_results),
                    modifier = Modifier.padding(horizontal = 32.dp),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null
                )
                Text(
                    text = "No results found",
                    style = typography.body1,
                    color = colors.onSurface,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        } else {
            LazyColumn(state = listState) {
                itemsIndexed(recordingList) { index, recording ->
                    when (recording) {
                        recordingList.last() -> {
                            RecordingCard(recording, onClickListener = { onClick(recording) })
                            Box(Modifier.height(80.dp))
                        }
                        recordingList.first() -> {
                            Column {
                                Box {
                                    Image(
                                        imageVector = ImageVector.vectorResource(id = R.drawable.homescreen_background),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(bottom = 16.dp)
                                            .clip(
                                                shape = RoundedCornerShape(
                                                    bottomEnd = 16.dp,
                                                    bottomStart = 16.dp,
                                                )
                                            )
                                            .fillMaxWidth(),
                                        contentScale = ContentScale.FillBounds
                                    )
                                    Column(
                                        modifier = Modifier
                                            .align(Alignment.BottomStart)
                                            .padding(start = 32.dp, bottom = 64.dp)
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
                                    androidx.compose.animation.AnimatedVisibility(
                                        visible = showButton.value,
                                        modifier = Modifier
                                            .align(Alignment.BottomEnd)
                                            .padding(end = 16.dp, bottom = 32.dp)
                                    ) {
                                        SortButton(
                                            modifier = Modifier,
                                            onClick = {
                                                val bottomSheetFragment =
                                                    SortMethodListSheetFragment()
                                                bottomSheetFragment.show(
                                                    activity.supportFragmentManager,
                                                    bottomSheetFragment.tag
                                                )
                                            })
                                    }
                                }
                            }
                            RecordingCard(recording, onClickListener = { onClick(recording) })
                        }
                        else -> {
                            RecordingCard(recording, onClickListener = { onClick(recording) })
                        }
                    }
                }
            }
        }

        RecordButton(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            isRecording = recordingState.value,
            onClickListener = onRecordPressed(
                activity = activity,
                homeViewModel = homeViewModel
            )
        )
    }
}

@Composable
private fun SortButton(modifier: Modifier, onClick: () -> Unit) {
    Surface(
        elevation = 2.dp,
        color = if (colors.isLight) {
            surfaceWhite
        } else {
            colors.surface
        },
        modifier = modifier
            .clip(shapes.small)
            .clickable(onClick = onClick)
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
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_sort),
                colorFilter = ColorFilter.tint(colors.primary),
                contentDescription = null
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

        // haptic feedback

        if (isRecording) {
            homeViewModel.stopRecording()
        } else {
            activity.requestAudioRecording()
        }

    }
}