package com.example.astroidfield;

import static java.lang.Math.pow;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.Random;

public class Game {



    private ImageButton buttonLeft, buttonRight;
    private MaterialButton buttonMenu;
    private MaterialTextView timer;
    private MaterialTextView points;
    private ImageView lives[];
    private Tile[][] tiles;


    public final float SENSITIVITY = 2;
    private final int MAX_LIVES = 3;
    private final int NUMBER_OF_LANES = 5;
    private final int NUMBER_OF_LAYERS = 8; // player layer = NUMBER_OF_LAYERS -1


    //for bundles
    public static final String MODE = "MODE";
    public static final String PLAYER_SKIN = "PLAYER_SKIN";




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
    private int numOfPoints=0;
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
            points.setText("Points: " + toStingWithPad(numOfPoints,3));

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

    public void setLastViewedX(float lastViewedX) {
        this.lastViewedX = lastViewedX;
    }
    public void setDelay(int delay) {
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
    public void setPoints(MaterialTextView points) {
        this.points = points;
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
        cleanBoard();
        if(tiltMode){
            buttonLeft.setVisibility(View.INVISIBLE);
            buttonRight.setVisibility(View.INVISIBLE);
        }
        tiles[NUMBER_OF_LAYERS-1][NUMBER_OF_LANES/2].setPlayer();
        currentLives=MAX_LIVES;
        for(int i=0;i<currentLives;i++)
            lives[i].setVisibility(View.VISIBLE);
        odometer=0;
        numOfPoints=0;
        easterEgg=false;
        newAsteroid = true;
        randomEasterEggTimer = rand.nextInt(250) + 50;
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
            if(tiles[NUMBER_OF_LAYERS-1][i].getKind()!=Tile.PLAYER)
                tiles[NUMBER_OF_LAYERS-1][i].setEmpty();
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
                            tiles[i + 1][j].setAsteroid(tiles[i][j].getDrawable());
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
                (hitter.getKind() == Tile.PLAYER && hit.getKind() == Tile.ASTEROID)){ // player was hit by asteroid.
            return loseLife();
        }
        else if((hitter.getKind() == Tile.SUPPLY_CRATE && hit.getKind() == Tile.PLAYER) ||
                (hitter.getKind() == Tile.PLAYER && hit.getKind() == Tile.SUPPLY_CRATE)){
            numOfPoints++;
        }
        return false;

    }
    private boolean loseLife(){ //returns true if player died

        vibrate(500);
        currentLives--;
        lives[currentLives].setVisibility(View.INVISIBLE);
        if(currentLives==0){
            Toast.makeText(context, "You Lose.", Toast.LENGTH_SHORT).show();
            newGame();
            return true;
        }
        else{
            Toast.makeText(context, "Ouch", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void increaseTimer() {
       odometer++;
        if(randomEasterEggTimer==odometer)
            easterEgg=true;
    }

    public void modifyGameByBundle(Bundle b) {
        if(b!=null) {
            setTiltMode(b.getBoolean(Game.MODE, false));
            Tile.setCurrentPlayerSkin(b.getInt(Game.PLAYER_SKIN));
        }
        else
            setDefaultBundle();
    }

    private void setDefaultBundle() {
        setTiltMode(false);
        Tile.setCurrentPlayerSkin(Tile.DEFAULT_PLAYER_SKIN);
    }

}
