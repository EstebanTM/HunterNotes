package com.huntersoul.hunternotes.components

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.huntersoul.hunternotes.R

class NotificationWorker(
    context: Context,
    workerParameters: WorkerParameters
) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        val notaDataString = inputData.getString("Message status") ?: "Nota agregada con exito"
        showNotification("Mensaje", notaDataString)
        val outputData = Data.Builder().putString("work_result", "Nota agregada con éxito").build()
        return Result.success(outputData)
    }

    private fun showNotification(nota: String, desc: String) {
        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "message_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "message_name", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val builder = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle(nota)
            .setContentText(desc)
            .setSmallIcon(R.drawable.outline_notifications_24)

        manager.notify(1, builder.build())
    }

}
