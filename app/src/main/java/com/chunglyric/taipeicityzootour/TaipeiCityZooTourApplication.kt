package com.chunglyric.taipeicityzootour

import android.app.Application
import com.chunglyric.taipeicityzootour.data.AppContainer
import com.chunglyric.taipeicityzootour.data.AppContainerImpl

class TaipeiCityZooTourApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl()
    }
}
