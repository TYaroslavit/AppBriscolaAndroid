package com.tura.test;


import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tura.test.cardsStuff.Card;
import com.tura.test.cardsStuff.Deck;
import com.tura.test.cardsStuff.Hand;

import java.util.Random;

public class thirdAcivity extends AppCompatActivity {
    ImageView imageView,carta1,carta2;
    ImageView t0,t1,t2,tx1,tx2,tx3,tx;
    int x1 = 0, x2 = 1 , x3 = 2, y1 = 3, y2 = 4 , y3 = 5, z = 6 , punteggioA = 0 , punteggioB = 0;

    Animation[] animations = new Animation[16];

    Random rand = new Random();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        imageView = findViewById(R.id.im7);

        tx1 = findViewById(R.id.im1);
        tx2 = findViewById(R.id.im2);
        tx3 = findViewById(R.id.im3);
        t0 = findViewById(R.id.im4);
        t1 = findViewById(R.id.im5);
        t2 = findViewById(R.id.im6);
        carta1 = findViewById(R.id.carta1);
        carta2 = findViewById(R.id.carta2);


        Deck D = new Deck();
        Context c = getApplicationContext();
        Hand hand = new Hand(D);
        for(int i = 0; i<40; i++){
            Log.d("mazzo",""+hand.CardOnHandOneToOne(i)+"\n");
        }

        //per ottenere il nome della risorsa
        String s0 = "drawable/"+hand.getInfoCard1(x1)+hand.getInfoCard2(x1);
        String s1 = "drawable/"+hand.getInfoCard1(x2)+hand.getInfoCard2(x2);
        String s2 = "drawable/"+hand.getInfoCard1(x3)+hand.getInfoCard2(x3);

        String s3 = "drawable/"+hand.getInfoCard1(y1)+hand.getInfoCard2(y1);
        String s4 = "drawable/"+hand.getInfoCard1(y2)+hand.getInfoCard2(y2);
        String s5 = "drawable/"+hand.getInfoCard1(y3)+hand.getInfoCard2(y3);

        String s6 = "drawable/"+hand.getInfoCard1(z)+hand.getInfoCard2(z);


        //id della risorsa
        int id0 = c.getResources().getIdentifier(s0,null,c.getPackageName());
        int id1 = c.getResources().getIdentifier(s1,null,c.getPackageName());
        int id2 = c.getResources().getIdentifier(s2,null,c.getPackageName());

        int id3 = c.getResources().getIdentifier(s3,null,c.getPackageName());
        int id4 = c.getResources().getIdentifier(s4,null,c.getPackageName());
        int id5 = c.getResources().getIdentifier(s5,null,c.getPackageName());

        int id6 = c.getResources().getIdentifier(s6,null,c.getPackageName());

        t0.setImageResource(id0);
        t1.setImageResource(id1);
        t2.setImageResource(id2);

        tx1.setImageResource(id3);
        tx2.setImageResource(id4);
        tx3.setImageResource(id5);

        imageView.setImageResource(id6);


        //imposto dimensioni dei vari elementi
        t0.getLayoutParams().width = width/3-width/40;
        t0.getLayoutParams().height = height/3;
        t1.getLayoutParams().height = height/3;
        t1.getLayoutParams().width = width/3 - width/40;
        t2.getLayoutParams().height = height/3;
        t2.getLayoutParams().width = width/3-width/40;

        tx2.getLayoutParams().height = height/4;
        tx2.getLayoutParams().width = width/3-width/40;
        tx1.getLayoutParams().height = height/4;
        tx1.getLayoutParams().width = width/3-width/40;
        tx3.getLayoutParams().height = height/4;
        tx3.getLayoutParams().width = width/3-width/40;


        imageView.getLayoutParams().width = width/3;
        imageView.getLayoutParams().height = height/5;

        carta1.getLayoutParams().width = width/3;
        carta1.getLayoutParams().height = height/5;
        carta2.getLayoutParams().width = width/3;
        carta2.getLayoutParams().height = height/5;

        //animazioni
        animations[0] = AnimationUtils.loadAnimation(c,R.anim.scale);







        t0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card g1,pc ;
                g1  = hand.NextCard();
                pc  = hand.NextCard();

                if(g1 != null) {



                    /*
                    hand.modingHand(g1,0);

                    String s0 = "drawable/"+hand.getInfoCard1(0)+hand.getInfoCard2(0);

                    int id0 = c.getResources().getIdentifier(s0,null,c.getPackageName());
                    t0.setImageResource(id0);
                    //Log.d("out1",hand.CardsOnHandToString());
                    */

                    t0.startAnimation(animations[0]);  //carta  1 comincia a muoversi

                    int AI = AI(hand,x1,y1,y2,y3);      //viene scelta migliore carta e restituito il suo indice
                    tx = pcMove(AI);                      //viene scelta a caso animazione carta
                    tx.startAnimation(animations[0]);   //carta random del PC si muove
                    t0.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //carta1.setImageResource(id0);
                            carta1.setImageDrawable(t0.getDrawable());  //imposto la carta pick con carta del giocatore
                            tx.setImageAlpha(0);   //imposto carta PC trasparente

                            //carta2.setImageDrawable(tx.getDrawable());

                            String sc = "drawable/"+hand.getInfoCard1(AI)+hand.getInfoCard2(AI);    //creo la stringa a cui assegno riferimento all'oggetto
                            int idAI = c.getResources().getIdentifier(sc,null,c.getPackageName());  //intero
                            carta2.setImageResource(idAI);                                          //imposto carta PC pick
                            if(compareF(hand,z,x1,AI) == 1) punteggioA = punteggioA+hand.getValue(x1)+hand.getValue(AI);    //comparo e assegno i punteggi
                            else punteggioB =  punteggioB+hand.getValue(x1)+hand.getValue(AI);
                            Log.d("valore ","punteggio A "+punteggioA+" valore di A "+hand.getValue(x1)+
                                    " \n punteggio B "+punteggioB+" valore y "+hand.getValue(AI)+"\n info A "+hand.CardOnHandOneToOne(x1)+" " +
                                    ""+hand.CardOnHandOneToOne(AI)+"" +
                                    "\n"+hand.CardOnHandOneToOne(y1)+hand.CardOnHandOneToOne(y2)+hand.CardOnHandOneToOne(y3)+
                                    "\n"+hand.getValue(AI) +" "+AI+ " "+hand.getValue(y1)+" "+hand.getValue(y2)+" "+hand.getValue(y3)+"" +
                                    "\n"+hand.getValue(x1) + " "+hand.getValue(x2)+" "+hand.getValue(x3));
                            //t0.setImageAlpha(0);
                        }
                    }, 400);
                    t0.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hand.modingHand(g1,x1);             //assegna in posizione 0 la carta g1

                            String s0 = "drawable/"+hand.getInfoCard1(0)+hand.getInfoCard2(0);

                            int id0 = c.getResources().getIdentifier(s0,null,c.getPackageName());


                            t0.setImageResource(id0);

                            //modifico carta pc

                            hand.modingHand(pc,AI);

                            if(pc.equals(hand.getCardPos(6))) imageView.setImageAlpha(0);

                            String pc1 = "drawable/"+hand.getInfoCard1(AI)+hand.getInfoCard2(AI);

                            int pcInt = c.getResources().getIdentifier(pc1,null,c.getPackageName());

                            tx.setImageAlpha(255);
                            tx.setImageResource(pcInt);

                        }
                    }, 400);
                }
                else { //blocco dell'ultima mano
                    Log.d("ultima","else");
                    t0.startAnimation(animations[0]);

                    int AI = AI(hand,x1,y1,y2,y3);
                    tx = pcMove(AI);
                    tx.startAnimation(animations[0]);
                    carta1.setImageDrawable(t0.getDrawable());
                    String sc = "drawable/"+hand.getInfoCard1(AI)+hand.getInfoCard2(AI);
                    int idAI = c.getResources().getIdentifier(sc,null,c.getPackageName());
                    carta2.setImageResource(idAI);
                    if(compareF(hand,z,x1,AI) == 1) punteggioA = punteggioA+hand.getValue(x1)+hand.getValue(AI);
                    else punteggioB =  punteggioB+hand.getValue(x1)+hand.getValue(AI);

                    if(y1 == AI) y1 = 0;
                    if(y2 == AI) y2 = 0;
                    if(y3 == AI) y3 = 0;

                    imageView.setImageAlpha(0);
                    tx.setImageAlpha(0);
                    t0.setEnabled(false);
                    t0.setImageAlpha(0);
                }



            }
        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card g1,pc ;
                g1  = hand.NextCard();
                pc  = hand.NextCard();

                if(g1 != null) {
                    Log.d("valore",g1.toString());



                    /*
                    hand.modingHand(g1,0);

                    String s0 = "drawable/"+hand.getInfoCard1(0)+hand.getInfoCard2(0);

                    int id0 = c.getResources().getIdentifier(s0,null,c.getPackageName());
                    t0.setImageResource(id0);
                    //Log.d("out1",hand.CardsOnHandToString());
                    */

                    t1.startAnimation(animations[0]);

                    int AI = AI(hand,x1,y1,y2,y3);
                    tx = pcMove(AI);
                    tx.startAnimation(animations[0]);
                    t1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //carta1.setImageResource(id0);
                            carta1.setImageDrawable(t1.getDrawable());  //imposto la carta in mezzo con carta del giocatore
                            tx.setImageAlpha(0);   //imposto carta PC trasparente

                            //carta2.setImageDrawable(tx.getDrawable());

                            String sc = "drawable/"+hand.getInfoCard1(AI)+hand.getInfoCard2(AI);
                            int idAI = c.getResources().getIdentifier(sc,null,c.getPackageName());
                            carta2.setImageResource(idAI);
                            if(compareF(hand,z,x2,AI) == 1) punteggioA = punteggioA+hand.getValue(x2)+hand.getValue(AI);
                            else punteggioB =  punteggioB+hand.getValue(x2)+hand.getValue(AI);
                            Log.d("valore ","punteggio A "+punteggioA+" valore di A "+hand.getValue(x2)+
                                    " punteggio b "+punteggioB+" valore y "+hand.getValue(AI)+" info A "+hand.CardOnHandOneToOne(x1)+" "+hand.CardOnHandOneToOne(AI));
                            //t0.setImageAlpha(0);
                        }
                    }, 400);
                    t1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hand.modingHand(g1,x2);

                            String s1 = "drawable/"+hand.getInfoCard1(x2)+hand.getInfoCard2(x2);

                            int id1 = c.getResources().getIdentifier(s1,null,c.getPackageName());


                            t1.setImageResource(id1);
                            //modifico carta pc

                            hand.modingHand(pc,AI);

                            if(pc.equals(hand.getCardPos(6))) imageView.setImageAlpha(0);

                            String pc1 = "drawable/"+hand.getInfoCard1(AI)+hand.getInfoCard2(AI);

                            int pcInt = c.getResources().getIdentifier(pc1,null,c.getPackageName());

                            tx.setImageAlpha(255);
                            tx.setImageResource(pcInt);

                        }
                    }, 400);
                }
                else {
                    Log.d("valore","else");
                    t1.startAnimation(animations[0]);

                    int AI = AI(hand,x2,y1,y2,y3);
                    tx = pcMove(AI);
                    tx.startAnimation(animations[0]);
                    carta1.setImageDrawable(t1.getDrawable());
                    String sc = "drawable/"+hand.getInfoCard1(AI)+hand.getInfoCard2(AI);
                    int idAI = c.getResources().getIdentifier(sc,null,c.getPackageName());
                    carta2.setImageResource(idAI);
                    if(compareF(hand,z,x2,AI) == 1) punteggioA = punteggioA+hand.getValue(x2)+hand.getValue(AI);
                    else punteggioB =  punteggioB+hand.getValue(x2)+hand.getValue(AI);

                    if(y1 == AI) y1 = 0;
                    if(y2 == AI) y2 = 0;
                    if(y3 == AI) y3 = 0;

                    imageView.setImageAlpha(0);
                    tx.setImageAlpha(0);
                    t1.setEnabled(false);
                    t1.setImageAlpha(0);
                }



            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card g1,pc ;
                g1  = hand.NextCard();
                pc  = hand.NextCard();

                if(g1 != null) {
                    Log.d("valore",g1.toString());



                    /*
                    hand.modingHand(g1,0);

                    String s0 = "drawable/"+hand.getInfoCard1(0)+hand.getInfoCard2(0);

                    int id0 = c.getResources().getIdentifier(s0,null,c.getPackageName());
                    t0.setImageResource(id0);
                    //Log.d("out1",hand.CardsOnHandToString());
                    */

                    t2.startAnimation(animations[0]);

                    int AI = AI(hand,x3,y1,y2,y3);
                    tx = pcMove(AI);
                    tx.startAnimation(animations[0]);
                    t2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //carta1.setImageResource(id0);
                            carta1.setImageDrawable(t2.getDrawable());  //imposto la carta in mezzo con carta del giocatore
                            tx.setImageAlpha(0);   //imposto carta PC trasparente

                            //carta2.setImageDrawable(tx.getDrawable());

                            String sc = "drawable/"+hand.getInfoCard1(AI)+hand.getInfoCard2(AI);
                            int idAI = c.getResources().getIdentifier(sc,null,c.getPackageName());
                            carta2.setImageResource(idAI);
                            if(compareF(hand,z,x3,AI) == 1) punteggioA = punteggioA+hand.getValue(x3)+hand.getValue(AI);
                            else punteggioB =  punteggioB+hand.getValue(x3)+hand.getValue(AI);
                            Log.d("valore ","punteggio A "+punteggioA+" valore di A "+hand.getValue(x2)+
                                    " punteggio b "+punteggioB+" valore y "+hand.getValue(AI)+" info A "+hand.CardOnHandOneToOne(x1)+" "+hand.CardOnHandOneToOne(AI));
                            //t0.setImageAlpha(0);
                        }
                    }, 400);
                    t2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hand.modingHand(g1,x3);

                            String s1 = "drawable/"+hand.getInfoCard1(x3)+hand.getInfoCard2(x3);

                            int id1 = c.getResources().getIdentifier(s1,null,c.getPackageName());


                            t2.setImageResource(id1);
                            //modifico carta pc

                            hand.modingHand(pc,AI);

                            if(pc.equals(hand.getCardPos(6))) imageView.setImageAlpha(0);

                            String pc1 = "drawable/"+hand.getInfoCard1(AI)+hand.getInfoCard2(AI);

                            int pcInt = c.getResources().getIdentifier(pc1,null,c.getPackageName());

                            tx.setImageAlpha(255);
                            tx.setImageResource(pcInt);

                        }
                    }, 400);
                }
                else {
                    Log.d("valore","else");
                    t2.startAnimation(animations[0]);

                    int AI = AI(hand,x3,y1,y2,y3);
                    tx = pcMove(AI);
                    tx.startAnimation(animations[0]);
                    carta1.setImageDrawable(t2.getDrawable());
                    String sc = "drawable/"+hand.getInfoCard1(AI)+hand.getInfoCard2(AI);
                    int idAI = c.getResources().getIdentifier(sc,null,c.getPackageName());
                    carta2.setImageResource(idAI);
                    if(compareF(hand,z,x3,AI) == 1) punteggioA = punteggioA+hand.getValue(x3)+hand.getValue(AI);
                    else punteggioB =  punteggioB+hand.getValue(x3)+hand.getValue(AI);

                    if(y1 == AI) y1 = 0;
                    if(y2 == AI) y2 = 0;
                    if(y3 == AI) y3 = 0;

                    imageView.setImageAlpha(0);
                    tx.setImageAlpha(0);
                    t2.setEnabled(false);
                    t2.setImageAlpha(0);
                }



            }
        });
    }
    private ImageView pcMove(int Numero){
        //int pick_card = rand.nextInt(3);
        switch (Numero){
            case 3: return tx1;
            case 4: return tx2;
            default: return tx3;
        }
    }

    private int compareF(Hand h, int a, int b, int c){

        String cartaMain, cartaP2, cartaP1;

        cartaMain = h.getInfoCard1(a);
        cartaP2 = h.getInfoCard1(b);
        cartaP1 = h.getInfoCard1(c);

        if(cartaMain.compareTo(cartaP2) == 0 ){  //controllo se giocatore ha lo stesso seme della carta principale
            if(cartaP2.compareTo(cartaP1) == 0) { //se anche quella di AI ha lo stesso seme
                if(h.getInfoCard2(b)<h.getInfoCard2(c)) return 2; //confronto i ranghi delle due carte , se AI ha maggiore vince
                else return 1;  //altrimenti vince il giocatore

            }
            else return 1; //in caso di seme principale vince su AI che non ha seme principale
        }
        else { //giocatore non ha seme vincente
            if(cartaMain.compareTo(cartaP1) == 0) return 2; //se AI ha seme vincente ha vinto
            else {
                if(cartaP2.compareTo(cartaP1) == 0) {
                    if(h.getValue(b)>h.getValue(c)) return 1;
                    else return 2;
                }
                else return 1;
            }
        }


    }
    private int AI(Hand h , int p1 , int p2, int p3 , int p4){
        int v = h.getValue(p2) ;
        String seme1 = h.getInfoCard1(p1), MainSeme = h.getInfoCard1(6);

        /*devo fare in modo che se la carta di PC e' uscita allora non la deve usare piu
        quindi la mia strategia assegnare 0 alla carta uscita quindi p2 , p3 o p4 = 0 e al loro posto
        assegno una delle p2 , p3 o p4
         */

        for(int i = 0; i<2 ; i++){
            if(p2 == 0) p2 = p3;
            if(p3 == 0) p3 = p4;
            if(p4 == 0) p4 = p2;
        }



        if(h.getValue(p1) == 0 ) {    //se carta del giocatore 1 e' da 0 punti cerchiamo anche noi di dare una carta nulla
            if((v > h.getValue(p3))&& (h.getInfoCard1(p3).compareTo(MainSeme) != 0)) {return p3;}
            if((v > h.getValue(p4))&& (h.getInfoCard1(p4).compareTo(MainSeme) != 0)){return p4;}
            else return p2;
        }

        /*
        carta del giocatore 1 ha un valore allora dobbiamo capire se tra le nostre 3 carte c'e' una carta
        dello stesso seme ma di rango maggiore oppure una carta cheha lo stesso seme della carta scoperta sul tavolo
         */

        else {
            if (seme1.compareTo(h.getInfoCard1(p2)) == 0) {
                if (h.getValue(p1) < h.getValue(p2)) return p2;
                else if (h.getValue(p3) < h.getValue(p4)) return p3;
                else return p4;
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
            else if ((h.getValue(p2) < h.getValue(p3)) && (h.getValue(p2) < h.getValue(p4)))
                return p2;
            else if ((h.getValue(p3) < h.getValue(p2)) && (h.getValue(p3) < h.getValue(p4)))
                return p3;
            else return p4;


            }
        return p2;
        }


    //fuori da onCreate


}

