package com.desafio.kotlin.DesafioGreenMile.dto

import com.desafio.kotlin.DesafioGreenMile.domain.Coordinate
import com.desafio.kotlin.DesafioGreenMile.domain.LastCoordinate

data class AcessPointDTO (
        val id: Int,
        val lastCoordinate: LastCoordinate,
        var coordinates: Coordinate
)