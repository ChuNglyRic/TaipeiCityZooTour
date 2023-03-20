package com.chunglyric.taipeicityzootour.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chunglyric.taipeicityzootour.ui.home.HomeScreen

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
            HomeScreen()
        }
    }
}
