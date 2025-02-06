package com.tura.test;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

import com.tura.test.cardsStuff.Deck;
import com.tura.test.cardsStuff.Hand;

import java.util.Random;

public class SinglePlayerActivity2 extends AppCompatActivity {

    int height ;
    int width ;
    int r2 = R.drawable.r2;;
    FrameLayout FrameTop, FrameDown,CoinUp, CoinDown;

    private boolean isBackVisible = true;
    ImageView imageView,carta1,carta2;
    ImageView t0,t1,t2,tx1,tx2,tx3,tx,top;

    TextView testoSopra, testoGiu, ResultText ,coinUptext, coinDownText;
    int x1 = 0, x2 = 1 , x3 = 2, y1 = 3, y2 = 4 , y3 = 5, z = 6 , punteggioA = 0 , punteggioB = 0, odd,even,carte=0;
    //int x1 = 39, x2 = 39 , x3 = 39, y1 = 6, y2 = 6 , y3 = 6, z = 6 , punteggioA = 0 , punteggioB = 0, odd,even;



    Random rand = new Random();

    Boolean primo = true ,secondo = true,lose = true;

    Button ng,menuB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen_test);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
         height = displayMetrics.heightPixels;
         width = displayMetrics.widthPixels;

        imageView = findViewById(R.id.im7);

        tx1 = findViewById(R.id.im1);
        tx2 = findViewById(R.id.im2);
        tx3 = findViewById(R.id.im3);
        t0 = findViewById(R.id.im4);
        t1 = findViewById(R.id.im5);
        t2 = findViewById(R.id.im6);
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




        Deck D = new Deck();
        Context c = getApplicationContext();
        Hand hand = new Hand(D);
//        for(int i = 0; i<40; i++){
//            Log.d("mazzo",""+hand.CardOnHandOneToOne(i)+"\n");
//        }

        //per ottenere il nome della risorsa
        String s0 = "drawable/"+hand.getInfoCard1(x1)+hand.getInfoCard2(x1);
        String s1 = "drawable/"+hand.getInfoCard1(x2)+hand.getInfoCard2(x2);
        String s2 = "drawable/"+hand.getInfoCard1(x3)+hand.getInfoCard2(x3);

//        String s3 = "drawable/"+hand.getInfoCard1(y1)+hand.getInfoCard2(y1);
//        String s4 = "drawable/"+hand.getInfoCard1(y2)+hand.getInfoCard2(y2);
//        String s5 = "drawable/"+hand.getInfoCard1(y3)+hand.getInfoCard2(y3);

        String s6 = "drawable/"+hand.getInfoCard1(z)+hand.getInfoCard2(z);


        //id della risorsa
        int id0 = c.getResources().getIdentifier(s0,null,c.getPackageName());
        int id1 = c.getResources().getIdentifier(s1,null,c.getPackageName());
        int id2 = c.getResources().getIdentifier(s2,null,c.getPackageName());


        int id6 = c.getResources().getIdentifier(s6,null,c.getPackageName());

        t0.setImageResource(id0);
        t1.setImageResource(id1);
        t2.setImageResource(id2);


        imageView.setImageResource(id6);


        //imposto dimensioni dei vari elementi
        t0.getLayoutParams().width = width/4;
        t0.getLayoutParams().height = height/5;
        t1.getLayoutParams().height = height/5;
        t1.getLayoutParams().width = width/4;
        t2.getLayoutParams().height = height/5;
        t2.getLayoutParams().width = width/4;

        tx2.getLayoutParams().height = height/5;
        tx2.getLayoutParams().width = width/4;
        tx1.getLayoutParams().height = height/5;
        tx1.getLayoutParams().width = width/4;
        tx3.getLayoutParams().height = height/5;
        tx3.getLayoutParams().width = width/4;


        imageView.getLayoutParams().width = width/3;
        imageView.getLayoutParams().height = height/6;

        top.getLayoutParams().width = width/3;
        top.getLayoutParams().height = height/10;
        top.setTranslationY(height/22);
        top.setImageAlpha(255);

        carta1.getLayoutParams().width = width/3;
        carta1.getLayoutParams().height = height/6;
        carta2.getLayoutParams().width = width/3;
        carta2.getLayoutParams().height = height/6;

        //disattivo alcuni elementi
        ng.setVisibility(View.GONE);
        menuB.setVisibility(View.GONE);
        ResultText.setVisibility(View.GONE);

        //animazioni


        //aggiustamenti vari
        testoGiu.setText("");
        testoSopra.setText("");

        carta1.setVisibility(View.GONE);
        carta2.setVisibility(View.GONE);

        CoinUp.setVisibility(View.INVISIBLE);
        CoinDown.setVisibility(View.INVISIBLE);

        FrameTop.setVisibility(View.VISIBLE);
        FrameDown.setVisibility(View.VISIBLE);






        t0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(carte<39) {
                    Log.d("valori2","valori "+carte+" "+x1+" "+y1+" "+y2+" "+y3);


                    setClickable(t0,width/2+width/14,-height/3,false,1,false);

                    int AI = AI(hand,x1,y1,y2,y3);      //viene scelta migliore carta e restituito il suo indice

                    tx = pcMove();                      //viene scelta a caso  carta per animazione

                    t0.setEnabled(false);
                    t1.setEnabled(false);
                    t2.setEnabled(false);

                            String lstS = "drawable/"+hand.getInfoCard1(x1)+hand.getInfoCard2(x1);
                            int lstI = c.getResources().getIdentifier(lstS,null,c.getPackageName());
                            carta1.setImageResource(lstI);

                            String sc = "drawable/"+hand.getInfoCard1(AI)+hand.getInfoCard2(AI);    //creo la stringa a cui assegno riferimento all'oggetto
                            int idAI = c.getResources().getIdentifier(sc,null,c.getPackageName());  //intero
                    setClickable(tx1,width/4,height/3,true,idAI,false);
                            //carta2.setImageResource(idAI);                                          //imposto carta PC pick
                            if(compareA(hand,z,x1,AI) == 1) {   //punteggioA - del giocatore
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        goingDown(tx1);
                                        goingDown(t0);
                                        punteggioA = punteggioA+hand.getValue(x1)+hand.getValue(AI);    //comparo e assegno i punteggi
                                        testoGiu.setText(String.valueOf(hand.getValue(x1)+hand.getValue(AI)));
                                        CoinsAdd(CoinDown,1);
                                        updateCoinValue(coinDownText , punteggioA);
                                        //backCardToPosition(tx1);
                                        //backCardToPosition(t0);
                                    }
                                }, 1);

                            }
                            else{
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        goingTop(t0);
                                        goingTop(tx1);
                                        punteggioB =  punteggioB+hand.getValue(x1)+hand.getValue(AI);
                                        testoSopra.setText(String.valueOf(hand.getValue(x1)+hand.getValue(AI)));
                                        CoinsAdd(CoinUp,1);
                                        updateCoinValue(coinUptext , punteggioB);

                                        //backCardToPosition(tx1);
                                        //backCardToPosition(t0);
                                    }
                                }, 1);

                            }

                    t0.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //t0.setImageAlpha(255);
                            testoSopra.setText("");
                            testoGiu.setText("");
                            String pc1="";
                            x1 = NextPos(x1);
                            if(AI == y1) {
                                y1= NextPosPC(y1);

                            }
                            if(AI == y2){
                                y2= NextPosPC(y2);

                            }
                            if(AI == y3){
                                y3= NextPosPC(y3);
                            }


                            //hand.modingHand(g1,x1);             //assegna in posizione 0 la carta g1

                            String s0 = "drawable/"+hand.getInfoCard1(x1)+hand.getInfoCard2(x1);

                            int id0 = c.getResources().getIdentifier(s0,null,c.getPackageName());


                            t0.setImageResource(id0);

                            //modifico carta pc




                            tx.setImageAlpha(255);
                            if(x1>37){
                                top.setImageAlpha(0);
                                imageView.setImageAlpha(0);
                            }

                            t0.setEnabled(true);
                            t1.setEnabled(true);
                            t2.setEnabled(true);
                        }
                    }, 4000);
                }

                else { //blocco dell'ultima mano
                    Log.d("valori2","valori "+x1+" "+y1+" "+y2+" "+y3);
                    //x1 = 39;
                    top.setImageAlpha(0);

                    int AI = AI(hand,x1,y1,y2,y3);
//
//                    String lstS = "drawable/"+hand.getInfoCard1(x1)+hand.getInfoCard2(x1);
//                    int lstI = c.getResources().getIdentifier(lstS,null,c.getPackageName());
////                  carta1.setImageResource(lstI);
                    String sc = "drawable/"+hand.getInfoCard1(AI)+hand.getInfoCard2(AI);
                    int idAI = c.getResources().getIdentifier(sc,null,c.getPackageName());
////                    carta2.setImageResource(idAI);
////                    carta1.setImageAlpha(255);
////                    carta2.setImageAlpha(255);
                    setClickable(t0,width/2+width/14,-height/3,false,1,true);
                    setClickable(tx1,width/4,height/3,true,idAI,true);

                    if(compareA(hand,z,x1,AI) == 1) {

                        goingDown(tx1);
                        goingDown(t0);
                        punteggioA = punteggioA+hand.getValue(x1)+hand.getValue(AI);    //comparo e assegno i punteggi
                        testoGiu.setText(String.valueOf(hand.getValue(x1)+hand.getValue(AI)));
                        CoinsAdd(CoinDown,1);
                        updateCoinValue(coinDownText , punteggioA);
                    }
                    else{
                        goingTop(t0);
                        goingTop(tx1);
                        punteggioB =  punteggioB+hand.getValue(x1)+hand.getValue(AI);
                        testoSopra.setText(String.valueOf(hand.getValue(x1)+hand.getValue(AI)));
                        CoinsAdd(CoinUp,1);
                        updateCoinValue(coinUptext , punteggioB);
                    }
                    if(y1 == AI) {
                        y1 = 0;

                    }
                    if(y2 == AI){
                        y2 = 0;

                    }
                    if(y3 == AI){
                        y3 = 0;

                    }
//                    tx1.setVisibility(View.INVISIBLE);
//                    t0.setVisibility(View.INVISIBLE);
//                    imageView.setImageAlpha(0);
                    //tx.setImageAlpha(0);
                    //t0.setEnabled(false);

                    if((y1==0)&&(y2==0)&&(y3==0))newGame(lose,punteggioB,punteggioA);

                }


            }
        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(carte<39) {
                    Log.d("valori2","valori "+carte+" "+x1+" "+y1+" "+y2+" "+y3);


                    setClickable(t1,width/3,-height/3,false,1,false);

                    int AI = AI(hand,x1,y1,y2,y3);      //viene scelta migliore carta e restituito il suo indice

                    tx = pcMove();                      //viene scelta a caso animazione carta

                    t0.setEnabled(false);
                    t2.setEnabled(false);
                    t1.setEnabled(false);

                    String lstS = "drawable/"+hand.getInfoCard1(x1)+hand.getInfoCard2(x1);
                    int lstI = c.getResources().getIdentifier(lstS,null,c.getPackageName());
                    carta1.setImageResource(lstI);

                    String sc = "drawable/"+hand.getInfoCard1(AI)+hand.getInfoCard2(AI);    //creo la stringa a cui assegno riferimento all'oggetto
                    int idAI = c.getResources().getIdentifier(sc,null,c.getPackageName());  //intero
                    setClickable(tx2,0,height/3,true,idAI,false);
                    //carta2.setImageResource(idAI);                                          //imposto carta PC pick
                    if(compareA(hand,z,x2,AI) == 1) {   //punteggioA - del giocatore
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                goingDown(tx2);
                                goingDown(t1);
                                punteggioA = punteggioA+hand.getValue(x1)+hand.getValue(AI);    //comparo e assegno i punteggi
                                testoGiu.setText(String.valueOf(hand.getValue(x1)+hand.getValue(AI)));
                                CoinsAdd(CoinDown,1);
                                updateCoinValue(coinDownText , punteggioA);
                                //backCardToPosition(tx2);
                                //backCardToPosition(t1);
                            }
                        }, 1);

                    }
                    else{
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                goingTop(t1);
                                goingTop(tx2);
                                punteggioB =  punteggioB+hand.getValue(x1)+hand.getValue(AI);
                                testoSopra.setText(String.valueOf(hand.getValue(x1)+hand.getValue(AI)));
                                CoinsAdd(CoinUp,1);
                                updateCoinValue(coinUptext , punteggioB);

                                //backCardToPosition(tx2);
                                //backCardToPosition(t1);
                            }
                        }, 1);

                    }

                    t1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //t1.setImageAlpha(255);
                            testoSopra.setText("");
                            testoGiu.setText("");
                            String pc1="";
                            x1 = NextPos(x1);
                            if(AI == y1) {
                                y1= NextPosPC(y1);

                            }
                            if(AI == y2){
                                y2= NextPosPC(y2);

                            }
                            if(AI == y3){
                                y3= NextPosPC(y3);
                            }


                            //hand.modingHand(g1,x1);             //assegna in posizione 0 la carta g1

                            String s0 = "drawable/"+hand.getInfoCard1(x1)+hand.getInfoCard2(x1);

                            int id0 = c.getResources().getIdentifier(s0,null,c.getPackageName());


                            t1.setImageResource(id0);

                            //modifico carta pc




                            tx.setImageAlpha(255);
                            if(x1>37){
                                top.setImageAlpha(0);
                                imageView.setImageAlpha(0);
                            }

                            t0.setEnabled(true);
                            t2.setEnabled(true);
                            t1.setEnabled(true);
                        }
                    }, 4000);
                }

                else { //blocco dell'ultima mano
                    Log.d("valori2","valori "+x1+" "+y1+" "+y2+" "+y3);
                    //x1 = 39;
                    top.setImageAlpha(0);

                    int AI = AI(hand,x1,y1,y2,y3);
//
//                    String lstS = "drawable/"+hand.getInfoCard1(x1)+hand.getInfoCard2(x1);
//                    int lstI = c.getResources().getIdentifier(lstS,null,c.getPackageName());
////                  carta1.setImageResource(lstI);
                    String sc = "drawable/"+hand.getInfoCard1(AI)+hand.getInfoCard2(AI);
                    int idAI = c.getResources().getIdentifier(sc,null,c.getPackageName());
////                    carta2.setImageResource(idAI);
////                    carta1.setImageAlpha(255);
////                    carta2.setImageAlpha(255);
                    setClickable(t1,width/3,-height/3,false,1,true);
                    setClickable(tx2,0,height/3,true,idAI,false);

                    if(compareA(hand,z,x2,AI) == 1) {

                        goingDown(tx2);
                        goingDown(t1);
                        punteggioA = punteggioA+hand.getValue(x1)+hand.getValue(AI);    //comparo e assegno i punteggi
                        testoGiu.setText(String.valueOf(hand.getValue(x1)+hand.getValue(AI)));
                        CoinsAdd(CoinDown,1);
                        updateCoinValue(coinDownText , punteggioA);
                    }
                    else{
                        goingTop(t1);
                        goingTop(tx2);
                        punteggioB =  punteggioB+hand.getValue(x1)+hand.getValue(AI);
                        testoSopra.setText(String.valueOf(hand.getValue(x1)+hand.getValue(AI)));
                        CoinsAdd(CoinUp,1);
                        updateCoinValue(coinUptext , punteggioB);
                    }
                    if(y1 == AI) {
                        y1 = 0;

                    }
                    if(y2 == AI){
                        y2 = 0;

                    }
                    if(y3 == AI){
                        y3 = 0;

                    }
//                    tx2.setVisibility(View.INVISIBLE);
//                    t1.setVisibility(View.INVISIBLE);
//                    imageView.setImageAlpha(0);
                    //tx.setImageAlpha(0);
                    //t1.setEnabled(false);

                    if((y1==0)&&(y2==0)&&(y3==0))newGame(lose,punteggioB,punteggioA);

                }


            }
        });


        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(carte<39) {
                    Log.d("valori2","valori "+carte+" "+x1+" "+y1+" "+y2+" "+y3);


                    setClickable(t2,0,-height/3,false,1,false);

                    int AI = AI(hand,x1,y1,y2,y3);      //viene scelta migliore carta e restituito il suo indice

                    tx = pcMove();                      //viene scelta a caso animazione carta

                    t0.setEnabled(false);
                    t1.setEnabled(false);
                    t2.setEnabled(false);

                    String lstS = "drawable/"+hand.getInfoCard1(x1)+hand.getInfoCard2(x1);
                    int lstI = c.getResources().getIdentifier(lstS,null,c.getPackageName());
                    carta1.setImageResource(lstI);

                    String sc = "drawable/"+hand.getInfoCard1(AI)+hand.getInfoCard2(AI);    //creo la stringa a cui assegno riferimento all'oggetto
                    int idAI = c.getResources().getIdentifier(sc,null,c.getPackageName());  //intero
                    setClickable(tx3,-width/3,height/3,true,idAI,false);
                    //carta2.setImageResource(idAI);                                          //imposto carta PC pick
                    if(compareA(hand,z,x3,AI) == 1) {   //punteggioA - del giocatore
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                goingDown(tx3);
                                goingDown(t2);
                                punteggioA = punteggioA+hand.getValue(x1)+hand.getValue(AI);    //comparo e assegno i punteggi
                                testoGiu.setText(String.valueOf(hand.getValue(x1)+hand.getValue(AI)));
                                CoinsAdd(CoinDown,1);
                                updateCoinValue(coinDownText , punteggioA);
                                //backCardToPosition(tx3);
                                //backCardToPosition(t2);
                            }
                        }, 1);

                    }
                    else{
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                goingTop(t2);
                                goingTop(tx3);
                                punteggioB =  punteggioB+hand.getValue(x1)+hand.getValue(AI);
                                testoSopra.setText(String.valueOf(hand.getValue(x1)+hand.getValue(AI)));
                                CoinsAdd(CoinUp,1);
                                updateCoinValue(coinUptext , punteggioB);

                                //backCardToPosition(tx3);
                                //backCardToPosition(t2);
                            }
                        }, 1);

                    }

                    t2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //t2.setImageAlpha(255);
                            testoSopra.setText("");
                            testoGiu.setText("");
                            String pc1="";
                            x1 = NextPos(x1);
                            if(AI == y1) {
                                y1= NextPosPC(y1);

                            }
                            if(AI == y2){
                                y2= NextPosPC(y2);

                            }
                            if(AI == y3){
                                y3= NextPosPC(y3);
                            }


                            //hand.modingHand(g1,x1);             //assegna in posizione 0 la carta g1

                            String s0 = "drawable/"+hand.getInfoCard1(x1)+hand.getInfoCard2(x1);

                            int id0 = c.getResources().getIdentifier(s0,null,c.getPackageName());


                            t2.setImageResource(id0);

                            //modifico carta pc




                            tx.setImageAlpha(255);
                            if(x1>37){
                                top.setImageAlpha(0);
                                imageView.setImageAlpha(0);
                            }

                            t0.setEnabled(true);
                            t1.setEnabled(true);
                            t2.setEnabled(true);
                        }
                    }, 4000);
                }

                else { //blocco dell'ultima mano
                    Log.d("valori2","valori "+x1+" "+y1+" "+y2+" "+y3);
                    //x1 = 39;
                    top.setImageAlpha(0);

                    int AI = AI(hand,x1,y1,y2,y3);
//
//                    String lstS = "drawable/"+hand.getInfoCard1(x1)+hand.getInfoCard2(x1);
//                    int lstI = c.getResources().getIdentifier(lstS,null,c.getPackageName());
////                  carta1.setImageResource(lstI);
                    String sc = "drawable/"+hand.getInfoCard1(AI)+hand.getInfoCard2(AI);
                    int idAI = c.getResources().getIdentifier(sc,null,c.getPackageName());
////                    carta2.setImageResource(idAI);
////                    carta1.setImageAlpha(255);
////                    carta2.setImageAlpha(255);
                    setClickable(t2,0,-height/3,false,1,true);
                    setClickable(tx3,-width/3,height/3,true,idAI,true);

                    if(compareA(hand,z,x3,AI) == 1) {

                        goingDown(tx3);
                        goingDown(t2);
                        punteggioA = punteggioA+hand.getValue(x1)+hand.getValue(AI);    //comparo e assegno i punteggi
                        testoGiu.setText(String.valueOf(hand.getValue(x1)+hand.getValue(AI)));
                        CoinsAdd(CoinDown,1);
                        updateCoinValue(coinDownText , punteggioA);
                    }
                    else{
                        goingTop(t2);
                        goingTop(tx3);
                        punteggioB =  punteggioB+hand.getValue(x1)+hand.getValue(AI);
                        testoSopra.setText(String.valueOf(hand.getValue(x1)+hand.getValue(AI)));
                        CoinsAdd(CoinUp,1);
                        updateCoinValue(coinUptext , punteggioB);
                    }
                    if(y1 == AI) {
                        y1 = 0;

                    }
                    if(y2 == AI){
                        y2 = 0;

                    }
                    if(y3 == AI){
                        y3 = 0;

                    }
//                    tx3.setVisibility(View.INVISIBLE);
//                    t2.setVisibility(View.INVISIBLE);
//                    imageView.setImageAlpha(0);
                    //tx.setImageAlpha(0);
                    //t2.setEnabled(false);

                    if((y1==0)&&(y2==0)&&(y3==0))newGame(lose,punteggioB,punteggioA);

                }


            }
        });


    }

    private ImageView pcMove(){
        int pick_card = rand.nextInt(5);
        Log.d("fuck","switch");
        switch (pick_card){
            case 3: {
                //setClickable(tx1,width/4,height/3,true);
                return tx1;
            }
            case 4: {
               // setClickable(tx2,0,height/3,true);
                return tx2;
            }
            default: {
                //setClickable(tx3,-width/3,height/3,true);
                return tx3;
            }
        }
    }

    private int NextPosPC (int pos){
        if(odd == 38) return 6;
        if(primo){
            odd = 8;
            primo = false;
            return odd;
        }
        else{
            odd = odd+2;
            return odd;
        }
    }
    private int NextPos (int pos){
        if(secondo){
            even = 7;
            secondo = false;
            return even;
        }
        else{
            even = even+2;
            carte = even;
            return even;
        }
    }


    private int compareA(Hand h, int a, int b, int c){
        Log.d("valori","carta giocatore "+h.getValue(b)+h.getInfoCard1(b)+h.getInfoCard2(b)+" "+h.getValue(c) + h.getInfoCard1(c)+h.getInfoCard2(c));
        //a posizione carta con seme della partita , b carta del giocatore, c carta AI
        String semePartita = h.getInfoCard1(a);
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


//    private int AIv2(Hand h , int p1 , int p2, int p3 , int p4){
//
//        String semePartita = h.getInfoCard1(6);
//        int migliorCarta = -1; //in caso di errori o altro per default scegli la prima dei 3
//        List<String[]> nostreCarte = new LinkedList<String[]>();
//        Card c1 = new Card(h.getInfoCard1())
//        nostreCarte.add(h.getInfoCardRangoSeme(p2));
//        nostreCarte.add(h.getInfoCardRangoSeme(p3));
//        nostreCarte.add(h.getInfoCardRangoSeme(p4));
//
//        // Controlla se c'è una carta di briscola tra le nostre
//        for (Card nostraCarta : nostreCarte) {
//            if (nostraCarta.getSeme().equals(semeBriscola)) {
//                if (migliorCarta == -1 || nostraCarta.getValore() > migliorCarta.getValore()) {
//                    migliorCarta = nostraCarta;
//                }
//            }
//        }
//
//        // Se non abbiamo carte di briscola
//        if (migliorCarta == null) {
//            String semeGiocatore = cartaGiocatore.getSeme();
//            for (Carta nostraCarta : nostreCarte) {
//                if (nostraCarta.getSeme().equals(semeGiocatore)) {
//                    if (migliorCarta == null || nostraCarta.getValore() > migliorCarta.getValore()) {
//                        migliorCarta = nostraCarta;
//                    }
//                }
//            }
//        }
//
//        // Se non abbiamo carte del seme del giocatore
//        if (migliorCarta == null) {
//            // Gioca la carta con valore più basso
//            for (Carta nostraCarta : nostreCarte) {
//                if (migliorCarta == null || nostraCarta.getValore() < migliorCarta.getValore()) {
//                    migliorCarta = nostraCarta;
//                }
//            }
//        }
//
//        return migliorCarta;
//
//    }

    private int min(int a , int b ,int c){
//        if(a<=b && a<=c) return a;
//        if(c<=b && c<=a) return c;
//        else return b;
        return Math.min(a, Math.min(b, c));
    }
    private int AI(Hand h, int p1, int p2, int p3, int p4) {
        int v = h.getValue(p2) ;
        String seme1 = h.getInfoCard1(p1), MainSeme = h.getInfoCard1(6);

        String carta1,carta2,carta3;
        carta1 = h.CardOnHandOneToOne(p2);
        carta2 = h.CardOnHandOneToOne(p3);
        carta3 = h.CardOnHandOneToOne(p4);

        /*devo fare in modo che se la carta di PC e' uscita allora non la deve usare piu
        quindi la mia strategia assegnare 0 alla carta uscita quindi p2 , p3 o p4 = 0 e al loro posto
        assegno una delle p2 , p3 o p4
         */
        for(int i = 0; i<3 ; i++){
            if(p2 == 0) p2 = p3;
            if(p3 == 0) p3 = p4;
            if(p4 == 0) p4 = p2;
        }
        if(h.getValue(p1) == 0 ) {    //se carta del giocatore 1 e' da 0 punti cerchiamo anche noi di dare una carta nulla
            if((v >= h.getValue(p3))&& (h.getInfoCard1(p3).compareTo(MainSeme) != 0)) {return p3;}
            if((v >=  h.getValue(p4))&& (h.getInfoCard1(p4).compareTo(MainSeme) != 0)){return p4;}
            else return p2;
        }

        /*
        carta del giocatore 1 ha un valore allora dobbiamo capire se tra le nostre 3 carte c'e' una carta
        dello stesso seme ma di rango maggiore oppure una carta cheha lo stesso seme della carta scoperta sul tavolo
         */

        else {//carta del giocatore ha valore
            if (seme1.compareTo(h.getInfoCard1(p2)) == 0) {
                if (h.getValue(p1) < h.getValue(p2)) return p2;
                else {
                    if(h.getValue(p1) < h.getValue(p3)) return p3;
                    else return p4;
                }
            }

            /*
            Se il giocatore ha la carta dello stesso seme con carta principale allora dobbiamo
            vedere se e' una carta che ha valore asso ,tre , re ,cavallo o fante altrimenti se la
            carta non ha un valore dobbiamo restituire una carta senza un valore o di alore piu
            piccolo possibile

             */

            else if (seme1.compareTo(h.getInfoCard1(6))== 0) {
                if(seme1.compareTo(h.getInfoCard1(p2)) == 0) {
                    if((v>0)&& (v<h.getValue(p2))) return p2;
                }
                else if(seme1.compareTo(h.getInfoCard1(p3)) == 0) {
                    if((v>0)&& (v<h.getValue(p3))) return p3;
                }
                else if(seme1.compareTo(h.getInfoCard1(p4)) == 0) {
                    if((v>0)&& (v<h.getValue(p4))) return p4;
                }
            }

            //prima carta e' dello stesso seme di carta principale?

            else if (h.getInfoCard1(p2).compareTo(h.getInfoCard1(6)) == 0) return p2;
            else if (h.getInfoCard1(p3).compareTo(h.getInfoCard1(6)) == 0) return p3;
            else if (h.getInfoCard1(p4).compareTo(h.getInfoCard1(6)) == 0) return p4;

                //cerchiamo il valore piu piccolo
            else if ((h.getValue(p2) <= h.getValue(p3)) && (h.getValue(p2) <= h.getValue(p4)))
                return p2;
            else if ((h.getValue(p3) <= h.getValue(p2)) && (h.getValue(p3) <= h.getValue(p4)))
                return p3;
            else return p4;


        }
        return p2;
    }


    private void newGame(boolean result,int pc ,int player){
//        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layoutR);
//        if(result) layout.setBackgroundColor(getColor(R.color.greenVictory));
//        else layout.setBackgroundColor(getColor(R.color.redLose));
        t2.setVisibility(View.INVISIBLE);
        tx1.setVisibility(View.INVISIBLE);
        tx2.setVisibility(View.INVISIBLE);
        tx3.setVisibility(View.INVISIBLE);
        t0.setVisibility(View.INVISIBLE);
        t1.setVisibility(View.INVISIBLE);
        testoSopra.setText("");
        testoGiu.setText("");
        FrameTop.setVisibility(View.INVISIBLE);
        FrameDown.setVisibility(View.INVISIBLE);
        ng.setVisibility(View.VISIBLE);
        menuB.setVisibility(View.VISIBLE);
        ResultText.setVisibility(View.VISIBLE);
        ResultText.setText("Punteggio:\n pc "+pc+"\n giocatore "+player);
        ng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }});
        menuB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SinglePlayerActivity2.this, MenuPrincipale.class);
                finish();  //Kill the activity from which you will go to next activity
                startActivity(i);
            }});

    }
    public void setClickable(ImageView im,int x,int y,boolean top,int id,boolean last){

        ObjectAnimator animX = ObjectAnimator.ofFloat(im, "translationX", x);
        ObjectAnimator animY = ObjectAnimator.ofFloat(im, "translationY", y);

        // Combina le animazioni
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animX, animY);
        animatorSet.setDuration(500); // Durata in millisecondi
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        if(top) flipCard(im,id);
        animatorSet.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                im.setVisibility(View.INVISIBLE);
                animatorSet.reverse();
                if(top) flipCard(im,r2);
            }
        }, 3500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //testoGiu.setVisibility(View.INVISIBLE);
                //testoSopra.setVisibility(View.INVISIBLE);
                if(!last) im.setVisibility(View.VISIBLE);
            }

        }, 4000);


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



    //fuori da onCreate


}

