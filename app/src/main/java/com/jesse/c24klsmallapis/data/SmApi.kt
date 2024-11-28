package com.jesse.c24klsmallapis.data

import com.jesse.c24klsmallapis.data.model.SmResponse
import retrofit2.Response
import retrofit2.http.GET

interface SmApi {

    @GET("characters")
    suspend fun getData(): Response<SmResponse>
}