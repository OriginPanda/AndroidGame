package com.example.projectwjp;

import android.graphics.Bitmap;

public class Map {
    protected Bitmap background, ground;
// TODO różne mapy w zaleznosci od poziomu
    public Map(Bitmap background, Bitmap ground) {
        this.background = background;
        this.ground = ground;
    }
}
