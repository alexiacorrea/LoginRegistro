package com.example.login.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Viagem (
    @PrimaryKey (autoGenerate = true) val id: Long = 0,
    val destino: String = "",
    val tipo: Int = 0, // 0 - Lazer 1 -Negócios
    val dataIni: String = "",
    val dataFim: String = "",
    val orcamento: Double = 0.0
) {
}