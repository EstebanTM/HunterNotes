package com.huntersoul.hunternotes.alarmManager

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.huntersoul.hunternotes.R

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
        val titulo = intent?.getStringExtra("EXTRA_TITLE") ?: return
        val channelId = "alarm_id"
        context?.let {ctx ->
            val notificationManager = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val builder = NotificationCompat.Builder(ctx, channelId)
                .setSmallIcon(androidx.core.R.drawable.notification_action_background)
                .setContentTitle(titulo)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)

            notificationManager.notify(1, builder.build())
        }
        println("Alarm triggered: $message")
    }
}