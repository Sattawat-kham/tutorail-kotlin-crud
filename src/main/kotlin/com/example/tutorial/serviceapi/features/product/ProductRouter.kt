package com.example.tutorial.serviceapi.features.product

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ProductRouter {

    @Bean
    fun pRouter(handler: ProductHandler) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            GET("/getAllProduct", handler::getAllProduct)
            GET("/getProductById", handler::getProductById)
            POST("/createProduct", handler::createCustomer)
            POST("/updatePriceProduct", handler::updateAgeCustomer)
            POST("/deleteProduct", handler::deleteCustomer)
        }
    }
}