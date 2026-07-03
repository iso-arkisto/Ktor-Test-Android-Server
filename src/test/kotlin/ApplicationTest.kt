package com.yourname

import com.yourname.models.ApiResponse
import com.yourname.repository.MainRepository
import com.yourname.repository.MainRepositoryImpl
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.cio.Response
import io.ktor.http.headers
import io.ktor.server.testing.testApplication
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ApplicationTest {
    @Test
    fun `access route endpoint`() = testApplication {
        application {
            module()
        }

        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello, Ktor!", bodyAsText())
        }
    }

    private val mainRepository = MainRepositoryImpl()

//    @Test
//    fun `access all languages endpoint, query all pages, assert correct information`() = testApplication {
//        application {
//            module()
//        }
//
//        val languageByPage = listOf(
//            mainRepository.page1,
//            mainRepository.page2,
//            mainRepository.page3
//        )
//
//        (1..3).forEach { page ->
//            client.get("storage/languages?page=$page").apply {
//                assertEquals(HttpStatusCode.OK, status)
//            }
//        }
//
//
//    }

    private val json = Json { ignoreUnknownKeys = true }

    private suspend fun io.ktor.client.HttpClient.getApiResponse(path: String): ApiResponse {
        val body = get(path) {
            headers.append(HttpHeaders.Accept, ContentType.Application.Json.toString())
        }.bodyAsText()

        return json.decodeFromString(ApiResponse.serializer(), body)
    }

    @Test
    fun `get all languages default page returns three items`() = testApplication {
        application { module() }

        val response = client.getApiResponse("/storage/languages")

        assertTrue(response.success)
        assertEquals(3, response.items.size)
        assertEquals("Python", response.items[0].name)
        assertEquals("Kotlin", response.items[1].name)
        assertEquals("Java", response.items[2].name)
    }

    @Test
    fun `get all languages page one has next page only`() = testApplication {
        application { module() }

        val response = client.getApiResponse("/storage/languages?page=1")

        assertNull(response.prevPage)

        val nextPage = response.nextPage

        assertEquals(2, nextPage)
    }

    @Test
    fun `get all languages second page has both pagination links`() = testApplication {
        application { module() }

        val response = client.getApiResponse("/storage/languages?page=2")

        assertEquals(1, response.prevPage)
        assertEquals(3, response.nextPage)
        assertEquals(mainRepository.page2, response.items)
    }

    @Test
    fun `get all languages out of range page returns error`() = testApplication {
        application { module() }

        val response = client.getApiResponse("/storage/languages?page=4")

        assertEquals(false, response.success)
        assertEquals("Server has only 3 pages", response.message)
    }

    @Test
    fun `get all languages out of number page returns error`() = testApplication {
        application { module() }

        val response = client.getApiResponse("/storage/languages?page=kotlni")

        assertEquals(false, response.success)
        assertEquals("Only numbers allowed for this request", response.message)
    }

    @Test
    fun `search language by kotlin return single match`() = testApplication {
        application { module() }

        val response = client.getApiResponse("/storage/languages/search?name=kot")

        assertEquals(true, response.success)
        assertEquals("Kotlin", response.items[0].name)
    }
}
