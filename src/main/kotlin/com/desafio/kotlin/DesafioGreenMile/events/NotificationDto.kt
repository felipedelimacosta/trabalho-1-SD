package com.desafio.kotlin.DesafioGreenMile.events

import com.desafio.kotlin.DesafioGreenMile.domain.Coordinate
import com.desafio.kotlin.DesafioGreenMile.domain.LastCoordinate
import com.desafio.kotlin.DesafioGreenMile.dto.AcessPointDTO

data class NotificationDto (
        val coordinate: Coordinate?,
        val lastCoordinateEquipment: LastCoordinate?,
)