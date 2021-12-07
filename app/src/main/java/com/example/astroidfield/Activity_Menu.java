package com.example.astroidfield;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;


/*
What needs to be done:
menu with options:
    TODO: change base speed fast/slow                                   update 2
    TODO: change sensitivity for tilt mode                              update 2
TODO: score screen + save scores                                        update 3 from class-4
 */

public class Activity_Menu extends AppCompatActivity {

    private MaterialButton buttonPlay;
    private MaterialButton buttonLeaderboards;
    private MaterialButton buttonOptions;
    private MaterialButton buttonExit;
    private Bundle optionsBundle;

    private MyDB myDB;
    private Gson gson;
    private Options options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViews();
        setBundle();


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

    private void setBundle() {
        gson = new Gson();
        String js = MSPV3.getMe().getString("MY_DB", "");
        myDB = gson.fromJson(js, MyDB.class);
        if(myDB == null){ // will only enter on first time opening app
            myDB = new MyDB();
            myDB.setDefaultDB();
            //set json
            String json = new Gson().toJson(myDB);
            MSPV3.getMe().putString("MY_DB", json);
        }
        optionsBundle = myDB.getOptions().createOptionsBundle();
    }
}