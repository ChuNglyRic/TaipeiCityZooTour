package com.chunglyric.taipeicityzootour.data

import com.chunglyric.taipeicityzootour.data.guides.impl.ApiGuidesRepository
import com.chunglyric.taipeicityzootour.service.ApiService

interface AppContainer {
    val apiGuidesRepository: ApiGuidesRepository
}

class AppContainerImpl : AppContainer {
    override val apiGuidesRepository: ApiGuidesRepository by lazy {
        val apiService = ApiService.getInstance()
        ApiGuidesRepository(apiService)
    }
}
