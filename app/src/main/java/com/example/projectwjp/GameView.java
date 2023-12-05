package com.example.projectwjp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import android.os.Handler;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {
    private Map mapa;
    private Rect rectBackground, rectGround;
    private Context context;
    private Handler handler;
    private Enemy enemy;
    private Hero hero;
    private final int REFRESH_RATE = 60;
    private Runnable runnable;
    private Paint textPaint = new Paint();
    private Paint healthPaint = new Paint();
    private float TEXT_SIZE = 120;
    // private int points = 0; //Enemy Hp TODO zmiana klasy
    protected static int dWidth, dHeight;
    private Random random;
    private float oldX;
    private float oldheroX;


    public GameView(Context context) {
        super(context);

        enemy = new Enemy();
        this.context = context;
        mapa = new Map(BitmapFactory.decodeResource(getResources(), R.drawable.backgroundgame),BitmapFactory.decodeResource(getResources(),R.drawable.ground));




        //
        Display display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;

        rectBackground = new Rect(0,0,dWidth,dHeight);
        rectGround = new Rect(0,dHeight-mapa.ground.getHeight(),dWidth,dHeight);
        handler = new Handler();

        // odswieżanie View (obrazu ekranu)
        runnable = this::invalidate;
        textPaint.setColor(Color.BLUE);
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setTypeface(ResourcesCompat.getFont(context, R.font.nova));
        healthPaint.setColor(Color.GREEN);

        hero = new Hero(context);
        random = new Random();

        hero.actX = (float) dWidth / 2 - (float) hero.getbody().getHeight() /2;
        hero.actY = dHeight - mapa.ground.getHeight() - hero.getbody().getHeight();



        for(int i=0; i<3;i++){
            Obstacle obs = new Obstacle(context);
            enemy.obstacles.add(obs);
            obs.resetPosition();
        }

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawBitmap(mapa.background, null, rectBackground,null);
        canvas.drawBitmap(mapa.ground, null,rectGround,null);
        canvas.drawBitmap(hero.getbody(),hero.actX,hero.actY,null);

        for(int i=0; i<enemy.obstacles.size();i++){
            Obstacle obs = enemy.obstacles.get(i);
            canvas.drawBitmap(obs.getbody(),obs.actX,obs.actY,null);


            obs.animation(REFRESH_RATE);

            obs.actY += 20 ;//enemy.obstacles.get(i).obsVelocity; //TODO w zaleznosci od trudnosci

            // TODO Animacja impactu
            //enemy.obstacles.get(i).actY +enemy.obstacles.get(i).getObstacleHight()>=dHeight-mapa.ground.getHeight()
            if(obs.ifGroundHit(dHeight,mapa.ground.getHeight())){
                enemy.onHit();
                obs.onGroundHit(context);
               // canvas.drawBitmap(obs.getImpact().getImpactAnim(obs.getImpact().impactFrame),obs.getImpact().impactX,obs.getImpact().impactY,null);
                obs.resetPosition();
            }
            if(hero.ifHit(obs)){
                obs.onHit();
                hero.onHit();

                if(hero.getHeroHP()<=0){
                    Intent intent = new Intent(context, GameOver.class);
                    intent.putExtra("points",enemy.getEnemyHP());
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
            }


        }



        if(hero.getHeroHP() <= 70 && hero.getHeroHP() >= 40){
            healthPaint.setColor(Color.YELLOW);
        }
        else if(hero.getHeroHP()<=40){
            healthPaint.setColor(Color.RED);
        }
        canvas.drawRect(dWidth-200, 30,dWidth-200+1.8f*hero.getHeroHP(),80,healthPaint);// rysowanie HP
        canvas.drawText(" " + enemy.getEnemyHP(),20,TEXT_SIZE,textPaint);
        handler.postDelayed(runnable, 1/REFRESH_RATE);
        }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        if(touchY>=hero.actY){
            int action = event.getAction();
            if(action == MotionEvent.ACTION_DOWN){
                oldX = event.getX();
                oldheroX = hero.actX;
            }
            if(action == MotionEvent.ACTION_MOVE){
                float shift = oldX-touchX;
                float newheroX = oldheroX - shift;
                if(newheroX <=0)
                {
                    hero.actX =0;
                }
                else if (newheroX >= dWidth - hero.getbody().getWidth())
                {
                    hero.actX = dWidth - hero.getbody().getWidth();
                }
                else{
                    hero.actX = newheroX;
                }
            }
        }
        return true;
        }






}
