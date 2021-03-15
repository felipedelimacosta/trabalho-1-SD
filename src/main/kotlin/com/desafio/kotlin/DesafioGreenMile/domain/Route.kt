package com.desafio.kotlin.DesafioGreenMile.domain

import javax.persistence.*

@Entity(name = "route")
data class Route(
        @Id
        val id: Int,
        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "equipment_id", referencedColumnName = "id")
        val equipment: Equipment,
        val name: String,
        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
        @JoinColumn(name = "stop_id", referencedColumnName = "id")
        val stops: List<Stop>,
        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
        @JoinColumn(name = "acessPoint_id", referencedColumnName = "id")
        val acessPoint: AcessPoint
)