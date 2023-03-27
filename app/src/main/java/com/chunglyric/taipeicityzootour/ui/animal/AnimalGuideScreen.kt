package com.chunglyric.taipeicityzootour.ui.animal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.bumptech.glide.signature.ObjectKey
import com.chunglyric.taipeicityzootour.R
import com.chunglyric.taipeicityzootour.data.guides.impl.INVALID_DATA_ID
import com.chunglyric.taipeicityzootour.data.guides.impl.animalData1
import com.chunglyric.taipeicityzootour.data.guides.impl.invalidAnimalData
import com.chunglyric.taipeicityzootour.model.AnimalGuide
import com.chunglyric.taipeicityzootour.ui.theme.TaipeiCityZooTourTheme
import com.chunglyric.taipeicityzootour.ui.utils.*

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AnimalGuideImage(
    reloadImageUiState: ReloadImageUiState,
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
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            loading = placeholder { CenterLoading() },
            failure = placeholder { GuideNoImage() }
        ) {
            it.signature(ObjectKey(reloadImageUiState.timestamp))
        }
    }
}

fun spawnContentWithTitle(
    title: String,
    content: String
): String {
    return if (content.isNotEmpty()) "\n$title\n${
        content
            .replace("\\n ", "\\n")
            .replace("\\n", "\n")
            .replace("\\r", "\r")
    }\n" else ""
}

@Composable
fun AnimalGuideCard(
    data: AnimalGuide.Data,
    reloadImageUiState: ReloadImageUiState
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (image, title, content, divider) = createRefs()

        AnimalGuideImage(
            reloadImageUiState = reloadImageUiState,
            data = data,
            modifier = Modifier
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
                        "\n${stringResource(id = R.string.latest_updated)}: ${data.a_update}",
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

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalMaterial3Api
@Composable
fun AnimalGuideScreen(
    data: AnimalGuide.Data,
    onGoBack: () -> Unit,
    viewModel: ReloadImageViewModel = ReloadImageViewModel()
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)
    val animalGuideUiState by viewModel.uiState.collectAsState()

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
        var refreshing by remember { mutableStateOf(false) }
        val state = rememberPullRefreshState(
            refreshing = refreshing,
            onRefresh = {
                refreshing = true
                viewModel.reloadImage()
                refreshing = false
            }
        )
        Box(
            modifier = Modifier.pullRefresh(state = state)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
            ) {
                item {
                    AnimalGuideCard(
                        data = data,
                        animalGuideUiState
                    )
                }
            }

            PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
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
