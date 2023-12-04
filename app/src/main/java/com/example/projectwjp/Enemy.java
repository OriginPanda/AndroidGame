package com.example.projectwjp;


import android.graphics.Bitmap;

public class Enemy {
    private Bitmap body;// TODO przypisac konkretne body
    private int diffLevel;
    private int hp;//przeciwnost points z GameView TODO przeniesc tutaj

    private Type typ;

    private Obstacle [] obstacles;
    public Enemy(Type typ) {
        obstacles = new Obstacle[diffLevel];
        hp = diffLevel*100;
        this.typ =typ;
    }


}
