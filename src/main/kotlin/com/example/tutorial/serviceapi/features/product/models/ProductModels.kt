package com.example.tutorial.serviceapi.features.product.models

import com.example.tutorial.serviceapi.repositories.domains.ProductEntity

data class GetAllProductResponse(
    val data: MutableList<ProductEntity>?
)

data class GetProductByIdRequest(

    val id: String
)

data class CreateProductRequest(

    val name: String,
    val price: Int,
    val total: Int,
)

data class UpdatePriceProductRequest(

    val id: String,
    val price: Int,
)

data class DeleteProductRequest(

    val id: String
)

data class ProductResponse(

    val id: String,
    val message: String
)