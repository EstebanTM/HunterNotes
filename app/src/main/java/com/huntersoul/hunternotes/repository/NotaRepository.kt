package com.huntersoul.hunternotes.repository

import com.huntersoul.hunternotes.models.NotaEntity
import com.huntersoul.hunternotes.notaDao.NotaDao
import com.huntersoul.hunternotes.state.NotaState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch


class NotaRepository(private val notaDao: NotaDao) {
    fun obtenerTodasLasNotas(): List<NotaEntity> {
        return notaDao.getAllNotes()
    }
    fun getOneByID(id: Int): NotaEntity{
        return notaDao.getNotaById(id)
    }
    fun insertarNota(nota: NotaEntity) {
        val entity = NotaEntity(
            id = 0,
            titulo = nota.titulo,
            contenido = nota.contenido,
            multimedia = nota.multimedia
        )

        notaDao.insertNota(entity)
    }

    fun actualizarNota(nota: NotaEntity){
        viewModelScope.launch {
            repository.actualizarNota(nota)
        }
    }

    fun eliminarNota(nota: NotaEntity) {
        // Aquí debes implementar la lógica para eliminar la nota de la base de datos
        // y luego actualizar el estado de la aplicación
        viewModelScope.launch {
            notaRepository.eliminarNota(nota)
            state = state.copy(
                notas = notaRepository.obtenerTodasLasNotas()
            )
        }
    }
}
}