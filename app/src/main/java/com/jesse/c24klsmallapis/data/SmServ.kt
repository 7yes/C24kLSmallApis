package com.jesse.c24klsmallapis.data

import com.jesse.c24klsmallapis.data.model.SmResponseItem
import javax.inject.Inject

class SmServ @Inject constructor(private val smApi: SmApi) {

    suspend fun getData(): List<SmResponseItem> {
        val call = smApi.getData()

        if (call.isSuccessful) {
            return call.body() ?: emptyList()
        }
        return emptyList()
    }
}