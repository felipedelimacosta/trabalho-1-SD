package com.desafio.kotlin.DesafioGreenMile.controller

import com.desafio.kotlin.DesafioGreenMile.domain.Route

import com.desafio.kotlin.DesafioGreenMile.repository.RouteRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/routes")
class RouteController(private val routeRepository: RouteRepository) {

    @PostMapping
    fun addRoute(@RequestBody route: Route): ResponseEntity<Route> {
        routeRepository.save(route)
        return ResponseEntity.ok().body(route)
    }

    @GetMapping
    fun getRoute(): ResponseEntity<MutableList<Route>> {
        return ResponseEntity(routeRepository.findAll(), HttpStatus.OK)
    }

    @PutMapping("{id}")
    fun putLastCoordinateRoute(@PathVariable("id") id: Int, @RequestBody newRoute:Route): ResponseEntity<Route>{
        return routeRepository.findById(id)
                .map { rota ->
                    val novaVersaoDaRota = newRoute.copy(id = rota.id, stops = rota.stops, acessPoint = rota.acessPoint)
                    routeRepository.save(novaVersaoDaRota)
                    ResponseEntity.ok().body(novaVersaoDaRota)
                }.orElse(ResponseEntity(HttpStatus.NOT_FOUND))
    }
}