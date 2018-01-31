package com.example.o2.anayumeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Random;

public class SubActivity extends Activity {

    private TextView textView;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        textView = (TextView)findViewById(R.id.text_label);
        setMessage();
        handler.postDelayed(splashTask, 1000);
        fadeinXml();

    }

    private void setMessage() {
        String[] messages = {
                "良い夢ですね。",
                "そうなんですね。",
                "素敵ですね。"
        };

        Random randomGenerator = new Random();
        int num = randomGenerator.nextInt(messages.length);
        textView.setText(messages[num]);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(splashTask);
    }
    private Runnable splashTask = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(SubActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
    };

    private void fadeinXml() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_fadein);
        textView.startAnimation(animation);
    }
}
