package com.migia.chatter.database;

import android.app.Activity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MessageManager {
    public static final String userPath = "users";
    public static final String friends_path = "friends";
    public static final String message_path = "message";

    FirebaseFirestore firestore;
    private static final MessageManager manager = new MessageManager();

    private MessageManager() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void registerCallback(Activity activity, UpdataMessage update) {
        firestore.collection(userPath).addSnapshotListener(activity, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e == null) {
                    Toast.makeText(activity, "Registration of listener on the path " + userPath + " is successful", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    public boolean send(Activity activity, String message) {
        HashMap<String, Object> messages = new HashMap<>();
        messages.put(message_path, message);

        firestore.collection(userPath).document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("messages")
                .add(messages).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(activity, "Message has been uploaded successfully", Toast.LENGTH_SHORT).show();

                } else
                    Toast.makeText(activity, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
        return false;
    }

    public List<String> getMessages(Activity activity) {
        Task<QuerySnapshot> query = firestore.collection(userPath).document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("messages").get();
        for (DocumentSnapshot ref : query.getResult().getDocuments()) {
            // Toast.makeText(activity, ref.get(message_path), Toast.LENGTH_SHORT).show();
        }
        return null;
    }


}
