package com.example.astroidfield;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class LeaderboardsMain extends AppCompatActivity {


   MaterialButton exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards_main);

        findViews();

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void findViews() {
        exitButton = findViewById(R.id.leaderboards_BTN_back);
    }
}