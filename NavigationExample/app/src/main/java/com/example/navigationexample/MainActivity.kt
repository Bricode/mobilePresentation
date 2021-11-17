package com.example.navigationexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var notificationId: Int = 42069
        createNotificationChannel()
        val notificationButton: Button = findViewById(R.id.notificationButton)
        notificationButton.setOnClickListener{with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, builder.build())
        }}
    }
    var builder = NotificationCompat.Builder(this, NotificationCompat.EXTRA_CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
            //sets the title
        .setContentTitle("Example Title")
            //sets the text contained within
        .setContentText("Example content text")
        //sets the level of importance that determines notifications, what it appears over, if sound will play etc
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    //registers the notification with the system, so that it knows to expect notifications
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Test notification"
            val descriptionText = "This is an example notification channel"
            //tells the system the overall importance of the notifications being sent from this channel
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(NotificationCompat.EXTRA_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}