package com.example.tutorial.serviceapi.features.product.services

import com.example.tutorial.serviceapi.features.product.models.*
import com.example.tutorial.serviceapi.repositories.ProductRepository
import com.example.tutorial.serviceapi.repositories.domains.ProductEntity
import com.example.tutorial.serviceapi.repositories.models.CreateProductModel
import com.example.tutorial.serviceapi.repositories.models.UpdatePriceProductModel
import com.example.tutorial.serviceapi.utils.generateUUID
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProductService (

    private val productRepository: ProductRepository
){

    companion object {
        private val logger = LoggerFactory.getLogger(ProductService::class.java)
    }

    suspend fun getAllProduct(): GetAllProductResponse {

        return GetAllProductResponse(
            data = productRepository.getAllDataProduct()
        )
    }

    suspend fun getProductById(request: GetProductByIdRequest): ProductEntity {

        return productRepository.getProductById(request.id)!!
    }

    suspend fun createProduct(
        request: CreateProductRequest
    ): ProductResponse {

        if (request.price == 0 && request.total == 0) return ProductResponse(
            id = "",
            message = "price and total can't be 0."
        )

        val res: String
        try {
            val id = generateUUID()
            val createDate = LocalDateTime.now()
            productRepository.createProduct(
                CreateProductModel(
                    id = id,
                    name = request.name,
                    price = request.price,
                    total = request.total,
                    createdDatetime = createDate
                )
            )
            res = id

        } catch (e: Exception) {
            throw e
        }

        return ProductResponse(
            id = res,
            message = "success"
        )
    }

    suspend fun updatePriceProduct(
        request: UpdatePriceProductRequest
    ): ProductResponse {

        try {

            val updateDate = LocalDateTime.now()
            productRepository.updatePriceProduct(
                UpdatePriceProductModel(
                    id = request.id,
                    price = request.price,
                    updatedDatetime = updateDate
                )
            )

        } catch (e: Exception) {
            throw e
        }

        return ProductResponse(
            id = request.id,
            message = "success"
        )
    }

    suspend fun deleteProduct(
        request: DeleteProductRequest
    ): ProductResponse {

        if (request.id.isEmpty()) return ProductResponse(
            id = "",
            message = "id is null or empty"
        )

        try {

            productRepository.deleteProduct(id = request.id)

        } catch (e: Exception) {
            throw e
        }

        return ProductResponse(
            id = request.id,
            message = "success"
        )
    }
}