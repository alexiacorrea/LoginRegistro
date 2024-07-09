package com.example.login.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.login.bd.AppDatabase
import com.example.login.dao.ViagemDao
import com.example.login.entities.Viagem
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class  ViagemViewModelFactory(val db: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return ViagemViewModel(db.viagemDao) as T
    }
}

class ViagemViewModel(val viagemDao: ViagemDao): ViewModel() {

    private val _uiState = MutableStateFlow(Viagem())
    val uiState: StateFlow<Viagem> = _uiState.asStateFlow()

    fun updateDestino (destino: String) {
        _uiState.update {
            it.copy(destino = destino)
        }
    }

    fun updateTipo (tipo: Int) {
        _uiState.update {
            it.copy(tipo = tipo)
        }
    }

    fun updateDataIni (dataIni: String) {
        _uiState.update {
            it.copy(dataIni = dataIni)
        }
    }

    fun updateDataFim (dataFim: String) {
        _uiState.update {
            it.copy(dataFim = dataFim)
        }
    }

    fun updateOrcamento (orcamento: Double) {
        _uiState.update {
            it.copy(orcamento = orcamento)
        }
    }

    private fun updateId (id: Long) {
        _uiState.update {
            it.copy(id = id)
        }
    }

    private fun new() {
        _uiState.update {
            it.copy(id = 0, destino = "", tipo = 0, dataIni = "", dataFim = "", orcamento = 0.0)
        }
    }

    fun save() {
        viewModelScope.launch {
            val id = viagemDao.upsert(uiState.value)
            if (id > 0) {
                updateId(id)
            }
        }
    }

    fun savenew() {
        save()
        new()
    }

    fun getAll() = viagemDao.getAll()

    fun delet(viagem: Viagem){
        viewModelScope.launch {
            viagemDao.delete(viagem)
        }
    }

    suspend fun findById(id: Long): Viagem? {
        val deferred : Deferred<Viagem?> = viewModelScope.async {
            viagemDao.findById(id)
        }
        return deferred.await()
    }

    fun setUiState(viagem: Viagem) {
        _uiState.value = uiState.value.copy(
            id = viagem.id,
            destino = viagem.destino,
            dataIni = viagem.dataIni,
            dataFim = viagem.dataFim,
            tipo = viagem.tipo,
            orcamento = viagem.orcamento
        )
    }
}