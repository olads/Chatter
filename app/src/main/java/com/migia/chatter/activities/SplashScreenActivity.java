package com.migia.chatter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.migia.chatter.R;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    TextView chat, tter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        chat = findViewById(R.id.chat);
        tter = findViewById(R.id.ter);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.horizontal_anim);
        Animation vAnim = AnimationUtils.loadAnimation(this, R.anim.vertical_anim);
        chat.startAnimation(anim);
        tter.startAnimation(vAnim);

        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
        }, 4000);

    }
}