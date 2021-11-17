package com.example.astroidfield;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;


public class Tile {
    private ImageView img;
    private int kind;
    public static final int EMPTY = 0, PLAYER = 1 , ASTEROID = 2, SUPPLY_CRATE = 3;

    public Tile() { }

    public Tile(ImageView img, int kind) {
        this.img = img;
        this.kind = kind;
    }


    public int getKind() {
        return kind;
    }

    public Drawable getDrawable() {
        return img.getDrawable();
    }

    public void setPlayer(){
        kind=PLAYER;
        img.setImageResource(R.drawable.player);
        img.setVisibility(View.VISIBLE);
    }

    public void setEmpty(){
        kind=EMPTY;
        img.setVisibility(View.INVISIBLE);
    }

    public void setAsteroid(Drawable drawable){
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
        }


        this.kind=ASTEROID;
        img.setVisibility(View.VISIBLE);
    }
}
