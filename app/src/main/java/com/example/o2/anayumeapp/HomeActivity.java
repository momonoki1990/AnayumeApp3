package com.example.o2.anayumeapp;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class HomeActivity extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear().apply();
        PreferenceManager.setDefaultValues(this, R.xml.preference, true);

        Button button1 = (Button)findViewById(R.id.button_label1);
        Button button3 = (Button)findViewById(R.id.button_label3);
        imageView = (ImageView)findViewById(R.id.background);

        fadeinXml();


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Button1Activity.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MyPreferenceActivity.class);
                startActivity(intent);
            }
        });
    }
    private void fadeinXml() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_fadein);
        imageView.startAnimation(animation);
    }
}
