package com.huntersoul.hunternotes.repository

import com.huntersoul.hunternotes.models.NotaEntity
import com.huntersoul.hunternotes.notaDao.NotaDao

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
            descripcion = nota.descripcion,
            multimedia = nota.multimedia
        )

        notaDao.insertNota(entity)
    }

    fun actualizarNota(nota: NotaEntity) {

    }

    fun eliminarNota(nota: NotaEntity) {

    }
}