package com.chunglyric.taipeicityzootour.ui.utils.guidecard

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun GuideContent(
    text: String,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = text,
            color = Color.DarkGray,
            maxLines = 2,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
