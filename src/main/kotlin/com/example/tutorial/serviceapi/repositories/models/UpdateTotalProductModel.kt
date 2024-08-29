package com.example.tutorial.serviceapi.repositories.models

import java.time.LocalDateTime

data class UpdateTotalProductModel (

    var id: String,
    var total: Int,
    var updatedDatetime: LocalDateTime,
)