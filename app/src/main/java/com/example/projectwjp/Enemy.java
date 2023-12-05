package com.example.projectwjp;


import android.graphics.Bitmap;

import java.util.ArrayList;

public class Enemy extends Actor {
    private int diffLevel = 1;

    private int enemyHP = 100; //przeciwnost points z GameView TODO przeniesc tutaj
    protected ArrayList<Obstacle> obstacles;
    private Type typ;

    public Enemy() {
        obstacles = new ArrayList<>();

        //obstacles = new Obstacle[diffLevel];
        //hp = diffLevel*100;
        //this.typ =typ;
    }
    @Override
    public void onHit() {
        enemyHP+=10;
    }
    public int getEnemyHP() {
        return enemyHP;
    }
}
