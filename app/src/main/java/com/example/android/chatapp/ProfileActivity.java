package com.example.android.chatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private ImageView mProfileImage;

    private TextView mProfileName, mProfileStatus, mProfileFriendCount;

    private Button mProfileSendReq;

    private DatabaseReference mUsersDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String user_id = getIntent().getStringExtra("user id");

        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

        mProfileImage = (ImageView) findViewById(R.id.profile_image);

        mProfileName = (TextView) findViewById(R.id.profile_display_name);
        mProfileStatus = (TextView) findViewById(R.id.profile_display_status);
        mProfileFriendCount = (TextView) findViewById(R.id.profile_totalFriends);

        mProfileSendReq = (Button) findViewById(R.id.profile_send_req_btn);

        mUsersDatabase.addValueEventListner(new ValueEventListener()) {

        }



    }
}
