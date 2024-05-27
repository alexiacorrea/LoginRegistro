package com.example.login.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

data class Login(
    var usuario: String = "admin",
    var senha: String = "admin"
)

@Composable
fun rememberLogin(): Login {
    return remember {
        Login()
    }
}

