package com.desafio.kotlin.DesafioGreenMile.controller

import com.desafio.kotlin.DesafioGreenMile.domain.AcessPoint
import com.desafio.kotlin.DesafioGreenMile.domain.Coordinate
import com.desafio.kotlin.DesafioGreenMile.domain.LastCoordinate
import com.desafio.kotlin.DesafioGreenMile.dto.AcessPointDTO
import com.desafio.kotlin.DesafioGreenMile.events.ConsumerSimulation
import com.desafio.kotlin.DesafioGreenMile.repository.AcessPointRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/accespoint")
class AcessPointController (private val acessPointRepository: AcessPointRepository){

    @GetMapping("/{id}")
    fun getPoint(@PathVariable("id") id: Int): ResponseEntity<Optional<AcessPoint>> {
        val newAcessPoint =  acessPointRepository.findById(id)
        return ResponseEntity.ok().body(newAcessPoint)
    }

    @PostMapping
    fun testPost(@RequestBody consumerSimulation: ConsumerSimulation): ResponseEntity<ConsumerSimulation> {
        return ResponseEntity.ok().body(consumerSimulation)

    }

    @PutMapping("/{id}")
    fun updatePoint(@PathVariable("id") id: Int, @RequestBody novoAcessPoint: AcessPointDTO): ResponseEntity<Optional<AcessPoint>> {
        val saidaAcessPoint =  acessPointRepository.findById(id)
        saidaAcessPoint.get().coordinates.add(novoAcessPoint.coordinates)
        saidaAcessPoint.map { acessPoint ->
            val novaVersaoAcessPoint = this.dotToEntity(novoAcessPoint.id,novoAcessPoint.lastCoordinate, saidaAcessPoint.get().coordinates)
            acessPointRepository.save(novaVersaoAcessPoint)
            ResponseEntity.ok().body(novaVersaoAcessPoint)
        }.orElse(ResponseEntity(HttpStatus.NOT_FOUND))
        //        return ResponseEntity.ok().body(route)*/

        return ResponseEntity.ok().body(saidaAcessPoint)
    }

    private fun dotToEntity(id: Int, lastCoordinate: LastCoordinate, coordinate: MutableList<Coordinate>): AcessPoint {
        var retornoAcessPoint =  AcessPoint(id,lastCoordinate, coordinate)
        return retornoAcessPoint

    }
}