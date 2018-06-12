package com.proyecto.univalle.proyectomoviles;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyecto.univalle.proyectomoviles.modelo.Usuario;

public class ActivityRegistro extends AppCompatActivity {

    EditText txtnombre,txtapellidos,txtemail,txttelefono,txtpass,txtpassrep;
    Button btnregistrar;
    FirebaseAuth auth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        auth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();

        txtnombre=(EditText)findViewById(R.id.txtnombre);
        txtapellidos=(EditText)findViewById(R.id.txtapellidos);
        txtemail=(EditText)findViewById(R.id.txtemail);
        txttelefono=(EditText)findViewById(R.id.telefono);
        txtpass=(EditText)findViewById(R.id.txtcrearpass);
        txtpassrep=(EditText)findViewById(R.id.txtconfirmarpass);
        btnregistrar=(Button)findViewById(R.id.btncrearcuenta);

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nombre =txtnombre.getText().toString().trim();
                final String apellidos =txtapellidos.getText().toString().trim();
                final String email =txtemail.getText().toString().trim();
                final String telefono =txttelefono.getText().toString().trim();
                String pass =txtpass.getText().toString().trim();
                String passrep =txtpassrep.getText().toString().trim();
                if(TextUtils.isEmpty(nombre)||TextUtils.isEmpty(apellidos)||TextUtils.isEmpty(email)||
                        TextUtils.isEmpty(telefono)||TextUtils.isEmpty(pass)||TextUtils.isEmpty(passrep)){
                    Toast.makeText(getApplicationContext(),"Hay campos vacios",Toast.LENGTH_SHORT).show();

                }else{
                    auth.createUserWithEmailAndPassword(email,pass).
                            addOnCompleteListener(ActivityRegistro.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),"Hay problemas al crear",Toast.LENGTH_SHORT).show();
                                    }else{
                                        String uid=auth.getUid();
                                        Usuario u=new Usuario(uid,nombre,apellidos,email,"",telefono,"");
                                        databaseReference.child("usuario").child(uid).setValue(u);
                                        Toast.makeText(getApplicationContext(),"Se creo el usuario de manera satisfactoria",Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                            });

                }



            }
        });

    }
}
