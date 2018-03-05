package com.example.o2.anayumeapp;

import android.app.Activity;
import android.content.Intent;
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

        Button button1 = (Button)findViewById(R.id.button_label1);
        imageView = (ImageView)findViewById(R.id.background);

        fadeinXml();


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Button1Activity.class);
                startActivity(intent);
            }
        });
    }
    private void fadeinXml() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_fadein);
        imageView.startAnimation(animation);
    }
}
