package com.example.tutorial.serviceapi.features.customer.services

import com.example.tutorial.serviceapi.features.customer.models.*
import com.example.tutorial.serviceapi.repositories.CustomerRepository
import com.example.tutorial.serviceapi.repositories.domains.CustomerEntity
import com.example.tutorial.serviceapi.repositories.models.CreateCustomerModel
import com.example.tutorial.serviceapi.repositories.models.UpdateAgeCustomerModel
import com.example.tutorial.serviceapi.utils.generateUUID
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CustomerService(

    private val customerRepository: CustomerRepository
) {

    companion object {
        private val logger = LoggerFactory.getLogger(CustomerService::class.java)
    }

    suspend fun getAllCustomer(): GetAllDataResponse {

        return GetAllDataResponse(
            data = customerRepository.getAllDataCustomer()
        )
    }

    suspend fun getCustomerById(request: GetCustomerByIdRequest): CustomerEntity {

        return customerRepository.getCustomerById(request.id)!!
    }

    suspend fun createCustomer(
        request: CreateCustomerRequest
    ): CustomerResponse {

        if (request.age == 0) return CustomerResponse(
            id = "",
            message = "age can't be 0."
        )

        val res: String
        try {
            val id = generateUUID()
            val createDate = LocalDateTime.now()
            customerRepository.createCustomer(
                CreateCustomerModel(
                    id = id,
                    name = request.name,
                    age = request.age,
                    createdDatetime = createDate
                )
            )
            res = id

        } catch (e: Exception) {
            throw e
        }

        return CustomerResponse(
            id = res,
            message = "success"
        )
    }

    suspend fun updateAgeCustomer(
        request: UpdateAgeCustomerRequest
    ): CustomerResponse {

        if (request.age == 0) return CustomerResponse(
            id = "",
            message = "age can't be 0."
        )
        try {

            val updateDate = LocalDateTime.now()
            customerRepository.updateAgeCustomer(
                UpdateAgeCustomerModel(
                    id = request.id,
                    age = request.age,
                    updatedDatetime = updateDate
                )
            )

        } catch (e: Exception) {
            throw e
        }

        return CustomerResponse(
            id = request.id,
            message = "success"
        )
    }

    suspend fun deleteCustomer(
        request: DeleteCustomerRequest
    ): CustomerResponse {

        if (request.id.isEmpty()) return CustomerResponse(
            id = "",
            message = "id is null or empty"
        )
        try {

            customerRepository.deleteCustomer(id = request.id)

        } catch (e: Exception) {
            throw e
        }

        return CustomerResponse(
            id = request.id,
            message = "success"
        )
    }
}