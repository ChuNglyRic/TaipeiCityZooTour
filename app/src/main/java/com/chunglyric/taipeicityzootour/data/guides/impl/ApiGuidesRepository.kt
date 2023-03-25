package com.chunglyric.taipeicityzootour.data.guides.impl

import com.chunglyric.taipeicityzootour.data.ResponseStatus
import com.chunglyric.taipeicityzootour.data.guides.GuidesRepository
import com.chunglyric.taipeicityzootour.data.parseResponse
import com.chunglyric.taipeicityzootour.model.AnimalGuide
import com.chunglyric.taipeicityzootour.model.AreaGuide
import com.chunglyric.taipeicityzootour.service.ApiService

class ApiGuidesRepository(private val apiService: ApiService) : GuidesRepository {
    override suspend fun getAreaGuide(): ResponseStatus<AreaGuide> {
        val response = apiService.getAreaGuide()
        return response.parseResponse()
    }

    override suspend fun getAnimalGuideCount(): ResponseStatus<AnimalGuide> {
        val response = apiService.getAnimalGuide(limit = 1)
        return response.parseResponse()
    }

    override suspend fun getAnimalGuide(limit: Int?): ResponseStatus<AnimalGuide> {
        val response = apiService.getAnimalGuide(limit)
        return response.parseResponse()
    }
}
