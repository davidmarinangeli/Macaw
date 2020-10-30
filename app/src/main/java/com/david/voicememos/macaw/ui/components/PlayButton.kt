package com.david.voicememos.macaw.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.david.voicememos.macaw.R

@ExperimentalMaterialApi
@Composable
fun PlayButton(
    modifier: Modifier?
) {

    Button(
        modifier = modifier ?: Modifier,
        colors = ButtonConstants.defaultButtonColors(),
        shape = shapes.small,
        elevation = ButtonConstants.defaultElevation(defaultElevation = 1.dp),
        onClick = {}) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                colorFilter = ColorFilter.tint(Color.White),
                asset = vectorResource(id = R.drawable.ic_baseline_play_arrow_24),
                modifier = Modifier.preferredWidth(24.dp),
                contentScale = ContentScale.FillWidth
            )
            Text(
                "Play",
                color = Color.White,
                style = typography.button,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }

}