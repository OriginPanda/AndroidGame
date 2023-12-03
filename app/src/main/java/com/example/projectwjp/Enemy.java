package com.example.projectwjp;


import android.graphics.Bitmap;

public class Enemy {
    Bitmap body;// TODO przypisac konkretne body
    private int diffLevel;
    private int hp;

    private Type typ;

    private Obstacle [] obstacles;
    public Enemy(Type typ) {
        obstacles = new Obstacle[diffLevel];
        hp = diffLevel*100;
        this.typ =typ;
    }


}
