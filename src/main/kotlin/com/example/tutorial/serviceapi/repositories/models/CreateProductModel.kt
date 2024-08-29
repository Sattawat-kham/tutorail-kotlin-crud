package com.example.tutorial.serviceapi.repositories.models

import java.time.LocalDateTime

data class CreateProductModel (

    var id: String,
    var name: String,
    var price: Int,
    var total: Int,
    var createdDatetime: LocalDateTime,
)