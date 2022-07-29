package com.example.route

import com.example.model.categoriesList
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.categories() {
    route("/categories") {
        get {
            call.respond(categoriesList)
        }
    }
}