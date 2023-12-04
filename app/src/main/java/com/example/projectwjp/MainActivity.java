package com.example.projectwjp;

import static java.lang.Thread.sleep;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.projectwjp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button b;
    private Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// ustawia layout z pliku xml
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //Wlonczony ekran poki dziala aplikacja
        b = (Button)findViewById(R.id.startbutton);
        animation = AnimationUtils.loadAnimation(this,R.anim.fadein);
        b.startAnimation(animation);
        //animation.hasEnded();
    }

    public void StartGame(View view) {

        // Animacja guzika i start gry, chyba muszą być trzy funkcje niestety nawet jeśli puste
        animation = AnimationUtils.loadAnimation(this,R.anim.fadeout);
        b.startAnimation(animation);
        Context context =this;
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                GameView gameView = new GameView(context);
                setContentView(gameView);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });




    }
}