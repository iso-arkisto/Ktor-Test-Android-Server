package com.yourname.routes

import com.yourname.repository.MainRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.searchLanguages(
    mainRepository: MainRepository
) {
    get("/storage/languages/search") {
        val name = call.request.queryParameters["name"]
        val apiResponse = mainRepository.searchLanguage(name)

        call.respond(
            message = apiResponse,
            status = HttpStatusCode.OK
        )
    }
}