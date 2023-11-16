package com.huntersoul.hunternotes.state

import com.huntersoul.hunternotes.models.NotaEntity

data class NotaState(
    val nota: NotaEntity = NotaEntity(
        id = 0,
        titulo = "",
        contenido = "",
        descripcion = nota.descripcion,
        multimedia = nota.multimedia
    ),
    val notas: List<NotaEntity> = emptyList()
)