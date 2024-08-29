package com.example.tutorial.serviceapi.features.process.models

data class BuyProductRequest(

    val customerId: String,
    val productId: String,
    val amount: Int,
)

data class GetBuyProductRequest(

    val customerId: String
)

data class GetBuyProductResponse(
    val customerName: String,
    val product: MutableList<String>?
)