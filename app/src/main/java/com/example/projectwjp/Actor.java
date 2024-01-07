package com.example.projectwjp;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class Actor {
    protected Bitmap[] body;// ilosc slajdow animacji
    protected int size = 200;
    private int obsFrame = 0;
    protected int animDurr = 2;// klatki
    protected double  animSpeed = 0.2; // w secundach
    private int animState = 0;

    public float actX, actY;

    public void animation(int Hz){
        double x= Hz*animSpeed;
        animState++;
        if(animState>=x){
            obsFrame++;
            if(obsFrame>=animDurr){
                obsFrame = 0;
            }
            animState = 0;
        }


    }
    public Bitmap getbody(){
        return body[obsFrame];
    }

    public abstract void onHit();
    public abstract void draw(Canvas canvas);
    public abstract void onTick(Canvas canvas);

}
