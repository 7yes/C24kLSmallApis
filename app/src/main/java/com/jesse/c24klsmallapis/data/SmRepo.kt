package com.jesse.c24klsmallapis.data

import com.jesse.c24klsmallapis.data.model.SmResponseItem
import com.jesse.c24klsmallapis.domain.model.SmModel
import com.jesse.c24klsmallapis.domain.model.toDomain
import javax.inject.Inject

class SmRepo @Inject constructor(private val smServ: SmServ) {

    suspend fun getData(): List<SmModel> {
        return smServ.getData().map { it.toDomain() }
    }

}