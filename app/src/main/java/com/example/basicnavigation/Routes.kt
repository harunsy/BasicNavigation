package com.example.basicnavigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Profile

@Serializable
object Detail

@Serializable
object OrderForm

@Serializable
data class OrderDetails(
    val productName: String,
    val quantity: Int,
    val price: Double
)
