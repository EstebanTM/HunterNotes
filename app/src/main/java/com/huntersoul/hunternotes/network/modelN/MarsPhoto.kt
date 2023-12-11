package com.huntersoul.hunternotes.network.modelN

import kotlinx.serialization.Serializable

@Serializable
data class MarsPhoto(
    val id: String,  val img_src: String
)