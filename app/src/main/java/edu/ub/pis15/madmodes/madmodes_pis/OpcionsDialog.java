package edu.ub.pis15.madmodes.madmodes_pis;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by mcalvico7.alumnes on 11/05/17.
 */

class OpcionsDialog extends Dialog {
    public OpcionsDialog(@NonNull Context context) {
        super(context);
        this.setContentView(R.layout.dialog_opcions);
        this.setTitle("Title...");

    }
}
