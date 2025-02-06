package com.tura.test;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    /*
    ImageView imageView;
    ImageView t0,t1,t2,cartaCentrale;
    LinearLayout l2;

    Animation[] animations = new Animation[16];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        imageView = findViewById(R.id.cartaPrincipale);

        t0 = findViewById(R.id.T1);
        t1 = findViewById(R.id.T2);
        t2 = findViewById(R.id.T3);


        Deck D = new Deck();
        Context c = getApplicationContext();
        Hand hand = new Hand(D);

        //per ottenere il nome della risorsa
        String s0 = "drawable/"+hand.getInfoCard1(0)+hand.getInfoCard2(0);
        String s1 = "drawable/"+hand.getInfoCard1(1)+hand.getInfoCard2(1);
        String s2 = "drawable/"+hand.getInfoCard1(2)+hand.getInfoCard2(2);

        //id della risorsa
        int id0 = c.getResources().getIdentifier(s0,null,c.getPackageName());
        int id1 = c.getResources().getIdentifier(s1,null,c.getPackageName());
        int id2 = c.getResources().getIdentifier(s2,null,c.getPackageName());

        t0.setImageResource(id0);
        t1.setImageResource(id1);
        t2.setImageResource(id2);


        //imposto dimensioni dei vari elementi
        t0.getLayoutParams().width = width/3-width/40;
        t0.getLayoutParams().height = height/3;
        t1.getLayoutParams().height = height/3;
        t1.getLayoutParams().width = width/3 - width/40;
        t2.getLayoutParams().height = height/3;
        t2.getLayoutParams().width = width/3-width/40;

        imageView.getLayoutParams().width = width/3;
        imageView.getLayoutParams().height = height/6;

        //animazioni
        animations[0]= AnimationUtils.loadAnimation(c,R.anim.scale);





        t0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card g1 = hand.NextCard(D);
                if(g1 == null) imageView.setEnabled(false);
                else{
                    hand.modingHand(g1,0);

                    String s0 = "drawable/"+hand.getInfoCard1(0)+hand.getInfoCard2(0);

                    int id0 = c.getResources().getIdentifier(s0,null,c.getPackageName());
                    t0.setImageResource(id0);
                    Log.d("out1",hand.CardsOnHandToString());
                    //t1.setMaxWidth(1000);
                    //t1.setMaxHeight(1000);
                    t0.startAnimation(animations[0]);
                }
            }
        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card g1 = hand.NextCard(D);
                if(g1 == null) imageView.setEnabled(false);
                else{
                    hand.modingHand(g1,1);

                    String s1 = "drawable/"+hand.getInfoCard1(1)+hand.getInfoCard2(1);

                    int id1 = c.getResources().getIdentifier(s1,null,c.getPackageName());
                    t1.setImageResource(id1);
                    Log.d("out1",hand.CardsOnHandToString());

                }
            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card g1 = hand.NextCard(D);
                if(g1 == null) imageView.setEnabled(false);
                else{
                    hand.modingHand(g1,2);

                    String s2 = "drawable/"+hand.getInfoCard1(2)+hand.getInfoCard2(2);

                    int id2 = c.getResources().getIdentifier(s2,null,c.getPackageName());
                    t2.setImageResource(id2);
                    Log.d("out1",hand.CardsOnHandToString());

                }
            }
        });
    }


    //fuori da onCreate

*/
}
