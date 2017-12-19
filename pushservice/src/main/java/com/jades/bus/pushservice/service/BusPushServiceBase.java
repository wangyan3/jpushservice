package com.jades.bus.pushservice.service;


import com.jades.bus.pushservice.model.IPushNotifyModel;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangyan on 2017/11/2.
 */

public abstract class BusPushServiceBase implements IBusPushService{


    private static final AtomicInteger sNotificationId = new AtomicInteger(1);



    @Override
    public void onShowPushMessage(Context context, Class<? extends Activity> acty, Bundle notifyBundle, IPushNotifyModel iPushNotifyModel) {

        Intent intent = new Intent(context, acty);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        if(notifyBundle!=null){
            intent.putExtras(notifyBundle);
        }


        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, UUID.randomUUID().hashCode(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notifyBuilder =
                new NotificationCompat.Builder(context).setContentTitle(iPushNotifyModel.getTitle())
                        .setContentText(iPushNotifyModel.getContent()).setPriority(NotificationCompat.PRIORITY_MAX)
                        .setSmallIcon(iPushNotifyModel.getIconResId()).setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_ALL | Notification.DEFAULT_SOUND);


        notifyBuilder.setContentIntent(resultPendingIntent);
        if(iPushNotifyModel.getNotifyId()<0){
            manager.notify(sNotificationId.getAndIncrement(), notifyBuilder.build());
        }else{
            manager.notify(iPushNotifyModel.getNotifyId(), notifyBuilder.build());
        }


    }

    @Override
    public String getDesc() {
        return "BusPushServiceBase";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
