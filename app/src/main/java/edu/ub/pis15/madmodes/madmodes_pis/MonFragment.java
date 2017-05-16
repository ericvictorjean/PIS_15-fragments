package edu.ub.pis15.madmodes.madmodes_pis;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import edu.ub.pis15.madmodes.madmodes_pis.boss.BossMain;

public class MonFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View view = inflater.inflate(R.layout.itemmenu, container, false);
        ImageButton bBoss = (ImageButton) view.findViewById(R.id.bBoss);
        bBoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aNivells = new Intent(getActivity(), BossMain.class);
                startActivity(aNivells);
            }
        });
        Button mon2 = (Button) view.findViewById(R.id.mon2);
        mon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MonFragment2();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        ImageButton bRun = (ImageButton)view.findViewById(R.id.r1);
        bRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent aNivells = new Intent(RustickMon.this,GameRun.class);
                //startActivity(aNivells);
            }
        });

        ImageButton bStick = (ImageButton)view.findViewById(R.id.r2);
        bStick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent aNivells = new Intent(RustickMon.this,GameRun.class);
                //startActivity(aNivells);
            }
        });

        ImageButton bOpt = (ImageButton)view.findViewById(R.id.bOptMon);
        bOpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog opt = new OpcionsDialog(view.getContext());
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
        return view;
    }
}
