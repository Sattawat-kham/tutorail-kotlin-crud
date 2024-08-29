package com.example.tutorial.serviceapi.utils

import com.example.tutorial.serviceapi.dto.TransferResponse
import com.example.tutorial.serviceapi.starter.configuration.MDCContext
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.json
import java.util.*

inline fun <reified T : Any> getLogger(): Logger = LoggerFactory.getLogger(T::class.java)

fun generateUUID() = UUID.randomUUID().toString()

suspend inline fun <T : Any> transferResponseSuccess(headerRequest: ServerRequest.Headers, response: T) =
    ServerResponse
        .ok()
        .json()
        .bodyValueAndAwait(
            if (headerRequest != null) {
                TransferResponse(
                    response
                )
            } else {
                response
            }
        )

suspend inline fun ServerRequest.coHandler(crossinline block: suspend (ServerRequest) -> ServerResponse) =  withContext(
    MDCContext()
) { block(this@coHandler) }