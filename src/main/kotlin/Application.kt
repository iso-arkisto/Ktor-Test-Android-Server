package com.yourname

import com.yourname.plugins.configureDefaultHeaders
import com.yourname.plugins.configureKoin
import com.yourname.plugins.configureMonitoring
import com.yourname.plugins.configureSerialization
import com.yourname.plugins.configureStatusPages
import io.ktor.http.ContentType
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

//
//import io.ktor.server.application.Application
//
//fun Application.rootModule() {
//    configureRouting()
//}
//

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args) // enginemain - класс библиотеки ktor, который запускает веб сервер с использованием движка Netty
}

fun Application.module() {
        configureKoin()
        configureSerialization()
        configureMonitoring()
        configureRouting()
        configureDefaultHeaders()
        configureStatusPages()
}