package com.example.tutorial.serviceapi.features.process.services

import com.example.tutorial.serviceapi.features.process.models.BuyProductRequest
import com.example.tutorial.serviceapi.features.process.models.GetBuyProductRequest
import com.example.tutorial.serviceapi.features.process.models.GetBuyProductResponse
import com.example.tutorial.serviceapi.repositories.CustomerRepository
import com.example.tutorial.serviceapi.repositories.ProcessRepository
import com.example.tutorial.serviceapi.repositories.ProductRepository
import com.example.tutorial.serviceapi.repositories.models.BuyProductModel
import com.example.tutorial.serviceapi.repositories.models.UpdateTotalProductModel
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProcessService (

    private val customerRepository: CustomerRepository,
    private val productRepository: ProductRepository,
    private val processRepository: ProcessRepository
){

    companion object {
        private val logger = LoggerFactory.getLogger(ProcessService::class.java)
    }

    suspend fun buyProduct(
        request: BuyProductRequest
    ): String {

        try {

            customerRepository.getCustomerById(request.customerId) ?: return "Customer is empty"
            val getProduct = productRepository.getProductById(request.productId) ?: return "Product is empty"

            return if (request.amount < getProduct.total){
                processRepository.buyProduct(
                    BuyProductModel(
                        customerId = request.customerId,
                        productId = request.productId,
                        amount = request.amount
                    )
                )

                val total = getProduct.total - request.amount

                val updateDate = LocalDateTime.now()
                productRepository.updateTotalProduct(UpdateTotalProductModel(
                    id = request.productId,
                    total = total,
                    updatedDatetime = updateDate
                ))

                "success"

            }else{
                "Insufficient inventory"
            }
            

        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getBuyProduct(
        request: GetBuyProductRequest
    ): Any {

        try {

            val product: MutableList<String> = mutableListOf()
            val getCustomer = customerRepository.getCustomerById(request.customerId) ?: return "Customer is empty"

            processRepository.getBuyProductByCustomerId(request.customerId).let {
                it.forEach { it1 ->
                    val productName = productRepository.getProductById(it1.productId)!!.name
                    product.add(productName)
                }
            }

            return GetBuyProductResponse(
                customerName = getCustomer.name,
                product = product
            )

        } catch (e: Exception) {
            throw e
        }
    }
}