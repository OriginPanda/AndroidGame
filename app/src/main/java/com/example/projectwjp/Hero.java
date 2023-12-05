package com.example.projectwjp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
    protected boolean ifHit( Obstacle obs){

        return(obs.actX + obs.getObstacleWidth() >= this.actX
                && obs.actX <= this.actX+getbody().getWidth()
                && obs.actY + obs.getObstacleWidth() >= this.actY
                && obs.actY +obs.getObstacleWidth() <= this.actY+getbody().getHeight());
    }

    @Override
    public void onHit() {
        heroHP-=10;
    }
}