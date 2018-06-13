package com.proyecto.univalle.proyectomoviles;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.proyecto.univalle.proyectomoviles.modelo.Lugar;

import java.util.ArrayList;

public class MapaActivity extends AppCompatActivity {

    ArrayList<Lugar> datos;
    GoogleMap gp;
    FragmentManager manager;
    String nombre, descripcion, latitud, longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        datos= (ArrayList<Lugar>) getIntent().getSerializableExtra("Datos");


        crearFragmentMapa();
    }

    public void crearFragmentMapa() {

        if (gp instanceof GoogleMap) {

        } else {
            gp = new GoogleMap(datos);
            android.support.v4.app.FragmentTransaction transtion = getSupportFragmentManager().beginTransaction();
            transtion.add(R.id.fragmentMapa, gp);
            transtion.commit();
        }
    }
}
