package com.jesse.c24klsmallapis.data

import com.jesse.c24klsmallapis.domain.model.SampleModel
import com.jesse.c24klsmallapis.domain.model.toDomain
import javax.inject.Inject

class SampleRepository @Inject constructor(private val service: SampleServ){
    suspend fun getData(): List<SampleModel> {
       return service.getData().map { it.toDomain() }
    }
}