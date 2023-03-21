package com.chunglyric.taipeicityzootour.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chunglyric.taipeicityzootour.R
import com.chunglyric.taipeicityzootour.data.guides.impl.guide1
import com.chunglyric.taipeicityzootour.model.Guide
import com.chunglyric.taipeicityzootour.ui.theme.TaipeiCityZooTourTheme

@Composable
fun GuideImage(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.noimage),
            contentDescription = null, // decorative
            modifier = modifier
                .size(128.dp, 128.dp)
                .clip(MaterialTheme.shapes.small)
        )
    }
}

@Composable
fun GuideTitle(guide: Guide) {
    Text(
        text = guide.name,
        style = MaterialTheme.typography.titleLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun InfoAndMemo(
    guide: Guide,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = guide.info,
            color = Color.DarkGray,
            maxLines = 2,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = guide.memo.ifEmpty { stringResource(id = R.string.area_closed_empty) },
            color = Color.DarkGray,
            maxLines = 1,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun ForwardButton(
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = {},
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null
        )
    }
}

@Composable
fun AreaGuideCard(
    guide: Guide,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        GuideImage(Modifier.padding(4.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 2.dp)
        ) {
            GuideTitle(guide)
            InfoAndMemo(guide)
        }
        ForwardButton(
            modifier = Modifier
                .clearAndSetSemantics {}
        )
    }
}

@Preview("Area guide card")
@Composable
fun AreaGuidePreview() {
    TaipeiCityZooTourTheme {
        Surface {
            AreaGuideCard(guide1)
        }
    }
}
