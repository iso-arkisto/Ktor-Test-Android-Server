package com.yourname.repository

import com.yourname.models.ApiResponse
import com.yourname.models.ProgrammingLanguage

interface MainRepository {
    val items: Map<Int, List<ProgrammingLanguage>>
    val page1: List<ProgrammingLanguage>
    val page2: List<ProgrammingLanguage>
    val page3: List<ProgrammingLanguage>

    suspend fun getAllLanguages(page: Int = 1): ApiResponse
    suspend fun searchLanguage(query: String?): ApiResponse
}