package com.example.route

import com.example.model.productsList
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.products() {
    get("/products/{category?}") {
        val category = call.parameters["category"] ?: return@get call.respondText("Category not given", status = HttpStatusCode.BadRequest)
        val products = productsList.filter { it.category == category }
        call.respond(products)
    }
}