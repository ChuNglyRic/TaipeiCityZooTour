package com.chunglyric.taipeicityzootour.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chunglyric.taipeicityzootour.data.guides.impl.ApiGuidesRepository
import com.chunglyric.taipeicityzootour.service.ApiService
import com.chunglyric.taipeicityzootour.ui.home.HomeRoute
import com.chunglyric.taipeicityzootour.ui.home.HomeViewModel

@Composable
fun TaipeiCityZooTourNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = TaipeiCityZooTourDestinations.HOME_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(TaipeiCityZooTourDestinations.HOME_ROUTE) {
            val apiService = ApiService.getInstance()
            val apiGuidesRepository = ApiGuidesRepository(apiService)
            val homeViewModel = HomeViewModel(apiGuidesRepository)
            HomeRoute(homeViewModel = homeViewModel)
        }
    }
}
