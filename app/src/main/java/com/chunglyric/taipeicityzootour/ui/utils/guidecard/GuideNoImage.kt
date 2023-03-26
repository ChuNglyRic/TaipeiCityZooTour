package com.chunglyric.taipeicityzootour.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chunglyric.taipeicityzootour.R

@Composable
fun GuideNoImage() {
    Image(
        painter = painterResource(id = R.drawable.noimage),
        contentDescription = null,
        modifier = Modifier
            .size(128.dp, 128.dp)
            .clip(MaterialTheme.shapes.extraSmall)
    )
}
