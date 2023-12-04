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
        TextView scrpoints;
        TextView scrhighest;
        SharedPreferences sharedPreferences;
        ImageView scrhighscore;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        scrpoints = findViewById(R.id.scrpoints);
        scrhighest = findViewById(R.id.scrhighest);
        scrhighscore = findViewById(R.id.scrhighscore);
        int pts = getIntent().getExtras().getInt("points");
        scrpoints.setText(" "+ pts);
        sharedPreferences = getSharedPreferences("my_pref",0);
        int highest = sharedPreferences.getInt("highest",0);
        if(pts>highest){
            scrhighscore.setVisibility(View.VISIBLE);
            highest = pts;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("highest",highest);
            editor.apply();//apply ?

        }
        scrhighest.setText(" " + highest);

    }
    public void restart(View view){
        Intent intent  = new Intent(GameOver.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void exit(View view){
        finish();
    }
}
