package com.example.tutorial.serviceapi.features.process

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ProcessRouter {

    @Bean
    fun pcRouter(handler: ProcessHandler) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {

            POST("/buyProduct", handler::buyProduct)
            GET("/getBuyProduct", handler::getBuyProduct)
        }
    }
}