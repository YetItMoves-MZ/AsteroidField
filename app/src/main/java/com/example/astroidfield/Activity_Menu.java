package com.example.astroidfield;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
                comingSoon();

            }
        });

        buttonOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comingSoon();
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void comingSoon() {
        Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
    }

    private void startGame() {
        Intent myIntent = new Intent(this, Activity_Game.class);

        /*Bundle bundle = new Bundle();
        bundle.putString(Activity_Panel.SENSOR_TYPE,sns);
        bundle.putString(Activity_Summary.NAME, "Guy");
        bundle.putString(Activity_Summary.GENDER, "Female");

        myIntent.putExtra("Bundle", bundle);*/
        startActivity(myIntent);

    }

    private void findViews(){
        buttonPlay = findViewById(R.id.menu_BTN_Play);
        buttonLeaderboards = findViewById(R.id.menu_BTN_Leaderboards);
        buttonOptions = findViewById(R.id.menu_BTN_Options);
        buttonExit = findViewById(R.id.menu_BTN_Exit);
    }
}