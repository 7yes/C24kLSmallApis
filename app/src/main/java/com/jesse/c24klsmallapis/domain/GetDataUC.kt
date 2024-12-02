package com.jesse.c24klsmallapis.domain

import com.jesse.c24klsmallapis.data.SampleRepository
import javax.inject.Inject

class GetDataUC  @Inject constructor(private val repository: SampleRepository) {
    suspend operator fun invoke() = repository.getData()
}