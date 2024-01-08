package com.example.projectwjp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.TextView;

import java.util.Random;

public class Obstacle extends Actor{


    private int baseVelocity = 10;
    private int obsVelocity; //
    private Random random;
    private Paint numberPaint;
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {


        this.number = number;

    }

    private int number;
    private Impact impact;
    public Obstacle(Context context,Paint numberPaint) {
        size = 200; //TODO skalowanie z ekranem
        body = new Bitmap[2];
        this.numberPaint = numberPaint;
        body[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.baseobstacle1);// przydzielanie obrazu
        body[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.baseobstacle2);

        body[0] = Bitmap.createScaledBitmap(body[0],size,size,true);
        body[1] = Bitmap.createScaledBitmap(body[1],size,size,true);
        random = new Random();
    }
    public void resetPosition(){

        actX = random.nextInt(GameView.dWidth - size);
        actY = -200 + random.nextInt(600)*-1;
        obsVelocity = baseVelocity+random.nextInt(5);//TODO w zaleznosci od trudnosci

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

    @Override
    public void onTick(Canvas canvas) {

    }

    public Impact getImpact() {
        return impact;
    }
}
