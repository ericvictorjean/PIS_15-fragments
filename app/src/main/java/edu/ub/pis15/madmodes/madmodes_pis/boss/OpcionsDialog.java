package edu.ub.pis15.madmodes.madmodes_pis.boss;

/**
 * Created by Spring on 12/05/2017.
 */


import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.IntentSender;
import android.support.annotation.NonNull;
import edu.ub.pis15.madmodes.madmodes_pis.R;


class OpcionsDialog extends Dialog {
    public OpcionsDialog(@NonNull Context context) {
        super(context);
        this.setContentView(R.layout.dialog_opcions);
        this.setTitle("");

    }
}
