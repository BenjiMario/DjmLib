package com.example.djmsdk.fcm;

import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.djmsdk.tools.Utils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

/*
 *Copyright (c) Benjinn
 *(C) Benjinn 2019
 */
public class FcmService extends FirebaseMessagingService {
    public FcmService() {
    }


    @Override
    public void onNewToken(String token) {
        Log.e("FCM", "Refreshed token: " + token);
        //Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //sendRegistrationToServer(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
               if (remoteMessage.getData().size() > 0) {
            Log.e("FCM", "Message data payload: " + remoteMessage.getData());
            JSONObject received = new JSONObject(remoteMessage.getData());
            Log.e("FCM", "OBJECT ? : " + received.toString());

               }


        if (remoteMessage.getNotification() != null) {
            Log.e("FCM", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

    }
}