package com.chunglyric.taipeicityzootour.data.guides

import com.chunglyric.taipeicityzootour.data.ResponseStatus
import com.chunglyric.taipeicityzootour.model.AnimalGuide
import com.chunglyric.taipeicityzootour.model.AreaGuide

interface GuidesRepository {
    suspend fun getAreaGuide(): ResponseStatus<AreaGuide>
    suspend fun getAnimalGuideCount(): ResponseStatus<AnimalGuide>
    suspend fun getAnimalGuide(limit: Int? = null): ResponseStatus<AnimalGuide>
}
