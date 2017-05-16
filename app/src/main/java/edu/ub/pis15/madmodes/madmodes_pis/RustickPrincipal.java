package edu.ub.pis15.madmodes.madmodes_pis;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by mcalvico7.alumnes on 04/04/17.
 */

public class RustickPrincipal extends Activity {
    /*
        -El runner esta implementado en un archivo a parte de este hemos decidido no incluirlo ya que no nos ha dado tiempo de juntar el proyecto correctamente.
        -Las animaciones que hemos añadido pueden no ser las de la versión final.
        -Falta acabar el fragment del menu de mundos.
     */
    final Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        ImageButton bPlay = (ImageButton)findViewById(R.id.bPlay);
        ImageButton bOpt = (ImageButton)findViewById(R.id.bOpt);

        bPlay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent aNivells = new Intent(RustickPrincipal.this,RustickMon.class);
                startActivity(aNivells);
            }
        });

        bOpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog
                final Dialog opt = new OpcionsDialog(context);

                Button dialogButton = (Button) opt.findViewById(R.id.bprova);
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
    }
}
