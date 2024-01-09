package com.example.projectwjp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

public class Obstacle extends Actor{


    private final int BASE_VELOCITY = 20;
    private int obsVelocity; //
    private Random random;
    private Paint numberPaint;
    private int q = 0;
    private int diffLevel;
    private int number;
    private Impact impact;

    ArrayList<Bitmap> testAnim;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {


        this.number = number;

    }


    public Obstacle(Context context,Paint numberPaint,int q, int diffLevel) {

        super();
        size = 200; //TODO skalowanie z ekranem

        this.q=q;
        this.diffLevel = diffLevel;

        //body = new ArrayList<>();
        this.numberPaint = numberPaint;
        // przydzielanie obrazu

        body.add(0,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.pixilframe0),size,size,true));
        body.add(1,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.pixilframe1),size,size,true));
        body.add(2,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.pixilframe2),size,size,true));
        body.add(3,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.pixilframe3),size,size,true));

        testAnim = new ArrayList<>();
        testAnim.add(0,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.baseobstacle1),size,size,true));
        testAnim.add(1,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.baseobstacle2),size,size,true));
        currentFrame = body.get(0);
        random = new Random();
    }
    public void resetPosition(){
        //int full = (GameView.dWidth - size);
        int bound = (GameView.dWidth)/(diffLevel+2);

        actX = random.nextInt((q+1)*bound-(q*bound)-size)+(q*bound);

        actY = -200 + random.nextInt(600)*-1;
        obsVelocity = BASE_VELOCITY +random.nextInt(5)*diffLevel;//TODO w zaleznosci od trudnosci

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


    public void draw(Canvas canvas) {
        canvas.drawBitmap(getbody(),actX,actY,null);
        canvas.drawText(String.valueOf(getNumber()),actX+size/2, (float)(actY+size/1.3),numberPaint);
    }



    public Impact getImpact() {
        return impact;
    }
}
