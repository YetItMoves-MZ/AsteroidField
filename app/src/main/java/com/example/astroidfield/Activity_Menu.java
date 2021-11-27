package com.example.astroidfield;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;


/*
What needs to be done:
TODO: crash sound and crates sounds
TODO: menu with options:                                                        from class-5
    TODO: change base speed fast/slow
    TODO: sound volume
    TODO: change sensitivity for tilt mode
TODO: score screen + save scores + save options                                 from class-4
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
                comingSoon();

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

    private void comingSoon() {
        Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
    }

    private void startGame() {
        Intent myIntent = new Intent(this, Activity_Game.class);
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