package com.yourname.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.root() {
    get("/") {
        call.respond(
            message = "Hello, Ktor!",
            status = HttpStatusCode.OK
        )
    }
}