package com.example.tutorial.serviceapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class ServiceApiApplication

fun main(args: Array<String>) {
	runApplication<ServiceApiApplication>(*args)
}
