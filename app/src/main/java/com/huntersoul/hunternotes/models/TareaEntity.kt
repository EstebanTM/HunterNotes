package com.huntersoul.hunternotes.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TareaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val titulo: String,
    val contenido: String,
    val descripcion: String,
    val multimedia: Any
)