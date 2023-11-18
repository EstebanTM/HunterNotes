package com.huntersoul.hunternotes.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huntersoul.hunternotes.models.NotaEntity
import com.huntersoul.hunternotes.repository.NotaRepository
import com.huntersoul.hunternotes.state.NotaState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotaViewModel(
    private val notaRepository: NotaRepository) : ViewModel() {
    var state by mutableStateOf(NotaState())
        private set

    init {
       viewModelScope.launch {
           state = state.copy(
               notas = notaRepository.obtenerTodasLasNotas()
           )
       }
    }
    fun guardarNota(nota: NotaEntity){
        viewModelScope.launch {
            notaRepository.insertarNota(nota)
        }
    }

    fun actualizarNota(nota: NotaEntity){
        viewModelScope.launch {
            notaRepository.actualizarNota(nota)
        }
    }

    fun eliminarNota(nota:NotaEntity){
        viewModelScope.launch {
            notaRepository.eliminarNota(nota)
        }
    }
}
