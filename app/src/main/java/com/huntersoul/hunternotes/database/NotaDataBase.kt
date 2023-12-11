package com.huntersoul.hunternotes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.huntersoul.hunternotes.models.NotaEntity
import com.huntersoul.hunternotes.models.TareaEntity
import com.huntersoul.hunternotes.notaDao.NotaDao
import com.huntersoul.hunternotes.notaDao.TareaDao

@Database(entities = [NotaEntity::class, TareaEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class NotaDataBase: RoomDatabase() {
    abstract val notaDao: NotaDao
    abstract val tareaDao: TareaDao
}
