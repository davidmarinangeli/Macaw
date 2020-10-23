package com.david.voicememos.macaw.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.weight
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.david.voicememos.macaw.R
import com.david.voicememos.macaw.ui.components.RecordButton

@Preview
@Composable
fun accctivity() {
    Stack(
        modifier = Modifier.padding(top = 16.dp).fillMaxWidth().fillMaxHeight()
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Image(
                asset = vectorResource(id = R.drawable.ic_no_results),
                modifier = Modifier.padding(horizontal = 32.dp),
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = "No results found",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        RecordButton(
            layoutModifier = Modifier.padding(16.dp).align(Alignment.BottomCenter),
            isRecordingIdle = true,
            onClickListener = {}
        )
    }

}