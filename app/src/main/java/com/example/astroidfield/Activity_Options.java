package com.example.astroidfield;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Activity_Options extends AppCompatActivity {

    public static final String BUNDLE = "Bundle";

    //bundle variables
    private boolean gameModeTilt=false;
    private int playerSkinIndex = 0;

    //views
    private Bundle bundle;
    private Switch tiltMode;
    private MaterialButton buttonExit;
    private ImageView currentSkin;
    private ImageButton leftSkinButton;
    private ImageButton rightSkinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        bundle = new Bundle();
        findViews();
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
                    gameModeTilt=true;
                }
                else{
                    gameModeTilt=false;
                }
            }
        });
        leftSkinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPlayerSkin(true);
            }
        });
        rightSkinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPlayerSkin(false);
            }

        });

    }

    private void setPlayerSkin(boolean pressedLeft) {
        if(pressedLeft){
            playerSkinIndex--;
            if(playerSkinIndex<0){
                playerSkinIndex=Tile.PLAYER_SKIN_ARRAY_SIZE-1;
            }
        }
        else{
            playerSkinIndex++;
            if(playerSkinIndex>=Tile.PLAYER_SKIN_ARRAY_SIZE){
                playerSkinIndex=0;
            }
        }
        currentSkin.setImageResource(Tile.PLAYER_SKIN_ARRAY[playerSkinIndex]);
    }

    private void setBundle() {
        bundle.putInt(Game.PLAYER_SKIN,playerSkinIndex);
        bundle.putBoolean(Game.MODE,gameModeTilt);
    }

    private void findViews() {
        leftSkinButton = findViewById(R.id.options_BTN_leftButton);
        rightSkinButton = findViewById(R.id.options_BTN_rightButton);
        currentSkin = findViewById(R.id.options_IMAGE_player);
        buttonExit = findViewById(R.id.options_BTN_Exit);
        tiltMode = findViewById(R.id.options_SWITCH_TiltMode);


    }

    private void startMenu() {
        Intent myIntent = new Intent(this, Activity_Menu.class);
        setBundle();
        myIntent.putExtra(BUNDLE, bundle);
        startActivity(myIntent);
    }

}