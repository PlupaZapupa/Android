package ru.me.a14notification


import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val notificationId = intent.getIntExtra("notification_id", -1)
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        when (intent.action) {
            "dismiss_action" -> notificationManager.cancel(notificationId)
            "capture_action" -> {
                notificationManager.cancel(notificationId)
                val openAppIntent = Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(openAppIntent)
            }
        }
    }
}


