package com.huntersoul.hunternotes.models

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NotaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val titulo: String?,
    val contenido: String?,
    val multimedia: Uri?= null,
    val fecha: String?,
)
