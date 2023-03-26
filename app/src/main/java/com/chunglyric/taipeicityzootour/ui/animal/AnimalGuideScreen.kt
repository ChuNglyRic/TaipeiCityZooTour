package com.chunglyric.taipeicityzootour.ui.animal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.chunglyric.taipeicityzootour.R
import com.chunglyric.taipeicityzootour.data.guides.impl.INVALID_DATA_ID
import com.chunglyric.taipeicityzootour.data.guides.impl.animalData1
import com.chunglyric.taipeicityzootour.data.guides.impl.invalidAnimalData
import com.chunglyric.taipeicityzootour.model.AnimalGuide
import com.chunglyric.taipeicityzootour.ui.theme.TaipeiCityZooTourTheme
import com.chunglyric.taipeicityzootour.ui.utils.CenterLoading
import com.chunglyric.taipeicityzootour.ui.utils.GuideNoImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AnimalGuideImage(
    data: AnimalGuide.Data,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        GlideImage(
            model = data.a_pic01_url.ifEmpty { R.drawable.noimage },
            contentDescription = null,
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            loading = placeholder { CenterLoading() },
            failure = placeholder { GuideNoImage() }
        )
    }
}

fun spawnContentWithTitle(
    title: String,
    content: String
): String {
    return if (content.isNotEmpty()) "\n\n$title\n${
        content
            .replace("\\n ", "\\n")
            .replace("\\n", "\n")
            .replace("\\r", "\r")
    }" else ""
}

@Composable
fun AnimalGuideCard(data: AnimalGuide.Data) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (image, title, content, divider) = createRefs()

        AnimalGuideImage(
            data,
            Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 2f)
                .padding(start = 8.dp, end = 8.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                }
        )

        Text(
            text = (if (data._id != INVALID_DATA_ID) "${data.a_name_ch}\n" else "") + "${data.a_name_en}",
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .constrainAs(title) {
                    top.linkTo(image.bottom, margin = 8.dp)
                }
        )

        if (data._id != INVALID_DATA_ID) {
            Text(
                text = spawnContentWithTitle(
                    title = stringResource(id = R.string.alias),
                    content = data.a_alsoknown
                ) +
                        spawnContentWithTitle(
                            title = stringResource(id = R.string.distribution),
                            content = data.a_distribution
                        ) +
                        spawnContentWithTitle(
                            title = stringResource(id = R.string.habitat),
                            content = data.a_habitat
                        ) +
                        spawnContentWithTitle(
                            title = stringResource(id = R.string.feature),
                            content = data.a_feature
                        ) +
                        spawnContentWithTitle(
                            title = stringResource(id = R.string.behavior),
                            content = data.a_behavior
                        ) +
                        "\n\n${stringResource(id = R.string.latest_updated)}: ${data.a_update}",
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .constrainAs(content) {
                        top.linkTo(title.bottom)
                    }
            )

            Divider(
                thickness = 24.dp,
                modifier = Modifier
                    .constrainAs(divider) {
                        top.linkTo(content.bottom, margin = 8.dp)
                    }
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun AnimalGuideScreen(
    data: AnimalGuide.Data,
    onGoBack: () -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = data.a_name_ch) },
                navigationIcon = {
                    IconButton(onClick = onGoBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { padding ->
        Modifier.padding(padding)
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            item { AnimalGuideCard(data = data) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("Animal guide screen")
@Composable
fun AnimalGuideScreenPreview() {
    TaipeiCityZooTourTheme {
        Surface {
            AnimalGuideScreen(
                data = animalData1,
                onGoBack = {}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("Invalid animal guide screen")
@Composable
fun InvalidAnimalGuidePreview() {
    TaipeiCityZooTourTheme {
        Surface {
            AnimalGuideScreen(
                data = invalidAnimalData,
                onGoBack = {}
            )
        }
    }
}
