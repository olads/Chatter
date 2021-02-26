package com.migia.chatter.database;

import android.app.Activity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;

public class Register {
    private static final FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private static final Register reg = new Register();

    public Register getInstance() {
        return reg;
    }

    public void register(Activity activity, String username, String phone) {
        mFirebaseAuth.createUserWithEmailAndPassword(username, phone).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(activity, "The user is successfully registered", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(activity, "error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
