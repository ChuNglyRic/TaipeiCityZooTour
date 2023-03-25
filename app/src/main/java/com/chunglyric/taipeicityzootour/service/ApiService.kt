package com.chunglyric.taipeicityzootour.service

import com.chunglyric.taipeicityzootour.model.AnimalGuide
import com.chunglyric.taipeicityzootour.model.AreaGuide
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    suspend fun getAreaGuide(): Response<AreaGuide>

    @GET("a3e2b221-75e0-45c1-8f97-75acbd43d613?scope=resourceAquire")
    suspend fun getAnimalGuide(@Query("limit") limit: Int?): Response<AnimalGuide>

    companion object {
        var apiService: ApiService? = null
        fun getInstance(): ApiService {
            if (apiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://data.taipei/api/v1/dataset/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                apiService = retrofit.create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}
