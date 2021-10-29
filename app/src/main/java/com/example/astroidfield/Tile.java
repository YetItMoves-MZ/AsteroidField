package com.example.astroidfield;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;


public class Tile {
    private ImageView img;
    private int kind;
    public static final int EMPTY = 0, PLAYER = 1 , ASTEROID = 2;
    public Tile() { }

    public Tile(ImageView img, int kind) {
        this.img = img;
        this.kind = kind;
    }

    public int getKind() {
        return kind;
    }

    public ImageView getImg() {
        return img;
    }

    public void setKind(int kind, boolean easterEgg) {
        this.kind = kind;
        switch(kind){
            case PLAYER:
                img.setImageResource(R.drawable.player);
                img.setVisibility(View.VISIBLE);
                break;
            case ASTEROID:
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

                img.setVisibility(View.VISIBLE);
                break;
            case EMPTY:
                img.setVisibility(View.INVISIBLE);
                break;

        }



    }
}
