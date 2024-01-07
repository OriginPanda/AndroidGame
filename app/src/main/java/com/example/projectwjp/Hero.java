package com.example.projectwjp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Hero extends Actor {

    private int heroHP=100;
    public Hero(Context context) {
        size = 200;
        body = new Bitmap[1];
        body[0]= BitmapFactory.decodeResource(context.getResources(), R.drawable.hero);
        body[0] = Bitmap.createScaledBitmap(body[0],size,size,true);
    }
    public int getHeroHP() {
        return heroHP;
    }
    protected boolean ifHit( Actor obs){

        return(obs.actX + obs.body[0].getWidth() >= this.actX
                && obs.actX <= this.actX+getbody().getWidth()
                && obs.actY + obs.body[0].getWidth()  >= this.actY
                && obs.actY +obs.body[0].getWidth()  <= this.actY+getbody().getHeight());
    }

    @Override
    public void onHit() {
        heroHP-=10;
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void onTick(Canvas canvas) {

    }
}