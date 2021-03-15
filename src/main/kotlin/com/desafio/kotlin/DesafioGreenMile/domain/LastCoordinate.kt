package com.desafio.kotlin.DesafioGreenMile.domain

import javax.persistence.*

@Entity
data class LastCoordinate (
        @Id
        @GeneratedValue
        val id: Int,
        val latitude: Double,
        val longitude: Double
)