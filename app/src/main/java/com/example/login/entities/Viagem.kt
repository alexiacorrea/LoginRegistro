package com.example.login.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Viagem (
    @PrimaryKey (autoGenerate = true) val id: Long = 0,
    val destino: String = "",
    val tipo: Int = 0, // 0 - Lazer 1 -Negócios
    val dataIni: LocalDate = LocalDate.now(),
    val dataFim: LocalDate = LocalDate.now(),
    val orcamento: Double = 0.0
) {
}