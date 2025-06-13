//FirebaseMessageService.kt
package com.example.commercialcatalog.notifications

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.util.Log

class FirebaseMessageService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("FCM", "Mensaje recibido: ${remoteMessage.data}")

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as android.app.NotificationManager
        val channelId = "default_channel"
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = android.app.NotificationChannel(channelId, "Notificaciones", android.app.NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = androidx.core.app.NotificationCompat.Builder(this, channelId)
            .setContentTitle(remoteMessage.notification?.title ?: "Título")
            .setContentText(remoteMessage.notification?.body ?: "Mensaje recibido")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .build()

        notificationManager.notify(1, notification)
    }
}