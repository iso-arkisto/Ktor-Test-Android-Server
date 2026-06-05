package com.yourname
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