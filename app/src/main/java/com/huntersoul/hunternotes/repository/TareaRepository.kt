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
    fun insertarTarea(tarea: TareaEntity) {
        tareaDao.insertTarea(tarea)
    }

    fun actualizarTarea(tarea: TareaEntity) {
        tareaDao.actualizarTarea(tarea)
    }

    fun eliminarTarea(tarea: TareaEntity) {
        tareaDao.eliminarTarea(tarea)
    }
}