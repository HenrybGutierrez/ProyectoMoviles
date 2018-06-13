package com.proyecto.univalle.proyectomoviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PerfilActivity extends AppCompatActivity {
    String nombre;
    String correo;
    String foto;
    ImageView fotoPerfil;
    TextView lblInfoNombre, lblInfoCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        fotoPerfil=(ImageView)findViewById(R.id.fotoPerfil);
        lblInfoNombre=(TextView)findViewById(R.id.lblInfoPersona);
        lblInfoCorreo=(TextView)findViewById(R.id.lblInfoCorreo);

        Bundle recibo = getIntent().getExtras();
        if(recibo!=null){
            nombre=recibo.getString("nombre");
            correo=recibo.getString("correo");
            foto=recibo.getString("foto");
            lblInfoNombre.setText(nombre);
            lblInfoCorreo.setText(correo);
            new LoadImage(fotoPerfil).execute(foto);
        }
    }

    public void logout(View g){
        Intent intent = new Intent(PerfilActivity.this, SplashActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
