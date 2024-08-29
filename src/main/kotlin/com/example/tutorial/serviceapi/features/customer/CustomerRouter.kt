package com.example.tutorial.serviceapi.features.customer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CustomerRouter {

    @Bean
    fun cRouter(handler: CustomerHandler) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            GET("/getAllCustomer", handler::getAllCustomer)
            GET("/getCustomerById", handler::getCustomerById)
            POST("/createCustomer", handler::createCustomer)
            POST("/updateAgeCustomer", handler::updateAgeCustomer)
            POST("/deleteCustomer", handler::deleteCustomer)
        }
    }
}