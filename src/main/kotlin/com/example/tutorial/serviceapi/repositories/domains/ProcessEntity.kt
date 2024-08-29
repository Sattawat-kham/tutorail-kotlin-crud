package com.example.tutorial.serviceapi.repositories.domains

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("process")
data class ProcessEntity (

    @Column("customer_id")
    val customerId: String,
    @Column("product_id")
    val productId: String,
    @Column("amount")
    val amount: Int,
)