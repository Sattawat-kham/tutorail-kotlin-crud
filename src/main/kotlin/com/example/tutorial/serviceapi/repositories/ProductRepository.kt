package com.example.tutorial.serviceapi.repositories

import com.example.tutorial.serviceapi.repositories.domains.ProductEntity
import com.example.tutorial.serviceapi.repositories.models.CreateProductModel
import com.example.tutorial.serviceapi.repositories.models.UpdatePriceProductModel
import com.example.tutorial.serviceapi.repositories.models.UpdateTotalProductModel
import com.example.tutorial.serviceapi.utils.getLogger
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.core.usingAndAwait
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.data.relational.core.query.Update
import org.springframework.stereotype.Repository

@Repository
class ProductRepository (

    private val r2dbcEntityTemplate: R2dbcEntityTemplate,
){

    val logger = getLogger<ProductRepository>()

    internal suspend fun getAllDataProduct() =
        r2dbcEntityTemplate.runCatching {
            select(ProductEntity::class.java)
                .all()
                .collectList()
                .awaitFirstOrNull()
        }.onFailure {
            logger.error("select database Product error cause: ${it.message}")
        }.getOrElse { null }

    internal suspend fun getProductById(id: String) =
        r2dbcEntityTemplate.runCatching {
            select(
                Query.query(
                    Criteria.where(ProductEntity::id.name).`is`(id)
                ),
                ProductEntity::class.java
            ).awaitFirstOrNull()
        }.onFailure {
            logger.error("delete database Customer $id error cause: ${it.message}")
        }.getOrThrow()

    internal suspend fun createProduct(
        data: CreateProductModel
    ) {
        r2dbcEntityTemplate.runCatching {
            insert(ProductEntity::class.java).usingAndAwait(
                ProductEntity(
                    id = data.id,
                    name = data.name,
                    price = data.price,
                    total = data.total,
                    createdDatetime = data.createdDatetime,
                    updatedDatetime = null
                )
            )
        }.onFailure {
            logger.error("Update database Product {entity: $data} error cause: ${it.message}")
        }.getOrThrow()
    }

    internal suspend fun updatePriceProduct(
        data: UpdatePriceProductModel
    ) {
        r2dbcEntityTemplate.runCatching {

            update(
                Query.query(
                    Criteria.where(ProductEntity::id.name).`is`(data.id)
                ),
                Update.update(ProductEntity::price.name, data.price)
                    .set(ProductEntity::updatedDatetime.name, data.updatedDatetime),
                ProductEntity::class.java
            ).awaitFirstOrNull()

        }.onFailure {
            logger.error("Update database Product {entity: $data} error cause: ${it.message}")
        }.getOrThrow()
    }

    internal suspend fun updateTotalProduct(
        data: UpdateTotalProductModel
    ) {
        r2dbcEntityTemplate.runCatching {

            update(
                Query.query(
                    Criteria.where(ProductEntity::id.name).`is`(data.id)
                ),
                Update.update(ProductEntity::total.name, data.total)
                    .set(ProductEntity::updatedDatetime.name, data.updatedDatetime),
                ProductEntity::class.java
            ).awaitFirstOrNull()

        }.onFailure {
            logger.error("Update database Product {entity: $data} error cause: ${it.message}")
        }.getOrThrow()
    }

    internal suspend fun deleteProduct(
        id: String
    ) {
        r2dbcEntityTemplate.runCatching {

            delete(
                Query.query(
                    Criteria.where(ProductEntity::id.name).`is`(id)
                ),
                ProductEntity::class.java
            ).awaitFirstOrNull()

        }.onFailure {
            logger.error("delete database Product $id error cause: ${it.message}")
        }.getOrThrow()
    }
}