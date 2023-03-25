package com.chunglyric.taipeicityzootour.data

import com.chunglyric.taipeicityzootour.data.guides.impl.ApiGuidesRepository
import com.chunglyric.taipeicityzootour.model.GuidesCache
import com.chunglyric.taipeicityzootour.service.ApiService

interface AppContainer {
    val apiGuidesRepository: ApiGuidesRepository
    val guidesCache: GuidesCache
}

class AppContainerImpl : AppContainer {
    override val apiGuidesRepository: ApiGuidesRepository by lazy {
        val apiService = ApiService.getInstance()
        ApiGuidesRepository(apiService)
    }

    override val guidesCache: GuidesCache by lazy {
        GuidesCache()
    }
}
