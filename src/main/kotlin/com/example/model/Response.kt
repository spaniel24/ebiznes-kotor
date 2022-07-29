package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Response(val recipient: Recipient, val message: String)