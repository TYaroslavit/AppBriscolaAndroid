package com.tura.test.cardsStuff;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards;
    int index_1, index_2;
    Card temp;
    public Deck(){
        cards = new ArrayList<Card>();
        Random generator = new Random();

        for (int a=0; a<4; a++)
        {
            for (int b=0; b<10; b++)
            {
                cards.add( new Card(a,b) );
            }
        }

        int size = cards.size();

        for (int i=0; i<100; i++)
        {
            index_1 = generator.nextInt( size );
            index_2 = generator.nextInt( size );

            temp = (Card) cards.get( index_2 );
            cards.set( index_2 , cards.get( index_1 ) );
            cards.set( index_1, temp );
        }
    }
    public Deck(boolean v){
        cards = new ArrayList<Card>();

        for (int a=0; a<4; a++)
        {
            for (int b=0; b<10; b++)
            {
                cards.add( new Card(a,b) );
            }
        }

        int size = cards.size();

    }

    public Card drawFromDeck()
    {
        return cards.remove( cards.size()-1 );
    }
    public int getTotalCard(){
        return cards.size();
    }

}
