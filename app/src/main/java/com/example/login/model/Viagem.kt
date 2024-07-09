package com.example.login.model

import java.time.LocalDate


data class Viagem (

    val destino: String = "",
    val tipo: Int = 0, // 0 - Lazer 1 -Negócios
    val dataIni: LocalDate = LocalDate.now(),
    val dataFim: LocalDate = LocalDate.now(),
    val orcamento: Double = 0.0
){

}