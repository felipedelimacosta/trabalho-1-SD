package com.desafio.kotlin.DesafioGreenMile.repository

import com.desafio.kotlin.DesafioGreenMile.domain.AcessPoint
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.Entity

@Repository
interface AcessPointRepository: JpaRepository<AcessPoint, Int> {
}