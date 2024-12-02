package com.jesse.c24klsmallapis.data

import com.jesse.c24klsmallapis.data.model.SampleResponse
import retrofit2.Response
import retrofit2.http.GET

interface SampleApi {

    @GET("characters")
    suspend fun getData(): Response<SampleResponse>

}