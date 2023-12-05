package com.huntersoul.hunternotes.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huntersoul.hunternotes.models.NotaEntity
import com.huntersoul.hunternotes.models.TareaEntity
import com.huntersoul.hunternotes.repository.NotaRepository
import com.huntersoul.hunternotes.repository.TareaRepository
import com.huntersoul.hunternotes.state.NotaState
import com.huntersoul.hunternotes.state.TareaState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TareaViewModel(
    private val tareaRepository: TareaRepository) : ViewModel() {
    var state by mutableStateOf(TareaState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                tareas = tareaRepository.obtenerTodasLasTareas()
            )
        }
    }
    fun guardarNota(tarea: TareaEntity){
        viewModelScope.launch {
            tareaRepository.insertarNota(tarea)
        }
    }

    fun actualizarNota(tarea: TareaEntity){
        viewModelScope.launch {
            tareaRepository.actualizarNota(tarea)
        }
    }

    fun eliminarNota(tarea: TareaEntity){
        viewModelScope.launch {
            tareaRepository.eliminarNota(tarea)
        }
    }
}