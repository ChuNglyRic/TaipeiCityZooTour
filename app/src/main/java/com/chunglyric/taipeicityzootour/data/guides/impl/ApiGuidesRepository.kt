package com.chunglyric.taipeicityzootour.data.guides.impl

import com.chunglyric.taipeicityzootour.data.ResponseStatus
import com.chunglyric.taipeicityzootour.data.guides.GuidesRepository
import com.chunglyric.taipeicityzootour.data.parseResponse
import com.chunglyric.taipeicityzootour.model.AreaGuide
import com.chunglyric.taipeicityzootour.service.ApiService

class ApiGuidesRepository(private val apiService: ApiService) : GuidesRepository {
    override suspend fun getAreaGuide(): ResponseStatus<AreaGuide> {
        val response = apiService.getAreaGuide()
        return response.parseResponse()
    }
}
