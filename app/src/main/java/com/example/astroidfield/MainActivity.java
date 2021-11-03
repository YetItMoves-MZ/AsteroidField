package com.example.astroidfield;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //TODO: make a video explaining what to do using phone recorder
    //TODO: change buttons to look prettier.
    //TODO: implement player movement.
    //TODO: change player img rotated 180
    final int DELAY = 500;
    final int NUMBER_OF_LANES = 3;
    final int PLAYER_LAYER = 5; // also known as last layer
    final Handler handler = new Handler();

    Tile tiles[][];
    Button buttonLeft, buttonRight;
    TextView timer;
    ImageView lives[];
    int currentLives=3;

    Random rand = new Random();
    boolean easterEgg = false;
    boolean newAsteroid = true;
    private int milliseconds=0, seconds=0,minutes=0;
    private Runnable r = new Runnable() {
        public void run() {

            moveAsteroids();
            if(newAsteroid){
                int newAsteroidLocation= rand.nextInt(NUMBER_OF_LANES);
                tiles[0][newAsteroidLocation].setKind(Tile.ASTEROID,easterEgg);
                Log.d("test", "check visibility: "+tiles[0][newAsteroidLocation].getImg().getVisibility() );
            }
            newAsteroid=!newAsteroid;

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

    private void startTicker() {
        handler.postDelayed(r, DELAY);
    }

    private void stopTicker() {
        handler.removeCallbacks(r);
    }

    private int checkPlayerLocation(){
        for(int i=0 ; i<NUMBER_OF_LANES;i++){
            if(tiles[PLAYER_LAYER][i].getKind()==Tile.PLAYER)
                return i;
        }
        return -1;
    }
    private void moveAsteroids() {
        for(int i=0;i<NUMBER_OF_LANES;i++){//removing all non player objects from player layer
            if(tiles[PLAYER_LAYER][i].getKind()!=Tile.PLAYER)
                tiles[PLAYER_LAYER][i].setKind(Tile.EMPTY,easterEgg);
        }
        for(int i=PLAYER_LAYER-1;i>=0;i--){ // moving all objects one layer down (except player layer)
            for(int j=0;j<NUMBER_OF_LANES;j++){
                if(tiles[i][j].getKind()!=Tile.EMPTY)
                    if(tiles[i+1][j].getKind()==Tile.PLAYER) {
                        hit(tiles[i][j], tiles[i + 1][j]);
                        tiles[i][j].setKind(Tile.EMPTY, easterEgg);
                    }
                    else {
                        tiles[i + 1][j].setKind(tiles[i][j].getKind(), easterEgg);
                        tiles[i][j].setKind(Tile.EMPTY, easterEgg);
                    }
            }
        }
    }
    private void hit(Tile hitter, Tile hit){
       if((hitter.getKind() == Tile.ASTEROID && hit.getKind() == Tile.PLAYER) ||
               (hitter.getKind() == Tile.PLAYER && hit.getKind() == Tile.ASTEROID)){ // player was hit by asteroid.
           loseLife();

       }
    }

    private void loseLife(){
       currentLives--;
       lives[currentLives].setVisibility(View.INVISIBLE);
       if(currentLives==0){
           Toast.makeText(this, "You Lose.", Toast.LENGTH_SHORT).show();
           gameReset();
       }
       else{
           Toast.makeText(this, "Ouch", Toast.LENGTH_SHORT).show();
       }
    }

    private void gameReset() {
        //TODO: implement this func.
    }

    private void findViews(){
        timer=findViewById(R.id.timer);
        buttonLeft=findViewById(R.id.buttonLeft);
        buttonRight=findViewById(R.id.buttonRight);
        lives =new ImageView[]{
                findViewById(R.id.life1),
                findViewById(R.id.life2),
                findViewById(R.id.life3)
        };
        tiles = new Tile[][]{{
                new Tile( findViewById(R.id.panel_IMG_asteroid11), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_asteroid12), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_asteroid13), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_asteroid21), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_asteroid22), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_asteroid23), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_asteroid31), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_asteroid32), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_asteroid33), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_asteroid41), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_asteroid42), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_asteroid43), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_asteroid51), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_asteroid52), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_asteroid53), Tile.EMPTY)
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
        if(minutes>=10)
            easterEgg=true;
    }

    private boolean checkHit(){
        return false;
    }
}