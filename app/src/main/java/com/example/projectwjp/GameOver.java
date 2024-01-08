package com.example.projectwjp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    TextView result;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);

        int enemyHP = getIntent().getExtras().getInt("enemyHP",999);
        boolean won = getIntent().getExtras().getBoolean("won",false);
        result = findViewById(R.id.resultText);


        if(won){
            result.setText("Wygrana");

        }
        else if (!won) {
            result.setText("Przegrana");
        }

        sharedPreferences = getSharedPreferences("my_pref",0);



    }
    public void menu(View view){
        Intent intent  = new Intent(GameOver.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void restart(View view){
        Intent intent  = new Intent(GameOver.this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}
