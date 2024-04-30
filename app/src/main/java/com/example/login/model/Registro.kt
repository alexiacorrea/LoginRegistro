package com.example.login.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

data class Registro(
    var usuario: String = "",
    var senha: String = "",
    var email: String = ""
)

@Composable
fun rememberRegistro(): Registro {
    return remember {
        Registro()
    }
}