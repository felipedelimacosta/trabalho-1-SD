package com.desafio.kotlin.DesafioGreenMile.domain

import javax.persistence.*

@Entity
data class Equipment (
        @Id
        val id: Int,
        val name: String,
        val status: String,
        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
        @JoinColumn(name = "lastCoordinate_id", referencedColumnName = "id")
        val lastCoordinate: LastCoordinate
)