package com.yourname

import com.yourname.plugins.configureDefaultHeaders
import com.yourname.plugins.configureKoin
import com.yourname.plugins.configureMonitoring
import com.yourname.plugins.configureSerialization
import com.yourname.plugins.configureStatusPages
import io.ktor.server.application.*

fun Application.module() {
        configureKoin()
        configureSerialization()
        configureMonitoring()
        configureRouting()
        configureDefaultHeaders()
        configureStatusPages()
}