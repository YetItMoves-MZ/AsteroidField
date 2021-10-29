package com.example.astroidfield;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //TODO: change buttons to look prettier.
    final int DELAY = 500;
    final int NUMBER_OF_LANES = 3;
    final int PLAYER_LAYER = 5;
    ImageView Lives[];
    int currentLives=3;
    Button buttonLeft;
    Button buttonRight;
    TextView timer;
    Tile tiles[][];
    Random rand = new Random();
    boolean easterEgg = false; //TODO: implement space sphere easter egg
    boolean newAstroid = true;
    final Handler handler = new Handler();
    private int milliseconds=0, seconds=0,minutes=0;
    private Runnable r = new Runnable() {
        public void run() {

            moveAstroids();
            if(newAstroid){
                int newAstroidLocation= rand.nextInt(NUMBER_OF_LANES);
                newAstroid=false;
                tiles[0][newAstroidLocation].setKind(Tile.ASTROID);
            }
            else{
                newAstroid=true;
            }

            increaseTimer();
            timer.setText("Time: " + minutes + ":"+ seconds + ":" + milliseconds);
            handler.postDelayed(r, DELAY);
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();



    }

    @Override
    protected void onStart() {
        super.onStart();
        startTicker();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopTicker();
    }

    private int checkPlayerLocation(){
        //TODO: implement func
        for(int i=0 ; i<NUMBER_OF_LANES;i++){

        }
        return -1;
    }
    private void moveAstroids() {
        //TODO: implement func
    }
    private void hit(){
        //TODO: implement func
    }
    private void startTicker() {
        handler.postDelayed(r, DELAY);
    }

    private void stopTicker() {
        handler.removeCallbacks(r);
    }

    private void findViews(){
        timer=findViewById(R.id.timer);
        buttonLeft=findViewById(R.id.buttonLeft);
        buttonRight=findViewById(R.id.buttonRight);
        Lives=new ImageView[]{
                findViewById(R.id.life1),
                findViewById(R.id.life2),
                findViewById(R.id.life3)
        };
        tiles = new Tile[][]{{
                new Tile( findViewById(R.id.panel_IMG_astroid11), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_astroid12), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_astroid13), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_astroid21), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_astroid22), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_astroid23), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_astroid31), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_astroid32), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_astroid33), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_astroid41), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_astroid42), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_astroid43), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_astroid51), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_astroid52), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_astroid53), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_player1), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_player2), Tile.PLAYER),
                new Tile( findViewById(R.id.panel_IMG_player3), Tile.EMPTY)

        }};

    }

    private void increaseTimer() {
        milliseconds+=DELAY/10;
        if(milliseconds>=100){
            milliseconds-=100;
            seconds++;
            if(seconds>=60){
                seconds-=60;
                minutes++;
            }
        }
        if(minutes>=60)
            easterEgg=true;
    }

    private boolean checkHit(){
        return false;
    }
}