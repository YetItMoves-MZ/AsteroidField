package com.example.astroidfield;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Game g;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        g=new Game((Vibrator) getSystemService(Context.VIBRATOR_SERVICE), this);
        findViews();
        g.newGame();

        g.getButtonLeft().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                g.movePlayer(true);
            }
        });

        g.getButtonRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                g.movePlayer(false);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        g.startTicker();
    }

    @Override
    protected void onStop() {
        super.onStop();
        g.stopTicker();
    }



    public void findViews(){
        g.setTimer(findViewById(R.id.timer));
        g.setButtonLeft(findViewById(R.id.buttonLeft));
        g.setButtonRight(findViewById(R.id.buttonRight));
        g.setLives(new ImageView[]{
                findViewById(R.id.life1),
                findViewById(R.id.life2),
                findViewById(R.id.life3)
        });

        g.setTiles(new Tile[][]{{
                new Tile( findViewById(R.id.panel_IMG_tile11), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile12), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile13), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_tile21), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile22), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile23), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_tile31), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile32), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile33), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_tile41), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile42), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile43), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_tile51), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile52), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile53), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_tile61), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile62), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile63), Tile.EMPTY)

        }});


    }




}