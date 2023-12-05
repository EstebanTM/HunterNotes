package com.huntersoul.hunternotes.state

import com.huntersoul.hunternotes.models.TareaEntity

data class TareaState(
    val tarea: TareaEntity = TareaEntity(
        id = 0,
        titulo = "",
        contenido = "",
        descripcion = "",
        multimedia = ""
    ),
    val tareas: List<TareaEntity> = emptyList()
)