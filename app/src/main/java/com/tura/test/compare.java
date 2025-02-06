package com.tura.test;

import com.tura.test.cardsStuff.Hand;

public class compare {


    public int compareF(Hand h, int a, int b, int c){

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
}
