package com.pdxx.ms.MAR.MAR_3_15;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.pdxx.ms.R;

/**
 * Created by Administrator on 2018/3/15.
 */

public class NotificationUtil {

    public static void notify(Context context, int resDrawable, Bitmap bigImg, String title, String message, Intent intent) {
        Notification.Builder builder = new Notification.Builder(context);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(resDrawable);
        builder.setLargeIcon(bigImg);
        builder.setContentText(title);
        builder.setContentTitle(message);
        builder.setAutoCancel(true);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
