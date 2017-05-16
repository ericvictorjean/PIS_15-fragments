package edu.ub.pis15.madmodes.madmodes_pis.dades;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.ub.pis15.madmodes.madmodes_pis.R;

/**
 * Created by earaujgi7.alumnes on 02/05/17.
 */

public class Jugador extends Activity {
    int vida;
    int puntsmal;
    int diners;
    float tempsUltimHit;

    public Jugador(int v, int p,int d){
        this.vida = v;
        this.puntsmal = p;
        this.diners = d;
        tempsUltimHit = 0;
    }

    public int getDiners() {
        return diners;
    }

    public int getPuntsmal() {
        return puntsmal;
    }

    public int getVida() {
        return vida;
    }

    public void menysDiners(int diners) {
        this.diners -= diners;
    }

    public void mesDiners(int diners) {
        this.diners += diners;
    }

    public void mesPuntsmal(int puntsmal) {
        this.puntsmal += puntsmal;
    }

    public void mesVida(int vida) {
        this.vida += vida;
    }

    public void menysVida(int vida) {
        this.vida -= vida;
    }

    public void updateJugador(float v, int dmg){
        /*tempsUltimHit += v;
        while (tempsUltimHit>5000) {
            if (vida>dmg){
                menysVida(dmg);
                tempsUltimHit = 0;
            }else{
                vida=0;
            }
        }*/
    }


}
