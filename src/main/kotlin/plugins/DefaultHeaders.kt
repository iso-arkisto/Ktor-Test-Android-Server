package com.yourname.plugins

import io.ktor.http.HttpHeaders
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.defaultheaders.DefaultHeaders
import kotlin.time.Duration.Companion.days

fun Application.configureDefaultHeaders() {
    install(DefaultHeaders) {
        val oneYearInSeconds = 365.days.inWholeSeconds
        header(HttpHeaders.CacheControl, "public, max-age=$oneYearInSeconds")
    }
}