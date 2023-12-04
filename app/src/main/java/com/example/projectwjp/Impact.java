package com.example.projectwjp;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Impact {
    public Bitmap body[] = new Bitmap[1];// ilosc obrazow do animacji  TODO zrobienie obrazow do animacji i ilosc
    int impactFrame = 0;
    int animFrame = 1;
    int impactX,impactY;

    public Impact(Context context){
        body[0] = BitmapFactory.decodeResource(context.getResources(),R.drawable.impact1);

    }
    public Bitmap getImpactAnim(int Frame){
        return body[Frame];
    }

}
