package com.chunglyric.taipeicityzootour.ui.area

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.chunglyric.taipeicityzootour.R
import com.chunglyric.taipeicityzootour.data.guides.impl.areaData1
import com.chunglyric.taipeicityzootour.model.AreaGuide
import com.chunglyric.taipeicityzootour.ui.home.NoImage
import com.chunglyric.taipeicityzootour.ui.theme.TaipeiCityZooTourTheme
import com.chunglyric.taipeicityzootour.ui.utils.CenterLoading

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AreaGuideImage(
    data: AreaGuide.Data,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(3f / 2f)
    ) {
        GlideImage(
            model = data.e_pic_url,
            contentDescription = null,
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            loading = placeholder { CenterLoading() },
            failure = placeholder { NoImage() }
        )
    }
}

@Composable
fun AreaGuideUrlText(
    data: AreaGuide.Data,
    modifier: Modifier = Modifier
) {
    val annotatedLinkString: AnnotatedString = buildAnnotatedString {
        val str = stringResource(id = R.string.open_url)
        append(str)
        addStyle(
            style = SpanStyle(color = Color(0xff64B5F6)),
            start = 0,
            end = str.length
        )
        addStringAnnotation(
            tag = data.e_name,
            annotation = data.e_url,
            start = 0,
            end = str.length
        )
    }

    val uriHandler = LocalUriHandler.current

    ClickableText(
        text = annotatedLinkString,
        modifier = modifier,
        onClick = {
            annotatedLinkString
                .getStringAnnotations(data.e_name, it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )
}

@Composable
private fun AreaGuideScreenContent(
    data: AreaGuide.Data,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (image, info, memo, category, url, divider) = createRefs()

                AreaGuideImage(
                    data,
                    Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                        }
                )

                Text(
                    text = data.e_info,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .constrainAs(info) {
                            top.linkTo(image.bottom, margin = 8.dp)
                        }
                )

                Text(
                    text = data.e_memo.ifEmpty { stringResource(id = R.string.area_closed_empty) },
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .constrainAs(memo) {
                            top.linkTo(info.bottom, margin = 8.dp)
                        }
                )

                Text(
                    text = data.e_category,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .constrainAs(category) {
                            top.linkTo(memo.bottom)
                        }
                )

                AreaGuideUrlText(
                    data = data,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .constrainAs(url) {
                            top.linkTo(memo.bottom)
                            end.linkTo(parent.end)
                        }
                )

                Divider(
                    thickness = 24.dp,
                    modifier = Modifier
                        .constrainAs(divider) {
                            top.linkTo(category.bottom, margin = 8.dp)
                        }
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun AreaGuideScreen(
    data: AreaGuide.Data,
    onGoBack: () -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = data.e_name) },
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
        AreaGuideScreenContent(
            data = data,
            modifier = Modifier
                .padding(padding)
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("Area guide screen")
@Composable
fun AreaGuidePreview() {
    TaipeiCityZooTourTheme {
        Surface {
            AreaGuideScreen(
                data = areaData1,
                onGoBack = {}
            )
        }
    }
}
