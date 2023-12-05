package com.huntersoul.hunternotes.notaDao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.huntersoul.hunternotes.models.NotaEntity


@Dao
interface NotaDao {

    @Query("SELECT * FROM NotaEntity")
    fun getAllNotes(): List<NotaEntity>

    @Query("SELECT * FROM NotaEntity WHERE id = :notaId")
    fun getNota(notaId: Int): NotaEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNota(nota: NotaEntity)

    @Update
    fun actualizarNota(nota: NotaEntity)

    @Delete
    fun eliminarNota(nota: NotaEntity)
}