package com.prograv.mtax.proyectofinal.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.prograv.mtax.proyectofinal.Adapter.SitiosAdapter;
import com.prograv.mtax.proyectofinal.R;
import com.prograv.mtax.proyectofinal.utilities.DBUtility;
import com.prograv.mtax.proyectofinal.data.sitio;

import java.util.List;


/**
 * Created by mtax on 03/06/2018.
 */

public class mis_sitios extends Fragment implements View.OnClickListener {

    View v;
    private ListView lista;
    private DBUtility dbUtility;
    private List<sitio> listaSitios;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.mis_sitios, null);

        lista = (ListView) v.findViewById(R.id.conLstRepositorio);
        dbUtility = new DBUtility(getActivity());
        listaSitios = dbUtility.getDatos();
        if(listaSitios != null) {
            lista.setAdapter(new SitiosAdapter(getActivity(), listaSitios));
        }
        else {
            Toast.makeText(getActivity(), "No hay datos para mostrar.", Toast.LENGTH_LONG).show();
        }
        return v;
    }
    @Override
    public void onClick(View v) {

    }
}
