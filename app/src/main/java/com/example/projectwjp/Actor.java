package com.example.projectwjp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;

public abstract class Actor {
    public Actor() {
        body = new ArrayList<>();
    }

    protected ArrayList<Bitmap> body;// ilosc slajdow animacji
    protected int size = 200;
    protected Bitmap currentFrame;
    private int actFrame = 0;// klatki
    protected double  animSpeed = 0.2; // w secundach
    private int animState = 0;

    public float actX, actY;


    public void animation(int Hz,ArrayList<Bitmap> Frames){

        double x= Hz*animSpeed;
        animState++;
        if(animState>=x){
            actFrame++;
            if(actFrame >= Frames.size()){
                actFrame = 0;
            }
            animState = 0;

        }
        currentFrame = Frames.get(actFrame);

    }
    public Bitmap getbody(){
        if(currentFrame!=null){
            return currentFrame;
        }
        else{
            return body.get(0);
        }
    }

    public abstract void onHit();



}
