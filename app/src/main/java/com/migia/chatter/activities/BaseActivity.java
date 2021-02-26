package com.migia.chatter.activities;

import android.widget.Toast;

import com.migia.chatter.model.User;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private static User user;

    public void setUser(User user) {
        this.user = user;
        Toast.makeText(this, "The new User data has just been saved to the baseActivity", Toast.LENGTH_SHORT).show();
    }

    public User getUser() {
        return user;
    }

    public void show(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
