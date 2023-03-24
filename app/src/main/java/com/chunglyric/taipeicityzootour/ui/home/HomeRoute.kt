package com.chunglyric.taipeicityzootour.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel,
    navController: NavHostController
) {
    val uiStates by homeViewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiStates = uiStates,
        navController = navController
    )
}
