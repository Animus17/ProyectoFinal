package com.prograv.mtax.proyectofinal.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.prograv.mtax.proyectofinal.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import com.prograv.mtax.proyectofinal.MainActivity;
import com.prograv.mtax.proyectofinal.utilities.DBUtility;
import com.prograv.mtax.proyectofinal.data.sitio;

import org.json.JSONException;
import org.json.JSONObject;

import com.prograv.mtax.proyectofinal.WebApplication;
/**
 * Created by mtax on 01/06/2018.
 */

public class nuevo_sitio extends Fragment implements View.OnClickListener{

    View v;
    public int numDep, numMuni, tipoSitio, a1, a2, a3, numFotos, numMuniE;
    TextView txtNombreSitio, txtDescSitio;
    CheckBox amenidad1, amenidad2, amenidad3;
    ImageButton btnCamara, btnMapa;
    Button btnGuardar;
    private ImageView imgFoto1, imgFoto2, imgFoto3 ;
    Spinner spDepartamentos;
    Spinner spTipoSitio;

    private Bitmap image;
    String pImage1, pImage2, pImage3;
    String longitud, latitud, IDSitio;
    private Uri imageUri;

    private DBUtility dbUtility;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.nuevo_sitio, null);

        numDep = 0;
        numMuni = 0;
        numMuniE = 0;
        tipoSitio = 0;
        a1 = 0;
        a2 = 0;
        a3 = 0;
        numFotos = 0;
        pImage1 = "";
        pImage2 = "";
        pImage3 = "";
        longitud = "0";
        latitud = "0";
        IDSitio = "";

        dbUtility = new DBUtility(getActivity());

        //BOTONES
        btnCamara = v.findViewById(R.id.btn_foto);
        btnMapa = v.findViewById(R.id.btn_mapa);
        btnGuardar = v.findViewById(R.id.btn_guardar);

        btnCamara.setOnClickListener(this);
        btnMapa.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);

        //TEXTOS
        txtNombreSitio = v.findViewById(R.id.txt_nombreSitio);
        txtDescSitio = v.findViewById(R.id.txt_descSitio);

        //CHECKBOX
        amenidad1 = v.findViewById(R.id.checkbox_Parqueo);
        amenidad2 = v.findViewById(R.id.checkbox_Wifi);
        amenidad3 = v.findViewById(R.id.checkbox_Hoteles);

        amenidad1.setOnClickListener(this);
        amenidad2.setOnClickListener(this);
        amenidad3.setOnClickListener(this);

        amenidad1.setChecked(false);
        amenidad2.setChecked(false);
        amenidad3.setChecked(false);

        //IMAGENES
        imgFoto1 = v.findViewById(R.id.imgFoto1);
        imgFoto2 = v.findViewById(R.id.imgFoto2);
        imgFoto3 = v.findViewById(R.id.imgFoto3);

        // SPINNER DEPARTAMENTOS

        spDepartamentos = v.findViewById(R.id.spinner_departamentos);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.DEPARTAMENTOS, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDepartamentos.setAdapter(adapter);

        // SPINNER MUNICIPIOS
        final Spinner spMunicipios;
        spMunicipios = v.findViewById(R.id.spinner_municipios);
        ArrayAdapter<CharSequence> adapterMuni = ArrayAdapter.createFromResource(getActivity(),R.array.dp1, android.R.layout.simple_spinner_item);
        adapterMuni.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMunicipios.setAdapter(adapterMuni);

        // SPINNER TIPO SITIO
        spTipoSitio = v.findViewById(R.id.spinner_tipoSitio);
        ArrayAdapter<CharSequence> adapterTipo = ArrayAdapter.createFromResource(getActivity(),R.array.tiposSitio, android.R.layout.simple_spinner_item);
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipoSitio.setAdapter(adapterTipo);

        spDepartamentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                numDep = i + 1;
                numMuni = 0;
                ArrayAdapter<CharSequence> adapterMuni2;
                switch (numDep) {
                    case 1 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp1, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 0;
                        break;
                    case 2 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp2, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 17;
                        break;
                    case 3 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp3, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 25;
                        break;
                    case 4 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp4, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 41;
                        break;
                    case 5 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp5, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 57;
                        break;
                    case 6 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp6, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 70;
                        break;
                    case 7 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp7, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 84;
                        break;
                    case 8 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp8, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 103;
                        break;
                    case 9 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp9, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 111;
                        break;
                    case 10 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp10, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 135;
                        break;
                    case 11 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp11, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 156;
                        break;
                    case 12 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp12, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 165;
                        break;
                    case 13 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp13, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 195;
                        break;
                    case 14 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp14, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 227;
                        break;
                    case 15 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp15, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 248;
                        break;
                    case 16 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp16, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 256;
                        break;
                    case 17 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp17, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 273;
                        break;
                    case 18 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp18, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 287;
                        break;
                    case 19 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp19, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 292;
                        break;
                    case 20 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp20, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 303;
                        break;
                    case 21 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp21, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 314;
                        break;
                    case 22 :
                        adapterMuni2 = ArrayAdapter.createFromResource(getActivity(),R.array.dp22, android.R.layout.simple_spinner_item);
                        adapterMuni2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMunicipios.setAdapter(adapterMuni2);
                        numMuni = 321;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spMunicipios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(numDep < 7)
                    numMuniE= (numDep * 100) + numMuni + i + 1;
                else {
                    numMuniE = (numDep * 1000) + numMuni + i + 1;
                    if(numMuniE <= 7099)
                        numMuniE = numMuniE - 6300;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spTipoSitio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tipoSitio= i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return v;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0]== PackageManager.PERMISSION_GRANTED && grantResults[1]== PackageManager.PERMISSION_GRANTED && requestCode == 1) {
            takePicture();
        } else {
            Toast.makeText(getActivity(), "No se otorgo permiso para tomar fotos", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            image = BitmapFactory.decodeFile(imageUri.getPath());
            if(numFotos == 0) {
                imgFoto1.setImageBitmap(image);
                pImage1 = imageUri.getPath();
            }
            if(numFotos== 1) {
                imgFoto2.setImageBitmap(image);
                pImage2 = imageUri.getPath();
            }
            if(numFotos == 2) {
                imgFoto3.setImageBitmap(image);
                pImage3 = imageUri.getPath();
            }
            numFotos = numFotos + 1;
        }

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_foto) {
            if(numFotos >= 3){
                Toast.makeText(getActivity(), "Solamente puede incluir 3 Fotografias.", Toast.LENGTH_LONG).show();
                return;
            }
            isStoragePermissionGranted();
        }
        else if(v.getId() == R.id.checkbox_Parqueo){
            if(amenidad1.isChecked()){
                a1 = 1;
            }
            else{
                a1 = 0;
            }
        }
        else if(v.getId() == R.id.checkbox_Wifi){
            if(amenidad2.isChecked()){
                a2 = 1;
            }
            else{
                a2 = 0;
            }
        }
        else if(v.getId() == R.id.checkbox_Hoteles){
            if(amenidad3.isChecked()){
                a3 = 1;
            }
            else{
                a3 = 0;
            }
        }
        else if(v.getId() == R.id.btn_guardar){
            if(txtNombreSitio.getText().toString().equals("") || txtDescSitio.getText().toString().equals("")){
                Toast.makeText(getActivity(),"Ingrese todos los datos necesarios.", Toast.LENGTH_LONG).show();
                return;
            }
            if(pImage1.equals("")){
                Toast.makeText(getActivity(),"Ingrese al menos una fotografia.", Toast.LENGTH_LONG).show();
                return;
            }
            sitio elemento;
            int numS;
            numS = 0;
            IDSitio = dbUtility.getMaxID();
            if(IDSitio == null){
                IDSitio = "10005018_1";
            }
            else{
                numS = Integer.parseInt(IDSitio.substring(9, IDSitio.length())) + 1;
                IDSitio = "10005018_" + Integer.toString(numS);
            }
            elemento = new sitio(IDSitio, txtNombreSitio.getText().toString(), numDep, numMuniE, longitud, latitud, pImage1, pImage2, pImage3, txtDescSitio.getText().toString(), a1, a2, a3, tipoSitio);
            dbUtility.insertDatos(elemento);

            insertaApi();

        }
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if ((getActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) && (getActivity().checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)) {
                takePicture();
                return true;
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("Test","Permission is granted");
            takePicture();
            return true;
        }
    }

    public void takePicture () {
        imageUri = getOutputMediaFileUri();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intent, 101);
        }
    }

    private Uri getOutputMediaFileUri()
    {
        //Valida si tenemos posibilidad de escribir en la storage
        if(isExternalStorageAvaiable())
        {
            String mediaStorageDir = Environment.getExternalStorageDirectory() + File.separator + "proyecto_final";
            File mediaStorageDirFile = new File(mediaStorageDir);
            if (!mediaStorageDirFile.exists()) {
                mediaStorageDirFile.mkdir();
            }

            String fileName = "";
            String fileType = "";
            String timeStamp = Calendar.getInstance().getTimeInMillis() + "";

            fileName = "IMG_"+timeStamp;
            fileType = ".jpg";

            File mediaFile;
            try
            {
                mediaFile = File.createTempFile(fileName,fileType,mediaStorageDirFile);
                Log.i("st","File: "+Uri.fromFile(mediaFile));
            }
            catch (IOException e)
            {
                e.printStackTrace();
                Log.i("St","Error creating file: " + mediaStorageDir +fileName +fileType);
                return null;
            }
            return Uri.fromFile(mediaFile);
        }
        return null;

    }

    private boolean isExternalStorageAvaiable()
    {
        String state = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(state))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //Codificar archivo en base64 para almacenar en la foto
    private String encodeFileImage(Bitmap images) {//String filePath){
        Bitmap bm = null;//BitmapFactory.decodeFile(filePath);
        bm = getResizedBitmap(images, 200);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        // Recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bm,  (int)(newHeight * ((float)width/height)), newHeight, false);
        return resizedBitmap;
    }

    public void Vacia(){
        numDep = 0;
        numMuni = 0;
        numMuniE = 0;
        tipoSitio = 0;
        a1 = 0;
        a2 = 0;
        a3 = 0;
        numFotos = 0;
        pImage1 = "";
        pImage2 = "";
        pImage3 = "";
        longitud = "0";
        latitud = "0";
        IDSitio = "";

        txtNombreSitio.setText("");
        txtDescSitio.setText("");
        amenidad1.setChecked(false);
        amenidad2.setChecked(false);
        amenidad3.setChecked(false);
        imgFoto1.setImageResource(android.R.drawable.ic_menu_report_image);
        imgFoto2.setImageResource(android.R.drawable.ic_menu_report_image);
        imgFoto3.setImageResource(android.R.drawable.ic_menu_report_image);
        spDepartamentos.setSelection(0);
        spTipoSitio.setSelection(0);
    }

    public void insertaApi(){
        //INSERT A API
        StringRequest request = new StringRequest(Request.Method.POST, "http://186.151.140.61/galileo/sitio-turistico", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject json = null;
                try {
                    json = new JSONObject(response);
                    Toast.makeText(getActivity(), json.getString("mensaje"), Toast.LENGTH_LONG).show();
                    Vacia();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Vacia();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String error1 = error.getMessage();
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                JSONObject json = new JSONObject();
                try {
                    json.put("departamento", Integer.toString(numDep));
                    json.put("municipio", Integer.toString(numMuniE));
                    json.put("titulo", txtNombreSitio.getText().toString());
                    json.put("latitud", "10005018");
                    json.put("longitud", "10005018");
                    json.put("descripcion", txtDescSitio.getText().toString());
                    json.put("tipo", Integer.toString(tipoSitio));

                    if(pImage1.equals(""))
                        json.put("foto1", "");
                    else
                        json.put("foto1", encodeFileImage(BitmapFactory.decodeFile(pImage1)));

                    if(pImage2.equals(""))
                        json.put("foto2", "");
                    else
                        json.put("foto2", encodeFileImage(BitmapFactory.decodeFile(pImage2)));

                    if(pImage3.equals(""))
                        json.put("foto3", "");
                    else
                        json.put("foto3", encodeFileImage(BitmapFactory.decodeFile(pImage3)));

                    json.put("parqueo", Integer.toString(a1));
                    json.put("internet", Integer.toString(a2));
                    json.put("hoteles", Integer.toString(a3));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String httpPostBody = json.toString();

                // usually you'd have a field with some values you'd want to escape, you need to do it yourself if overriding getBody. here's how you do it
                return httpPostBody.getBytes();
            }
        };
        ((WebApplication) getActivity().getApplicationContext()).getQueue().add(request);

    }
}
