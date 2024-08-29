package com.example.tutorial.serviceapi.features.customer.models

import com.example.tutorial.serviceapi.repositories.domains.CustomerEntity

data class GetAllDataResponse(
    val data: MutableList<CustomerEntity>?
)

data class GetCustomerByIdRequest(

    val id: String
)

data class CreateCustomerRequest(

    val name: String,
    val age: Int,
)

data class UpdateAgeCustomerRequest(

    val id: String,
    val age: Int,
)

data class DeleteCustomerRequest(

    val id: String
)

data class CustomerResponse(

    val id: String,
    val message: String
)
