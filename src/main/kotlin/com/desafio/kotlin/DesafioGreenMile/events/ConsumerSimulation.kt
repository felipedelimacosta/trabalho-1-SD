package com.desafio.kotlin.DesafioGreenMile.events

import com.desafio.kotlin.DesafioGreenMile.domain.Route
import com.desafio.kotlin.DesafioGreenMile.dto.AcessPointDTO

data class ConsumerSimulation (
        val route: Route,
        val acessPoint: AcessPointDTO
)