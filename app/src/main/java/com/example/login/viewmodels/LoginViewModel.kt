package com.example.login.viewmodels

import androidx.lifecycle.ViewModel
import com.example.login.model.Login
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(Login())
    val uiState: StateFlow<Login> = _uiState.asStateFlow()

    fun updateUsuario(newUsuario: String) {
        _uiState.update {
            it.copy(usuario = newUsuario)
        }
    }

    fun updateSenha(newSenha: String) {
        _uiState.update {
            it.copy(senha = newSenha)
        }
    }
}
