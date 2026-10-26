package com.yourname.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateLanguage(
    val shortName: String,
    val creator: String,
    val inceptionYear: Int,
    val name: String,
    val rating: Double,
    val description: String
)
