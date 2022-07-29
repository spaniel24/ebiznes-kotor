package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Category(val id: Int, val name: String)

val categoriesList = listOf(
    Category(1, "Headphones"),
    Category(2, "Keyboard"),
    Category(3, "Mouse"),
    Category(4, "Notebook"),
    Category(5, "PC")
)