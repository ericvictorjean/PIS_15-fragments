package edu.ub.pis15.madmodes.madmodes_pis.runner;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.ub.pis15.madmodes.madmodes_pis.R;

/**
 * Created by Spring on 13/05/2017.
 */



public class RunnerMain extends Activity {

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.runner_vista);
    }
}