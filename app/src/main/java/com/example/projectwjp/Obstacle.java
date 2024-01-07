package com.example.projectwjp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.widget.TextView;

import java.util.Random;

public class Obstacle extends Actor{


    private int baseVelocity = 35;
    private int obsVelocity; //
    private Random random;
    private TextView number;
    private Impact impact;
    public Obstacle(Context context) {
        size = 200; //TODO skalowanie z ekranem
        body = new Bitmap[2];

        body[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.baseobstacle1);// przydzielanie obrazu
        body[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.baseobstacle2);

        body[0] = Bitmap.createScaledBitmap(body[0],size,size,true);
        body[1] = Bitmap.createScaledBitmap(body[1],size,size,true);
        random = new Random();
    }
    public void resetPosition(){

        actX = random.nextInt(GameView.dWidth - size);
        actY = -200 + random.nextInt(600)*-1;
        obsVelocity = baseVelocity+random.nextInt(30);//TODO w zaleznosci od trudnosci

    }
    public void updatePosition(){
        //TODO update position?

    }



    public void fall(){
        actY += obsVelocity;
    }
    public boolean ifGroundHit(int dHeight,int gHeight){
        return (actY+size>=dHeight-gHeight);

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

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void onTick(Canvas canvas) {

    }

    public Impact getImpact() {
        return impact;
    }
}
