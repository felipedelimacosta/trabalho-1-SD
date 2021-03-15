package com.desafio.kotlin.DesafioGreenMile.domain

import javax.persistence.*

@Entity
data class Stop(
        @Id
        @GeneratedValue
        val id: Int,
        val address: String,
        val latitude: Double,
        val longitude: Double,
)