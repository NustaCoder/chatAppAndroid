package com.example.chatapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatapplication.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {
    CircleImageView profile_image;
    TextView username;
    ImageButton btn_send;
    EditText text_send;

    FirebaseUser fUser;
    DatabaseReference reference;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        profile_image = findViewById(R.id.profile_image);
        username = findViewById(R.id.username);

        intent = getIntent();
        final String uid = intent.getStringExtra("uid");
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("user").child(uid);

        if(reference != null){
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    if(user.getUsername().equals("")){
                        username.setText("shlok");
                    }
                    else{
                        username.setText(user.getUsername());
                        profile_image.setImageResource(R.mipmap.ic_launcher);
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else{
            username.setText("jack");
        }

        btn_send = findViewById(R.id.btn_send);
        text_send = findViewById(R.id.text_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = text_send.getText().toString();

                if(!msg.equals("")){
                    sendMessage(fUser.getUid(),uid,msg);
                }
                else{
                    Toast.makeText(ChatActivity.this,"unable to send message", Toast.LENGTH_SHORT).show();
                }
                text_send.setText("");
            }
        });
    }

    private void sendMessage(String sender, String receiver, String message){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String,Object> hashMap = new HashMap<>();

        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("message",message);

        reference.child("chats").setValue(hashMap);

    }
}

