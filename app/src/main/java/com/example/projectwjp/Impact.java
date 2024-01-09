package com.example.projectwjp;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * nie użwana stara klasa do tworzenia animacji uderzenia
 */
public class Impact {
    public Bitmap body[] = new Bitmap[1];// ilosc obrazow do animacji  TODO zrobienie obrazow do animacji i ilosc
    int impactFrame = 0;
    int animFrame = 1;
    float impactX,impactY;

    public Impact(Context context){
        body[0] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hero);
        body[0] = Bitmap.createScaledBitmap(body[0],100,100,true); //Jakies scalowanie

    }
    public Bitmap getImpactAnim(int Frame){
        return body[Frame];
    }


}
