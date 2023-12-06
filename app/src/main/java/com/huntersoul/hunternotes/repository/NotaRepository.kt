package com.huntersoul.hunternotes.repository

import com.huntersoul.hunternotes.models.NotaEntity
import com.huntersoul.hunternotes.notaDao.NotaDao

class NotaRepository(
    private val notaDao: NotaDao
) {
    fun obtenerTodasLasNotas(): List<NotaEntity> {
        return notaDao.getAllNotes()
    }
    fun obtenerNota(id: Int): NotaEntity{
        return notaDao.getNota(id)
    }
    fun insertarNota(nota: NotaEntity) {
        notaDao.insertNota(nota)
    }

    fun actualizarNota(nota: NotaEntity) {
        notaDao.actualizarNota(nota)
    }

    fun eliminarNota(nota: NotaEntity) {
        notaDao.eliminarNota(nota)
    }
}