package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(val category: String, val name: String, val description: String, val price: Int)

val productsList = listOf(
    Product("Headphones", "Sluchawki 1", "Opis sluchawek 1", 300),
    Product("Headphones", "Sluchawki 2", "Opis sluchawek 2", 200),
    Product("Headphones", "Sluchawki 3", "Opis sluchawek 3", 100),
    Product("Headphones", "Sluchawki 4", "Opis sluchawek 4", 800),
    Product("Keyboard", "Klawiatura 1", "Opis klawiatury 1", 300),
    Product("Keyboard", "Klawiatura 2", "Opis klawiatury 2", 350),
    Product("Mouse", "Myszka 1", "Opis myszki 1", 1350),
    Product("Mouse", "Myszka 2", "Opis myszki 2", 1350),
    Product("Notebook", "Laptop 1", "Opis laptopa 1", 3000),
    Product("PC", "Komputer 1", "Opis komputera 1", 7000),
)