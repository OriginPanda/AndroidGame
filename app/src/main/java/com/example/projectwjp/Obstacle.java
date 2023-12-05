package com.example.projectwjp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Obstacle extends Actor{



    private int obsVelocity = 20; //
    private Random random;

    private Impact impact;
    public Obstacle(Context context) {
        size = 200;
        body = new Bitmap[2];

        body[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.baseobstacle1);// przydzielanie obrazu TODO rozne obrazy
        body[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.baseobstacle2);// przydzielanie obrazu TODO rozne obrazy

        body[0] = Bitmap.createScaledBitmap(body[0],size,size,true);
        body[1] = Bitmap.createScaledBitmap(body[1],size,size,true);
        random = new Random();
    }
    public void resetPosition(){
        actX = random.nextInt(GameView.dWidth - getObstacleWidth());
        actY = -200 + random.nextInt(600)*-1;
        obsVelocity = 35+random.nextInt(16);
    }




    public int getObstacleWidth(){
        return body[0].getWidth();
    }
    public int getObstacleHight(){

        return body[0].getHeight();
    }
    public void fall(){
        actY += obsVelocity;
    }
    public boolean ifGroundHit(int dHeight,int gHeight){
        return (actY+getObstacleHight()>=dHeight-gHeight);

    }
    public void onGroundHit(Context context){
        Impact impact = new Impact(context);
        impact.impactX =actX;
        impact.impactY = actY;

    }
    @Override
    public void onHit() {

        resetPosition();
    }

    public Impact getImpact() {
        return impact;
    }
}
