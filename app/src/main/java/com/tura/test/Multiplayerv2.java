package com.tura.test;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tura.test.cardsStuff.Deck;
import com.tura.test.cardsStuff.Hand;
import com.tura.test.models.Partita;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Multiplayerv2 extends AppCompatActivity {

    int height ;
    int width ;
    int r2 = R.drawable.r2;;
    FrameLayout FrameTop, FrameDown,CoinUp, CoinDown;

    Partita myPartita = new Partita();
    String idPlayer;
    String idPartita;

    ImageView imageView,carta1,carta2;
    ImageView Carta1Down,Carta2Down,Carta3Down,Carta1Top,Carta2Top,Carta3Top,tx,top , CartaPlayer1, CartaPlaye2;



    TextView testoSopra, testoGiu, ResultText , puntiPlayerTop , puntiPlayerDown,coinUptext, coinDownText;
    int is0 = 0, is1 = 1 , is2 = 2, it1 = 3, it2 = 4 , it3 = 5, z = 39 ,nostraCarta, cartaAvversario,result , punteggioA = 0 , punteggioB = 0 , Nostraposizione;

    Animation[] animations = new Animation[16];

    Random rand = new Random();

    Boolean primo = true ,secondo = true,lose = true , turno = false, TurnoFinito = true, AvversarioPickCarta =false , IoPickCarta = false, RisultatoFatto = true;

    Button ng,menuB;

    RequestQueue requestQueue;  // This is our requests queue to process our HTTP requests.

    String httpLink = "http://";
    //String ip = "192.168.1.134";
    String ip = "10.0.2.2";
    String url = httpLink + ip + ":8080/post/";



    Integer[] numbers = new Integer[40];

    private boolean isBackVisible = true;
    private Handler handler = new Handler();

    Deck D = new Deck(false);

    Hand hand = new Hand(D);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen_test);
        Context c = getApplicationContext();
        Bundle b = getIntent().getExtras();
         idPlayer = b.getString("idPlayer");
         idPartita = b.getString("idPartita");

         Log.d("carte","id pla "+idPlayer+" id p "+idPartita);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        requestQueue = Volley.newRequestQueue(c);
        imageView = findViewById(R.id.im7);

        Carta1Top = findViewById(R.id.im1); //ex tx 1
        Carta2Top = findViewById(R.id.im2);
        Carta3Top = findViewById(R.id.im3);
        Carta1Down = findViewById(R.id.im4); //ex t0
        Carta2Down = findViewById(R.id.im5); //ex Carta2Down
        Carta3Down = findViewById(R.id.im6);
        carta1 = findViewById(R.id.carta1);
        carta2 = findViewById(R.id.carta2);
        top = findViewById(R.id.im11);
        testoSopra = findViewById(R.id.textView);
        testoGiu = findViewById(R.id.textView2);
        ResultText = findViewById(R.id.resultText);
        ng = findViewById(R.id.ng);
        menuB = findViewById(R.id.menuButton);

        FrameTop = findViewById(R.id.coinWithTextUp);
        FrameDown = findViewById(R.id.coinWithTextDown);
        coinUptext = findViewById(R.id.coinText);
        coinDownText = findViewById(R.id.coinTextDown);
        CoinUp = findViewById(R.id.coinUp);
        CoinDown = findViewById(R.id.coinDown);





        for (int i = 0; i < 40; i++) {
            Log.d("mazzo", "" + hand.CardOnHandOneToOne(i) + "\n");
        }

        //ottieniCarte(idPartita,c); //ottengo le carte dal server

        //per ottenere il nome della risorsa
        GetPartita(idPlayer,idPartita,hand,c);
//        for (int i = 0; i < 40; i++) {
//            //Log.d("carte",numbers[i].toString());
//        }

        new Handler().postDelayed(new Runnable() {   //aspetto 1s prima di chiedere al server le carte
            @Override
            public void run() {
                String s0 , s1, s2 ,s6 , t0 ,t1,t2;

                    s6 = "drawable/" + hand.getInfoCard1(numbers[39]) + hand.getInfoCard2(numbers[39]);

                if(turno){
                    t0 = "drawable/" + hand.getInfoCard1(numbers[it1]) + hand.getInfoCard2(numbers[it1]);
                    t1 = "drawable/" + hand.getInfoCard1(numbers[it2]) + hand.getInfoCard2(numbers[it2]);
                    t2 = "drawable/" + hand.getInfoCard1(numbers[it3]) + hand.getInfoCard2(numbers[it3]);

                    s0 = "drawable/" + hand.getInfoCard1(numbers[is0]) + hand.getInfoCard2(numbers[is0]);
                    s1 = "drawable/" + hand.getInfoCard1(numbers[is1]) + hand.getInfoCard2(numbers[is1]);
                    s2 = "drawable/" + hand.getInfoCard1(numbers[is2]) + hand.getInfoCard2(numbers[is2]);



                }
                else{
                    t0 = "drawable/" + hand.getInfoCard1(numbers[is0]) + hand.getInfoCard2(numbers[is0]);
                    t1 = "drawable/" + hand.getInfoCard1(numbers[is1]) + hand.getInfoCard2(numbers[is1]);
                    t2 = "drawable/" + hand.getInfoCard1(numbers[is2]) + hand.getInfoCard2(numbers[is2]);

                    s0 = "drawable/" + hand.getInfoCard1(numbers[it1]) + hand.getInfoCard2(numbers[it1]);
                    s1 = "drawable/" + hand.getInfoCard1(numbers[it2]) + hand.getInfoCard2(numbers[it2]);
                    s2 = "drawable/" + hand.getInfoCard1(numbers[it3]) + hand.getInfoCard2(numbers[it3]);
                }






                //id della risorsa
                int id0 = c.getResources().getIdentifier(s0, null, c.getPackageName());
                int id1 = c.getResources().getIdentifier(s1, null, c.getPackageName());
                int id2 = c.getResources().getIdentifier(s2, null, c.getPackageName());

                int idt0 = c.getResources().getIdentifier(t0, null, c.getPackageName());
                int idt1 = c.getResources().getIdentifier(t1, null, c.getPackageName());
                int idt2 = c.getResources().getIdentifier(t2, null, c.getPackageName());



                int id6 = c.getResources().getIdentifier(s6, null, c.getPackageName());

                Carta1Down.setImageResource(id0);
                Carta2Down.setImageResource(id1);
                Carta3Down.setImageResource(id2);

                Carta1Top.setImageResource(idt0);
                Carta2Top.setImageResource(idt1);
                Carta3Top.setImageResource(idt2);


                imageView.setImageResource(id6);
                updateScreen(c);
                if (turno) {
                    Log.d("carte2","inside if turno");
                    enableCarts();
                    playTurn(c);

                } else {
                    Log.d("carte2","inside else turno");
                    disableCarts();
                    updateScreen(c);
                    //updateTurno(idPlayer,idPartita,c);
                }
            }


        }, 500);



        //imposto dimensioni dei vari elementi
        Carta1Down.getLayoutParams().width = width / 4;
        Carta1Down.getLayoutParams().height = height / 5;
        Carta2Down.getLayoutParams().height = height / 5;
        Carta2Down.getLayoutParams().width = width / 4;
        Carta3Down.getLayoutParams().height = height / 5;
        Carta3Down.getLayoutParams().width = width / 4;

        Carta2Top.getLayoutParams().height = height / 5;
        Carta2Top.getLayoutParams().width = width / 4;
        Carta1Top.getLayoutParams().height = height / 5;
        Carta1Top.getLayoutParams().width = width / 4;
        Carta3Top.getLayoutParams().height = height / 5;
        Carta3Top.getLayoutParams().width = width / 4;


        imageView.getLayoutParams().width = width / 3;
        imageView.getLayoutParams().height = height / 6;

        top.getLayoutParams().width = width / 3;
        top.getLayoutParams().height = height / 10;
        top.setTranslationY(height / 22);
        top.setImageAlpha(255);

        carta1.getLayoutParams().width = width / 4;
        carta1.getLayoutParams().height = height / 5;
        carta2.getLayoutParams().width = width / 4;
        carta2.getLayoutParams().height = height / 5;


        FrameTop.getLayoutParams().width = width / 6;;
        FrameTop.getLayoutParams().height = height / 12;

        FrameDown.getLayoutParams().width = width / 6;;
        FrameDown.getLayoutParams().height = height / 12;

        //disattivo alcuni elementi
        ng.setVisibility(View.GONE);
        menuB.setVisibility(View.GONE);
        ResultText.setVisibility(View.GONE);

        //animazioni
        animations[0] = AnimationUtils.loadAnimation(c, R.anim.scale);

        //aggiustamenti vari
        testoGiu.setText("");
        testoSopra.setText("");

        carta1.setVisibility(View.GONE);
        carta2.setVisibility(View.GONE);

        CoinUp.setVisibility(View.INVISIBLE);
        CoinDown.setVisibility(View.INVISIBLE);

        FrameTop.setVisibility(View.VISIBLE);
        FrameDown.setVisibility(View.VISIBLE);



//        Carta1Down.setEnabled(false);
//        Carta2Down.setEnabled(false);
//        Carta3Down.setEnabled(false);










//        setClickable(Carta1Top,width/4,height/3,true);
//        setClickable(Carta2Top,0,height/3,true);
//        setClickable(Carta3Top,-width/3,height/3,true);
//        setClickable(Carta1Down,width/2+width/14,-height/3,false);
//        setClickable(Carta2Down,width/4,-height/3,false);
//        setClickable(Carta3Down,0,-height/3,false);


//        setClickableTop(Carta2Top,0,1);
//        setClickableTop(Carta3Top,0,1);
//
//        setClickableDown(Carta1Down);
//
//        Carta2Top.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                flipCard(Carta2Top);
//                setImageonView(hand,27,Carta3Top);
//            }
//        });
//        goingTop(Carta1Top,Carta1Down);
//        goingDown(Carta1Down,Carta1Down);



    }

    private void updateScreen(Context c){
        handler.postDelayed(() -> updateScreen( c), 5000);
        String url1 = url + "getAnyUpdate";
        Uri.Builder builder = Uri.parse(url1).buildUpon();
        builder.appendQueryParameter("idpartita", idPartita);
        //builder.appendQueryParameter("param2", "value2");
        String urlWithParams = builder.build().toString();
        Log.d("Carte3","Updating...");
        requestQueue = Volley.newRequestQueue(c);


        StringRequest  stringRequest  = new StringRequest(
                Request.Method.POST, urlWithParams,
                new Response.Listener<String>() {

                    public void onResponse(String  response1) {
                        if (response1 == null){
                            Log.d("Carte3","null...");


                        }

                        else {

                            Log.d("Carte3","ho ricevuto i dati...");
                            try {
                                JSONObject response = new JSONObject(response1);
                                String getIdPlayer = response.getString("idFirstPlayer");
                                int  carta , posizione;
                                if(getIdPlayer.contentEquals(idPlayer)){
                                     carta= response.getInt("cartaP2");
                                     posizione = response.getInt("posCarta2");
                                }
                                else {
                                    carta= response.getInt("cartaP1");
                                    posizione = response.getInt("posCarta1");
                                }
                                if(carta != -1){

                                    if(posizione == 0) CartaPlayer1 = Carta1Top;
                                    else if (posizione == 1) CartaPlayer1 = Carta2Top;
                                    else CartaPlayer1 = Carta3Top;

                                    if(Nostraposizione == 0) CartaPlaye2 = Carta1Down;
                                    else if (Nostraposizione == 1) CartaPlaye2 = Carta2Down;
                                    else CartaPlaye2 = Carta3Down;


                                    cartaAvversario = carta;
                                    AvversarioPickCarta = true;
                                    if((!IoPickCarta)){
                                        Log.d("alinity","carta diversa da -1 e devo mettere la carta" + IoPickCarta + AvversarioPickCarta);
                                        enableCarts();
                                        playTurn(c);
                                    }
                                    else{
                                        result = compareA(hand,nostraCarta,cartaAvversario);
                                        Log.d("infocarte"," "+hand.getInfoCard1(nostraCarta)+" "+hand.getInfoCard1(cartaAvversario));
                                        Log.d("alinity","carta diversa da -1 devo dare risultato "+result);
                                        if(result == 1) {
                                            goingTop(CartaPlayer1);
                                            goingTop(CartaPlaye2);
                                            punteggioB =  punteggioB+hand.getValue(nostraCarta)+hand.getValue(cartaAvversario);
                                            testoSopra.setText(String.valueOf(hand.getValue(nostraCarta)+hand.getValue(cartaAvversario)));
                                            CoinsAdd(CoinUp,1);
                                            updateCoinValue(coinUptext , punteggioB);
                                        }
                                        else {
                                            goingDown(CartaPlayer1);
                                            goingDown(CartaPlaye2);
                                            punteggioA = punteggioA+hand.getValue(nostraCarta)+hand.getValue(cartaAvversario);    //comparo e assegno i punteggi
                                            testoGiu.setText(String.valueOf(hand.getValue(nostraCarta)+hand.getValue(cartaAvversario)));
                                            CoinsAdd(CoinDown,1);
                                            updateCoinValue(coinDownText , punteggioA);

                                        }
                                        setCarta(-1,0);
                                        IoPickCarta = false;

                                        enableCarts();

                                    }

                                        if(posizione == 0) setClickable(Carta1Top,width/4,height/3,true,carta,false);
                                        else if (posizione == 1) setClickable(Carta2Top,0,height/3,true,carta,false);
                                        else setClickable(Carta3Top,-width/3,height/3,true,carta,false);



                                }



                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Gestisci eventuali errori
                        Log.e("VolleyError", "Errore: richiesta " + error.getMessage());
                    }
                });


        requestQueue.add(stringRequest);


    }
    private void DrawCard(ImageView iv,int i,Hand hand){
        Context c = getApplicationContext();
        String s = "drawable/" + hand.getInfoCard1(numbers[i]) + hand.getInfoCard2(numbers[i]);
        int id0 = c.getResources().getIdentifier(s, null, c.getPackageName());
        iv.setImageResource(id0);
    }
    private void disableCarts(){
        Carta1Down.setEnabled(false);
        Carta2Down.setEnabled(false);
        Carta3Down.setEnabled(false);
    }
    private void enableCarts(){
        Carta1Down.setEnabled(true);
        Carta2Down.setEnabled(true);
        Carta3Down.setEnabled(true);
    }
    private void updateTurno(String idplay, String idPartita,Context c){
        requestQueue.cancelAll(request -> true);  // Cancella tutte le richieste

        Log.d("3H","turno"+turno);
        requestQueue = Volley.newRequestQueue(c);
        String url1 = url + "turno";
        Uri.Builder builder = Uri.parse(url1).buildUpon();
        builder.appendQueryParameter("idp", idPartita);
        String urlWithParams = builder.build().toString();
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, urlWithParams,
                response -> {
                    if(response == null) handler.postDelayed(() -> updateTurno(idplay,idPartita,c), 1000);

                    else if(response.contentEquals(idplay)){
                        turno = true;
                        enableCarts();
                        playTurn(c);
                    }
                    else {
                        turno = false;
                        getCarta(idPartita,c);
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
        handler.postDelayed(() -> updateTurno(idplay,idPartita,c), 4000);
    }


    private void playTurn(Context c){
        Log.d("3h","inside playTurn");
        if(TurnoFinito){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

//                    setClickable(Carta1Down,width/2+width/14,-height/3,false,1,false);
//                    setClickable(Carta2Down,width/4,-height/3,false,1,false);
//                    setClickable(Carta3Down,0,-height/3,false,1,false);
//                    Carta1Down.setEnabled(true);
//                    Carta2Down.setEnabled(true);
//                    Carta3Down.setEnabled(true);
                    Carta1Down.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            setCarta(is0,0);
                            IoPickCarta = true;
                            Nostraposizione = 0;
                            setClickable(Carta1Down,width/2+width/14,-height/3,false,101,false);

                            //setCarta(idPartita,idPlayer,String.valueOf(is0),1,c);
                        }});
                    Carta2Down.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            setCarta(is1,1);
                            IoPickCarta = true;
                            Nostraposizione = 1;
                            setClickable(Carta2Down,width/4,-height/3,false,102,false);
                            //setCarta(idPartita,idPlayer,String.valueOf(is1),2,c);
                        }});
                    Carta3Down.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            setCarta(is2,2);
                            IoPickCarta = true;
                            Nostraposizione = 2;
                            setClickable(Carta3Down,0,-height/3,false,103,false);
                            //setCarta(idPartita,idPlayer,String.valueOf(is2),3,c);
                        }});
                }
            }, 100);
        }
        else {
            handler.postDelayed(() -> playTurn(c), 1000);
        }

    }



    private void GetPartita(String idplay, String idPartita,Hand hand,Context c) {
        requestQueue = Volley.newRequestQueue(c);
        String url1 = url + "getPartita";
        Log.d("VolleyError","prima di JSON "+url1);
        JSONObject postData = new JSONObject();
        try {
            postData.put("idPartita", idPartita);
            postData.put("giocatore", idplay);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("carte","prima di JSON "+postData.toString());
        Log.d("carte","prima di richiesta "+idPartita);
        JsonObjectRequest  jsonObjectRequest  = new JsonObjectRequest(
                Request.Method.POST, url1,postData,
                new Response.Listener<JSONObject>() {

                    public void onResponse(JSONObject response) {
                        if (response == null){
                            Log.d("carte","if null  "+idPartita );
                            handler.postDelayed(() -> GetPartita(idplay, idPartita, hand, c), 1000);

                        }

                        else {

                            Log.d("carte", "response != null");
                            // Ottieni il campo "giocatoriExtra" come JSONArray
                            JSONArray carteExtraArray = null;
                            try {
                                myPartita.setIdFirstPlayer(response.getString("idFirstPlayer"));
                                myPartita.setIdSecondPlayer(response.getString("idSecondPlayer"));
                                myPartita.setIdPartita(response.getString("idPartita"));
                                myPartita.setCartaP1(response.getInt("cartaP1"));
                                myPartita.setCartaP2(response.getInt("cartaP2"));
                                myPartita.setCartaP11(response.getInt("cartaP11"));
                                myPartita.setCartaP12(response.getInt("cartaP12"));
                                myPartita.setCartaP13(response.getInt("cartaP13"));
                                myPartita.setCartaP21(response.getInt("cartaP21"));
                                myPartita.setCartaP22(response.getInt("cartaP22"));
                                myPartita.setCartaP23(response.getInt("cartaP23"));


                                String getIdPlayer = response.getString("turn");
                                carteExtraArray = response.getJSONArray("carte");
                                // Converti JSONArray in una List<String>
                                List<String> carte = new ArrayList<>();
//                                for (int i = 0; i < carteExtraArray.length(); i++) {
//                                    carte.add(carteExtraArray.getString(i));
//                                    Log.d("carte", " "+carteExtraArray.getString(i));
//                                }
                                for (int i = 0; i < 40; i++) {
                                    numbers[i] = Integer.parseInt(carteExtraArray.getString(i));
                                    Log.d("carte",""+numbers[i]);
                                }
                                if (idPlayer.contentEquals(getIdPlayer)){
                                    turno = true;
                                }
                                else turno = false;


                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }





                            // Ottieni il campo "giocatoriExtra" come JSONArray
                            Log.d("carte", response.toString());
//

                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Gestisci eventuali errori
                        Log.e("VolleyError", "Errore: richiesta " + error.getMessage());
                    }
                });


        requestQueue.add(jsonObjectRequest);

    }
    private void getCarta(String idPartita,Context c){
        Log.d("Carta3", " getCArta");
        requestQueue.cancelAll(request -> true);  // Cancella tutte le richieste
        if((!turno)&&(!TurnoFinito)) {
            setClickable(Carta1Top,width/4,height/3,true,Integer.valueOf(cartaAvversario),false);
            //int val = compareA(hand,Integer.parseInt(cartaAvversario),nostraCarta);
            TurnoFinito = true;
            coinUptext.setText(""+"20");
        }
        else{
            requestQueue = Volley.newRequestQueue(c);
            String url1 = url + "getCarta";
            Uri.Builder builder = Uri.parse(url1).buildUpon();
            builder.appendQueryParameter("idp", idPartita);
            //builder.appendQueryParameter("param2", "value2");
            String urlWithParams = builder.build().toString();


            StringRequest stringRequest = new StringRequest(
                    Request.Method.GET, urlWithParams,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response != null){
                                cartaAvversario=Integer.valueOf(response);
                                Log.d("3h","carta "+cartaAvversario);
                                //get pos da scrivere
                                if(!turno) setClickable(Carta1Top,width/4,height/3,true,Integer.valueOf(cartaAvversario),false);
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
            if(!turno) handler.postDelayed(() -> getCarta(idPartita,c), 5000);
        }

    }

    private void setCarta(int carta,int pos){
        Log.d("Carta3", " setCarta");
        disableCarts();
        requestQueue = Volley.newRequestQueue(this);
        String url1 = url + "putData";

        JSONObject postData = new JSONObject();
        try {
            postData.put("idFirstPlayer", myPartita.getIdFirstPlayer());
            postData.put("idSecondPlayer", myPartita.getIdSecondPlayer());
            postData.put("idPartita", myPartita.getIdPartita());
            postData.put("turn", idPlayer);
            postData.put("cartaP11", myPartita.getCartaP11());
            postData.put("cartaP12", myPartita.getCartaP12());
            postData.put("cartaP13", myPartita.getCartaP13());
            postData.put("cartaP21", myPartita.getCartaP21());
            postData.put("cartaP22", myPartita.getCartaP22());
            postData.put("cartaP23", myPartita.getCartaP23());
            postData.put("partitaFinita", false);

            if(myPartita.getIdFirstPlayer().contentEquals(idPlayer)) {
                myPartita.setCartaP1(carta);
                postData.put("cartaP1",carta);
                myPartita.setPosCarta1(pos);
                postData.put("posCarta1",pos);
                postData.put("cartaP2",-1);
            }
            else {
                myPartita.setCartaP1(carta);
                postData.put("cartaP2",carta);
                myPartita.setPosCarta1(pos);
                postData.put("posCarta2",pos);
                postData.put("cartaP1",-1);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest  jsonObjectRequest  = new JsonObjectRequest(
                Request.Method.POST, url1,postData,
                new Response.Listener<JSONObject>() {

                    public void onResponse(JSONObject response) {
                        //non fa niente con la risposta
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Gestisci eventuali errori
                        Log.e("VolleyError", "Errore: richiesta " + error.getMessage());
                    }
                });


        requestQueue.add(jsonObjectRequest);

    }






    private ImageView pcMove(){
        int pick_card = rand.nextInt(3);
        switch (pick_card){
            case 2: return Carta1Top;
            case 1: return Carta2Top;
            default: return Carta3Top;
        }
    }




    private int compareA(Hand h, int b, int c){

        //a posizione carta con seme della partita , b carta del giocatore, c carta AI
        String semePartita = h.getInfoCard1(0);
        String cartaP1 = h.getInfoCard1(b);
        String cartaP2 = h.getInfoCard1(c);

        // Controlla se le carte 2 e 3 appartengono al seme della partita
        boolean carta2SegueSeme = cartaP1.contentEquals(semePartita);
        boolean carta3SegueSeme = cartaP2.contentEquals(semePartita);

        // Confronto delle carte secondo le regole
        if (carta2SegueSeme && !carta3SegueSeme) {
            return 1;
        } else if (!carta2SegueSeme && carta3SegueSeme) {
            return 2;
        } else if (carta2SegueSeme && carta3SegueSeme) {
            // Se entrambe appartengono al seme della partita, vince quella con rango maggiore
            return h.getInfoCard2(b) > h.getInfoCard2(c) ? 1 : 2;
        } else {
            // Se nessuna segue il seme della partita, vince la carta con rango maggiore
            if(h.getInfoCard1(b).contentEquals(h.getInfoCard1(c)))
                return h.getInfoCard2(b) > h.getInfoCard2(c) ? 1 : 2;
            else return 1;
        }
    }


    private int min(int a , int b ,int c){
//        if(a<=b && a<=c) return a;
//        if(c<=b && c<=a) return c;
//        else return b;
        return Math.min(a, Math.min(b, c));
    }


    private void newGame(boolean result,int pc ,int player){
//        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layoutR);
//        if(result) layout.setBackgroundColor(getColor(R.color.greenVictory));
//        else layout.setBackgroundColor(getColor(R.color.redLose));

        Carta1Top.setVisibility(View.INVISIBLE);
        Carta2Top.setVisibility(View.INVISIBLE);
        Carta3Top.setVisibility(View.INVISIBLE);
        Carta1Down.setVisibility(View.INVISIBLE);
        Carta2Down.setVisibility(View.INVISIBLE);
        Carta3Down.setVisibility(View.INVISIBLE);
        testoSopra.setText("");
        testoGiu.setText("");
        FrameTop.setVisibility(View.INVISIBLE);
        FrameDown.setVisibility(View.INVISIBLE);
        ng.setVisibility(View.INVISIBLE);
        menuB.setVisibility(View.VISIBLE);
        ResultText.setVisibility(View.VISIBLE);
        ResultText.setText("Punteggio:\n pc "+pc+"\n giocatore "+player);
//        ng.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = getIntent();
//                finish();
//                startActivity(intent);
//            }});
        menuB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Multiplayerv2.this, MenuPrincipale.class);
                finish();  //Kill the activity from which you will go to next activity
                startActivity(i);
            }});

    }
    public void setClickable(ImageView im,int x,int y,boolean top,int id,boolean last){
        if(id == 101) nostraCarta = is0;
        else if (id == 102) nostraCarta = is1;
        else if (id == 103) nostraCarta = is2;

        ObjectAnimator animX = ObjectAnimator.ofFloat(im, "translationX", x);
        ObjectAnimator animY = ObjectAnimator.ofFloat(im, "translationY", y);

        // Combina le animazioni
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animX, animY);
        animatorSet.setDuration(500); // Durata in millisecondi
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        //if(top) flipCard(im,id);
        animatorSet.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //im.setVisibility(View.INVISIBLE);
                animatorSet.reverse();
//                if(top) flipCard(im,r2);
            }
        }, 10000);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //testoGiu.setVisibility(View.INVISIBLE);
//                //testoSopra.setVisibility(View.INVISIBLE);
//                if(!last) im.setVisibility(View.VISIBLE);
//            }
//
//        }, 10500);


    }




    public void goingTop(ImageView imTop){
        AnimatorSet animatorSet = new AnimatorSet();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animX = ObjectAnimator.ofFloat(imTop, "translationX", 0);
                ObjectAnimator animY = ObjectAnimator.ofFloat(imTop, "translationY", -height);

                // Combina le animazioni

                animatorSet.playTogether(animX, animY);
                animatorSet.setDuration(500); // Durata in millisecondi
                animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                //flipCard(imTop,0);
                animatorSet.start();
                //testoSopra.setText("<- + punti");
                //testoSopra.setVisibility(View.VISIBLE);
            }
        }, 2500);
    }

    public void goingDown(ImageView imDonw){
        AnimatorSet animatorSet = new AnimatorSet();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animX = ObjectAnimator.ofFloat(imDonw, "translationX", 0);
                ObjectAnimator animY = ObjectAnimator.ofFloat(imDonw, "translationY", height);

                // Combina le animazioni

                animatorSet.playTogether(animX, animY);
                animatorSet.setDuration(500); // Durata in millisecondi
                animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                //flipCard(imDonw,0);
                animatorSet.start();
                //testoGiu.setText("<- + punti");
                //testoGiu.setVisibility(View.VISIBLE);

            }
        }, 2500);

    }

    public void updateCoinValue(TextView tv , int val){


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tv.setText(String.valueOf(val));

            }
        }, 1500);

    }

    public void CoinsAdd(FrameLayout f,int pos){
        AnimatorSet animatorSet = new AnimatorSet();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int durat = 1000;
                ObjectAnimator animX;
                ObjectAnimator animY;
                if(pos == 1){
                    f.setVisibility(View.VISIBLE);
                    animX = ObjectAnimator.ofFloat(f, "translationX", -width);
                    animY = ObjectAnimator.ofFloat(f, "translationY", 0);
                }
                else {
                    animX = ObjectAnimator.ofFloat(f, "translationX", 0);
                    animY = ObjectAnimator.ofFloat(f, "translationY", 0);
                    durat = 1;
                }


                // Combina le animazioni

                animatorSet.playTogether(animX,animY);
                animatorSet.setDuration(durat); // Durata in millisecondi
                animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                animatorSet.start();

            }
        }, 500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                f.setVisibility(View.INVISIBLE);
                CoinsAdd(f,0);

            }
        }, 2000);

    }

    public void flipCard(ImageView tt,int id){
        @SuppressLint("ResourceType") AnimatorSet flipOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.anim.flip_out);
        @SuppressLint("ResourceType") AnimatorSet flipIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.anim.flip_in);

        flipOut.setTarget(tt);
        flipOut.start();

        flipOut.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // Cambia immagine quando la carta è invisibile (metà del flip)
                tt.setImageResource(id); // Mostra la maglia



                isBackVisible = !isBackVisible; // Inverti stato

                // Avvia la seconda parte dell'animazione
                flipIn.setTarget(tt);
                flipIn.start();
            }

            @Override
            public void onAnimationStart(Animator animation) {}
            @Override
            public void onAnimationCancel(Animator animation) {}
            @Override
            public void onAnimationRepeat(Animator animation) {}
        });
    }

    private void setImageonView(Hand h,int pos,ImageView im){
        String path = "drawable/" + h.getInfoCard1(pos) + h.getInfoCard2(pos);
        Context c = getApplicationContext();
        //id della risorsa
        int getID = c.getResources().getIdentifier(path, null, c.getPackageName());
        im.setImageResource(getID);
    }
}
