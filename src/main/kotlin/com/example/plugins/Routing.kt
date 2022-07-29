package com.example.plugins

import com.example.route.categories
import com.example.route.messenger
import com.example.route.products
import io.ktor.server.routing.*
import io.ktor.server.application.*


fun Application.configureRouting() {
    routing {
        categories()
        products()
        messenger()
    }
}
