package com.example.tutorial.serviceapi.repositories.models

import java.time.LocalDateTime

data class UpdatePriceProductModel (

    var id: String,
    var price: Int,
    var updatedDatetime: LocalDateTime,
)
