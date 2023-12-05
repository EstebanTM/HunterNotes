package com.huntersoul.hunternotes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.huntersoul.hunternotes.models.NotaEntity
import com.huntersoul.hunternotes.notaDao.NotaDao
import com.huntersoul.hunternotes.notaDao.TareaDao

@Database(entities = [NotaEntity::class], version = 1)
abstract class NotaDataBase: RoomDatabase() {
    abstract val notaDao: NotaDao
    abstract val tareaDao: TareaDao
}
