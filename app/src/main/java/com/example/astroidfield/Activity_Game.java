package com.example.astroidfield;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class Activity_Game extends AppCompatActivity {
    private Game g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        g=new Game((Vibrator) getSystemService(Context.VIBRATOR_SERVICE), this);

        findViews();

        g.modifyGameByDB();
        g.newGame();
        setListeners();

    }

    @Override
    protected void onResume() {
        super.onResume();
        g.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        g.onPause();
    }

    private void setListeners() {
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

        g.getButtonMenu().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void findViews(){
        g.setTimer(findViewById(R.id.odometer));
        g.setScore(findViewById(R.id.points));
        g.setButtonLeft(findViewById(R.id.buttonLeft));
        g.setButtonRight(findViewById(R.id.buttonRight));
        g.setButtonMenu(findViewById(R.id.game_BTN_menu));
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