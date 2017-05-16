package edu.ub.pis15.madmodes.madmodes_pis.boss;

import java.util.EmptyStackException;

import edu.ub.pis15.madmodes.madmodes_pis.dades.Model;


/**
 * Created by earaujgi7.alumnes on 02/05/17.
 */

class BossView implements Runnable{
    private Model model;
    private CanalCallBack canalCallBack;

    private Thread gameThread;
    private volatile boolean running = false;


    public BossView(CanalCallBack cBack, Model model) {
        this.model = model;
        this.canalCallBack = cBack;
    }

    public void initGame() {
        running = true;
        gameThread = new Thread(this, "Game Thread");
        gameThread.start();
    }

    public void pauseGame() {
        running = false;
        while (gameThread.isAlive()) {
            try {
                gameThread.join();
                break;
            } catch (InterruptedException e) {
            }
        }
    }
    public void ResumeGame() {
        running = true;
        gameThread.start();
    }





    private int updateAndRender(long delta) {

        if(model.actualizardatos(delta,model.getBoss(1).getPuntsmal())==0) {
            this.canalCallBack.UpdateTextBoss(String.valueOf(model.getPlayer().getVida()), String.valueOf(model.getPlayer().getDiners()));
            return 0;
        }
        return 1;
    }

    public void run() {
        long updateDurationMillis = 0;
        long sleepDurationMillis = 0;
        while (running) {
            long beforeUpdateRender = System.nanoTime();
            long deltaMillis = sleepDurationMillis + updateDurationMillis;
            int x=updateAndRender(deltaMillis);

            if(x==1){
                ///////YOU LOSE!
            }

            updateDurationMillis = (System.nanoTime() - beforeUpdateRender) / 1000000L;//milisegundos
            sleepDurationMillis = Math.max(2, 40 - updateDurationMillis);
            try {
                Thread.sleep(sleepDurationMillis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
