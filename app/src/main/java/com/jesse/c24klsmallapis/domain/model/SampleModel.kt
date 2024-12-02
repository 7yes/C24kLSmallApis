package com.jesse.c24klsmallapis.domain.model

import com.jesse.c24klsmallapis.data.model.SampleResponseItem

data class SampleModel(
    val id:Int,
    val image: String,
    val name: String,
    val hair: String?,
    val voicedBy: String?,
    val wikiUrl: String,
)

fun SampleResponseItem.toDomain() = SampleModel(
    id = id,
    image = image,
    name = name,
    hair = hair,
    voicedBy = voicedBy,
    wikiUrl = wikiUrl
)
