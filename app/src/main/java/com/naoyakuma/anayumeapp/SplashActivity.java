package com.naoyakuma.anayumeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends Activity {

    private TextView textView;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textView = (TextView)findViewById(R.id.text_label);
        handler.postDelayed(splashTask, 3000);
        fadeinXml();
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(splashTask);
    }
    private Runnable splashTask = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

    private void fadeinXml() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_fadein);
        textView.startAnimation(animation);
    }
}