package com.example.o2.anayumeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
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
        handler.postDelayed(splashTask, 1000);
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

/*
public class SplashActivity extends AppCompatActivity {

    final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mHandler.postDelayed(mSplashTask, 2000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacks(mSplashTask);
    }

    private Runnable mSplashTask = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);//画面遷移のためのIntentを準備
            startActivity(intent);//実際の画面遷移を開始
            finish();//現在のActivityを削除
        }
    };
}




 */
