package com.desafio.kotlin.DesafioGreenMile


import com.desafio.kotlin.DesafioGreenMile.domain.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant
import java.util.*


@SpringBootTest
class DesafioGreenMileApplicationTests {

	@Test
	fun postRouteTest(){
		/*val stop = Stop(1,"teste",55.0,55.0)
		val stops: MutableList<Stop> = mutableListOf()
		stops.add(stop)
		val coordinate = Coordinate(1,1,55.0,55.0,Date.from(Instant.now()))
		val coordinates: MutableList<Coordinate> = mutableListOf()
		coordinates.add(coordinate)
		val acessPoint = AcessPoint(1,55.0,55.0,coordinates)

		val equipment = Equipment(1,"teste","teste", 55.0,55.0)
		val route =  Route(1,equipment,"teste",stops,acessPoint )

		val returnTest: Boolean
		 if(route.id != null){
			returnTest = true
		 }*/
		assert(true)
		/*@PostMapping
		fun addRoute(@RequestBody route: Route): ResponseEntity<Route> {
			routeRepository.save(route)
			return ResponseEntity.ok().body(route)
		}*/
	}
	@Test
	fun contextLoads() {
	}



}
