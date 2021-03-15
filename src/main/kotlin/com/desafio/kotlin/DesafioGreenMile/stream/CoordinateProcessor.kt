package com.desafio.kotlin.DesafioGreenMile.stream

import com.desafio.kotlin.DesafioGreenMile.domain.AcessPoint
import com.desafio.kotlin.DesafioGreenMile.domain.Coordinate
import com.desafio.kotlin.DesafioGreenMile.domain.LastCoordinate
import com.desafio.kotlin.DesafioGreenMile.domain.Route
import com.desafio.kotlin.DesafioGreenMile.dto.AcessPointDTO
import com.desafio.kotlin.DesafioGreenMile.events.ConsumerSimulation
import com.desafio.kotlin.DesafioGreenMile.events.EventLog
import com.desafio.kotlin.DesafioGreenMile.events.NotificationDto
import com.desafio.kotlin.DesafioGreenMile.events.NotifyDanger
import com.desafio.kotlin.DesafioGreenMile.repository.AcessPointRepository
import com.desafio.kotlin.DesafioGreenMile.repository.RouteRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.*

@Component
class CoordinateProcessor(
        private val eventLog: EventLog,
        private val notifyDanger: NotifyDanger,
        private val routeRepository: RouteRepository,
        private val acessPointRepository: AcessPointRepository,
        private val restTemplate: RestTemplate
        //private var codAcessPoint: Int,
        //private var codRoute: Int,
): Observable() {

    private val log = LoggerFactory.getLogger(CoordinateProcessor::class.java)

    fun receiveAcessPoint(acessPointDTO: ConsumerSimulation) {

        log.info("Coordinate received: [{}]", acessPointDTO::class.toString())

        val saidaAcessPoint = acessPointRepository.findById(acessPointDTO.acessPoint.id)
        saidaAcessPoint.get().coordinates.add(acessPointDTO.acessPoint.coordinates)
        saidaAcessPoint.map { acessPoint ->
            val novaVersaoAcessPoint = this.dotToEntity(acessPointDTO.acessPoint.id, acessPointDTO.acessPoint.lastCoordinate, saidaAcessPoint.get().coordinates)
            acessPointRepository.save(novaVersaoAcessPoint)
        }
        restTemplate.put("http://localhost:8080/routes/1",acessPointDTO.route)

        setChanged()
        notifyObservers(NotificationDto(saidaAcessPoint.get().coordinates.get(saidaAcessPoint.get().coordinates.size-1),acessPointDTO.route.equipment.lastCoordinate))
    }
    /*fun receiveRoute(route: Route){

        log.info("Coordinate received: [{}]", route::class.toString())

        restTemplate.put("http://localhost:8080/routes/1",route)
    }*/

    @Scheduled(fixedDelay = 100000, initialDelay = 10000)
    fun consumeCoordinates(){
        val mapper = ObjectMapper().registerModule(KotlinModule())

        val jsonContent = "[{\"route\":{\"id\":1,\"equipment\":{\"id\":1,\"name\":\"Rota UFC - Russas\",\"status\":\"Saida Centro de Distribuição\",\"lastCoordinate\":{\"latitude\":-4.846593119313565,\"longitude\":-37.782383039875235}},\"name\":\"Rota Teste PUT\",\"stops\":[{\"id\":4,\"address\":\"Rota Teste PUT\",\"latitude\":-4.846593119313565,\"longitude\":-37.782383039875235},{\"id\":4,\"address\":\"Rota Teste PUT\",\"latitude\":-4.846179287101671,\"longitude\":-37.785691254903966},{\"id\":4,\"address\":\"Rota Teste PUT\",\"latitude\":-4.843039862069841,\"longitude\":-37.784831978273125},{\"id\":4,\"address\":\"Rota Teste PUT\",\"latitude\":-4.8419981404883865,\"longitude\":-37.78223982703597},{\"id\":4,\"address\":\"Rota Teste PUT\",\"latitude\":-4.839215451776017,\"longitude\":-37.78119437392202},{\"id\":4,\"address\":\"Rota Teste PUT\",\"latitude\":-4.83703021925808,\"longitude\":-37.772380199734144},{\"id\":4,\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"id\":4,\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"id\":4,\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"id\":4,\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"id\":4,\"address\":\"Rota Teste PUT\",\"latitude\":-4.838604352149532,\"longitude\":-37.7850790470985},{\"id\":4,\"address\":\"Rota Teste PUT\",\"latitude\":-4.845082476108717,\"longitude\":-37.79405127934276}],\"acessPoint\":{\"id\":48,\"coordinates\":[{\"equipmentId\":1,\"latitude\":50,\"longitude\":50}],\"lastCoordinate\":{\"latitude\":-4.846593119313565,\"longitude\":-37.782383039875235}}},\"acessPoint\":{\"id\":48,\"lastCoordinate\":{\"latitude\":-4.846593119313565,\"longitude\":-37.782383039875235},\"coordinates\":{\"latitude\":-4.846593119313565,\"longitude\":-37.782383039875235}}},{\"route\":{\"id\":1,\"equipment\":{\"id\":1,\"name\":\"Rota Teste PUT\",\"status\":\"Teste\",\"lastCoordinate\":{\"latitude\":-4.846179287101671,\"longitude\":-37.785691254903966}},\"name\":\"Rota Teste PUT\",\"stops\":[{\"address\":\"Rota Teste PUT\",\"latitude\":-4.846593119313565,\"longitude\":-37.782383039875235},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.846179287101671,\"longitude\":-37.785691254903966},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.843039862069841,\"longitude\":-37.784831978273125},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.8419981404883865,\"longitude\":-37.78223982703597},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.839215451776017,\"longitude\":-37.78119437392202},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.83703021925808,\"longitude\":-37.772380199734144},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.838604352149532,\"longitude\":-37.7850790470985},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.845082476108717,\"longitude\":-37.79405127934276}],\"acessPoint\":{\"id\":48,\"coordinates\":[{\"equipmentId\":1,\"latitude\":50,\"longitude\":50}],\"lastCoordinate\":{\"latitude\":-4.846179287101671,\"longitude\":-37.785691254903966}}},\"acessPoint\":{\"id\":48,\"lastCoordinate\":{\"latitude\":-4.846179287101671,\"longitude\":-37.785691254903966},\"coordinates\":{\"latitude\":-4.846179287101671,\"longitude\":-37.785691254903966}}},{\"route\":{\"id\":1,\"equipment\":{\"id\":1,\"name\":\"Rota Teste PUT\",\"status\":\"Teste\",\"lastCoordinate\":{\"latitude\":-4.843039862069841,\"longitude\":-37.784831978273125}},\"name\":\"Rota Teste PUT\",\"stops\":[{\"address\":\"Rota Teste PUT\",\"latitude\":-4.846593119313565,\"longitude\":-37.782383039875235},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.846179287101671,\"longitude\":-37.785691254903966},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.843039862069841,\"longitude\":-37.784831978273125},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.8419981404883865,\"longitude\":-37.78223982703597},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.839215451776017,\"longitude\":-37.78119437392202},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.83703021925808,\"longitude\":-37.772380199734144},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.838604352149532,\"longitude\":-37.7850790470985},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.845082476108717,\"longitude\":-37.79405127934276}],\"acessPoint\":{\"id\":48,\"coordinates\":[{\"equipmentId\":1,\"latitude\":50,\"longitude\":50}],\"lastCoordinate\":{\"latitude\":-4.843039862069841,\"longitude\":-37.784831978273125}}},\"acessPoint\":{\"id\":48,\"lastCoordinate\":{\"latitude\":-4.843039862069841,\"longitude\":-37.784831978273125},\"coordinates\":{\"latitude\":-4.843039862069841,\"longitude\":-37.784831978273125}}},{\"route\":{\"id\":1,\"equipment\":{\"id\":1,\"name\":\"Rota Teste PUT\",\"status\":\"Teste\",\"lastCoordinate\":{\"latitude\":-4.8419981404883865,\"longitude\":-37.78223982703597}},\"name\":\"Rota Teste PUT\",\"stops\":[{\"address\":\"Rota Teste PUT\",\"latitude\":-4.846593119313565,\"longitude\":-37.782383039875235},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.846179287101671,\"longitude\":-37.785691254903966},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.843039862069841,\"longitude\":-37.784831978273125},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.8419981404883865,\"longitude\":-37.78223982703597},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.839215451776017,\"longitude\":-37.78119437392202},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.83703021925808,\"longitude\":-37.772380199734144},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.838604352149532,\"longitude\":-37.7850790470985},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.845082476108717,\"longitude\":-37.79405127934276}],\"acessPoint\":{\"id\":48,\"coordinates\":[{\"equipmentId\":1,\"latitude\":50,\"longitude\":50}],\"lastCoordinate\":{\"latitude\":-4.8419981404883865,\"longitude\":-37.78223982703597}}},\"acessPoint\":{\"id\":48,\"lastCoordinate\":{\"latitude\":-4.8419981404883865,\"longitude\":-37.78223982703597},\"coordinates\":{\"latitude\":-4.8419981404883865,\"longitude\":-37.78223982703597}}},{\"route\":{\"id\":1,\"equipment\":{\"id\":1,\"name\":\"Rota Teste PUT\",\"status\":\"Teste\",\"lastCoordinate\":{\"latitude\":-4.839215451776017,\"longitude\":-37.78119437392202}},\"name\":\"Rota Teste PUT\",\"stops\":[{\"address\":\"Rota Teste PUT\",\"latitude\":-4.846593119313565,\"longitude\":-37.782383039875235},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.846179287101671,\"longitude\":-37.785691254903966},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.843039862069841,\"longitude\":-37.784831978273125},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.8419981404883865,\"longitude\":-37.78223982703597},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.839215451776017,\"longitude\":-37.78119437392202},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.83703021925808,\"longitude\":-37.772380199734144},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.838604352149532,\"longitude\":-37.7850790470985},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.845082476108717,\"longitude\":-37.79405127934276}],\"acessPoint\":{\"id\":48,\"coordinates\":[{\"equipmentId\":1,\"latitude\":50,\"longitude\":50}],\"lastCoordinate\":{\"latitude\":-4.839215451776017,\"longitude\":-37.78119437392202}}},\"acessPoint\":{\"id\":48,\"lastCoordinate\":{\"latitude\":-4.8419981404883865,\"longitude\":-37.78223982703597},\"coordinates\":{\"latitude\":-4.8419981404883865,\"longitude\":-37.78223982703597}}},{\"route\":{\"id\":1,\"equipment\":{\"id\":1,\"name\":\"Rota Teste PUT\",\"status\":\"Teste\",\"lastCoordinate\":{\"latitude\":-4.83703021925808,\"longitude\":-37.772380199734144}},\"name\":\"Rota Teste PUT\",\"stops\":[{\"address\":\"Rota Teste PUT\",\"latitude\":-4.846593119313565,\"longitude\":-37.782383039875235},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.846179287101671,\"longitude\":-37.785691254903966},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.843039862069841,\"longitude\":-37.784831978273125},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.8419981404883865,\"longitude\":-37.78223982703597},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.839215451776017,\"longitude\":-37.78119437392202},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.83703021925808,\"longitude\":-37.772380199734144},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.835698257755387,\"longitude\":-37.78319548599562},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.838604352149532,\"longitude\":-37.7850790470985},{\"address\":\"Rota Teste PUT\",\"latitude\":-4.845082476108717,\"longitude\":-37.79405127934276}],\"acessPoint\":{\"id\":48,\"coordinates\":[{\"equipmentId\":1,\"latitude\":50,\"longitude\":50}],\"lastCoordinate\":{\"latitude\":-4.83703021925808,\"longitude\":-37.772380199734144}}},\"acessPoint\":{\"id\":48,\"lastCoordinate\":{\"latitude\":-4.8419981404883865,\"longitude\":-37.78223982703597},\"coordinates\":{\"latitude\":-4.8419981404883865,\"longitude\":-37.78223982703597}}}]"
        val listAcessPoint = mapper.readValue(jsonContent, Array<ConsumerSimulation>::class.java).asList()


        //this.addObserver(eventLog)
        this.addObserver(notifyDanger)

        listAcessPoint.forEach { coordinate ->
            Thread.sleep(500)
            receiveAcessPoint(coordinate)
        }


    }
    private fun dotToEntity(id: Int, lastCoordinate: LastCoordinate, coordinate: MutableList<Coordinate>): AcessPoint {
        var retornoAcessPoint =  AcessPoint(id,lastCoordinate, coordinate)
        return retornoAcessPoint

    }
}


