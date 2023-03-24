package com.chunglyric.taipeicityzootour

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.chunglyric.taipeicityzootour.ui.TaipeiCityZooTourApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val appContainer = (application as TaipeiCityZooTourApplication).container
        setContent {
            TaipeiCityZooTourApp(appContainer = appContainer)
        }
    }
}
