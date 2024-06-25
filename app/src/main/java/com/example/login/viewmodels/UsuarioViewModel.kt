package com.example.login.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.login.bd.AppDatabase
import com.example.login.dao.UsuarioDao
import com.example.login.entities.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class  UsuarioViewModelFactory(val db: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return UsuarioViewModel(db.usuarioDao) as T
    }
}

class UsuarioViewModel(val usuarioDao: UsuarioDao): ViewModel() {

    private val _uiState = MutableStateFlow(Usuario())
    val uiState: StateFlow<Usuario> = _uiState.asStateFlow()

    fun updateEmail (email: String) {
        _uiState.update {
            it.copy(email = email)
        }
    }

    fun updateUser (user: String) {
        _uiState.update {
            it.copy(user = user)
        }
    }

    fun updateSenha (senha: String) {
        _uiState.update {
            it.copy(senha = senha)
        }
    }

    private fun updateId (id: Long) {
        _uiState.update {
            it.copy(id = id)
        }
    }

    private fun new() {
        _uiState.update {
            it.copy(id = 0, email = "", user = "", senha = "")
        }
    }

    fun save() {
        viewModelScope.launch {
            val id = usuarioDao.upsert(uiState.value)
            if (id > 0) {
                updateId(id)
            }
        }
    }

    fun savenew() {
        save()
        new()
    }
}