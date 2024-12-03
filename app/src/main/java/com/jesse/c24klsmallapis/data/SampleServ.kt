package com.jesse.c24klsmallapis.data

import com.jesse.c24klsmallapis.data.model.SampleResponseItem
import javax.inject.Inject

class SampleServ @Inject constructor(private val api: SampleApi) {
    suspend fun getData(): List<SampleResponseItem> {
        val call = api.getData()
        return if (call.isSuccessful) {
            call.body() ?: emptyList()
        }
        else emptyList()
    }
}