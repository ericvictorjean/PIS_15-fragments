package edu.ub.pis15.madmodes.madmodes_pis.dades;


import java.sql.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import edu.ub.pis15.madmodes.madmodes_pis.boss.BossMain;

/**
 * Created by earaujgi7.alumnes on 02/05/17.
 */

public class Model {
    //private static Nivell bossActual;
    private static ArrayList<Nivell> Nivells= new ArrayList<Nivell>();
    Jugador player;
    int nivell;


    public Model(Jugador player){
        this.player = player;
        this.nivell=1;  //comença a nivell 1 per defecte
        Nivells.add(new Nivell(500,25,1)); //vida, mal, nºnivell
        Nivells.add(new Nivell(1000,50,2));
    }

    public Boss getBoss(int n) {//torna el boss del nivell n
        return Nivells.get(n-1).getBoss();
    }

    public Jugador getPlayer() {
        return player;
    }

    public int getLevel(){ return nivell;}
    public void nextLevel(int n){ this.nivell=n;}

    //public void setBossActual(Nivell nivellActual) {
        //this.bossActual = nivellActual;
    //}


    public void setPlayer(Jugador player) {
        this.player = player;
    }


    public int actualizardatos(long f, int dmgBoss){
        //this.player.menysVida(this.getBoss(nivell).getPuntsmal());

        if (player.getVida()>0){
            this.player.updateJugador(f,dmgBoss);
            return 0;
        }
        return 1;   //significa que el player ha mort
    }
}
