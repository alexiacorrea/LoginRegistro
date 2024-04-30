package com.example.login.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

data class Login(
    var usuario: String = "",
    var senha: String = ""
)

fun rememberLogin(): Login {
    return remember {
        Login()
    }
}

