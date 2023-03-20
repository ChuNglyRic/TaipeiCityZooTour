package com.chunglyric.taipeicityzootour.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.chunglyric.taipeicityzootour.ui.theme.TaipeiCityZooTourTheme

@Composable
fun TaipeiCityZooTourApp() {
    TaipeiCityZooTourTheme {
        val navController = rememberNavController()
        TaipeiCityZooTourNavGraph(
            navController = navController,
        )
    }
}
