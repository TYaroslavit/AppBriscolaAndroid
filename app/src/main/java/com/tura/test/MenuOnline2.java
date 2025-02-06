package com.tura.test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;

import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.*;
import java.net.*;
import java.util.Random;

public class MenuOnline2 extends AppCompatActivity {
    private static Socket socket;
    private static DataInputStream dataIn;
    private static DataOutputStream dataOut;
    private PrintWriter output;
    private BufferedReader input;

    Button FindGame, BackToMenu;
    TextView messageOnDisplay;
    Random random = new Random();
    String idPlayer, idPartita;




    String httpLink = "http://";
    //String ip = "192.168.1.134";
    String ip = "10.0.2.2";
    String url = httpLink + ip + ":8080/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String mId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_menu);

        FindGame = findViewById(R.id.trovaPartita);
        BackToMenu = findViewById(R.id.TornaMenu);
        messageOnDisplay = findViewById(R.id.Avviso);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;


        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float density = metrics.density; // Ottieni il fattore di scala della densità

        //messageOnDisplay.setText(String.valueOf(density));
        //messageOnDisplay.setEnabled(false);
        messageOnDisplay.setVisibility(View.INVISIBLE);
    }

    public void FindGame(View view) throws IOException {

        //FindGame.setEnabled(false);


        @SuppressLint("HardwareIds") String mId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url1 = url + "ready";
        Uri.Builder builder = Uri.parse(url1).buildUpon();
        builder.appendQueryParameter("idp", mId);
        Log.d("id1",mId);
        //builder.appendQueryParameter("param2", "value2");
        String urlWithParams = builder.build().toString();

        messageOnDisplay.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, urlWithParams,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Gestisci la risposta come stringa
                        if (response.contentEquals("new")){
                            messageOnDisplay.setText("Connesso , aspetta inizio partita!");
                            startNewGame();
                        }
                        if ((!response.contentEquals("new"))){
                            Intent i = new Intent(MenuOnline2.this, Multiplayer.class);
                            i.putExtra("idPlayer",mId);
                            i.putExtra("idPartita",response);
                            finish();  //Kill the activity from which you will go to next activity
                            startActivity(i);
                        }

                        else
                            messageOnDisplay.setText("Errore , probabilmente non hai connessione a Internet!");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Gestisci eventuali errori
                        Log.e("VolleyError", "Errore: " + error.getMessage());
                    }
                });
        requestQueue.add(stringRequest);






    }

    public void startNewGame() {
        @SuppressLint("HardwareIds") String mId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url1 = url + "start";
        Uri.Builder builder = Uri.parse(url1).buildUpon();
        builder.appendQueryParameter("idp", mId);
        //builder.appendQueryParameter("param2", "value2");
        String urlWithParams = builder.build().toString();


        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, urlWithParams,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Gestisci la risposta come stringa
                        if (!response.contentEquals("wait")) {
                            idPartita = (response);

                            messageOnDisplay.setText("Inizia il gioco");
                            Intent i = new Intent(MenuOnline2.this, Multiplayer.class);
                            i.putExtra("idPlayer",idPlayer);
                            i.putExtra("idPartita",idPartita);
                            finish();  //Kill the activity from which you will go to next activity
                            startActivity(i);
                        }
                        else {
                            messageOnDisplay.setText("Aspetta un avversario");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("pausa","waiting new game");
                                    startNewGame();
                                }
                            }, 5000); // Ritardo di 5 secondi
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Gestisci eventuali errori
                        Log.e("VolleyError", "Errore: " + error.getMessage());
                    }
                });
        requestQueue.add(stringRequest);

    }

    public void BackToMenu(View v){
        Intent i = new Intent(MenuOnline2.this, MenuPrincipale.class);
        finish();  //Kill the activity from which you will go to next activity
        startActivity(i);
    }

}