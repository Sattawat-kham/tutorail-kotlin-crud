package com.example.tutorial.serviceapi.repositories.domains

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("customer")
data class CustomerEntity (

    @Column("id")
    val id: String,
    @Column("name")
    val name: String,
    @Column("age")
    val age: Int,
    @Column("created_datetime")
    val createdDatetime: LocalDateTime,
    @Column("updated_datetime")
    val updatedDatetime: LocalDateTime?,
)

