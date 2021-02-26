package com.migia.chatter.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.migia.chatter.R;

import java.util.HashMap;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class ChatActivity extends BaseActivity {

    RecyclerView messages;
    EditText editMessages;
    Button send;
    FirebaseUser user;
    FirebaseFirestore firestore;
    private static DocumentReference userData;
    private static CollectionReference friends;
    private static String mail = "awol@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        messages = findViewById(R.id.messages);
        editMessages = findViewById(R.id.edit_messages);
        send = findViewById(R.id.btn_send);
        user = FirebaseAuth.getInstance().getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
        userData = firestore.collection("users/").document(mail);
        friends = userData.collection("friends");
        //addUser(mail);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(editMessages.getText().toString());
            }
        });


    }

    public void addUser(String userEmail) {
        HashMap<String, Object> emm = new HashMap<>();
        emm.put("email", mail);
        friends.add(mail);
        Toast.makeText(this, "Friend added successfully to the list of friends ", Toast.LENGTH_SHORT).show();
    }




    public void sendMessage(String message) {
        friends.whereEqualTo("email", mail).addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e == null) {
                    Toast.makeText(ChatActivity.this, "Sending message now...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}