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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.chunglyric.taipeicityzootour.R
import com.chunglyric.taipeicityzootour.data.guides.impl.guide1
import com.chunglyric.taipeicityzootour.model.Guide
import com.chunglyric.taipeicityzootour.ui.theme.TaipeiCityZooTourTheme

@Composable
fun GuideImage(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.noimage),
            contentDescription = null, // decorative
            modifier = Modifier
                .size(128.dp, 128.dp)
                .clip(MaterialTheme.shapes.small)
        )
    }
}

@Composable
fun GuideTitle(guide: Guide, modifier: Modifier = Modifier) {
    Text(
        text = guide.name,
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun GuideInfo(
    guide: Guide,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = guide.info,
            color = Color.DarkGray,
            maxLines = 2,
            style = MaterialTheme.typography.bodyMedium
        )
    }

}

@Composable
fun GuideMemo(
    guide: Guide,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
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
    Icon(
        modifier = modifier,
        imageVector = Icons.Default.KeyboardArrowRight,
        contentDescription = null
    )
}

@Composable
fun AreaGuideCard(
    guide: Guide,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (image, right, group, title, info, memo, forward) = createRefs()

        GuideImage(
            modifier = Modifier
                .padding(4.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )
        ConstraintLayout(
            modifier = Modifier.constrainAs(right) {
                centerVerticallyTo(parent)
                start.linkTo(image.end)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        ) {
            ConstraintLayout(
                modifier = Modifier.constrainAs(group) {
                    start.linkTo(parent.start)
                    end.linkTo(forward.start)
                    width = Dimension.fillToConstraints
                }
            ) {
                GuideTitle(
                    guide = guide,
                    modifier = Modifier.constrainAs(title) {
                        top.linkTo(parent.top)
                    }
                )

                GuideInfo(
                    guide = guide,
                    modifier = Modifier.constrainAs(info) {
                        top.linkTo(title.bottom, margin = 8.dp)
                    }
                )

                GuideMemo(
                    guide = guide,
                    modifier = Modifier.constrainAs(memo) {
                        top.linkTo(info.bottom, margin = 8.dp)
                    }
                )
            }

            ForwardButton(
                modifier = Modifier.constrainAs(forward) {
                    centerVerticallyTo(parent)
                    end.linkTo(parent.end)
                }
            )
        }
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
