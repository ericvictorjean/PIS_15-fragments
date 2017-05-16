package edu.ub.pis15.madmodes.madmodes_pis.util;

import java.util.ArrayList;

/**
 * Created by mcalvico7.alumnes on 02/05/17.
 */

public class Millora {
    private int cost,tipus;
    private int dany, vida;


    public Millora(int c, int d, int v, int t) {
        cost = c;
        dany = d;
        vida = v;
        tipus = t;
    }

    public int getCost() {
        return cost;
    }

    public int getTipus() {
        return tipus;
    }

    public int getValor() {
        if (dany != 0) return dany;
        return vida;
    }

    private static int lastContactId = 0;

    public static ArrayList<Millora> createMillorasLevel(int nLevel) {  //les millores depenen del nivell
        ArrayList<Millora> milloras = new ArrayList<Millora>();
        switch (nLevel) {
        case 1:
            for (int i = 1; i <= 8; i++) {
                milloras.add(new Millora(i*100,i*25,i+3,1));
                milloras.add(new Millora(i*200,i*(i+1),i+3,2));
            }
            break;
        }
        return milloras;
    }

    public static ArrayList<Millora> createMillorasList(int numContacts) {
        ArrayList<Millora> milloras = new ArrayList<Millora>();

        for (int i = 1; i <= numContacts; i++) {
            milloras.add(new Millora(i,i+2,i+3,1));
        }

        return milloras;
    }
}
