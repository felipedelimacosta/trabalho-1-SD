package com.desafio.kotlin.DesafioGreenMile

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
class DesafioGreenMileApplication

fun main(args: Array<String>) {
	runApplication<DesafioGreenMileApplication>(*args)
}
