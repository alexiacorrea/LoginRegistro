package com.example.login.viewmodels

import androidx.lifecycle.ViewModel
import com.example.login.model.Registro
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegistroViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(Registro())
    val uiState: StateFlow<Registro> = _uiState.asStateFlow()

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

    fun updateEmail(newEmail: String) {
        _uiState.update {
            it.copy(email = newEmail)
        }
    }
}
