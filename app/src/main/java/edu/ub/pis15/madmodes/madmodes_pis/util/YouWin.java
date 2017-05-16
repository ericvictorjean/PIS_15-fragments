package edu.ub.pis15.madmodes.madmodes_pis.util;

import android.app.Activity;

import android.content.Context;

import android.os.Bundle;
import android.support.annotation.Nullable;

import edu.ub.pis15.madmodes.madmodes_pis.R;


/**
 * Created by Spring on 13/05/2017.
 */

public class YouWin extends Activity {

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youwin);

    }
}

