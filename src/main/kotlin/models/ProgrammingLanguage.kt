package com.yourname.models

import kotlinx.serialization.Serializable

@Serializable
data class ProgrammingLanguage(
    val id: Int,
    val shortName: String,
    val image: String,
    val creator: String,
    val inceptionYear: Int,
    val name: String
)
