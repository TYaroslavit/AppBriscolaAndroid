package com.tura.test.cardsStuff;

public class Card {
    private int rango,seme;
    private static String[] semi = {"Denaro","Spada","Bastone","Coppa"};
    private static String[] ranghi= {"Asso","2","3","4","5","6","7","fante","cavallo","re"};

    public static String rangoAsString( int __rank ) {
        return ranghi[__rank];
    }

    public static String semiAsString( int __rank ) {
        return ranghi[__rank];
    }

    Card(int seme, int rango){
        this.rango=rango;
        this.seme=seme;

    }



    public int getRango() {
        return rango;
    }

    public int getSeme() {
        return seme;
    }

    public String getValueRango(int v) {
        return ranghi[v];
    }

    public String getValueSeme(int n) {
        return semi[n];
    }

    @Override
    public String toString() {
        return "Card{" +
                "rango=" + rango +
                ", seme=" + seme +
                '}';
    }
}
