package com.example.tutorial.serviceapi.repositories.models

import java.time.LocalDateTime

data class CreateCustomerModel(
    var id: String,
    var name: String,
    var age: Int,
    var createdDatetime: LocalDateTime,
)