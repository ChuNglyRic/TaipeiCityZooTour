package com.chunglyric.taipeicityzootour.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.chunglyric.taipeicityzootour.data.AppContainer
import com.chunglyric.taipeicityzootour.ui.theme.TaipeiCityZooTourTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaipeiCityZooTourApp(
    appContainer: AppContainer
) {
    TaipeiCityZooTourTheme {
        val navController = rememberNavController()
        ModalNavigationDrawer(
            drawerContent = { ModalDrawerSheet {} },
            gesturesEnabled = false
        ) {
            TaipeiCityZooTourNavGraph(
                appContainer = appContainer,
                navController = navController
            )
        }
    }
}
