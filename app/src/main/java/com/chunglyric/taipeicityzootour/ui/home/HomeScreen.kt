package com.chunglyric.taipeicityzootour.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.chunglyric.taipeicityzootour.R
import com.chunglyric.taipeicityzootour.ui.TaipeiCityZooTourDestinations
import com.chunglyric.taipeicityzootour.ui.utils.CenterLoading

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

@ExperimentalMaterialApi
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    uiStates: HomeUiStates,
    navController: NavHostController,
    onRefresh: () -> Unit
) {
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
            emptyContent = {
                CenterLoading()
            },
            content = {
                if (uiStates is HomeUiStates.HasData) {
                    var refreshing by remember { mutableStateOf(false) }
                    val state = rememberPullRefreshState(
                        refreshing = refreshing,
                        onRefresh = {
                            refreshing = true
                            onRefresh()
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
                            items(uiStates.guidesCache.areaData ?: emptyList()) { item ->
                                AreaGuideCard(
                                    data = item,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .clickable {
                                            navController.navigate("${TaipeiCityZooTourDestinations.AREA_GUIDE_ROUTE}/$item")
                                        }
                                )
                                if (item != uiStates.guidesCache.areaData?.last()) Divider(thickness = 2.dp)
                            }
                        }

                        PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        Text(
                            modifier = Modifier.padding(padding),
                            text = stringResource(id = R.string.reversed)
                        )
                    }
                }
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
        CenterLoading()
    }
}
