package com.example.astroidfield;

import static java.lang.Math.pow;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game {



    private MediaPlayer soundBackgroundMusic;
    private MediaPlayer inGameSoundCrash;
    private MediaPlayer inGameSoundCollect;
    private MediaPlayer inGameSoundSphere;
    private ImageButton buttonLeft, buttonRight;
    private MaterialButton buttonMenu;
    private MaterialTextView timer;
    private MaterialTextView score;
    private ImageView lives[];
    private Tile[][] tiles;
    private MySensor mySensor;

    public final float SENSITIVITY = 2;
    private final int MAX_LIVES = 3;
    private final int NUMBER_OF_LANES = 5;
    private final int NUMBER_OF_LAYERS = 8; // player layer = NUMBER_OF_LAYERS -1
    private final static int MAX_VOLUME = 100;
    private final int MAX_DELAY = 700;
    private final int MIN_DELAY = 300;
    private final int MIN_EASTER_EGG_TIMER = 50;
    private final int MAX_EASTER_EGG_TIMER = 100;


    //for bundles
    public static final String MODE = "MODE";
    public static final String PLAYER_SKIN = "PLAYER_SKIN";
    public static final String VOLUME = "VOLUME";




    private boolean tiltMode = false;
    private boolean tiltModeInitialized = false;
    private float initializedX;
    private float initializedY;
    private float lastViewedX;



    private int delay = 500;
    private Vibrator v;
    private Activity_Game context;
    private final Handler handler = new Handler();
    private int currentLives=3;
    private int randomEasterEggTimer;
    private Random rand = new Random();
    private boolean easterEgg = false;
    private boolean newAsteroid = true;
    private int odometer=0;
    private int numOfScore =0;
    private int backgoundVolume = 50;
    private String  name = null;
    private MyDB myDB =  MyDB.getDB();
    private ArrayList<Record> records = myDB.getRecords();
    private int highScoreIndex;
    double lat=0;
    double lon=0;

    private Runnable r = new Runnable() {
        public void run() {
            moveObjects();
            increaseTimer();
            if(newAsteroid){
                int newAsteroidLocation= rand.nextInt(NUMBER_OF_LANES);
                tiles[0][newAsteroidLocation].createAsteroid(easterEgg);
                easterEgg=false; //easter egg happens only once per game
            }
            else{
                int newCrateLocation= rand.nextInt(NUMBER_OF_LANES);
                tiles[0][newCrateLocation].setSupplyCrate();
            }
            newAsteroid=!newAsteroid;

            timer.setText("Odometer: " + toStingWithPad(odometer,3));
            score.setText("Points: " + toStingWithPad(numOfScore,3));

            handler.postDelayed(r, delay);
        }
    };



    public Game(){}
    public Game(Vibrator v, Activity_Game context) {
        this.v=v;
        this.context=context;
    }

    public float getLastViewedX() {
        return lastViewedX;
    }
    public int getDelay() {
        return delay;
    }
    public boolean isTiltModeInitialized() {
        return tiltModeInitialized;
    }
    public float getInitializedX() {
        return initializedX;
    }
    public float getInitializedY() {
        return initializedY;
    }
    public ImageButton getButtonRight() {
        return buttonRight;
    }
    public ImageButton getButtonLeft() {
        return buttonLeft;
    }
    public MaterialButton getButtonMenu() {
        return buttonMenu;
    }
    public  Handler getHandler() {
        return handler;
    }
    public  Runnable getRunnable() {
        return r;
    }
    public boolean getTiltMode() {
        return tiltMode;
    }
    public MySensor getMySensor() {
        return mySensor;
    }

    public void setMySensor(MySensor mySensor) {
        this.mySensor = mySensor;
    }
    public void setLastViewedX(float lastViewedX) {
        this.lastViewedX = lastViewedX;
    }
    public void setDelay(int delay) {
        if(delay>MAX_DELAY)
            this.delay = MAX_DELAY;
        else if (delay<MIN_DELAY)
            this.delay = MIN_DELAY;
        else
            this.delay = delay;
    }
    public void setTiltModeInitialized(boolean tiltModeInitialized) {
        this.tiltModeInitialized = tiltModeInitialized;
    }
    public void setInitializedX(float initializedX) {
        this.initializedX = initializedX;
    }
    public void setInitializedY(float initializedY) {
        this.initializedY = initializedY;
    }
    public void setTimer(MaterialTextView timer) {
        this.timer = timer;
    }
    public void setScore(MaterialTextView score) {
        this.score = score;
    }
    public void setTiltMode(boolean tiltMode) {
        this.tiltMode = tiltMode;
    }
    public void setButtonRight(ImageButton buttonRight) {
        this.buttonRight = buttonRight;
    }
    public void setLives(ImageView[] lives) {
        this.lives = lives;
    }
    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }
    public void setButtonLeft(ImageButton buttonLeft) {
        this.buttonLeft = buttonLeft;
    }
    public void setButtonMenu(MaterialButton buttonMenu) {
        this.buttonMenu = buttonMenu;
    }

    private String toStingWithPad(int num, int numOfPadding){
        String str=Integer.toString(num);

        for(int i=0;i<numOfPadding;i++){
            if(num<pow(10,numOfPadding))
                str="0"+str;
            num*=10;
        }



        return str;
    }

    public  void newGame() {
        checkForGpsPermission();
        getGpsFromPlayer();
        cleanBoard();
        if(getTiltMode()){
            mySensor = new MySensor();
            mySensor.initSensor(context);
            mySensor.setCallBack_sensors(new CallBack_Sensors() {
                @Override
                public void getData(float x, float y) {
                    movementWithTilt(x,y);
                }
            });
        }
        if(tiltMode){
            buttonLeft.setVisibility(View.INVISIBLE);
            buttonRight.setVisibility(View.INVISIBLE);
        }
        tiles[NUMBER_OF_LAYERS-1][NUMBER_OF_LANES/2].setPlayer();
        currentLives=MAX_LIVES;
        for(int i=0;i<currentLives;i++)
            lives[i].setVisibility(View.VISIBLE);
        odometer=0;
        numOfScore =0;
        easterEgg=false;
        newAsteroid = true;
        randomEasterEggTimer = rand.nextInt(MAX_EASTER_EGG_TIMER-MIN_EASTER_EGG_TIMER) + MIN_EASTER_EGG_TIMER;
        createMusic();
    }

    private void cleanBoard() {
        for(int i=0;i<NUMBER_OF_LAYERS;i++){
            for(int j=0;j<NUMBER_OF_LANES;j++){
                tiles[i][j].setEmpty();
            }
        }
    }

    public void startTicker() {
        getHandler().postDelayed(getRunnable(), delay);
    }
    public void stopTicker() {
        getHandler().removeCallbacks(getRunnable());
    }

    private void vibrate(int x) {
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(x, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(x);
        }
    }

    public void movePlayer(boolean movingLeft) {
        int playerLocation = checkPlayerLocation();
        int moveTo;
        if(movingLeft) {
            if (tiles[NUMBER_OF_LAYERS - 1][0].getKind() == Tile.PLAYER)
                return;
            else
                moveTo = playerLocation - 1;
        }
        else {
            if (tiles[NUMBER_OF_LAYERS - 1][NUMBER_OF_LANES - 1].getKind() == Tile.PLAYER)
                return;
            else
                moveTo = playerLocation + 1;
        }
        if(!checkHit(tiles[NUMBER_OF_LAYERS-1][playerLocation],tiles[NUMBER_OF_LAYERS-1][moveTo])) {
            tiles[NUMBER_OF_LAYERS - 1][playerLocation].setEmpty();
            tiles[NUMBER_OF_LAYERS - 1][moveTo].setPlayer();
        }
    }
    private void moveObjects() {
        for(int i=0;i<NUMBER_OF_LANES;i++){//removing all non player objects from player layer
            if(tiles[NUMBER_OF_LAYERS-1][i].getKind()!=Tile.PLAYER){
                if(tiles[NUMBER_OF_LAYERS-1][i].getKind() == Tile.SPACE_SPHERE){
                    inGameSoundSphere =createSoundEffect(inGameSoundSphere, R.raw.spaaaaaaaace);
                }
                tiles[NUMBER_OF_LAYERS-1][i].setEmpty();
            }
        }
        for(int i=NUMBER_OF_LAYERS-2;i>=0;i--){ // moving all objects one layer down (except player layer)
            for(int j=0;j<NUMBER_OF_LANES;j++){

                if(tiles[i+1][j].getKind()==Tile.PLAYER){ // if player is about to get hit
                    if(checkHit(tiles[i][j], tiles[i + 1][j])){ //if player died
                        return;
                    }
                }
                else{
                    switch(tiles[i][j].getKind()){ // movement
                        case Tile.ASTEROID:
                        case Tile.SPACE_SPHERE:
                            tiles[i + 1][j].setAsteroid(tiles[i][j].getDrawable(), tiles[i][j].getKind()==Tile.SPACE_SPHERE);
                            break;

                        case Tile.SUPPLY_CRATE:
                            tiles[i + 1][j].setSupplyCrate();
                            break;

                        default:
                            break;
                    }
                }
                tiles[i][j].setEmpty();
            }
        }
    }

    private int checkPlayerLocation(){
        for(int i=0 ; i<NUMBER_OF_LANES;i++){
            if(tiles[NUMBER_OF_LAYERS-1][i].getKind()==Tile.PLAYER)
                return i;
        }
        return -1;
    }
    private boolean checkHit(Tile hitter, Tile hit){ //returns true if player died
        if((hitter.getKind() == Tile.ASTEROID && hit.getKind() == Tile.PLAYER) ||
                (hitter.getKind() == Tile.PLAYER && hit.getKind() == Tile.ASTEROID) ||
                (hitter.getKind() == Tile.SPACE_SPHERE && hit.getKind() == Tile.PLAYER) ||
                (hitter.getKind() == Tile.PLAYER && hit.getKind() == Tile.SPACE_SPHERE)){ // player was hit by asteroid.
            inGameSoundCrash = createSoundEffect(inGameSoundCrash, R.raw.hit);
            return loseLife();
        }
        else if((hitter.getKind() == Tile.SUPPLY_CRATE && hit.getKind() == Tile.PLAYER) ||
                (hitter.getKind() == Tile.PLAYER && hit.getKind() == Tile.SUPPLY_CRATE)){ // player was hit by crate.
            inGameSoundCollect = createSoundEffect(inGameSoundCollect, R.raw.collect);
            numOfScore++;

        }
        return false;

    }
    private boolean loseLife(){ //returns true if player died

        vibrate(500);
        currentLives--;
        lives[currentLives].setVisibility(View.INVISIBLE);
        if(currentLives==0){
            Toast.makeText(context, "You Lose.", Toast.LENGTH_SHORT).show();
            if(!checkTopTen()){
                newGame();
            }
            else{//game stop
                if(tiltMode){
                    mySensor.pauseSensor();
                }
                delay = Integer.MAX_VALUE;
            }
            return true;
        }
        else{
            Toast.makeText(context, "Ouch", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private boolean checkTopTen() {
        for(int i=9;i>=0;i--){
            if(records.get(i).getScore()<numOfScore){
                getNameFromPlayer();
                highScoreIndex=i;
                return true;
            }
        }
        return false;
    }

    private void getNameFromPlayer() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("New High Score");


        // Set up the input
        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = input.getText().toString();
                setNewHighScore(name);
                context.finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = "no name given";
                setNewHighScore(name);
                dialog.cancel();
                context.finish();
            }
        });

        builder.show();
    }

    private void setNewHighScore(String name) {
        records.set(highScoreIndex,new Record(name,odometer,numOfScore,lat,lon));
        Collections.sort(records);
        myDB.setRecords(records);
        myDB.setDB();
    }

    private void getGpsFromPlayer() {
        LocationManager locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        boolean gotGPSPermission = !(context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED);
        if(gotGPSPermission) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                   lon = location.getLongitude();
                   lat = location.getLatitude();
                }
            });
        }
    }

    private void checkForGpsPermission() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(context,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            }
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(context,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

            }
        }
    }

    private void increaseTimer() {
       odometer++;
        if(randomEasterEggTimer==odometer){
            easterEgg=true;
            inGameSoundSphere = createSoundEffect(inGameSoundSphere, R.raw.space_space);
        }
    }

    public void modifyGameByBundle(Bundle b) {
        if(b!=null) {
            setTiltMode(b.getBoolean(Game.MODE, false));
            Tile.setCurrentPlayerSkin(b.getInt(Game.PLAYER_SKIN));
            backgoundVolume = b.getInt(Game.VOLUME);
        }
        else
            setDefaultBundle();
    }

    private void setDefaultBundle() {
        setTiltMode(false);
        Tile.setCurrentPlayerSkin(Tile.DEFAULT_PLAYER_SKIN);
    }

    private void createMusic() {
        if(soundBackgroundMusic==null) {
            soundBackgroundMusic = createSoundEffect(null,R.raw.background_music);
            soundBackgroundMusic.setLooping(true);
        }
    }

    private MediaPlayer createSoundEffect(MediaPlayer mp, int soundEffect){
        if(mp!=null){
            mp.release();
        }
        mp = MediaPlayer.create(context, soundEffect);
        float volume = (float) (1 - (Math.log(MAX_VOLUME - backgoundVolume) / Math.log(MAX_VOLUME)));
        mp.setVolume(volume,volume);
        mp.start();
        return mp;
    }

    //TODO: move everything from activity game to here
    public void onResume() {
        if(soundBackgroundMusic!=null) {
            soundBackgroundMusic.start();
        }
    }

    public void onPause() {
        soundBackgroundMusic.pause();
    }

    public void movementWithTilt(float x, float y) {
        if (isTiltModeInitialized()) {
            float intervalX = getInitializedX()-x;
            float lastViewedIntervalX = getLastViewedX()-x;
            float intervalY = getInitializedY()-y;
            if(intervalX>SENSITIVITY &&  lastViewedIntervalX>SENSITIVITY){ //move right
                movePlayer(false);
                setLastViewedX(x);
            }
            else if(intervalX<-SENSITIVITY && lastViewedIntervalX<-SENSITIVITY){ //move left
                movePlayer(true);
                setLastViewedX(x);
            }
            else if(lastViewedIntervalX>SENSITIVITY || lastViewedIntervalX<-SENSITIVITY){ // movement was reset
                setLastViewedX(x);
            }
            if(intervalY>SENSITIVITY*2){ //make game faster
                setDelay(getDelay()-((int)intervalY)*3);
            }
            else if(intervalY<-SENSITIVITY*1){ // make game slower
                setDelay(getDelay()-((int)intervalY*12));
            }

        }
        else{
            setInitializedX(x);
            setInitializedY(y);
            setLastViewedX(x);
            setTiltModeInitialized(true);
        }
    }

}
