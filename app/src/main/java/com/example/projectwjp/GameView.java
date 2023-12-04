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
    private Bitmap background, ground, hero;
    private Rect rectBackground, rectGround;
    private Context context;
    private Handler handler;
    private Enemy enemy;
    private final long REFRESH_RATE = 60;
    private Runnable runnable;
    private Paint textPaint = new Paint();
    private Paint healthPaint = new Paint();
    private float TEXT_SIZE = 120;
    private int points = 0;//Enemy Hp TODO zmiana klasy
    private int life = 3;
    protected static int dWidth, dHeight;
    private Random random;
    private float heroX, heroY;
    private float oldX;
    private float oldheroX;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Impact> impacts;

    public GameView(Context context) {
        super(context);

        //enemy = new Enemy(typ);
        this.context = context;
        background = BitmapFactory.decodeResource(getResources(), R.drawable.backgroundgame);
        ground = BitmapFactory.decodeResource(getResources(),R.drawable.ground);
        hero = BitmapFactory.decodeResource(getResources(), R.drawable.hero);
        Display display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;
        rectBackground = new Rect(0,0,dWidth,dHeight);
        rectGround = new Rect(0,dHeight-ground.getHeight(),dWidth,dHeight);
        handler = new Handler();
        // odswieżanie View (obrazu ekranu)
        runnable = this::invalidate;
        textPaint.setColor(Color.BLUE);
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setTypeface(ResourcesCompat.getFont(context, R.font.nova));
        healthPaint.setColor(Color.GREEN);
        random = new Random();
        heroX = (float) dWidth / 2 - (float) hero.getHeight() /2;
        heroY = dHeight - ground.getHeight() - hero.getHeight();
        obstacles = new ArrayList<>();
        impacts = new ArrayList<>();
        for(int i=0; i<3;i++){
            Obstacle obs = new Obstacle(context);
            obstacles.add(obs);
        }

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawBitmap(background, null, rectBackground,null);
        canvas.drawBitmap(ground, null,rectGround,null);
        canvas.drawBitmap(hero,heroX,heroY,null);
        for(int i=0; i<obstacles.size();i++){
            canvas.drawBitmap(obstacles.get(i).getbody(),obstacles.get(i).obsX,obstacles.get(i).obsY,null);

//            TODO animacja
//            obstacles.get(i).obsFrame++;
//            if(obstacles.get(i).obsFrame>3){
//                obstacles.get(i).obsFrame = 0;
//            }
            obstacles.get(i).obsY += 20 ;//obstacles.get(i).obsVelocity; TODO w zaleznosci od trudnosci
            // TODO Animacja impactu
            if(obstacles.get(i).obsY+obstacles.get(i).getObstacleHight()>=dHeight-ground.getHeight()){
                points += 10;
                Impact impact = new Impact(context);// Alokacja ?
                impact.impactX =obstacles.get(i).obsX;
                impact.impactY = obstacles.get(i).obsY;
                impacts.add(impact);
                obstacles.get(i).resetPosition();
            }
        }
        for(int i=0; i<obstacles.size();i++){
            Obstacle obs =obstacles.get(i);
            if(ifHit(obs)){
                life--;
                obs.resetPosition();
                if(life==0){
                    Intent intent = new Intent(context, GameOver.class);
                    intent.putExtra("points",points);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
            }

        }
        // TODO zmiana do animacji impactu
        for(int i = 0 ; i<impacts.size();i++){
            Impact imp = impacts.get(i);
            canvas.drawBitmap(imp.getImpactAnim(imp.impactFrame),imp.impactX,imp.impactY,null);
            imp.impactFrame++;
            if(imp.impactFrame>=imp.animFrame){
                impacts.remove(i);
            }
        }
        // TODO zmiana do bohatera
        if(life == 2){
            healthPaint.setColor(Color.YELLOW);
        }
        else if(life==1){
            healthPaint.setColor(Color.RED);
        }
        canvas.drawRect(dWidth-200, 30,dWidth-200+60*life,80,healthPaint);// rysowanie HP
        canvas.drawText(" " + points,20,TEXT_SIZE,textPaint);
        handler.postDelayed(runnable, 1/REFRESH_RATE);
        }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        if(touchY>=heroY){
            int action = event.getAction();
            if(action == MotionEvent.ACTION_DOWN){
                oldX = event.getX();
                oldheroX = heroX;
            }
            if(action == MotionEvent.ACTION_MOVE){
                float shift = oldX-touchX;
                float newheroX = oldheroX - shift;
                if(newheroX <=0)
                {
                    heroX =0;
                }
                else if (newheroX >= dWidth - hero.getWidth())
                {
                    heroX = dWidth - hero.getWidth();
                }
                else{
                    heroX = newheroX;
                }
            }
        }
        return true;
        }


    protected boolean ifHit( Obstacle obs){
        return(obs.obsX + obs.getObstacleWidth() >= heroX
            && obs.obsX <= heroX+hero.getWidth()
            && obs.obsY + obs.getObstacleWidth() >= heroY
            && obs.obsY+obs.getObstacleWidth() <= heroY+hero.getHeight());
    }




}
