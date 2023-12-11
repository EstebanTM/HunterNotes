package com.huntersoul.hunternotes.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huntersoul.hunternotes.models.TareaEntity
import com.huntersoul.hunternotes.repository.TareaRepository
import com.huntersoul.hunternotes.state.TareaState
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
    fun guardarTarea(tarea: TareaEntity){
        viewModelScope.launch {
            tareaRepository.insertarTarea(tarea)
        }
    }

    fun actualizarTarea(tarea: TareaEntity){
        viewModelScope.launch {
            tareaRepository.actualizarTarea(tarea)
        }
    }

    fun eliminarTarea(tarea: TareaEntity){
        viewModelScope.launch {
            tareaRepository.eliminarTarea(tarea)
        }
    }
}