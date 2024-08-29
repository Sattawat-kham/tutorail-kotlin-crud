package com.example.tutorial.serviceapi.repositories.models

import java.time.LocalDateTime

data class UpdateAgeCustomerModel (

    var id: String,
    var age: Int,
    var updatedDatetime: LocalDateTime,
)