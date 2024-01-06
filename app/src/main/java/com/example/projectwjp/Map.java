package com.example.projectwjp;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;

public class Map {
    public Paint getHealthPaint() {
        return healthPaint;
    }

    private Paint healthPaint = new Paint();
    protected Bitmap background, ground;



    // TODO różne mapy w zaleznosci od poziomu


    public Map(Bitmap background, Bitmap ground) {

        this.background = background;
        this.ground = ground;
    }
    public void updateHPBar(int hp){

        healthPaint.setColor(Color.GREEN);
        if(hp <= 70 && hp >= 40){
            healthPaint.setColor(Color.YELLOW);
        }
        else if(hp<=40){
            healthPaint.setColor(Color.RED);
        }
    }

}
