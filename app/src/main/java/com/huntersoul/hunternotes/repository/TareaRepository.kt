package com.huntersoul.hunternotes.repository

import com.huntersoul.hunternotes.models.TareaEntity

import com.huntersoul.hunternotes.notaDao.TareaDao

class TareaRepository(
    private val tareaDao: TareaDao
) {
    fun obtenerTodasLasTareas(): List<TareaEntity> {
        return tareaDao.getAllTareas()
    }
    fun obtenerTarea(id: Int): TareaEntity {
        return tareaDao.getTarea(id)
    }
    fun insertarNota(tarea: TareaEntity) {
        tareaDao.insertTarea(tarea)
    }

    fun actualizarNota(tarea: TareaEntity) {

    }

    fun eliminarNota(tarea: TareaEntity) {

    }
}