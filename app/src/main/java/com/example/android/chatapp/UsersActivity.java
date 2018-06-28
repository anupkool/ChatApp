package com.example.android.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersActivity extends AppCompatActivity {
    private Toolbar mToolBar;
    private RecyclerView mUsersList;
    private DatabaseReference mUsersDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        mToolBar = (Toolbar) findViewById(R.id.users_appBar);

        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("All Users");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        mUsersList = findViewById(R.id.users_list);
        mUsersList.setHasFixedSize(true);
        mUsersList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Users> options =

                new FirebaseRecyclerOptions.Builder<Users>()
                        .setQuery(mUsersDatabase, Users.class)
                        .build();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Users, UsersViewHolder>(options) {

            @Override

            protected void onBindViewHolder(@NonNull UsersViewHolder holder, int position, @NonNull Users users) {
                holder.setName(users.getName());
                holder.setRole(users.getStatus());
                holder.setImage(users.getImage());
                final String user_id = getRef(position).getKey();
                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent profileIntent = new Intent(UsersActivity.this, ProfileActivity.class);
                        profileIntent.putExtra("user id", user_id);
                        startActivity(profileIntent);

                    }
                });
//....
            }

            @NonNull
            @Override
            public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.users_single_layout, parent, false);
                return new UsersViewHolder(view);
            }
        };
        mUsersList.setAdapter(adapter);
        adapter.startListening();
    }

    @Override

    protected void onStop() {
        super.onStop();
    }


    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(View itemView) {

            super(itemView);
            mView =itemView;
        }

        public void setName(String name) {
            TextView userNameView = mView.findViewById(R.id.user_single_name);
            userNameView.setText(name);

        }
        public void setRole(String role){
            TextView userRole = mView.findViewById(R.id.user_single_status);
            userRole.setText(role);
        }

        public void setImage(String image){
            CircleImageView userImage = mView.findViewById(R.id.user_single_image);
            Picasso.get().load(image).placeholder(R.drawable.default_pic).into(userImage);
        }

    }

}