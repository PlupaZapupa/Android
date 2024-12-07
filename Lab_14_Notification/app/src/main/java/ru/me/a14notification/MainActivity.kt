package ru.me.a14notification
import android.Manifest
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "dreamland_channel"
    private val NOTIFICATION_ID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Создание канала уведомлений для API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

        val button: Button = findViewById(R.id.notifyButton)
        button.setOnClickListener {
            showNotification()
        }
    }

    private fun createNotificationChannel() {
        val name = "Dreamland Channel"
        val descriptionText = "Channel for Dreamland notifications"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun showNotification() {
        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.icon_1)
        val swordsIcon = BitmapFactory.decodeResource(resources, R.drawable.swords)

        // Intent для кнопки "Захватить"
        val captureIntent = Intent(this, MainActivity::class.java)
        val capturePendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            captureIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        // Intent для кнопки "Отказаться"
        val dismissIntent = Intent(this, DismissReceiver::class.java).apply {
            putExtra("notification_id", NOTIFICATION_ID)
        }
        val dismissPendingIntent: PendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            dismissIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        // Уведомление
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.swords)
            .setLargeIcon(largeIcon)
            .setContentTitle("Уведомление")
            .setContentText("Милорд, пора захватить Дримландию!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            // Звук будет воспроизводиться через MediaPlayer
            .setAutoCancel(true)
            .addAction(
                R.drawable.fire,
                "Захватить",
                capturePendingIntent
            )
            .addAction(
                R.drawable.white_flag,
                "Отказаться",
                dismissPendingIntent
            )
            .build()

        if (NotificationManagerCompat.from(this).areNotificationsEnabled()) {
            if (NotificationManagerCompat.from(this).areNotificationsEnabled()) {
                with(NotificationManagerCompat.from(this)) {
                    notify(NOTIFICATION_ID, notification)
                }
                // Воспроизведение звука через MediaPlayer
                val mediaPlayer = MediaPlayer()
                try {
                    val afd = assets.openFd("notify.wav")
                    mediaPlayer.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                Toast.makeText(this, "Notifications are disabled", Toast.LENGTH_SHORT).show()
            }
        }

        class DismissReceiver : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val notificationManager = NotificationManagerCompat.from(context!!)
                notificationManager.cancel(1) // Удалить уведомление
            }
        }
    }
}
