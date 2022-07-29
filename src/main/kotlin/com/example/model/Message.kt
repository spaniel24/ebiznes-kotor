package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(val mid: String, val text: String)