package com.yourname

import com.yourname.repository.MainRepository
import com.yourname.routes.getAllLanguages
import com.yourname.routes.root
import com.yourname.routes.searchLanguages
import io.ktor.server.application.*
import io.ktor.server.http.content.resources
import io.ktor.server.http.content.static
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val mainRepository: MainRepository by inject()

    routing {
        root()
        getAllLanguages(mainRepository)
        searchLanguages(mainRepository)

        static(
            "/images"
        ) {
            resources("images")
        }
    }
}