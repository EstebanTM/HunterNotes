package com.huntersoul.hunternotes.alarmManager

import java.time.LocalDateTime

data class AlarmItem(
    val time: LocalDateTime,
    val title: String?,
    val message: String?
)