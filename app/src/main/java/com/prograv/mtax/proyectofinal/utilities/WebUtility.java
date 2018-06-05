package com.prograv.mtax.proyectofinal.utilities;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;

import com.prograv.mtax.proyectofinal.data.sitio;
import com.prograv.mtax.proyectofinal.WebApplication;

import com.prograv.mtax.proyectofinal.fragments.buscar;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mtax on 04/06/2018.
 */

public class WebUtility {

    public static String getRepositorios(String id, final Context context) {
        String url = "http://186.151.140.61/galileo/sitio-turistico/" + id;

        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                List<sitio> lista;
                lista = getListFromJson((String) response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error en la comunicacion", Toast.LENGTH_LONG).show();
            }
        });

        ((WebApplication) context.getApplicationContext()).getQueue().add(solicitud);
        return url;
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
