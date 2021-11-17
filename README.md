## Mobile Presentation

# Chosen topic: Notifications

The topic I chose to cover was push-notifications. These can be fired from a variety of events within your app, and are useful to inform your user about changes in state when they dont currently have the app open in front of them, or to store information for later in the task bar.

# Required Dependencies
```
dependencies {
  implementation("androidx.core:core-ktx:1.6.0")
}
```
In order to use notifications, you need to have the core-ktx dependency in your module build.gradle file.
Portions of this code were gathered from the android developer tutorial page on notifcations, AndroidDev, 2021-november-11,Create a notification, https://developer.android.com/training/notify-user/build-notification
For information on implementing larger notifications, reference the website mentioned above.
/n
# Code implementation

```
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
            notify(notificationId, builder.build())
        }}
    }
    var builder = NotificationCompat.Builder(this, NotificationCompat.EXTRA_CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
            //sets the title
        .setContentTitle("Test")
            //sets the text contained within
        .setContentText("test")
        //sets the level of importance that determines notifications, what it appears over, if sound will play etc
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    
    //registers the notification with the system, so that it knows to expect notifications
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Test notification channel"
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
```
Lets take a look at what each of these pieces is doing. /n

The variable "builder" is where the content that is sent in the notification is set, as well as how intrusive this notification is allowed to be. /n

The function "createNotificationChannel" is what connects your app with the system its running on.
The details of the channel are pased through the notification manager
"Notification Channels provide us with the ability to group the notifications that our application sends into manageable groups" - Birch J , 2017 march 23, Exploring Android 0: Notification Channels, https://medium.com/exploring-android/exploring-android-o-notification-channels-94cd274f604c
/n
Lastly, we need to attach the built notification to an event, this could be anything, but for simplicity, I have it running on a button click.

| Values of note | Description |
| -------------- | ----------- |
| CHANNEL_ID | Identifier that groups notifications together in task bar |
| NotificationId | Identifier that allows for the app to update or delete notifications in future |

# Potential implementaions into my travelling app:
  Send a notification when the user comes within a certain distance of the tourist attractions letting them know of their proximity to it. /n
  Send a notification of one common phrase of the week to challenge the user to fit it into a conversation.
  
References: 
https://developer.android.com/training/notify-user/build-notification
https://medium.com/exploring-android/exploring-android-o-notification-channels-94cd274f604c
