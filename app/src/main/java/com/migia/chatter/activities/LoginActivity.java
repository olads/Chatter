package com.migia.chatter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.migia.chatter.R;
import com.migia.chatter.model.User;

import androidx.annotation.NonNull;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";
    FirebaseAuth auth;
    EditText email, password;
    Button signIn, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.edit_username);
        password = findViewById(R.id.edit_password);
        signIn = findViewById(R.id.sign_in);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Reister button clicked");
                signUp(email.getText().toString(), password.getText().toString());
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "sign in button clicked");
                sign(email.getText().toString(), password.getText().toString());
            }
        });

    }

    public void signUp(String email, String pass) {
        auth.createUserWithEmailAndPassword(email, pass).
                addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "User created successfully ...", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, ChatActivity.class));
                                    User user = new User("username", email, false);
                                    LoginActivity.this.setUser(user);
                                    return;
                                } else
                                    Toast.makeText(LoginActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );

    }

    public void sign(String email, String pass) {
        auth.signInWithEmailAndPassword(email, pass).
                addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "User Signed in successfully ...", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, ChatActivity.class));
                                    User user = new User("username", email, false);
                                    LoginActivity.this.setUser(user);
                                    return;
                                } else
                                    Toast.makeText(LoginActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );

    }
}