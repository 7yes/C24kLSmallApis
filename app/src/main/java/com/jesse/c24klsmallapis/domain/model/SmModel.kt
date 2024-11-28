package com.jesse.c24klsmallapis.domain.model

import com.google.gson.annotations.SerializedName
import com.jesse.c24klsmallapis.data.model.Relative
import com.jesse.c24klsmallapis.data.model.SmResponseItem

data class SmModel (
    val id:Int?,
    val name:String?,
    val age:String?,
    val image:String?,
    val firstEpisode:String?
)

fun SmResponseItem.toDomain() = SmModel(
    id = id,
    name = name,
    age = age,
    image = image,
    firstEpisode = firstEpisode
)