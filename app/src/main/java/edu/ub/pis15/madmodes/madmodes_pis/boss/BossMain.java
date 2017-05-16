package edu.ub.pis15.madmodes.madmodes_pis.boss;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


import edu.ub.pis15.madmodes.madmodes_pis.R;
import edu.ub.pis15.madmodes.madmodes_pis.RustickMon;
import edu.ub.pis15.madmodes.madmodes_pis.dades.*;
import edu.ub.pis15.madmodes.madmodes_pis.util.*;


/**
 * Created by earaujgi7.alumnes on 02/05/17.
 */

public class BossMain extends Activity implements CanalCallBack {
    public static final int GAME_WIDTH = 10;
    public static final int GAME_HEIGHT = 10;
    public static BossView bossV;
    public static Jugador player;
    public static Model m;
    public int contador,inici=0;

    final Context context = this;

    ArrayList<Millora> millores;
    ScheduledExecutorService actuVida=Executors.newScheduledThreadPool(1);
    ScheduledFuture actuVidaHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boss_vista);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        final SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        ImageButton runnerb = (ImageButton)findViewById(R.id.Runner);


        player = new Jugador(500,1,10000);
        //int vidajugador = sharedPref.getInt("vida", vida);
        //int dañojugador = sharedPref.getInt("puntsmal", daño);
        //player.nouNivell(vidajugador,dañojugador,1000);


        m = new Model(player);

        bossV = new BossView(this,m);
        bossV.initGame();
        TextView vidaBoss = (TextView)findViewById(R.id.vidaBoss);
        vidaBoss.setText(String.valueOf(m.getBoss(1).getVida()));

        TextView diners = (TextView)findViewById(R.id.diners);
        diners.setText(String.valueOf(m.getPlayer().getDiners()));

        final LinearLayout surface = (LinearLayout)findViewById(R.id.surfaceBoss);

        //View v = findViewById(R.id.surfaceBoss);
        //bossAppear(v);


        surface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surface.setBackgroundResource(R.drawable.selector_bossdamage);
                //surface.setBackgroundResource(R.drawable.selector_bossappear);

                v.setActivated(true);
                v.setActivated(false);


                m.getBoss(1).treureVida(m.getPlayer().getPuntsmal());

                TextView vidaBoss = (TextView)findViewById(R.id.vidaBoss);
                vidaBoss.setText(String.valueOf(m.getBoss(1).getVida()));

                TextView danyPlayer = (TextView)findViewById(R.id.danyPlayer);
                danyPlayer.setText(String.valueOf(m.getPlayer().getPuntsmal()));

                TextView diners = (TextView)findViewById(R.id.diners);
                diners.setText(String.valueOf(m.getPlayer().getDiners()));

                if(m.getBoss(1).getVida() <= 0){
                    surface.setBackgroundResource(R.drawable.selector_bossdie);
                    v.setActivated(true);
                    v.setActivated(false);

                    surface.setOnClickListener(null);

                    v.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent win = new Intent(BossMain.this,YouWin.class);
                            //startActivity(win);
                            finish();
                        }
                    }, 4000);


                    //setContentView(R.layout.youwin);
                    // editor.putInt("vida", m.getPlayer().getVida());
                    //editor.commit();

                }
            }
        });

        runnerb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                editor.putInt("vida", m.getPlayer().getVida());
                editor.putInt("puntsmal",m.getPlayer().getPuntsmal());
                editor.putInt("diners",m.getPlayer().getDiners());
                editor.commit();
                bossV.pauseGame();
                Intent runnerB = new Intent(BossMain.this,RustickMon.class);
                startActivity(runnerB);
            }
        });


        ImageButton settings = (ImageButton)findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog opt = new OpcionsDialog(context);
                Button dialogButton = (Button)opt.findViewById(R.id.bprova);
                opt.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        opt.dismiss();
                    }
                });
                opt.show();
            }
        });

        ImageButton home = (ImageButton)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent aHome = new Intent(BossMain.this,RustickMon.class);
                startActivity(aHome);
                finish();
            }
        });

        ImageButton runner = (ImageButton)findViewById(R.id.Runner);
        runner.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Intent aRunner = new Intent(BossMain.this,RunnerMain.class);

                //startActivity(aRunner);
                finish();
            }
        });


        //cada 1 segundo actualiza los corazones con la vida del Player //
        contador=0;
        actuVidaHandler = actuVida.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                BossMain.this.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        if (player.getVida() > 0 && m.getBoss(m.getLevel()).getVida() > 0) {
                            if (inici==0 ){
                                inici=1;
                                View v = findViewById(R.id.surfaceBoss);
                                bossAppear(v);
                            }else {
                                actualitzarVida();
                                contador++;
                                if (contador == 3) {
                                    player.menysVida(m.getBoss(m.getLevel()).getPuntsmal());
                                    Toast.makeText(BossMain.this, "vida total " + player.getVida(), Toast.LENGTH_SHORT).show();
                                    contador = 0;
                                    surface.setBackgroundResource(R.drawable.selector_bossatack);
                                    View v = findViewById(R.id.surfaceBoss);
                                    v.setActivated(true);
                                    v.setActivated(false);
                                    //actualitzarVida();
                                }
                            }
                        } else {
                            surface.setOnClickListener(null);
                            actualitzarVida();

                            if (player.getVida() <= 0) {
                                //you Lose
                            } else {
                                //surface.setBackgroundResource(R.drawable.noboss);
                                //you win
                            }

                        }
                    }
                });
            }},0,1,TimeUnit.SECONDS);






        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvBossShop);
        // Initialize millores
        millores = Millora.createMillorasLevel(1);
        // Create adapter passing in the sample user data
        final MilloresAdapter adapter = new MilloresAdapter(this, millores);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));


        LinearLayout vidaJugador =(LinearLayout) findViewById(R.id.vidaJugador);






        adapter.setOnItemClickListener(new MilloresAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Millora currentMillora = millores.get(position);
                Jugador player = m.getPlayer();

                int preu = currentMillora.getCost();
                if (player.getDiners() < preu) {
                    Toast.makeText(BossMain.this, "Not enought points", Toast.LENGTH_SHORT).show();
                }
                else {
                    player.menysDiners(preu);
                    TextView diners = (TextView)findViewById(R.id.diners);
                    diners.setText(String.valueOf(m.getPlayer().getDiners()));

                    switch (currentMillora.getTipus()){
                        case 1:
                            player.mesVida(currentMillora.getValor());
                            actualitzarVida();
                            Toast.makeText(BossMain.this, "vida total "+player.getVida(), Toast.LENGTH_SHORT).show(); //mostra la vida
                            break;

                        case 2:
                            player.mesPuntsmal(currentMillora.getValor());
                            TextView danyPlayer = (TextView)findViewById(R.id.danyPlayer);
                            danyPlayer.setText(String.valueOf(player.getPuntsmal()));
                            break;
                    }

                    millores.remove(position);
                    adapter.notifyItemRemoved(position);
                }
            }
        });
    }

    @Override
    public void UpdateTextBoss(String vidaJ, String puntsJ) {
        /*TextView vidaJugador = (TextView)findViewById(R.id.vidaJugador);
        vidaJugador.setText(vidaJ);

        TextView puntsJugador = (TextView)findViewById(R.id.diners);
        puntsJugador.setText(puntsJ);*/
    }



    @Override
    public void onPause(){
        super.onPause();
        actuVidaHandler.cancel(true);
        bossV.pauseGame();
        //actuVidaHandler=null;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        actuVidaHandler.cancel(true);
        //actuVidaHandler=null;
    }
    @Override
    public void onStop(){
        super.onStop();
        actuVidaHandler.cancel(true);
        bossV.pauseGame();
        //actuVidaHandler=null;
    }

    @Override
    public void onResume(){
        super.onResume();
        //bossV.resumeGame();
            inici=0;

        //View v = findViewById(R.id.surfaceBoss);
        //bossAppear(v);
    }

    public void bossAppear(View v){
        LinearLayout surface = (LinearLayout)findViewById(R.id.surfaceBoss);
        surface.setBackgroundResource(R.drawable.selector_bossappear);
        v.setActivated(true);
        v.setActivated(false);
    }


    public void actualitzarVida(){
        ImageView cor1= (ImageView)findViewById(R.id.heart1);
        ImageView cor2= (ImageView)findViewById(R.id.heart2);
        ImageView cor3= (ImageView)findViewById(R.id.heart3);
        ImageView cor4= (ImageView)findViewById(R.id.heart4);
        ImageView cor5= (ImageView)findViewById(R.id.heart5);

        int v = player.getVida();
        cor1.setBackgroundResource(R.drawable.hempty);
        cor2.setBackgroundResource(R.drawable.hempty);
        cor3.setBackgroundResource(R.drawable.hempty);
        cor4.setBackgroundResource(R.drawable.hempty);
        cor5.setBackgroundResource(R.drawable.hempty);
        if(v>0)cor1.setBackgroundResource(R.drawable.hhalf);
        if(v>50)cor1.setBackgroundResource(R.drawable.hfull);
        if(v>100)cor2.setBackgroundResource(R.drawable.hhalf);
        if(v>150)cor2.setBackgroundResource(R.drawable.hfull);
        if(v>200)cor3.setBackgroundResource(R.drawable.hhalf);
        if(v>250)cor3.setBackgroundResource(R.drawable.hfull);
        if(v>300)cor4.setBackgroundResource(R.drawable.hhalf);
        if(v>350)cor4.setBackgroundResource(R.drawable.hfull);
        if(v>400)cor5.setBackgroundResource(R.drawable.hhalf);
        if(v>450)cor5.setBackgroundResource(R.drawable.hfull);

    }







}
