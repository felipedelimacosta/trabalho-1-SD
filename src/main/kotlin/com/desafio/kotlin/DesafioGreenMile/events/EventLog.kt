package com.desafio.kotlin.DesafioGreenMile.events

import com.desafio.kotlin.DesafioGreenMile.domain.AcessPoint
import com.desafio.kotlin.DesafioGreenMile.domain.Coordinate
import com.desafio.kotlin.DesafioGreenMile.domain.LastCoordinate
import com.desafio.kotlin.DesafioGreenMile.repository.AcessPointRepository
import com.desafio.kotlin.DesafioGreenMile.repository.RouteRepository
import com.desafio.kotlin.DesafioGreenMile.util.haversineDistance
import org.springframework.stereotype.Component

import java.util.*

@Component
class EventLog (
        private val routeRepository: RouteRepository,
        private val acessPointRepository: AcessPointRepository
) : IEventsProcessor, Observer {
    override fun processCoordinate(notificationDto: NotificationDto) {
       /* val saidaAcessPoint = notificationDto.acessPointDTO?.id?.let { acessPointRepository.findById(it) }
        saidaAcessPoint?.get()?.coordinates?.add(notificationDto.acessPointDTO.coordinates)
        saidaAcessPoint?.map { acessPoint ->
            val novaVersaoAcessPoint = notificationDto.acessPointDTO?.id?.let { this.dotToEntity(it, notificationDto.acessPointDTO.lastCoordinate, saidaAcessPoint.get().coordinates) }
            novaVersaoAcessPoint?.let { acessPointRepository.save(it) }
        }*/
    }
    override fun update(o: Observable?, notificationDto: Any?) {
        processCoordinate(notificationDto as NotificationDto)
    }
    private fun dotToEntity(id: Int, lastCoordinate: LastCoordinate, coordinate: MutableList<Coordinate>): AcessPoint {
        var retornoAcessPoint =  AcessPoint(id,lastCoordinate, coordinate)
        return retornoAcessPoint

    }
}

