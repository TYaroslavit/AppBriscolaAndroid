package com.tura.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipale extends AppCompatActivity {
    Button Single, Multiplayer;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        Single = findViewById(R.id.Single);
        Multiplayer = findViewById(R.id.Multiplayer);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;


        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float density = metrics.density; // Ottieni il fattore di scala della densità

    }

    public void Single(View v){
        Intent i = new Intent(MenuPrincipale.this, SinglePlayerActivity2.class);
        finish();  //Kill the activity from which you will go to next activity
        startActivity(i);
    }

    public void Multiplayer(View v){
        Intent i = new Intent(MenuPrincipale.this, MenuOnlinev2.class);
        finish();  //Kill the activity from which you will go to next activity
        startActivity(i);
    }
}
