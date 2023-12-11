package com.huntersoul.hunternotes.alarmManager

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.huntersoul.hunternotes.network.dataN.DefaultAppContainer

class AlarmApp: Application() {
    lateinit var container: DefaultAppContainer
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        container = DefaultAppContainer()
        super.onCreate()
        val channelId = "alarm_id"
        val channelName = "alarm_name"
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)
    }
}