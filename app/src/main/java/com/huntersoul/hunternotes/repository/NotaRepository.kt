package com.huntersoul.hunternotes.repository

import com.huntersoul.hunternotes.models.NotaEntity
import com.huntersoul.hunternotes.notaDao.NotaDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotaRepository(private val notaDao: NotaDao) {
    suspend fun obtenerTodasLasNotas(): List<NotaEntity> {
        return withContext(Dispatchers.IO) {
            notaDao.getAllNotes()
        }
    }
}