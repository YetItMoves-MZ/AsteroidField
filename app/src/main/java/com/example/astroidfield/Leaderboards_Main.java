package com.example.astroidfield;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class Leaderboards_Main extends AppCompatActivity {

    private Leaderboards_Score_Fragment fragmentScores;
    private Leaderboards_Maps_Fragment fragmentMap;
    MaterialButton exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards_main);

        findViews();

        fragmentScores = new Leaderboards_Score_Fragment();
        fragmentScores.setActivity(this);
        fragmentScores.setCallBackMap(callBackMap);
        getSupportFragmentManager().beginTransaction().add(R.id.frame1, fragmentScores).commit();

        fragmentMap = new Leaderboards_Maps_Fragment();
        fragmentMap.setActivity(this);
        getSupportFragmentManager().beginTransaction().add(R.id.frame2, fragmentMap).commit();



        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    CallBack_Leaderboards_Scores callBackMap = new CallBack_Leaderboards_Scores() {
        @Override
        public void mapPing(double lon, double lat, String name) {
            fragmentMap.setPing(lon,lat,name);
        }
    };

    private void findViews() {
        exitButton = findViewById(R.id.leaderboards_BTN_back);
    }
}