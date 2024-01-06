package com.example.projectwjp;


import android.graphics.Bitmap;

import java.util.ArrayList;

public class Enemy extends Actor {
    private int diffLevel = 1;
    private  Type type = Type.Addition;
    private int enemyHP = 100; //przeciwnost points z GameView TODO przeniesc tutaj
    protected ArrayList<Obstacle> obstacles;

    public Enemy(int diffLevel,Type type) {

        this.type = type;
        this.diffLevel = diffLevel;
        obstacles = new ArrayList<>();
        enemyHP *= diffLevel;


        //obstacles = new Obstacle[diffLevel];

    }
    @Override
    public void onHit() {
        enemyHP+=10;
    }
    public int getEnemyHP() {
        return enemyHP;
    }
    public Type getType(){
        return type;
    }
}
