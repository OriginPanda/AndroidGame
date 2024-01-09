package com.example.projectwjp;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Klasa przechowujaca tlo. troche przestazala i malo uzyteczna
 */
public class Map {

    protected Bitmap background, ground;

    public Map(Bitmap background, Bitmap ground) {

        this.background = background;
        this.ground = ground;
    }

}
