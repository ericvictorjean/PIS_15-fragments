package edu.ub.pis15.madmodes.madmodes_pis.dades;

/**
 * Created by mcalvico7.alumnes on 11/05/17.
 */

public class Boss {
    int vida;
    int puntsmal;

    public Boss(int vida, int puntsmal){
        this.vida = vida;
        this.puntsmal = puntsmal;
    }

    public int getPuntsmal() {
        return puntsmal;
    }

    public int getVida() {
        return vida;
    }

    public void setPuntsmal(int puntsmal) {
        this.puntsmal = puntsmal;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void treureVida(int mal) {
        this.vida -= mal;
    }
}
