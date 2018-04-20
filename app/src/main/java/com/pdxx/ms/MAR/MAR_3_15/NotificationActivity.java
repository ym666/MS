package com.pdxx.ms.MAR.MAR_3_15;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.pdxx.ms.R;

/**
 * Created by Administrator on 2018/3/15.
 */

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    Button bt_normal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initview();
    }

    private void initview() {
        bt_normal = findViewById(R.id.bt_normal);
        bt_normal.setOnClickListener(this);
        findViewById(R.id.bt_cuttle).setOnClickListener(this);
        findViewById(R.id.bt_hang).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_normal:
                Notification.Builder builder = new Notification.Builder(this);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net"));
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
                builder.setContentIntent(pendingIntent);
                builder.setSmallIcon(R.mipmap.icon_jszy);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                builder.setContentText("123344");
                builder.setContentTitle("普通通知");
                builder.setAutoCancel(true);
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(0, builder.build());
                break;
            case R.id.bt_cuttle:
                builder = new Notification.Builder(this);
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net"));
                pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
                builder.setContentIntent(pendingIntent);
                builder.setSmallIcon(R.mipmap.icon_jszy);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                builder.setContentText("123344");
                builder.setContentTitle("普通通知");
                builder.setAutoCancel(true);
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.view_remoteview);
                builder.setCustomBigContentView(remoteViews);
                manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(0, builder.build());
                break;
            case R.id.bt_hang:
                builder = new Notification.Builder(this);
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net"));
                pendingIntent = PendingIntent.getActivity(this, 0, intent,0);

                Intent hangIntent = new Intent();
                hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                hangIntent.setClass(this, NotificationActivity.class);
                //如果描述的PendingIntent已经存在，则在产生新的Intent之前会先取消掉当前的
                PendingIntent hangPendingIntent = PendingIntent.getActivity(this, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT);

                builder.setContentIntent(pendingIntent);
                builder.setSmallIcon(R.mipmap.icon_jszy);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                builder.setContentText("123344");
                builder.setContentTitle("普通通知");
                builder.setAutoCancel(true);
                builder.setFullScreenIntent(hangPendingIntent, true);
                manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(2, builder.build());
                break;
        }
    }
}
