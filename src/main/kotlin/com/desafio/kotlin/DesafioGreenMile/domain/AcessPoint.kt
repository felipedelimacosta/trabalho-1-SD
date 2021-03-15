package com.desafio.kotlin.DesafioGreenMile.domain

import javax.persistence.*

@Entity
data class AcessPoint (
        @Id
        val id: Int,
        //var idAcessPoint: Int,
        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
        @JoinColumn(name = "lastCoordinate_id", referencedColumnName = "id")
        val lastCoordinate: LastCoordinate,
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        @JoinColumn(name = "coordinate_id", referencedColumnName = "id")
        val coordinates: MutableList<Coordinate>
)
