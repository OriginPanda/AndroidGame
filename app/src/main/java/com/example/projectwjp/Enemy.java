package com.example.projectwjp;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.icu.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Enemy extends Actor {
    public int getDiffLevel() {
        return diffLevel;
    }

    private int diffLevel = 1;
    private  Type type;
    private int enemyHP = 100;

    public int[] getNumbers() {
        return numbers;
    }

    private int [] numbers;

    public String getEquation() {
        return equation;
    }

    private String equation = "lmao";
    public int getAnswer() {
        return answer;
    }

    private  int answer = 0;
    Random random;

    protected ArrayList<Obstacle> obstacles;

    public Enemy(Context context,int diffLevel, Type type) {
        super();

        this.type = type;
        this.diffLevel = diffLevel;
        obstacles = new ArrayList<>();
        enemyHP += 20*diffLevel;
        size = 800;
        if(type == Type.Dodawanie || type==Type.Odejmowanie){
            body.add(0,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.additionbody0),size,size,true));
            body.add(1,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.additionbody1),size,size,true));
            body.add(2,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.additionbody2),size,size,true));
            body.add(3,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.additionbody3),size,size,true));
            body.add(4,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.additionbody4),size,size,true));
        }
        else {
            body.add(0,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.divbody0),size,size,true));
            body.add(1,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.divbody1),size,size,true));
            body.add(2,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.divbody2),size,size,true));
            body.add(3,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.divbody3),size,size,true));
            body.add(4,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.divbody4),size,size,true));
        }


        currentFrame =body.get(0);
        random = new Random();
        numbers = new int [diffLevel+2];
        setEquation();


        //obstacles = new Obstacle[diffLevel];

    }
    @Override
    public void onHit() {
        enemyHP-=10;
    }




    public void setEquation(){
        random = new Random();
        int bounds = 10*diffLevel;
        int x = random.nextInt(bounds)+1;

        int y = random.nextInt(bounds)+1;

        switch (type){
            case Dodawanie:
                answer = x+y;

                equation = x+" + "+y;
                break;
            case Odejmowanie:
                if(x>y){
                    answer = x-y;

                    equation = x+" - "+y;
                }
                else{
                    answer = y-x;
                    equation = y+" - "+x;
                }

                break;
            case Mnozenie:
                bounds = 5*diffLevel;
                x = random.nextInt(bounds)+1;
                y = random.nextInt(bounds)+1;
                answer = x*y;
                bounds=25*diffLevel*diffLevel;
                equation = x+" * "+y;
                break;
            case Dzielenie:
                bounds = 5*diffLevel;
                x = random.nextInt(bounds)+1;
                y = random.nextInt(bounds)+1;
                if(x>y){
                    answer = x/y;

                    equation = x+" / "+y;
                }
                else{
                    answer = y/x;
                    equation = y+" / "+x;
                }

                break;
            default:
                answer = 99;
                equation = x+" ? "+y;
        }

        Arrays.fill(numbers, 0);

        numbers[random.nextInt(diffLevel+1)]=answer;

        for(int i = 1;i<diffLevel+2;i++){
            if(numbers[i]==0) {
                numbers[i] = random.nextInt(bounds) + 1;

                while (numbers[i] == answer) {
                    numbers[i] = random.nextInt(bounds) + 1;
                }
            }
        }
    }


    public int getEnemyHP() {
        return enemyHP;
    }
    public Type getType(){
        return type;
    }



}
