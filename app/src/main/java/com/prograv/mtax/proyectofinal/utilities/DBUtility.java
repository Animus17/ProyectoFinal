package com.prograv.mtax.proyectofinal.utilities;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.prograv.mtax.proyectofinal.data.sitio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mtax on 01/06/2018.
 */

public class DBUtility {
    public static final String DBNAME = "PROYECTO_FINAL";
    public static final int DBVER = 1;

    private DBHelper conn;
    private Context context;

    public class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DBNAME, null, DBVER);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE SITIOS ( ID TEXT PRIMARY KEY, NOMBRE TEXT, DEPARTAMENTO INT, MUNICIPIO INT, LONGITUD TEXT, LATITUD TEXT, FOTO1 TEXT, FOTO2 TEXT, FOTO3 TEXT, DESCRIPCION TEXT, AMENIDAD1 INT, AMENIDAD2 INT, AMENIDAD3 INT, TIPO INT);";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public DBUtility(Context context) {
        this.context = context;
        conn = new DBHelper(context);
    }

    public void insertDatos (sitio item) {
        SQLiteDatabase db =  conn.getWritableDatabase();
        String query = "Insert INTO SITIOS (ID, NOMBRE, DEPARTAMENTO, MUNICIPIO, LONGITUD, LATITUD, FOTO1, FOTO2, FOTO3, DESCRIPCION, AMENIDAD1, AMENIDAD2, AMENIDAD3, TIPO) VALUES ('" + item.getID() + "', '" + item.getNombre() +"', " + item.getDepartamento() + ", " + item.getMunicipio() + ", '" + item.getLongitud() + "', '" + item.getLatitud() + "', '" + item.getFoto1() + "', '" + item.getFoto2() + "', '" + item.getFoto3() + "', '" + item.getDespcripcion() + "', " + item.getAmenidad1() + ", " + item.getAmenidad2() + ", " + item.getAmenidad3() + ", " + item.getTipo() + ")";
        db.execSQL(query);
        db.close();
    }

    public String getMaxID(){
        String maxId;
        maxId = "";
        Cursor c;
        SQLiteDatabase db = conn.getWritableDatabase();
        String query = "SELECT MAX(ID) AS ID FROM SITIOS";
        c = db.rawQuery(query, null);
        if (c != null ) {
            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {
                    maxId = c.getString(c.getColumnIndex("ID"));
                    c.moveToNext();
                }
            }
        }
        return maxId;
    }

    public List<sitio> getDatos() {
        List<sitio> lista = null;
        sitio item;
        Cursor c;
        SQLiteDatabase db = conn.getWritableDatabase();
        String query = "SELECT ID, NOMBRE, DEPARTAMENTO, MUNICIPIO, LONGITUD, LATITUD, FOTO1, FOTO2, FOTO3, DESCRIPCION, AMENIDAD1, AMENIDAD2, AMENIDAD3, TIPO FROM SITIOS";
        c = db.rawQuery(query, null);
        if (c != null ) {
            if (c.getCount() > 0) {
                c.moveToFirst();
                lista = new ArrayList<sitio>();
                while (!c.isAfterLast()) {
                    item = new sitio();
                    item.setID(c.getString(c.getColumnIndex("ID")));
                    item.setNombre(c.getString(c.getColumnIndex("NOMBRE")));
                    item.setDepartamento(c.getInt(c.getColumnIndex("DEPARTAMENTO")));
                    item.setMunicipio(c.getInt(c.getColumnIndex("MUNICIPIO")));
                    item.setLongitud(c.getString(c.getColumnIndex("LONGITUD")));
                    item.setLatitud(c.getString(c.getColumnIndex("LATITUD")));
                    item.setFoto1(c.getString(c.getColumnIndex("FOTO1")));
                    item.setFoto2(c.getString(c.getColumnIndex("FOTO2")));
                    item.setFoto3(c.getString(c.getColumnIndex("FOTO3")));
                    item.setDespcripcion(c.getString(c.getColumnIndex("DESCRIPCION")));
                    item.setAmenidad1(c.getInt(c.getColumnIndex("AMENIDAD1")));
                    item.setAmenidad2(c.getInt(c.getColumnIndex("AMENIDAD2")));
                    item.setAmenidad3(c.getInt(c.getColumnIndex("AMENIDAD3")));
                    item.setTipo(c.getInt(c.getColumnIndex("TIPO")));
                    lista.add(item);
                    c.moveToNext();
                }
            }
        }
        return lista;
    }
}

