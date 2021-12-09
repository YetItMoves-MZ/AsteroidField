package com.example.astroidfield;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;


public class Tile {
    private ImageView img;
    private int kind;





    private static int currentPlayerSkin;
    public static final int EMPTY = 0, PLAYER = 1 , ASTEROID = 2,
            SUPPLY_CRATE = 3, SPACE_SPHERE = 4;
    public static final int DEFAULT_PLAYER_SKIN = 0;
    public static final int PLAYER_SKIN_ARRAY_SIZE = 9;
    public static final int[] PLAYER_SKIN_ARRAY = {
            R.drawable.player,
            R.drawable.player2,
            R.drawable.player3,
            R.drawable.player4,
            R.drawable.player5,
            R.drawable.player6,
            R.drawable.player7,
            R.drawable.player8,
            R.drawable.player9
    };

    public Tile() { }

    public Tile(ImageView img, int kind) {
        this.img = img;
        this.kind = kind;
        setCurrentPlayerSkin(DEFAULT_PLAYER_SKIN);

    }

    public int getKind() {
        return kind;
    }

    public Drawable getDrawable() {
        return img.getDrawable();
    }
    public static void setCurrentPlayerSkin(int currentPlayerSkin) {
        Tile.currentPlayerSkin = currentPlayerSkin;
    }

    public void setPlayer(){
        kind=PLAYER;
        img.setImageResource(PLAYER_SKIN_ARRAY[currentPlayerSkin]);
        img.setVisibility(View.VISIBLE);
    }

    public void setEmpty(){
        kind=EMPTY;
        img.setVisibility(View.INVISIBLE);
    }

    public void setAsteroid(Drawable drawable, boolean isEaster){
        if(isEaster)
            kind = SPACE_SPHERE;
        else
            kind=ASTEROID;
        img.setImageDrawable(drawable);
        img.setVisibility(View.VISIBLE);
    }

    public void setSupplyCrate(){
        kind=SUPPLY_CRATE;
        img.setImageResource(R.drawable.supply_crate);
        img.setVisibility(View.VISIBLE);
    }

    public void createAsteroid(boolean easterEgg) {
        if(easterEgg){
            img.setImageResource(R.drawable.space_sphere);
            this.kind = SPACE_SPHERE;
        }
        else{
            Random rnd = new Random();
            switch(rnd.nextInt(4)){
                case 0:
                    img.setImageResource(R.drawable.space_meteor1);
                    break;
                case 1:
                    img.setImageResource(R.drawable.space_meteor2);
                    break;
                case 2:
                    img.setImageResource(R.drawable.space_meteor3);
                    break;
                case 3:
                    img.setImageResource(R.drawable.space_meteor4);
                    break;
            }
            this.kind=ASTEROID;
        }
        img.setVisibility(View.VISIBLE);
    }
}
