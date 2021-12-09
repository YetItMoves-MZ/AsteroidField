package com.example.astroidfield;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class Activity_Menu extends AppCompatActivity {

    private MaterialButton buttonPlay;
    private MaterialButton buttonLeaderboards;
    private MaterialButton buttonOptions;
    private MaterialButton buttonExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViews();


        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });

        buttonLeaderboards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLeaderboards();
            }
        });

        buttonOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startOptions();
                finish();
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void startOptions() {
        Intent myIntent = new Intent(this, Activity_Options.class);
        startActivity(myIntent);
    }

    private void startGame() {
        Intent myIntent = new Intent(this, Activity_Game.class);
        startActivity(myIntent);
    }

    private void startLeaderboards(){
        Intent myIntent = new Intent(this, Leaderboards_Main.class);
        startActivity(myIntent);
    }

    private void findViews(){
        buttonPlay = findViewById(R.id.menu_BTN_Play);
        buttonLeaderboards = findViewById(R.id.menu_BTN_Leaderboards);
        buttonOptions = findViewById(R.id.menu_BTN_Options);
        buttonExit = findViewById(R.id.menu_BTN_Exit);
    }
}