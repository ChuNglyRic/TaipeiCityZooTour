package com.chunglyric.taipeicityzootour.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.chunglyric.taipeicityzootour.R
import com.chunglyric.taipeicityzootour.data.guides.impl.areaData1
import com.chunglyric.taipeicityzootour.model.AreaGuide
import com.chunglyric.taipeicityzootour.ui.theme.TaipeiCityZooTourTheme
import com.chunglyric.taipeicityzootour.ui.utils.guidecard.GuideContent
import com.chunglyric.taipeicityzootour.ui.utils.guidecard.GuideForwardButton
import com.chunglyric.taipeicityzootour.ui.utils.guidecard.GuideImage
import com.chunglyric.taipeicityzootour.ui.utils.guidecard.GuideTitle

@Composable
fun GuideMemo(
    data: AreaGuide.Data,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = data.e_memo.ifEmpty { stringResource(id = R.string.area_closed_empty) },
            color = Color.DarkGray,
            maxLines = 1,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun AreaGuideCard(
    data: AreaGuide.Data,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (image, right, group, title, info, memo, forward) = createRefs()

        GuideImage(
            url = data.e_pic_url,
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
                    text = data.e_name,
                    modifier = Modifier.constrainAs(title) {
                        top.linkTo(parent.top)
                    }
                )

                GuideContent(
                    text = data.e_info,
                    modifier = Modifier.constrainAs(info) {
                        top.linkTo(title.bottom, margin = 8.dp)
                    }
                )

                GuideMemo(
                    data = data,
                    modifier = Modifier.constrainAs(memo) {
                        top.linkTo(info.bottom, margin = 8.dp)
                    }
                )
            }

            GuideForwardButton(
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
            AreaGuideCard(areaData1)
        }
    }
}
