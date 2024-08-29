package com.example.tutorial.serviceapi.features.product

import com.example.tutorial.serviceapi.dto.TransferRequest
import com.example.tutorial.serviceapi.features.product.models.CreateProductRequest
import com.example.tutorial.serviceapi.features.product.models.DeleteProductRequest
import com.example.tutorial.serviceapi.features.product.models.GetProductByIdRequest
import com.example.tutorial.serviceapi.features.product.models.UpdatePriceProductRequest
import com.example.tutorial.serviceapi.features.product.services.ProductService
import com.example.tutorial.serviceapi.utils.coHandler
import com.example.tutorial.serviceapi.utils.transferResponseSuccess
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.awaitBody

@Component
class ProductHandler (

    private val productService: ProductService
){

    suspend fun getAllProduct(request: ServerRequest) =
        request.coHandler {
            transferResponseSuccess(
                request.headers(),
                withContext(NonCancellable) {
                    productService.getAllProduct()
                }
            )
        }

    suspend fun getProductById(request: ServerRequest) =
        request.coHandler {
            request.awaitBody<TransferRequest<GetProductByIdRequest>>().let {
                transferResponseSuccess(
                    request.headers(),
                    withContext(NonCancellable) {
                        productService.getProductById(it.content)
                    }
                )
            }
        }

    suspend fun createCustomer(request: ServerRequest) =
        request.coHandler {
            request.awaitBody<TransferRequest<CreateProductRequest>>().let {
                transferResponseSuccess(
                    request.headers(),
                    withContext(NonCancellable) {
                        productService.createProduct(it.content)
                    }
                )
            }
        }

    suspend fun updateAgeCustomer(request: ServerRequest) =
        request.coHandler {
            request.awaitBody<TransferRequest<UpdatePriceProductRequest>>().let {
                transferResponseSuccess(
                    request.headers(),
                    withContext(NonCancellable) {
                        productService.updatePriceProduct(it.content)
                    }
                )
            }
        }

    suspend fun deleteCustomer(request: ServerRequest) =
        request.coHandler {
            request.awaitBody<TransferRequest<DeleteProductRequest>>().let {
                transferResponseSuccess(
                    request.headers(),
                    withContext(NonCancellable) {
                        productService.deleteProduct(it.content)
                    }
                )
            }
        }
}