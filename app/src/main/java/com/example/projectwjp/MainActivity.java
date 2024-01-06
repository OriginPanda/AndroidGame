package com.example.projectwjp;

import static java.lang.Thread.sleep;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import com.google.android.material.snackbar.Snackbar;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuInflater;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.WindowCompat;

import androidx.fragment.app.FragmentManager;
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

    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// ustawia layout z pliku xml

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //Wlonczony ekran poki dziala aplikacja
        b = (Button)findViewById(R.id.startbutton);
        animation = AnimationUtils.loadAnimation(this,R.anim.fadein);
        b.startAnimation(animation);


        Toolbar menu = findViewById(R.id.menu);
        setSupportActionBar(menu);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    public void StartGame(View view) {

        // Animacja guzika i start gry, chyba muszą być trzy funkcje niestety nawet jeśli puste
        animation = AnimationUtils.loadAnimation(this,R.anim.fadeout);
        b.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                startLevel();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });




    }
    public void startLevel(){

        b.setClickable(false);
        b.setVisibility(View.GONE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle args = new Bundle();
        args.putInt("diffLevel",1);
        args.putString("levelType",Type.Addition.toString());

        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, GameFragment.class, args).setReorderingAllowed(true).addToBackStack("name").commit();

//        GameFragment gameFragment = GameFragment.newInstance(1,Type.Addition);
//
//        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, gameFragment).setReorderingAllowed(true).addToBackStack("name").commit();

        //GameView gameView = new GameView(context);
        //setContentView(gameView);
    }

    //Dużo roboty by inaczej zrobić
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        b.setClickable(true);
        b.setVisibility(View.VISIBLE);
        animation = AnimationUtils.loadAnimation(this,R.anim.fadein);
        b.startAnimation(animation);



    }

}