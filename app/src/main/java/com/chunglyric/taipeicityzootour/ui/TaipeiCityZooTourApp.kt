package com.chunglyric.taipeicityzootour.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.chunglyric.taipeicityzootour.data.AppContainer
import com.chunglyric.taipeicityzootour.ui.theme.TaipeiCityZooTourTheme

@Composable
fun TaipeiCityZooTourApp(
    appContainer: AppContainer
) {
    TaipeiCityZooTourTheme {
        val navController = rememberNavController()
        TaipeiCityZooTourNavGraph(
            appContainer = appContainer,
            navController = navController
        )
    }
}
