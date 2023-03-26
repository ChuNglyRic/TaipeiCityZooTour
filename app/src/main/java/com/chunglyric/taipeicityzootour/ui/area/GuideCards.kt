package com.chunglyric.taipeicityzootour.ui.area

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.chunglyric.taipeicityzootour.R
import com.chunglyric.taipeicityzootour.data.guides.impl.animalData1
import com.chunglyric.taipeicityzootour.model.AnimalGuide
import com.chunglyric.taipeicityzootour.ui.theme.TaipeiCityZooTourTheme
import com.chunglyric.taipeicityzootour.ui.utils.CenterLoading

@Composable
fun NoImage() {
    Image(
        painter = painterResource(id = R.drawable.noimage),
        contentDescription = null,
        modifier = Modifier
            .size(128.dp, 128.dp)
            .clip(MaterialTheme.shapes.extraSmall)
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GuideImage(
    data: AnimalGuide.Data,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        GlideImage(
            model = data.a_pic01_url,
            contentDescription = null,
            modifier = Modifier
                .size(128.dp, 128.dp)
                .clip(MaterialTheme.shapes.extraSmall),
            contentScale = ContentScale.Crop,
            loading = placeholder { CenterLoading() },
            failure = placeholder { NoImage() }
        )
    }
}

@Composable
fun GuideTitle(
    data: AnimalGuide.Data,
    modifier: Modifier = Modifier
) {
    Text(
        text = data.a_name_ch,
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun GuideInfo(
    data: AnimalGuide.Data,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = data.a_alsoknown,
            color = Color.DarkGray,
            maxLines = 2,
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
fun AnimalGuideCard(
    data: AnimalGuide.Data,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (image, right, group, title, info, forward) = createRefs()

        GuideImage(
            data = data,
            modifier = Modifier
                .padding(8.dp)
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
                    data = data,
                    modifier = Modifier.constrainAs(title) {
                        top.linkTo(parent.top)
                    }
                )

                GuideInfo(
                    data = data,
                    modifier = Modifier.constrainAs(info) {
                        top.linkTo(title.bottom, margin = 8.dp)
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

@Preview("Animal guide card")
@Composable
fun AnimalGuidePreview() {
    TaipeiCityZooTourTheme {
        Surface {
            AnimalGuideCard(animalData1)
        }
    }
}
