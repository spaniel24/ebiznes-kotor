package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class MessageRequest(val `object`: String, val entry: List<Entry>)