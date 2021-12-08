package com.example.astroidfield;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;


public class Activity_Options extends AppCompatActivity {

    public static final String BUNDLE = "Bundle";



    //bundle variables
    private boolean gameModeTilt;
    private int playerSkinIndex;
    private int volume;
    private int sensitivity;
    private int gameSpeed;

    //seekBar info
    int musicSeekBarStep = 1;
    int musicSeekBarMax = 100;
    int musicSeekBarMin = 0;
    int sensitivitySeekBarStep = 1;
    int sensitivitySeekBarMax = 10;
    int sensitivitySeekBarMin = 1;
    int gameSpeedSeekBarStep = 1;
    int gameSpeedSeekBarMax = 3;
    int gameSpeedSeekBarMin = 0;

    //views
    private Switch tiltMode;
    private MaterialButton buttonExit;
    private ImageView currentSkin;
    private ImageButton leftSkinButton;
    private ImageButton rightSkinButton;
    private SeekBar musicVolume;
    private SeekBar sbSensitivity;
    private SeekBar sbGameSpeed;
    private TextView musicVolumeText;
    private TextView sensitivityText;
    private TextView gameSpeedText;
    private MyDB myDB;
    private Options options;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        findViews();
        getDatabaseFromJson();
        setViews();


        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMenu();
                finish();
            }
        });

        musicVolume.setMax( (musicSeekBarMax - musicSeekBarMin) / musicSeekBarStep);
        musicVolume.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener()
                {
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {}

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {}

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,
                                                  boolean fromUser)
                    {
                        volume = musicSeekBarMin + (progress * musicSeekBarStep);
                        musicVolumeText.setText("Music Volume: " + volume);
                    }
                }
        );

        sbGameSpeed.setMax( (gameSpeedSeekBarMax - gameSpeedSeekBarMin) / gameSpeedSeekBarStep);
        sbGameSpeed.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                sbSensitivity.setMax((sensitivitySeekBarMax - sensitivitySeekBarMin) /
                        sensitivitySeekBarStep);
        sbSensitivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

    private void setViews() {
        currentSkin.setImageResource(Tile.PLAYER_SKIN_ARRAY[playerSkinIndex]);

        tiltMode.setChecked(gameModeTilt);

        musicVolume.setProgress(volume);
        musicVolumeText.setText("Music Volume: " + volume);

        sbGameSpeed.setProgress(gameSpeed);
        String stringGameSpeed;
        switch (gameSpeed){
            case 1:
                stringGameSpeed = "slow";
                break;
            case 2:
                stringGameSpeed = "medium";
                break;
            case 3:
                stringGameSpeed = "fast";
                break;
            default:
                stringGameSpeed = "";
        }
        gameSpeedText.setText("GameSpeed: " + stringGameSpeed);

        sbSensitivity.setProgress(sensitivity);
        sensitivityText.setText("Sensitivity: " + sensitivity);
    }

    private void getDatabaseFromJson() {
        myDB = MyDB.getDB();
        options = myDB.getOptions();
        gameModeTilt = options.isGameModeTilt();
        playerSkinIndex = options.getPlayerSkinIndex();
        volume = options.getVolume();
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

    private void startMenu() {
        setDataBaseToJson();
        Intent myIntent = new Intent(this, Activity_Menu.class);
        startActivity(myIntent);
    }

    private void setDataBaseToJson(){
        options = new Options();
        options.setGameModeTilt(gameModeTilt);
        options.setVolume(volume);
        options.setPlayerSkinIndex(playerSkinIndex);
        myDB.setOptions(options);
        myDB.setDB();
    }

    private void findViews() {
        leftSkinButton = findViewById(R.id.options_BTN_leftButton);
        rightSkinButton = findViewById(R.id.options_BTN_rightButton);
        currentSkin = findViewById(R.id.options_IMAGE_player);
        buttonExit = findViewById(R.id.options_BTN_Exit);
        tiltMode = findViewById(R.id.options_SWITCH_TiltMode);
        musicVolume = findViewById(R.id.options_SEEKBAR_background_volume);
        musicVolumeText = findViewById(R.id.options_TEXTVIEW_text_volume);
        sbGameSpeed = findViewById(R.id.options_SEEKBAR_background_gameSpeed);
        gameSpeedText = findViewById(R.id.options_TEXTVIEW_text_gameSpeed);
        sbSensitivity = findViewById(R.id.options_SEEKBAR_background_sensitivity);
        sensitivityText = findViewById(R.id.options_TEXTVIEW_text_sensitivity);
    }

}