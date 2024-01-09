package com.example.projectwjp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

/**
 * klasa przeszkody z odpowiedziamy
 */
public class Obstacle extends Actor{


    private final int BASE_VELOCITY = 20;
    private int obsVelocity; //
    private Random random;
    private Paint numberPaint;
    private int q = 0;
    private int diffLevel;
    private int number;
    private Impact impact;

    ArrayList<Bitmap> testAnim;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {


        this.number = number;

    }

    /**
     * Tutaj sa przypisywane Bitmapy do animaacji jak i reszta zmiennych
     * @param context informacje z aktywnosci
     * @param numberPaint to styl numerow wyswietlanych na przeszkodach, moglby on sie znajdowac tutaj, lecz zamysl by by wykorzystac do innych rzeczy
     * @param q to miejsce w tabeli przeszkod by moc podzielic ekran i odpowienio daleko stawiac przeszkody by usprawnic rozgrywke
     * @param diffLevel poziom trudnosci
     */
    public Obstacle(Context context,Paint numberPaint,int q, int diffLevel) {

        super();
        size = 200;

        this.q=q;
        this.diffLevel = diffLevel;

        //body = new ArrayList<>();
        this.numberPaint = numberPaint;
        // przydzielanie obrazu

        body.add(0,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.pixilframe0),size,size,true));
        body.add(1,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.pixilframe1),size,size,true));
        body.add(2,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.pixilframe2),size,size,true));
        body.add(3,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.pixilframe3),size,size,true));

        testAnim = new ArrayList<>();
        testAnim.add(0,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.baseobstacle1),size,size,true));
        testAnim.add(1,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.baseobstacle2),size,size,true));
        currentFrame = body.get(0);
        random = new Random();
    }

    /**
     * Funkcja resetowania pozyscji przeszkody
     */
    public void resetPosition(){
        //int full = (GameView.dWidth - size);
        int bound = (GameView.dWidth)/(diffLevel+2);

        actX = random.nextInt((q+1)*bound-(q*bound)-size)+(q*bound);

        actY = -200 + random.nextInt(600)*-1;
        obsVelocity = BASE_VELOCITY +random.nextInt(5)*diffLevel;//TODO w zaleznosci od trudnosci

    }


    /**
     * funkcja zmieniajaca pozycje na podstawie predkosci co onDraw
     */
    public void fall(){
        actY += obsVelocity;
    }

    /**
     * sprawdzenie czy uderzono w ziemie
     * @param dHeight wysokosc urzadzenia
     * @param gHeight wysokosc ziemi, czyli poziomu gdzie uderza
     */
    public boolean ifGroundHit(int dHeight,int gHeight){
        return (actY+size>=dHeight-gHeight);

    }

    /**
     * tworzenie obiektu klasy impact lecz i tak nie jest on wyswietlany w trakcie gry
     * jak i reset pozycji
     * @param context
     */
    public void onGroundHit(Context context){
        Impact impact = new Impact(context);
        impact.impactX =actX;
        impact.impactY = actY;
        resetPosition();

    }

    /**
     * funkcja onHit miala pelnic jescze role inicjacji uderzenia lub innych zmian
     * lecz teraz tylko resetuje pozycje
     */
    @Override
    public void onHit() {

        resetPosition();
    }

    /**
     * Funkcja zajmujaca sie rysowaniem przeszkody
     * @param canvas
     */
    public void draw(Canvas canvas) {
        fall();
        canvas.drawBitmap(getbody(),actX,actY,null);
        canvas.drawText(String.valueOf(getNumber()),actX+size/2, (float)(actY+size/1.3),numberPaint);
    }


    /**
     * nie uzywana funkcja impactu
     */
    public Impact getImpact() {
        return impact;
    }
}
