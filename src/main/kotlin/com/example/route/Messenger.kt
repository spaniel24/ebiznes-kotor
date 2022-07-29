package com.example.route

import com.example.model.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.messenger() {
    route("/webhook") {

        post {
            val messageRequest = call.receive<MessageRequest>()

            if (messageRequest.`object` == "page") {
                for (entry in messageRequest.entry) {
                    handleMessage(entry.messaging[0])
                }

                call.respondText("EVENT_RECEIVED", status = HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        get {

            val mode = call.request.queryParameters["hub.mode"]
            val token = call.request.queryParameters["hub.verify_token"]
            val challenge = call.request.queryParameters["hub.challenge"]

            if (null != mode && null != token) {
                if (mode == "subscribe" && token == "") {
                    call.respondText(challenge!!, status = HttpStatusCode.OK)
                }
            } else {
                call.respond(HttpStatusCode.Forbidden)
            }
        }
    }
}

suspend fun handleMessage(messaging: Messaging) {
    val senderPsid = messaging.sender.id
    val text = messaging.message.text
    val responseMessage = getResponseMessage(text)

    callSendApi(senderPsid, responseMessage)
}

fun getResponseMessage(text: String): String {
    if (text == "/Categories") {

        var categoriesResponse = "Categories: "
        categoriesList.forEach {
            categoriesResponse += (it.name + "\n")
        }
        return categoriesResponse

    } else if (text.startsWith("/")) {

        val matchingCategory = categoriesList.find { it.name == text.substring(1) }

        return if (null != matchingCategory) {

            var productsResponse = "Products from category ${matchingCategory.name}:\n"
            productsList.forEach {
                if (it.name == matchingCategory.name) {
                    productsResponse += (it.name + ", price: " + it.price + "\n ")
                }
            }
            productsResponse

        } else {
            "wrong command"
        }
    } else {
        return "Instructions: \n /Category for categories \n /category-name for products listed for the category-name"
    }
}

suspend fun callSendApi(senderPsid: String, responseMessage: String) {

    val response = Response(Recipient(senderPsid), responseMessage)
    val responseJson = """{"recipient":{"id":${senderPsid}},"message":{"text":"$responseMessage"}}"""
    val httpClient = HttpClient()

    httpClient.request("https://graph.facebook.com/v14.0/me/messages") {
        method = HttpMethod.Post
        url {
            parameters.append("access_token", "")
        }
        contentType(ContentType.Application.Json)
        setBody(responseJson)
    }

    httpClient.close()
}
