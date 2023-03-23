package com.chunglyric.taipeicityzootour.data.guides

import com.chunglyric.taipeicityzootour.data.ResponseStatus
import com.chunglyric.taipeicityzootour.model.AreaGuide

interface GuidesRepository {
    suspend fun getAreaGuide(): ResponseStatus<AreaGuide>
}
