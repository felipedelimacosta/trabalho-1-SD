package com.desafio.kotlin.DesafioGreenMile.domain

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Coordinate (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,
        val equipmentId: Int,
        val latitude: Double,
        val longitude: Double)