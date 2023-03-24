package com.chunglyric.taipeicityzootour.ui

import android.os.Build
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.chunglyric.taipeicityzootour.data.AppContainer
import com.chunglyric.taipeicityzootour.data.guides.impl.areaGuide1
import com.chunglyric.taipeicityzootour.model.AreaGuide
import com.chunglyric.taipeicityzootour.model.AreaGuideType
import com.chunglyric.taipeicityzootour.ui.area.AreaGuideScreen
import com.chunglyric.taipeicityzootour.ui.home.HomeRoute
import com.chunglyric.taipeicityzootour.ui.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaipeiCityZooTourNavGraph(
    appContainer: AppContainer,
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
            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.provideFactory(appContainer.apiGuidesRepository)
            )

            HomeRoute(
                homeViewModel = homeViewModel,
                navController = navController
            )
        }

        composable(
            route = "${TaipeiCityZooTourDestinations.AREA_GUIDE_ROUTE}/{areaGuide}",
            arguments = listOf(navArgument("areaGuide") { type = AreaGuideType() })
        ) { navBackStackEntry ->
            val areaGuide = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                navBackStackEntry.arguments?.getParcelable("areaGuide", AreaGuide.Metadata.Guide::class.java)
            } else {
                @Suppress("DEPRECATION") navBackStackEntry.arguments?.getParcelable("areaGuide")
            }

            AreaGuideScreen(
                guide = areaGuide ?: areaGuide1,
                onGoBack = { navController.popBackStack() }
            )
        }
    }
}
