package com.desafio.kotlin.DesafioGreenMile.events

//const val geofence = 0.2 // 200 meters

interface IEventDanger {
    fun processCoordinate(notificationDangerDto: ConsumerSimulation)

}