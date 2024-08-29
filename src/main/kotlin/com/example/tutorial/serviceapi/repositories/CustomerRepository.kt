package com.example.tutorial.serviceapi.repositories

import com.example.tutorial.serviceapi.repositories.domains.CustomerEntity
import com.example.tutorial.serviceapi.repositories.models.CreateCustomerModel
import com.example.tutorial.serviceapi.repositories.models.UpdateAgeCustomerModel
import com.example.tutorial.serviceapi.utils.getLogger
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.core.usingAndAwait
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.data.relational.core.query.Update
import org.springframework.stereotype.Repository

@Repository
class CustomerRepository(

    private val r2dbcEntityTemplate: R2dbcEntityTemplate,
) {
    val logger = getLogger<CustomerRepository>()

    internal suspend fun getAllDataCustomer() =
        r2dbcEntityTemplate.runCatching {
            select(CustomerEntity::class.java)
                .all()
                .collectList()
                .awaitFirstOrNull()
        }.onFailure {
            logger.error("select database Customer error cause: ${it.message}")
        }.getOrElse { null }


    internal suspend fun getCustomerById(id: String) =
        r2dbcEntityTemplate.runCatching {
            select(
                Query.query(
                    Criteria.where(CustomerEntity::id.name).`is`(id)
                ),
                CustomerEntity::class.java
            ).awaitFirstOrNull()
        }.onFailure {
            logger.error("delete database Customer $id error cause: ${it.message}")
        }.getOrThrow()


    internal suspend fun createCustomer(
        data: CreateCustomerModel
    ) {
        r2dbcEntityTemplate.runCatching {
            insert(CustomerEntity::class.java).usingAndAwait(
                CustomerEntity(
                    id = data.id,
                    name = data.name,
                    age = data.age,
                    createdDatetime = data.createdDatetime,
                    updatedDatetime = null
                )
            )
        }.onFailure {
            logger.error("Update database Customer {entity: $data} error cause: ${it.message}")
        }.getOrThrow()
    }

    internal suspend fun updateAgeCustomer(
        data: UpdateAgeCustomerModel
    ) {
        r2dbcEntityTemplate.runCatching {

            update(
                Query.query(
                    Criteria.where(CustomerEntity::id.name).`is`(data.id)
                ),
                Update.update(CustomerEntity::age.name, data.age)
                    .set(CustomerEntity::updatedDatetime.name, data.updatedDatetime),
                CustomerEntity::class.java
            ).awaitFirstOrNull()

        }.onFailure {
            logger.error("Update database Customer {entity: $data} error cause: ${it.message}")
        }.getOrThrow()
    }

    internal suspend fun deleteCustomer(
        id: String
    ) {
        r2dbcEntityTemplate.runCatching {

            delete(
                Query.query(
                    Criteria.where(CustomerEntity::id.name).`is`(id)
                ),
                CustomerEntity::class.java
            ).awaitFirstOrNull()

        }.onFailure {
            logger.error("delete database Customer $id error cause: ${it.message}")
        }.getOrThrow()
    }
}