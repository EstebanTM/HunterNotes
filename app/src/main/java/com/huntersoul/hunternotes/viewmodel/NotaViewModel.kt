package com.huntersoul.hunternotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huntersoul.hunternotes.models.NotaEntity
import com.huntersoul.hunternotes.repository.NotaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotaViewModel(private val notaRepository: NotaRepository) : ViewModel() {
    private val _notas = MutableStateFlow<List<NotaEntity>>(emptyList())
    val notas = _notas.asStateFlow()

    init {
        // Cargar las notas al inicializar el ViewModel
        cargarNotas()
    }

    private fun cargarNotas() {
        viewModelScope.launch(Dispatchers.IO) {
            // Obtener las notas desde la base de datos Room
            val listaNotas = notaRepository.obtenerTodasLasNotas()

            // Actualizar el estado con las notas cargadas
            _notas.value = listaNotas
        }
    }
}