package com.prograv.mtax.proyectofinal.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.prograv.mtax.proyectofinal.R;
import com.prograv.mtax.proyectofinal.data.sitio;

import java.util.List;

/**
 * Created by mtax on 04/06/2018.
 */

public class SitiosAdapterWeb extends ArrayAdapter<sitio> {
    private Context context;
    private int layout;
    private List<sitio> lista;

    public SitiosAdapterWeb(@NonNull Context context, @NonNull List<sitio> objects){
        super(context, R.layout.row_mis_sitios, objects);
        this.context = context;


        this.layout = R.layout.row_mis_sitios;
        this.lista = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View v = convertView;
        if(v == null){
            LayoutInflater lf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = lf.inflate(layout, null);
        }
        if(lista.get(position) != null){
            TextView txtNombre, txtDepartamento, txtMunicipio, txtTipo, txtParqueo, txtWiFi, txtHoteles, txtDescripcion;
            ImageView foto1;
            ListView amenidades;
            txtNombre = (TextView) v.findViewById(R.id.conNombreSitio);
            txtDepartamento = (TextView) v.findViewById(R.id.conDepartamento);
            txtMunicipio = (TextView) v.findViewById(R.id.conMunicipio);
            txtTipo = (TextView) v.findViewById(R.id.conTipoSitio);
            foto1 = (ImageView) v.findViewById(R.id.conImgSitio);
            txtParqueo = (TextView) v.findViewById(R.id.conParqueo);
            txtWiFi = (TextView) v.findViewById(R.id.conWiFi);
            txtHoteles = (TextView) v.findViewById(R.id.conHoteles);
            txtDescripcion = (TextView) v.findViewById(R.id.conDescripcion);

            txtNombre.setText(lista.get(position).getNombre());
            int numDepa = lista.get(position).getDepartamento();
            String depa = "";
            switch (numDepa) {
                case 1: depa = "Guatemala";break;
                case 2:depa = "El Progreso";break;
                case 3:depa = "Sacatepéquez";break;
                case 4:depa = "Chimaltenango";break;
                case 5:depa = "Escuintla";break;
                case 6:depa = "Santa Rosa";break;
                case 7:depa = "Sololá";break;
                case 8:depa = "Totonicapán";break;
                case 9:depa = "Quetzaltenango";break;
                case 10:depa = "Suchitepéquez";break;
                case 11:depa = "Retalhuleu";break;
                case 12:depa = "San Marcos";break;
                case 13:depa = "Huehuetenango";break;
                case 14:depa = "Quiché";break;
                case 15:depa = "Baja Verapaz";break;
                case 16:depa = "Alta Verapaz";break;
                case 17:depa = "Petén";break;
                case 18:depa = "Izabal";break;
                case 19:depa = "Zacapa";break;
                case 20:depa = "Chiquimula";break;
                case 21:depa = "Jalapa";break;
                case 22:depa = "Jutiapa";break;
            }
            txtDepartamento.setText("Departamento: " + depa);

            int numTipo = lista.get(position).getTipo();
            String tipo = "";
            switch (numTipo){
                case 1: tipo = "Lago"; break;
                case 2: tipo = "Rio"; break;
                case 3: tipo = "Sitio Arqueologico"; break;
                case 4: tipo = "Mirador"; break;
                case 5: tipo = "Museo"; break;
                case 6: tipo = "Valneario"; break;
                case 7: tipo = "Comercio"; break;
                case 8: tipo = "Volcan"; break;
                case 9: tipo = "Montaña"; break;
                case 10: tipo = "Otro"; break;
            }

            String municipio = "";
            String codigoMuni = "";
            String[] codigosMunis = context.getResources().getStringArray(R.array.codMunis);
            int cont = 0;
            while(cont < codigosMunis.length){
                codigoMuni = codigosMunis[cont].substring(0,codigosMunis[cont].indexOf("_"));

                if(Integer.parseInt(codigoMuni) == lista.get(position).getMunicipio()){
                    municipio = codigosMunis[cont].substring(codigosMunis[cont].indexOf("_") + 1);
                    break;
                }
                cont = cont + 1;
            }
            txtMunicipio.setText("Municipio: " + municipio);

            if(lista.get(position).getAmenidad1() == 1){
                txtParqueo.setVisibility(View.VISIBLE);
            }
            if(lista.get(position).getAmenidad2() == 1){
                txtWiFi.setVisibility(View.VISIBLE);
            }
            if(lista.get(position).getAmenidad3() == 1){
                txtHoteles.setVisibility(View.VISIBLE
                );
            }

            txtTipo.setText(tipo);
            txtDescripcion.setText(lista.get(position).getDespcripcion());
            Bitmap myBitmap = getBitmap(lista.get(position).getFoto1());
            foto1.setImageBitmap(myBitmap);
        }

        return v;
    }

    public Bitmap getBitmap(String data) {
        Bitmap decodedByte = null;
        if (!data.equals("")) {
            byte[] decodedString = Base64.decode(data, Base64.DEFAULT);
            decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        }
        return decodedByte;
    }
}
