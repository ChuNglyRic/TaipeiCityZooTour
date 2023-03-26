package com.chunglyric.taipeicityzootour.ui.utils.guidecard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.chunglyric.taipeicityzootour.ui.utils.CenterLoading
import com.chunglyric.taipeicityzootour.ui.utils.GuideNoImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GuideImage(
    url: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        GlideImage(
            model = url,
            contentDescription = null,
            modifier = Modifier
                .size(128.dp, 128.dp)
                .clip(MaterialTheme.shapes.extraSmall),
            contentScale = ContentScale.Crop,
            loading = placeholder { CenterLoading() },
            failure = placeholder { GuideNoImage() }
        )
    }
}
