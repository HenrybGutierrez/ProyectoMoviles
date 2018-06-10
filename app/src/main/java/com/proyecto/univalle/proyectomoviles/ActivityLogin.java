package com.proyecto.univalle.proyectomoviles;

import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    private EditText txtusername, txtpassword;

    boolean isMobile,IsWifi=false;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private GoogleSignInClient mGoogleSignInClient;
    private GoogleApiClient googleApiClient;

    AccountManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ///Componentes interfaz
        txtusername = (EditText) findViewById(R.id.txtusername);
        txtpassword = (EditText) findViewById(R.id.txtpassword);
        txtusername.setText("admin");
        txtpassword.setText("admin");

        ///Para el login a traves del API de Google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        manager = AccountManager.get(this);

        findViewById(R.id.sign_in).setOnClickListener(this);


    }

    public void ingresarSistema(View view) {
        String username = txtusername.getText().toString().trim();
        String pass = txtpassword.getText().toString().trim();

        Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    //////////////////////////TODO LO QUE TIENE QUE VER GON GOOGLE
    public  boolean checkConnection(){
        Toast.makeText(this,"Verificando conexion...",Toast.LENGTH_SHORT).show();
        boolean isConnet=false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if(activeNetwork!=null){
            isConnet = activeNetwork.isConnectedOrConnecting();
        }
        if(isConnet && activeNetwork!=null){
            isMobile= activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
            IsWifi = activeNetwork.getType()== ConnectivityManager.TYPE_WIFI;
            return  true;
        }else{
            return  false;
        }
    }
    private  void signIn(){
        if(checkConnection()){
            Toast.makeText(this,"Conexion exitosa",Toast.LENGTH_SHORT).show();
            if(isMobile){
                Toast.makeText(this,"Conexion por Datos",Toast.LENGTH_SHORT).show();
                android.support.v7.app.AlertDialog dialogo = new android.support.v7.app.AlertDialog.Builder(this).create();
                dialogo.setTitle("Validar red");
                dialogo.setMessage("Desea consumir datos");
                dialogo.setCancelable(false);
                dialogo.setButton(DialogInterface.BUTTON_POSITIVE,"Aceptar",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent signIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                        startActivityForResult(signIntent,RC_SIGN_IN);
                    }
                });
                dialogo.setButton(DialogInterface.BUTTON_NEGATIVE, "La proxima", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialogo.show();
            }else{
                Toast.makeText(this,"Conexion Tipo Wifi",Toast.LENGTH_SHORT).show();
                Intent signIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(signIntent,RC_SIGN_IN);
            }
        }else{
            android.support.v7.app.AlertDialog dialogo = new android.support.v7.app.AlertDialog.Builder(this).create();
            dialogo.setTitle("Sin conexion");
            dialogo.setMessage("No puede ingresar sin estar conectado");
            dialogo.setButton(DialogInterface.BUTTON_NEUTRAL, "Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialogo.show();
        }
    }
    private void handleSignInResult(GoogleSignInResult result ) {
        if(result.isSuccess()){
            GoogleSignInAccount acct = result.getSignInAccount();
            String nombre=acct.getDisplayName();
            String correo=acct.getEmail();
            String foto=""+acct.getPhotoUrl();
            Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK);

            Bundle bundle = new Bundle();
            bundle.putString("nombre", nombre);
            bundle.putString("correo", correo);
            bundle.putString("foto", foto);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (RC_SIGN_IN==requestCode){
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            GoogleSignInResult resul = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(resul);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_in:
                signIn();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
