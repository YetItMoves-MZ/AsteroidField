package com.example.astroidfield;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;


/*
What needs to be done:
menu with options:
    TODO: change base speed fast/slow                                   update 2
    TODO: change sensitivity for tilt mode                              update 2
TODO: score screen + save scores + save options                         update 3 from class-4
 */

public class Activity_Menu extends AppCompatActivity {

    private MaterialButton buttonPlay;
    private MaterialButton buttonLeaderboards;
    private MaterialButton buttonOptions;
    private MaterialButton buttonExit;
    private Bundle optionsBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViews();
        if(getIntent().hasExtra(Activity_Options.BUNDLE)) {
            optionsBundle = getIntent().getExtras().getBundle(Activity_Options.BUNDLE);
        }
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
        finish();
    }

    private void comingSoon() { //TODO: delete this later
        Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
    }

    private void startGame() {
        Intent myIntent = new Intent(this, Activity_Game.class);
        if(optionsBundle!=null)
            myIntent.putExtra(Activity_Options.BUNDLE, optionsBundle);
        startActivity(myIntent);



    }

    private void startLeaderboards(){
        Intent myIntent = new Intent(this, Leaderboards_Main.class);
        if(optionsBundle!=null)
            myIntent.putExtra(Activity_Options.BUNDLE, optionsBundle);
        startActivity(myIntent);
    }

    private void findViews(){
        buttonPlay = findViewById(R.id.menu_BTN_Play);
        buttonLeaderboards = findViewById(R.id.menu_BTN_Leaderboards);
        buttonOptions = findViewById(R.id.menu_BTN_Options);
        buttonExit = findViewById(R.id.menu_BTN_Exit);
    }
}