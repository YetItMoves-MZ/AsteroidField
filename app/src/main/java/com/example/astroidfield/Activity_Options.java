package com.example.astroidfield;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Activity_Options extends AppCompatActivity {

    public static final String BUNDLE = "Bundle";

    private Bundle bundle;
    private Switch tiltMode;
    private MaterialButton buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        bundle = new Bundle();
        findViews();
        defaultBundle();

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMenu();
                finish();
            }
        });

        tiltMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {

                    bundle.putBoolean(Game.MODE,true);
                }
                else{
                    bundle.putBoolean(Game.MODE,false);
                }
            }
        });
    }

    private void defaultBundle() {
    }

    private void findViews() {

        buttonExit = findViewById(R.id.options_BTN_Exit);
        tiltMode = findViewById(R.id.options_SWITCH_TiltMode);
    }

    private void startMenu() {
        Intent myIntent = new Intent(this, Activity_Menu.class);
        myIntent.putExtra(BUNDLE, bundle);
        startActivity(myIntent);
    }

}