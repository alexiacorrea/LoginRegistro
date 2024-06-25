package com.example.login.entities


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuario (
    @PrimaryKey (autoGenerate = true) val id: Long = 0,
    val email: String = "",
    val user: String = "",
    val senha: String = ""
) {
}
