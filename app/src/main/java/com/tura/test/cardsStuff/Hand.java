package com.tura.test.cardsStuff;

public class Hand {
    private Card[] cards;
    private int[] value;

    private int pointerArrayCard = 7;

    public Hand(Deck d){
        value = new int[40];
        cards = new Card[40];
        for (int x=0; x<40; x++)
        {
            cards[x] = d.drawFromDeck(); //fill up cards[] array.
            switch (cards[x].getRango()){
                case 0: value[x]=11;break;
                case 2: value[x]=10;break;
                case 9: value[x]=4;break;
                case 8: value[x]=3;break;
                case 7: value[x]=2;break;
                default: value[x]=0;
            }
        }

    }
    public Card NextCard(){
        pointerArrayCard++;
        if(pointerArrayCard == 39) return cards[6];
        if(pointerArrayCard>39) return null;
        else return cards[pointerArrayCard-1];
    }

    public Card NextCardV2(int pos){
        //pointerArrayCard++;
        if(pos == 39) return cards[6];
        if(pos>39) return null;
        else return cards[pos];
    }

    public Card getCardPos(int pos){return cards[pos];}

    public void modingHand(Card income,int pos){
            cards[pos] = income;
    }
    public String CardsOnHandToString(){
        //cards[0].getValueSeme(cards[0].getSeme())+" "+ cards[1].getValueSeme(cards[1].getSeme())+" "+cards[2].getValueSeme(cards[2].getSeme())+" "+
        //Log.d("solve1", " " +cards[0].getRango() );
        //Log.d("solve2",""+cards[0].getValueRango(9));
        //Log.d("Seme1","" + cards[0].getSeme()+cards[1].getSeme()+cards[2].getSeme());
        return cards[0].getValueSeme(cards[0].getSeme())+" "+cards[0].getValueRango(cards[0].getRango())+
                " "+ cards[1].getValueSeme(cards[1].getSeme())+" "+cards[1].getValueRango(cards[1].getRango())+
                " "+cards[2].getValueSeme(cards[2].getSeme())+" "+cards[2].getValueRango(cards[2].getRango());

    }
    public String CardOnHandOneToOne(int pos){
        return cards[pos].getValueSeme(cards[pos].getSeme())+" "+cards[pos].getValueRango(cards[pos].getRango());

    }
    public String getInfoCard1(int pos) {
        int x = cards[pos].getSeme();
        switch (x) {
            case 0:
                return "d";
            case 1:
                return "s";
            case 2:
                return "b";
            default:
                return "c";
        }
    }

    public String[] getInfoCardRangoSeme(int pos){
        String[] array = new String[2];
        String res1,res2;
        res1 = getInfoCard1(pos);
        res2 = String.valueOf(getInfoCard2(pos));
        array[0]= res1;
        array[1]=res2;
        return array;
    }

    public int getInfoCard2(int pos) {
        return cards[pos].getRango();
    }

    public int getValue(int pos){return value[pos];}


}
