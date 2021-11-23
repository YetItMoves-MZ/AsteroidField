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



/*
What needs to be done:
TODO: crash sound and crates sounds
TODO: menu with options:                                                        from class-5
    TODO: change mode from 2 bottons to tilt modes
    TODO: change base speed fast/slow
    TODO: sound volume
    TODO: change player skin
TODO: tilt mode (no buttons but can move left and right up and down instead)
TODO: score screen + save scores                                                from class-4
TODO: change from timer to Odometer (Distance counter)
 */
public class Activity_Main extends AppCompatActivity {
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
        g.setPoints(findViewById(R.id.points));
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
                new Tile( findViewById(R.id.panel_IMG_tile13), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile14), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile15), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_tile21), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile22), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile23), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile24), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile25), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_tile31), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile32), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile33), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile34), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile35), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_tile41), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile42), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile43), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile44), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile45), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_tile51), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile52), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile53), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile54), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile55), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_tile61), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile62), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile63), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile64), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile65), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_tile71), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile72), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile73), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile74), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile75), Tile.EMPTY)
        }, {
                new Tile( findViewById(R.id.panel_IMG_tile81), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile82), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile83), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile84), Tile.EMPTY),
                new Tile( findViewById(R.id.panel_IMG_tile85), Tile.EMPTY)

        }});


    }




}