package com.prograv.mtax.proyectofinal.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.prograv.mtax.proyectofinal.Adapter.SitiosAdapterWeb;
import com.prograv.mtax.proyectofinal.R;
import com.prograv.mtax.proyectofinal.data.sitio;


import com.prograv.mtax.proyectofinal.WebApplication;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mtax on 03/06/2018.
 */

public class buscar  extends Fragment implements View.OnClickListener {

    View v;
    private ListView lista;
    private TextView mensaje;
    private EditText txtId;
    private Button consultaBtn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.buscar, null);

        txtId = v.findViewById(R.id.buscaID);

        consultaBtn = v.findViewById(R.id.buscaBtn);
        consultaBtn.setOnClickListener(this);
        return v;
    }

    public void cargaDatos(List<sitio> list) {
        lista = v.findViewById(R.id.buscaLstResultado);
        mensaje = v.findViewById(R.id.buscaMensaje);

        if (list != null) {
            lista.setAdapter(new SitiosAdapterWeb(getActivity(), list));
            lista.setVisibility(View.VISIBLE);
            mensaje.setVisibility(View.GONE);
        } else {
            lista.setVisibility(View.GONE);
            mensaje.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.buscaBtn:
                if(txtId.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), "Ingrese dato de Busqueda", Toast.LENGTH_LONG).show();
                    return;
                }
                getRepositorios(txtId.getText().toString(), getActivity());
                txtId.setText("");
                break;
        }
    }

    public void getRepositorios(String id, final Context context) {
        String url = "http://186.151.140.61/galileo/sitio-turistico/" + id;

        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                List<sitio> lista;
                lista = getListFromJson((String) response);
                cargaDatos(lista);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error en la comunicacion", Toast.LENGTH_LONG).show();
            }
        });

        ((WebApplication) getActivity().getApplicationContext()).getQueue().add(solicitud);
    }
    public static List<sitio> getListFromJson(String json) {
        List<sitio> list = null;
        sitio item;
        try {
            JSONArray array = new JSONArray(json);
            if (array.length() > 0) {
                list = new ArrayList<sitio>();
                for (int i = 0; i < array.length(); i ++) {
                    item = new sitio();
                    item.setID("10005018");
                    item.setNombre(array.getJSONObject(i).getString("titulo"));
                    item.setDepartamento(Integer.parseInt(array.getJSONObject(i).getString("departamento")));
                    item.setMunicipio(Integer.parseInt(array.getJSONObject(i).getString("municipio")));
                    item.setLongitud(array.getJSONObject(i).getString("longitud"));
                    item.setLatitud(array.getJSONObject(i).getString("latitud"));
                    item.setFoto1(array.getJSONObject(i).getString("foto1"));
                    item.setFoto2(array.getJSONObject(i).getString("foto2"));
                    item.setFoto3(array.getJSONObject(i).getString("foto3"));
                    item.setDespcripcion(array.getJSONObject(i).getString("descripcion"));
                    item.setAmenidad1(Integer.parseInt(array.getJSONObject(i).getString("parqueo")));
                    item.setAmenidad2(Integer.parseInt(array.getJSONObject(i).getString("internet")));
                    item.setAmenidad3(Integer.parseInt(array.getJSONObject(i).getString("hoteles")));
                    item.setTipo(Integer.parseInt(array.getJSONObject(i).getString("tipo_sitio")));
                    list.add(item);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
