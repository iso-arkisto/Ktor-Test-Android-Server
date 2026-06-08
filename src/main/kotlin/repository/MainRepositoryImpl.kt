package com.yourname.repository

import com.yourname.models.ApiResponse
import com.yourname.models.ProgrammingLanguage

const val NEXT_PAGE = "next_page" // next page key
const val PREVIOUS_PAGE = "previous_page" // prev page key

class MainRepositoryImpl: MainRepository {
    override val items: Map<Int, List<ProgrammingLanguage>> by lazy {
        mapOf(
            1 to page1,
            2 to page2,
            3 to page3
        )
    }

    override val page1: List<ProgrammingLanguage> = listOf(
        ProgrammingLanguage(1,"py","/images/python.png","Guido van Rossum",1991,"Python"),
        ProgrammingLanguage(2,"kt","/images/kotlin.png","JetBrains",2011,"Kotlin"),
        ProgrammingLanguage(3,"java","/images/java.png","James Gosling",1995,"Java")
    )

    override val page2: List<ProgrammingLanguage> = listOf(
        ProgrammingLanguage(4,"go","/images/go.png","Robert Griesemer, Rob Pike, Ken Thompson",2009,"Go"),
        ProgrammingLanguage(5,"cs","/images/c#.png","Anders Hejlsberg",2000,"C#"),
        ProgrammingLanguage(6,"php","/images/php.png","Rasmus Lerdorf",1995,"PHP")
    )

    override val page3: List<ProgrammingLanguage> = listOf(
        ProgrammingLanguage(7,"rs","/images/rust.jpg","Graydon Hoare",2010,"Rust"),
        ProgrammingLanguage(8,"rb","/images/ruby.png","Yukihiro Matsumoto",1995,"Ruby"),
        ProgrammingLanguage(9,"js","/images/js.png","Brendan Eich",1995,"JavaScript")
    )

    override suspend fun getAllLanguages(page: Int): ApiResponse {
        return ApiResponse(
            success = true,
            message = "OK",
            prevPage = calculatePage(page)[PREVIOUS_PAGE],
            nextPage = calculatePage(page)[NEXT_PAGE],
            items = items[page] ?: emptyList(),
            lastUpdate = System.currentTimeMillis()
        )
    }

    override suspend fun searchLanguage(query: String?): ApiResponse {
        return ApiResponse(
            success = true,
            message = "OK",
            items = findLanguage(query)
        )
    }

    private fun calculatePage(page: Int): Map<String, Int?> {
        var prevPage: Int? = page
        var nextPage: Int? = page

       if(page in 1..2) {
           nextPage = nextPage?.plus(1)
       }

        if(page in 2..3) {
            prevPage = prevPage?.minus(1)
        }

        if(page == 1) {
            prevPage = null
        }

        if(page == 3) {
            nextPage = null
        }

        return mapOf(
            PREVIOUS_PAGE to prevPage,
            NEXT_PAGE to nextPage,
        )
    }

    private fun findLanguage(query: String?): List<ProgrammingLanguage> {
        val found = mutableListOf<ProgrammingLanguage>()

        return if(!query.isNullOrEmpty()) {
            items.forEach { _, languagesOnPage ->
                languagesOnPage.forEach { language ->
                    if(language.name.lowercase().contains(query.lowercase())) {
                        found.add(language)
                    }
                }
            }

            found
        } else {
            emptyList()
        }


    }

}