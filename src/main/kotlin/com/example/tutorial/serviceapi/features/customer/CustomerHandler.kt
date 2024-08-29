package com.example.tutorial.serviceapi.features.customer

import com.example.tutorial.serviceapi.dto.TransferRequest
import com.example.tutorial.serviceapi.features.customer.models.CreateCustomerRequest
import com.example.tutorial.serviceapi.features.customer.models.DeleteCustomerRequest
import com.example.tutorial.serviceapi.features.customer.models.GetCustomerByIdRequest
import com.example.tutorial.serviceapi.features.customer.models.UpdateAgeCustomerRequest
import com.example.tutorial.serviceapi.features.customer.services.CustomerService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.awaitBody
import com.example.tutorial.serviceapi.utils.coHandler
import com.example.tutorial.serviceapi.utils.transferResponseSuccess
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext

@Component
class CustomerHandler (

    private val customerService: CustomerService
){

    suspend fun getAllCustomer(request: ServerRequest) =
        request.coHandler {
                transferResponseSuccess(
                    request.headers(),
                    withContext(NonCancellable) {
                        customerService.getAllCustomer()
                    }
                )
        }

    suspend fun getCustomerById(request: ServerRequest) =
        request.coHandler {
            request.awaitBody<TransferRequest<GetCustomerByIdRequest>>().let {
                transferResponseSuccess(
                    request.headers(),
                    withContext(NonCancellable) {
                        customerService.getCustomerById(it.content)
                    }
                )
            }
        }

    suspend fun createCustomer(request: ServerRequest) =
        request.coHandler {
            request.awaitBody<TransferRequest<CreateCustomerRequest>>().let {
                transferResponseSuccess(
                    request.headers(),
                    withContext(NonCancellable) {
                        customerService.createCustomer(it.content)
                    }
                )
            }
        }

    suspend fun updateAgeCustomer(request: ServerRequest) =
        request.coHandler {
            request.awaitBody<TransferRequest<UpdateAgeCustomerRequest>>().let {
                transferResponseSuccess(
                    request.headers(),
                    withContext(NonCancellable) {
                        customerService.updateAgeCustomer(it.content)
                    }
                )
            }
        }

    suspend fun deleteCustomer(request: ServerRequest) =
        request.coHandler {
            request.awaitBody<TransferRequest<DeleteCustomerRequest>>().let {
                transferResponseSuccess(
                    request.headers(),
                    withContext(NonCancellable) {
                        customerService.deleteCustomer(it.content)
                    }
                )
            }
        }
}