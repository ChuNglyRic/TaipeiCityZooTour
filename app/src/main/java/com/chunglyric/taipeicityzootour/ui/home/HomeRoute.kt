package com.chunglyric.taipeicityzootour.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel
) {
    val uiStates by homeViewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(uiStates = uiStates)
}
