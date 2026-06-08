package com.yourname.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val items: List<ProgrammingLanguage> = emptyList(),
    val lastUpdate: Long? = null
)