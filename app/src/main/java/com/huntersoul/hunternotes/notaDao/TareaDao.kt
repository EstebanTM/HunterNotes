package com.huntersoul.hunternotes.notaDao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.huntersoul.hunternotes.models.TareaEntity


@Dao
interface TareaDao {

    @Query("SELECT * FROM TareaEntity")
    fun getAllTareas(): List<TareaEntity>

    @Query("SELECT * FROM TareaEntity WHERE id = :tareaId")
    fun getTarea(tareaId: Int): TareaEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTarea(tarea: TareaEntity)

    @Update
    fun actualizarTarea(tarea: TareaEntity)

    @Delete
    fun eliminarTarea(tarea: TareaEntity)
}