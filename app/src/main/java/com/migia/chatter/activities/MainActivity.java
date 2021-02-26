package com.migia.chatter.activities;

import android.os.Bundle;

import com.migia.chatter.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar bar = findViewById(R.id.toolbar);
        setSupportActionBar(bar);
        getSupportActionBar().setTitle("Chatters");
        // getSupportActionBar().setCustomView(R.layout.toolb);

        //this.setActionBar(bar);

    }
}