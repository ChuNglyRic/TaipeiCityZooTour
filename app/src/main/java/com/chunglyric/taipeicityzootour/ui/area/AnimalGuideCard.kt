package com.chunglyric.taipeicityzootour.ui.area

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.chunglyric.taipeicityzootour.data.guides.impl.animalData1
import com.chunglyric.taipeicityzootour.model.AnimalGuide
import com.chunglyric.taipeicityzootour.ui.theme.TaipeiCityZooTourTheme
import com.chunglyric.taipeicityzootour.ui.utils.guidecard.GuideContent
import com.chunglyric.taipeicityzootour.ui.utils.guidecard.GuideForwardButton
import com.chunglyric.taipeicityzootour.ui.utils.guidecard.GuideImage
import com.chunglyric.taipeicityzootour.ui.utils.guidecard.GuideTitle

@Composable
fun AnimalGuideCard(
    data: AnimalGuide.Data,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (image, right, group, title, info, forward) = createRefs()

        GuideImage(
            url = data.a_pic01_url,
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
                    text = data.a_name_ch,
                    modifier = Modifier.constrainAs(title) {
                        top.linkTo(parent.top)
                    }
                )

                GuideContent(
                    text = data.a_alsoknown,
                    modifier = Modifier.constrainAs(info) {
                        top.linkTo(title.bottom, margin = 8.dp)
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

@Preview("Animal guide card")
@Composable
fun AnimalGuidePreview() {
    TaipeiCityZooTourTheme {
        Surface {
            AnimalGuideCard(animalData1)
        }
    }
}
