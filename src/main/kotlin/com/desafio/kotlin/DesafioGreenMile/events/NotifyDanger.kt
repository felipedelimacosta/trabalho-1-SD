package com.desafio.kotlin.DesafioGreenMile.events

import com.desafio.kotlin.DesafioGreenMile.dto.AcessPointDTO
import com.desafio.kotlin.DesafioGreenMile.repository.AcessPointRepository
import com.desafio.kotlin.DesafioGreenMile.repository.RouteRepository
import com.desafio.kotlin.DesafioGreenMile.stream.CoordinateProcessor
import com.desafio.kotlin.DesafioGreenMile.util.haversineDistance
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

@Component
class NotifyDanger(acessPointRepository: AcessPointRepository,
                   routeRepository: RouteRepository
): IEventsProcessor, Observer {

    private val log = LoggerFactory.getLogger(CoordinateProcessor::class.java)

    override fun processCoordinate(notificationDto: NotificationDto) {
        val distance = haversineDistance(notificationDto.lastCoordinateEquipment?.latitude!!, notificationDto.lastCoordinateEquipment?.longitude!!,
                notificationDto.coordinate?.latitude!!, notificationDto.coordinate?.longitude!!)

        if (distance != null) {
            if (distance >= geofence) {
                log.info("Equipamento em perigo!!! Ultima Coordenada atualizada " + notificationDto.lastCoordinateEquipment.toString())
            }else{
                log.info("Equipamento seguindo para demanda de Coordenada " + notificationDto.lastCoordinateEquipment.toString())

            }
        }
    }

    override fun update(o: Observable?, notificationDto: Any?) {
        processCoordinate(notificationDto as NotificationDto)
    }
}