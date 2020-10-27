package com.david.voicememos.macaw.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.align
import androidx.compose.foundation.layout.RowScope.align
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme.colors
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
import androidx.ui.tooling.preview.Preview
import com.david.voicememos.macaw.R

@Composable
fun PlayButton(
    modifier: Modifier
) {

    Button(
        modifier = modifier,
        backgroundColor = colors.primary,
        shape = shapes.small,
        elevation = 1.dp,
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
                "Button",
                color = Color.White,
                style = typography.button,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }

}