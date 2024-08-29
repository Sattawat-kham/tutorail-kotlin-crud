package com.example.tutorial.serviceapi.features.process

import com.example.tutorial.serviceapi.dto.TransferRequest
import com.example.tutorial.serviceapi.features.process.models.BuyProductRequest
import com.example.tutorial.serviceapi.features.process.models.GetBuyProductRequest
import com.example.tutorial.serviceapi.features.process.services.ProcessService
import com.example.tutorial.serviceapi.utils.coHandler
import com.example.tutorial.serviceapi.utils.transferResponseSuccess
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.awaitBody

@Component
class ProcessHandler (

    private val processService: ProcessService
){

    suspend fun buyProduct(request: ServerRequest) =
        request.coHandler {
            request.awaitBody<TransferRequest<BuyProductRequest>>().let {
                transferResponseSuccess(
                    request.headers(),
                    withContext(NonCancellable) {
                        processService.buyProduct(it.content)
                    }
                )
            }
        }

    suspend fun getBuyProduct(request: ServerRequest) =
        request.coHandler {
            request.awaitBody<TransferRequest<GetBuyProductRequest>>().let {
                transferResponseSuccess(
                    request.headers(),
                    withContext(NonCancellable) {
                        processService.getBuyProduct(it.content)
                    }
                )
            }
        }
}