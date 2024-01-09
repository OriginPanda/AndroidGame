package com.example.projectwjp;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;



import androidx.appcompat.widget.Toolbar;


import androidx.fragment.app.FragmentManager;




import android.view.Menu;
import android.view.MenuItem;

import android.view.WindowManager;
/**
 * Glowne okno na ktorym praktycznie wszystko sie dzieje oprocz ekranu konca gry.
 * Pierwszy raz robilem cokolwiek w androidzie wiec jest troche bajzel
 * wiele rzeczy na wiele sposobow mozna robic wiec na bierzaco bylo wszystko zmieniane
 */

public class MainActivity extends AppCompatActivity {


    Context context = this;

    public int getDiffLevel() {
        return diffLevel;
    }

    private int diffLevel = 1;

    /**
     *
     * funkcja tworzaca glowne okno i od razu dodajaca do nie go podoknow (fragment)
     * zczytuje tez dane z pliku zapisu z telefonu dla tej aplikacji (SharedPreferences)
     * ustawia layout z pliku xml
     * i ustawia by ekran byl caly czas wlonczony poki aplikacja dziala
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("my_pref",0);
        diffLevel = sharedPreferences.getInt("diffLevel",1);

        setContentView(R.layout.activity_main);// ustawia layout z pliku xml

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, StartFragment.class,null).setReorderingAllowed(true).commit();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //Wlonczony ekran poki dziala aplikacja


//        b = (Button)findViewById(R.id.startbutton);
//        animation = AnimationUtils.loadAnimation(this,R.anim.fadein);
//        b.startAnimation(animation);


        Toolbar menu = findViewById(R.id.menu);
        setSupportActionBar(menu);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    /**
     *
     * @param item
     * zmianany za pomocą przyciskow menu poziomu trudnosci
     * poziom jest zapisywany do kolejnego otworzenia aplikacji
     */
    public void upLevel(MenuItem item) {

        if(diffLevel<3){
            diffLevel += 1;
        }
        getSharedPreferences("my_pref",0).edit().putInt("diffLevel",diffLevel).apply();
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, StartFragment.class,null).setReorderingAllowed(true).commit();
    }

    public void downLevel(MenuItem item) {

        if(diffLevel>1){
            diffLevel -= 1;
        }
        getSharedPreferences("my_pref",0).edit().putInt("diffLevel",diffLevel).apply();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, StartFragment.class,null).setReorderingAllowed(true).commit();
    }

    /**
     *
     * @param item
     *
     * otworzenie fragmentu menu
     */
    public void settings(MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, SettingsFragment.class,null).setReorderingAllowed(true).addToBackStack(null).commit();
    }
}


