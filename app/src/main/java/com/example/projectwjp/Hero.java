package com.example.projectwjp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;

public class Hero extends Actor {

    private int heroHP=100;
    public Hero(Context context) {
        size = 200;
        body = new ArrayList<>();

        body.add(0,BitmapFactory.decodeResource(context.getResources(), context.getSharedPreferences("my_pref",0).getInt("hero",R.drawable.hero1)));

        body.add(0,Bitmap.createScaledBitmap(body.get(0),size,size,true)) ;
    }
    public int getHeroHP() {
        return heroHP;
    }
    protected boolean ifHit( Actor obs){

        return(obs.actX + obs.body.get(0).getWidth() >= this.actX
                && obs.actX <= this.actX+getbody().getWidth()
                && obs.actY + obs.body.get(0).getWidth()  >= this.actY
                && obs.actY +obs.body.get(0).getWidth()  <= this.actY+getbody().getHeight());
    }

    @Override
    public void onHit() {
        heroHP-=10;
    }




}