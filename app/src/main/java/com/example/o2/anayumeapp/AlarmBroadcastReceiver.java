package com.example.o2.anayumeapp;


import android.app.Notification;
//import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class AlarmBroadcastReceiver extends BroadcastReceiver {

    @Override   // データを受信した
    public void onReceive(Context context, Intent intent) {

        Log.d("AlarmBroadcastReceiver", "onReceive() pid=" + android.os.Process.myPid());

        Intent intents = new Intent(context, SplashActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intents, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Notification notification = builder
                // 通知の日時
                .setWhen(System.currentTimeMillis())
                // 通知のタイトル
                .setContentTitle("あなたの夢はなんですか？")
                // 通知の詳細メッセージ
                .setContentText("タップするとアプリを開きます。")
                // 通知のアイコン
                .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                // 通知を表示した瞬間、通知バーに表示するショートメッセージ
                .setTicker("あなたの夢はなんですか？")
                // 通知をタップした時に使う PendingIntent
                .setContentIntent(pendingIntent)
                // この通知が未だ表示されていない時だけ、音やバイブレーション、ショートメッセージの表示を行う
                .setOnlyAlertOnce(true)
                // タップしたら消えるようにする
                .setAutoCancel(true)
                .build();

        // 直接インスタンス化せず、Context を経由してインスタンスを取得する
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // 通知の種類に応じて id を割当てることが出来る。
        // id の異なる通知は違うものとして扱われる。
        manager.notify(0, notification);



/*
        int requestCode = intent.getIntExtra("RequestCode", 0);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, requestCode, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        // app name
        String title = context.getString(R.string.app_name);

        long currentTime = System.currentTimeMillis();
        SimpleDateFormat dataFormat =
                new SimpleDateFormat("HH:mm:ss", Locale.JAPAN);
        String cTime = dataFormat.format(currentTime);

        // メッセージ　+ 時刻
        String message = "時間になりました。 " + cTime;


        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(
                        Context.NOTIFICATION_SERVICE);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(
                RingtoneManager.TYPE_NOTIFICATION);


        if (notificationManager != null) {

            Notification notification = new Notification.Builder(context)
                    .setContentTitle(title)
                    // android標準アイコンから
                    .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setWhen(System.currentTimeMillis())
                    .build();

            // 通知
            notificationManager.notify(R.string.app_name, notification);
*/

/*
    @Override
    public void onReceive(Context context, Intent intent) {
        //通知がクリックされた時に発行されるIntentの生成
        Intent sendIntent = new Intent(context, SplashActivity.class);
        PendingIntent sender = PendingIntent.getActivity(context, 0, sendIntent, 0);

        //通知オブジェクトの生成
        Notification noti = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                .setTicker("お時間ですよ!")
                .setContentTitle("通知")
                .setContentText("設定した時間がきました")
                .setVibrate(new long[]{0, 200, 100, 200, 100, 200})
                .setAutoCancel(true)
                .setContentIntent(sender)
                .build();
        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, noti);*/

    }
}
