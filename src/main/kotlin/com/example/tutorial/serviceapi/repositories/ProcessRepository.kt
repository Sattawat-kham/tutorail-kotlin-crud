package com.example.tutorial.serviceapi.repositories

import com.example.tutorial.serviceapi.repositories.domains.ProcessEntity
import com.example.tutorial.serviceapi.repositories.models.BuyProductModel
import com.example.tutorial.serviceapi.utils.getLogger
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.core.usingAndAwait
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.stereotype.Repository

@Repository
class ProcessRepository (

    private val r2dbcEntityTemplate: R2dbcEntityTemplate
){

    val logger = getLogger<ProcessRepository>()

    internal suspend fun buyProduct(
        data: BuyProductModel
    ) {
        r2dbcEntityTemplate.runCatching {
            insert(ProcessEntity::class.java).usingAndAwait(
                ProcessEntity(
                    customerId = data.customerId,
                    productId = data.productId,
                    amount = data.amount,
                )
            )
        }.onFailure {
            logger.error("Update database Process {entity: $data} error cause: ${it.message}")
        }.getOrThrow()
    }

    internal suspend fun getBuyProductByCustomerId(customerId: String) =
        r2dbcEntityTemplate.runCatching {
            select(
                Query.query(
                    Criteria.where(ProcessEntity::customerId.name).`is`(customerId)
                ),ProcessEntity::class.java).asFlow().toList()
        }.onFailure {
            logger.error("delete database Customer $customerId error cause: ${it.message}")
        }.getOrThrow()
}