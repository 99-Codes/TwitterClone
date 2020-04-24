package com.example.ac_twitterclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class SocialMediaActivity extends AppCompatActivity {

    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

        btnLogout = findViewById(R.id.btnLogout);

    }

    public void onClickLogout (View v) {
        Intent intent = new Intent(SocialMediaActivity.this, Login.class);
        startActivity(intent);
        ParseUser.logOut();
    }
}
