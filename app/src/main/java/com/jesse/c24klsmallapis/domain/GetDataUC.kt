package com.jesse.c24klsmallapis.domain

import com.jesse.c24klsmallapis.data.SmRepo
import com.jesse.c24klsmallapis.domain.model.SmModel
import javax.inject.Inject

class GetDataUC @Inject constructor(private val smRepo: SmRepo) {
    suspend operator fun invoke(): List<SmModel> = smRepo.getData()
}