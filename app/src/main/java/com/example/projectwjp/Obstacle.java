package com.example.projectwjp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Obstacle {
    private Bitmap [] body = new Bitmap[1];
    int obsFrame = 0;
    int animFrame = 1;
    int obsX , obsY, obsVelocity;
    Random random;

    public Obstacle(Context context) {
        this.body[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.hero);// przydzielanie obrazu TODO rozne obrazy
        random = new Random();
    }
    public void resetPosition(){
        obsX = random.nextInt(GameView.dWidth - getObstacleWidth());
        obsY= -200 + random.nextInt(600)*-1;
        obsVelocity = 35+random.nextInt(16);
    }

    public Bitmap getbody(){   //TODO eweontualna animacja
        return body[obsFrame];
    }

    public int getObstacleWidth(){
        return body[0].getWidth();
    }
    public int getObstacleHight(){

        return body[0].getHeight();
    }

}
