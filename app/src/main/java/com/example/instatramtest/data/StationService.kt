package com.example.instatramtest.data

import retrofit2.http.GET
import retrofit2.Response

interface StationService {
    @GET("/b/6090a297d64cd16802a8e804")
    suspend fun getStationData(): Response<List<Station>>
}