package com.yourname.routes

import com.yourname.models.ApiResponse
import com.yourname.repository.MainRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.getAllLanguages(
    mainRepository: MainRepository
) {
    get("/storage/languages") {
     try {
         val page = call.request.queryParameters["page"]?.toInt() ?: 1

         require(page in 1..3)

         val apiResponse = mainRepository.getAllLanguages(page)

         call.respond(
             message = apiResponse,
             status = HttpStatusCode.OK
         )
     }  catch (e: NumberFormatException) {
         call.respond(
             message = ApiResponse(
                 success = false,
                 message = "Only numbers allowed for this request"
             )
         )
     } catch (e: IllegalArgumentException) {
         call.respond(
             message = ApiResponse(
                 success = false,
                 message = "Server has only 3 pages"
             )
         )
     }
    }
}