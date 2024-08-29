package com.example.tutorial.serviceapi.repositories.domains

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("product")
data class ProductEntity (

    @Column("id")
    val id: String,
    @Column("name")
    val name: String,
    @Column("price")
    val price: Int,
    @Column("total")
    val total: Int,
    @Column("created_datetime")
    val createdDatetime: LocalDateTime,
    @Column("updated_datetime")
    val updatedDatetime: LocalDateTime?,
)