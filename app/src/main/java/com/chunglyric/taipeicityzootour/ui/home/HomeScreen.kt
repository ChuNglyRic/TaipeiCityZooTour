package com.chunglyric.taipeicityzootour.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.chunglyric.taipeicityzootour.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopAppBar(
    topAppBarState: TopAppBarState = rememberTopAppBarState(),
    scrollBehavior: TopAppBarScrollBehavior? = TopAppBarDefaults.enterAlwaysScrollBehavior(
        topAppBarState
    )
) {
    val title = stringResource(id = R.string.app_name)
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(R.string.drawer)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun LoadingContent(
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    if (empty) {
        emptyContent()
    } else {
        content()
    }
}

@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(uiStates: HomeUiStates) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)

    Scaffold(
        topBar = {
            HomeTopAppBar(topAppBarState = topAppBarState)
        }
    ) { padding ->
        LoadingContent(
            empty = when (uiStates) {
                is HomeUiStates.HasData -> false
                is HomeUiStates.NoData -> uiStates.isLoading
            },
            emptyContent = { FullScreenLoading() },
            content = {
                Text(
                    modifier = Modifier
                        .padding(padding)
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                    text = stringResource(id = R.string.reversed)
                )
            }
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview("Full Screen Loading")
@Composable
fun FullScreenLoadingPreview() {
    Scaffold(
        topBar = {
            HomeTopAppBar()
        }
    ) {
        FullScreenLoading()
    }
}
