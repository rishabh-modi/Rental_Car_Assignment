package com.example.rentalcarassignment.Model

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Message
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.rentalcarassignment.R
import com.example.rentalcarassignment.Views.MainActivity


const val channel_id = "1121"
const val channel_name = "default channel"


class FirebasePushNotificationService /* :  FirebaseMessagingService*/ {


    fun authenticateUser() {
        //method to authenticate API calls
    }


    //callback method for receiving notification messages
    fun onNotificationReceived(message: Message) {

        //if message title and messages != null
        createNotification("message_title", "message_body")

    }


    //errors due to context
    @SuppressLint("ServiceCast", "NewApi")
    fun createNotification(title: String, message: String) {
        val notiIntent = Intent(this, MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(
            this, 0, notiIntent, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        var builder = NotificationCompat.Builder(applicationContext, channel_id)
            .setSmallIcon(com.google.android.material.R.drawable.abc_ic_star_black_16dp)
            .setAutoCancel(true).setOnlyAlertOnce(true).setContentIntent(pendingIntent)

        builder = builder.setContent(getRemoteView(title, message))
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notficationChannel =
            NotificationChannel(channel_id, channel_name, NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(notficationChannel)

        notificationManager.notify(0, builder.build())

    }

    @SuppressLint("RemoteViewLayout")
    private fun getRemoteView(title: String, message: String): RemoteViews? {
        val remoteview = RemoteViews("com.example.rentalcarassignment", R.layout.notification)
        remoteview.setTextViewText(R.id.notificationtextView, title)
        remoteview.setTextViewText(R.id.notificationMessagetextView, message)
        return remoteview
    }

}