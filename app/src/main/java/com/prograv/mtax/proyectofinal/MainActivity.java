package com.prograv.mtax.proyectofinal;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.prograv.mtax.proyectofinal.utilities.DBUtility;
import com.prograv.mtax.proyectofinal.fragments.nuevo_sitio;
import com.prograv.mtax.proyectofinal.fragments.mis_sitios;
import com.prograv.mtax.proyectofinal.fragments.acerca_De;
import com.prograv.mtax.proyectofinal.fragments.buscar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    protected ActionBarDrawerToggle mDrawerToggle;
    private ListView listaDrawer;
    private DBUtility dbUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* --------- BD --------- */
        dbUtility = new DBUtility(this);
        /* --------- BD --------- */

        /* --------- drawer --------- */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        mDrawerToggle.setHomeAsUpIndicator(R.mipmap.ic_launcher);

        listaDrawer = (ListView) findViewById(R.id.lista_drawer);
        listaDrawer.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.opciones)));
        listaDrawer.setOnItemClickListener(this);

        /* --------- drawer --------- */
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0 :
                nuevo_sitio nuevoSitio = new nuevo_sitio();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, nuevoSitio).commit();
                drawerLayout.closeDrawers();
                break;
            case 1 :
                mis_sitios misSitios = new mis_sitios();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, misSitios).commit();
                drawerLayout.closeDrawers();
                break;
            case 2 :
                buscar busqueda = new buscar();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, busqueda).commit();
                drawerLayout.closeDrawers();
                break;
            case 3:
                acerca_De acerca_de = new acerca_De();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, acerca_de).commit();
                drawerLayout.closeDrawers();
                break;
        }
    }

}
