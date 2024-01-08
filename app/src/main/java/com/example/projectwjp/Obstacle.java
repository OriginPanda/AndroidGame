package com.example.projectwjp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Obstacle extends Actor{


    private int baseVelocity = 10;
    private int obsVelocity; //
    private Random random;
    private Paint numberPaint;
    private int q = 0;
    private int diffLevel;



    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {


        this.number = number;

    }

    private int number;
    private Impact impact;
    public Obstacle(Context context,Paint numberPaint,int q, int diffLevel) {

        size = 200; //TODO skalowanie z ekranem

        this.q=q;
        this.diffLevel = diffLevel;

        body = new ArrayList<>();
        this.numberPaint = numberPaint;

        // przydzielanie obrazu
        body.add(0,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.baseobstacle1),size,size,true));
        body.add(1,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.baseobstacle2),size,size,true));
        random = new Random();
    }
    public void resetPosition(){
        //int full = (GameView.dWidth - size);
        int bound = (GameView.dWidth - size)/(diffLevel+2);

        actX = random.nextInt((q+1)*bound-(q*bound))+(q*bound);

        actY = -200 + random.nextInt(600)*-1;
        obsVelocity = baseVelocity+random.nextInt(5);//TODO w zaleznosci od trudnosci

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
        resetPosition();

    }
    @Override
    public void onHit() {

        resetPosition();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(getbody(),actX,actY,null);
        canvas.drawText(String.valueOf(getNumber()),actX+size/2, (float) (actY+size/1.3),numberPaint);
    }



    public Impact getImpact() {
        return impact;
    }
}
