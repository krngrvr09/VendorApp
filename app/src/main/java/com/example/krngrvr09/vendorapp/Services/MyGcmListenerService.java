package com.example.krngrvr09.vendorapp.Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.krngrvr09.vendorapp.Activities.MainActivity;
import com.example.krngrvr09.vendorapp.Helpers.DataDownload;
import com.example.krngrvr09.vendorapp.R;
import com.google.android.gms.gcm.GcmListenerService;


/**
 * Created by siddharth on 11/21/15.
 */
public class MyGcmListenerService extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";

    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String order_id = data.getString("order_id");
        DataDownload download = new DataDownload();

//        Log.d(TAG, "From: " + from);
//        Log.d(TAG, "Message: " + message);
//
//        if (from.startsWith("/topics/")) {
//            // message received from some topic.
//        } else {
//            // normal downstream message.
//        }

//        sendNotification(order_id);

        // [END_EXCLUDE]
    }



    private void sendNotification(String orderId) {
        Intent intent = new Intent(this, MainActivity.class);       //Change name of activity.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("orderId",orderId);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ingredients24black)        //change icon in notification
                .setContentTitle("New Order Received")
                .setContentText("Click to see order")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);



        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());//TODO:: ID OF notification
    }
}