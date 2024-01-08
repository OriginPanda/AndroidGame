package com.example.projectwjp;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public abstract class Actor {
    protected ArrayList<Bitmap> body;// ilosc slajdow animacji
    protected int size = 200;

    private int actFrame = 0;
    protected int animDur = 2;// klatki
    protected double  animSpeed = 0.2; // w secundach
    private int animState = 0;

    public float actX, actY;


    public void animation(int Hz){
        double x= Hz*animSpeed;
        animState++;
        if(animState>=x){
            actFrame++;
            if(actFrame >= animDur){
                actFrame = 0;
            }
            animState = 0;
        }


    }
    public Bitmap getbody(){
        return body.get(actFrame);
    }

    public abstract void onHit();
    public abstract void draw(Canvas canvas);


}
