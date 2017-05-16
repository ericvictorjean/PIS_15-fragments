package edu.ub.pis15.madmodes.madmodes_pis.dades;


/**
 * Created by earaujgi7.alumnes on 02/05/17.
 */

public class Nivell {
    private Boss boss;
    public int nivell;

    public Nivell(int vida, int puntsmal,int nivell){
        this.boss = new Boss(vida,puntsmal);
        this.nivell = nivell;
    }

    public Boss getBoss() {
        return boss;
    }

    public int getNivell() {
        return nivell;
    }

    public void setNivell(int nivell) {
        this.nivell = nivell;
    }
}
