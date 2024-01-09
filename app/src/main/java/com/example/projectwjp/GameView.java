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
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import java.util.Random;

/**
 * Glowna klasy gry w ktorej najwiecej sie dzieje
 */
public class GameView extends View {
    private Map mapa;
    private Rect rectBackground, rectGround,rectEnemy;
    private Context context;
    private Handler handler;
    private Enemy enemy;
    private Hero hero;
    private final int REFRESH_RATE = 60;
    private Runnable runnable;

    private Paint numberPaint = new Paint();
    private float TEXT_SIZE = 200;
    private float NUMBER_SIZE = 120;
    protected static int dWidth, dHeight;
    private Random random;
    private float oldX;
    private float oldheroX;
    private boolean isEnd = false;
    TextView levelType;
    TextView levelEquation;
    ProgressBar enemyHPBar;
    ProgressBar heroHPBar;

    /**
     *
     * @param context to aktywnosc w ktorej funkcjonuje widok
     * @param viewUI to drugi rownolegle dzialajacy widok "UI"
     * @param args to przekazane argumenty z fragmentu
     * Konstruktor tworzy przeciwnika, bohatera, mape, napisy, progressbary i przeszkody
     */
    public GameView(Context context, View viewUI, Bundle args) {

        super(context);


        this.levelEquation = viewUI.findViewById(R.id.levelEquation);
        this.levelType = viewUI.findViewById(R.id.levelType);
        this.enemyHPBar = viewUI.findViewById(R.id.enemyHPBar);
        this.heroHPBar = viewUI.findViewById(R.id.heroHPBar);




        enemy = new Enemy(context,args.getInt("diffLevel",1),Type.valueOf(args.getString("levelType")));



        this.context = context;
        Bitmap mapbg;
        if(enemy.getDiffLevel()==1){
            mapbg = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        }
        else if(enemy.getDiffLevel()==2){
            mapbg = BitmapFactory.decodeResource(getResources(), R.drawable.backgroundsnow);
        }
        else if(enemy.getDiffLevel()==3){
            mapbg = BitmapFactory.decodeResource(getResources(), R.drawable.backgroundnigh);
        }
        else{
            mapbg = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        }
        mapa = new Map(mapbg,BitmapFactory.decodeResource(getResources(),R.drawable.ground));

        Display display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;

        rectEnemy = new Rect(dWidth/8,dHeight/3,(dHeight/8)+enemy.size,(dHeight/3)+enemy.size);

        rectBackground = new Rect(0,0,dWidth,dHeight-mapa.ground.getHeight());
        rectGround = new Rect(0,dHeight-mapa.ground.getHeight(),dWidth,dHeight);
        handler = new Handler();


        runnable = this::invalidate;


        numberPaint.setColor(context.getResources().getColor(R.color.steel_blue));
        numberPaint.setTextSize(NUMBER_SIZE);
        numberPaint.setTextAlign(Paint.Align.CENTER);
        numberPaint.setTypeface(ResourcesCompat.getFont(context, R.font.arcade));

        hero = new Hero(context);
        random = new Random();

        hero.actX = (float) dWidth / 2 - (float) hero.getbody().getHeight() /2;
        hero.actY = dHeight - mapa.ground.getHeight() - hero.getbody().getHeight();

        levelEquation.setText(enemy.getEquation());
        levelType.setText(enemy.getType().toString());

        enemyHPBar.setMax(enemy.getEnemyHP());
        heroHPBar.setMax(hero.getHeroHP());

        enemyHPBar.setProgress(enemy.getEnemyHP());
        heroHPBar.setProgress(hero.getHeroHP());




        //enemy.setEquation();
        for(int i=0; i<enemy.getDiffLevel()+2;i++){
            Obstacle obs = new Obstacle(context,numberPaint,i, enemy.getDiffLevel());
            obs.resetPosition();
            obs.setNumber(enemy.getNumbers()[i]);
            enemy.obstacles.add(obs);


        }

    }

    /**
     * Funkcja ta zajmuje sie rysowania i tym samym odswiezaniem obrazu gry.
     * Znajduja sie tutaj funkcje do rysowania i logiki gry
     *
     * @param canvas to miejsce do rysowania naszej gry
     */
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawBitmap(mapa.background, null, rectBackground,null);
        canvas.drawBitmap(mapa.ground, null,rectGround,null);

        canvas.drawBitmap(enemy.getbody(),null,rectEnemy,null);

        canvas.drawBitmap(hero.getbody(),hero.actX,hero.actY,null);
        enemyHPBar.setProgress(enemy.getEnemyHP());
        heroHPBar.setProgress(hero.getHeroHP());

        enemy.animation(REFRESH_RATE,enemy.body);

        for(int i=0; i<enemy.obstacles.size();i++){


            Obstacle obs = enemy.obstacles.get(i);

            obs.draw(canvas);
            enemy.obstacles.get(i).setNumber(enemy.getNumbers()[i]);
            obs.animation(REFRESH_RATE,obs.body);

            // TODO Animacja impactu

            if(obs.ifGroundHit(dHeight,mapa.ground.getHeight())){

                obs.onGroundHit(context);
                // canvas.drawBitmap(obs.getImpact().getImpactAnim(obs.getImpact().impactFrame),obs.getImpact().impactX,obs.getImpact().impactY,null);
            }

            //Koniec gry

            if(hero.ifHit(obs)){
                obs.onHit();

                //

                if(obs.getNumber()==enemy.getAnswer())
                {
                    enemy.onHit();


                    if(enemy.getEnemyHP() <=0 && !isEnd){
                        isEnd = true;
                        endGame(true);
                    }
                    else{
                        for(int j=0; j<enemy.getDiffLevel()+2;j++){

                            enemy.obstacles.get(j).resetPosition();
                            enemy.setEquation();
                            levelEquation.setText(enemy.getEquation());

                        }
                    }

                }
                else{
                    hero.onHit();
                }

                if(hero.getHeroHP()<=0 && !isEnd){ // dodanie isEnd by tylko jedno okno sie otworzyło

                    isEnd = true;
                    endGame(false);

                }
            }
        }



        //canvas.drawRect(dWidth-200, 30,dWidth-200+1.8f*enemy.getEnemyHP(),80,mapa.getHealthPaint());// rysowanie HP
        //canvas.drawText( " "+ enemy.getEnemyHP(),20,TEXT_SIZE,textPaint);// TODO zmiana na max hp
        handler.postDelayed(runnable, 1000/REFRESH_RATE);//opoznienie odswiezania
        
        }

    /**
     *Funkcja zajmujaca sie ruchem postaci biorac dane z ruchu po ekranie
     */
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

    /**
     * Funkcja konczaca gre tworzac nowa aktywnosc (okno)
     * @param won okresla czy gra zostala wygrana czy nie
     */
    public void endGame(boolean won){

            Intent intent = new Intent(context, GameOver.class);
            intent.putExtra("won",won);
            intent.putExtra("type",enemy.getType().toString());

            context.startActivity(intent);
            ((Activity)context).finish();

        }






}
