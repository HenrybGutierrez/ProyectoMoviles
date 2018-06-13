package com.proyecto.univalle.proyectomoviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.proyecto.univalle.proyectomoviles.R;
import com.proyecto.univalle.proyectomoviles.modelo.Lugar;

import java.util.ArrayList;

public class InfoLugaresActivity extends AppCompatActivity {

    TextView lblTitulo,lblInfoDescripcion;
    String nombre, descripcion, latitud, longitud;
    ArrayList<Lugar> datos;
    int id;
    ArrayList<Lugar> lugarUnico=new ArrayList<Lugar>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_lugares);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoLugaresActivity.this, MapaActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK);

                //Enviar datos
                intent.putExtra("Datos", lugarUnico);
                startActivity(intent);
            }
        });

        lblTitulo = (TextView) findViewById(R.id.lblTitulo);
        lblInfoDescripcion=(TextView)findViewById(R.id.lblInfoDescripcion);

        //Recibir datos
        datos= (ArrayList<Lugar>) getIntent().getSerializableExtra("Datos");
        Bundle recibo = getIntent().getExtras();
        if (recibo != null) {
            id = recibo.getInt("Id");

            nombre = datos.get(id).getNombre();
            descripcion = datos.get(id).getDescripcion();
            latitud = datos.get(id).getLatitud();
            longitud = datos.get(id).getLongitud();

            lblTitulo.setText(nombre);
            lblInfoDescripcion.setText(descripcion);

            lugarUnico.add(datos.get(id));
        }


    }
}