package com.prograv.mtax.proyectofinal.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prograv.mtax.proyectofinal.R;

/**
 * Created by mtax on 03/06/2018.
 */

public class acerca_De extends Fragment implements View.OnClickListener {

    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.about, null);
        return v;
    }

    @Override
    public void onClick(View v) {

    }
}
