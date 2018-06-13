package com.proyecto.univalle.proyectomoviles;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyecto.univalle.proyectomoviles.modelo.Categoria;
import com.proyecto.univalle.proyectomoviles.modelo.Lugar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class InfoCategoriasActivity extends AppCompatActivity {
    ImageView icon;
    ListView lista;
    ArrayAdapter<String> adaptador;
    ArrayList<Lugar> datos;
    int tipoCat = 0;
    ListViewAdapter adapter;
    public static String jsonDroguerias = "https://www.datos.gov.co/resource/fkrz-8whg.json";
    public static String jsonServicPublic = "https://www.datos.gov.co/resource/r6wb-irji.json";
    public static String jsonEmprTransp = "https://www.datos.gov.co/resource/uh4k-d5as.json";
    public static String jsonBancos = "https://www.datos.gov.co/resource/m5vp-ypu6.json";
    public static String jsonSupermercados = "https://www.datos.gov.co/resource/jas6-84i5.json";
    public static String jsonParques = "https://www.datos.gov.co/resource/tbnf-tvbj.json";
    public static String jsonIglesias = "https://www.datos.gov.co/resource/3e2q-shug.json";
    public static String id, nombre, descripcion, direccion, latitud, longitud, url, horario;
    private static double calificacion;
    private List<String> fotosurl;
    private Categoria categoria;
    private List<String> email;
    private List<String> sitioweb;
    private List<String> telefonos;
    DatabaseReference databaseReference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_categorias);

        icon = (ImageView) findViewById(R.id.icon);

        Bundle recibo = getIntent().getExtras();
        if (recibo != null) {
            tipoCat = recibo.getInt("Tipo");
        }


        int idImg = verTipo(tipoCat);

        if(tipoCat==8){
            readJson();
        }else{
            leerJsonOnline(url);
        }

        ArrayList<String> cat = new ArrayList<String>();
        for (int i = 0; i < datos.size(); i++) {
            cat.add(datos.get(i).getNombre() + "\n" + datos.get(i).getDescripcion());
        }

        final ListView lista = (ListView) findViewById(R.id.listView);
        adapter = new ListViewAdapter(this, cat, idImg);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), datos.get(i).getNombre(), Toast.LENGTH_SHORT).show();
                Intent ir = new Intent(InfoCategoriasActivity.this, InfoLugaresActivity.class);
                ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);

                Bundle bundle = new Bundle();
                bundle.putInt("Id", i);
                ir.putExtras(bundle);
                ir.putExtra("Datos", datos);
                startActivity(ir);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "presiono LARGO " + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        /*lista = (ListView)findViewById(R.id.listView);
        adaptador = new ArrayAdapter<String>(this,R.layout.fila_lista, R.id.nombre_fila_lista,cat);


        lista.setAdapter(adaptador);*/
    }

    public int verTipo(int tipo) {
        int idImg = 0;

        switch (tipo) {
            case 1:
                nombre = "nombre_del_establecimiento_establishment_name";
                descripcion = "";
                direccion = "direcci_n_address";
                latitud = "georeferenciaci_n_georeferencing";
                longitud = "georeferenciaci_n_georeferencing";
                horario = "horario_de_atenci_n_de_lunes_a_sabado_office_hours_from_monday_to_saturday";
                url = jsonDroguerias;
                idImg = R.drawable.pin_otros;
                break;
            case 2:
                nombre = "empresa";
                descripcion = "";
                direccion = "direccion_empresa";
                latitud = "latitud";
                longitud = "longitud";
                horario = "";
                url = jsonServicPublic;
                idImg = R.drawable.pin_taxi;
                break;
            case 3:
                nombre = "nombre";
                descripcion = "rutas";
                direccion = "local";
                latitud = "latitud";
                longitud = "longitud";
                horario = "horario_de_atenci_n";
                url = jsonEmprTransp;
                //icon.setImageResource(R.drawable.pin_bus);
                idImg = R.drawable.pin_bus;
                break;
            case 4:
                nombre = "entidad_bancaria";
                descripcion = "";
                direccion = "direccion";
                latitud = "latitud";
                longitud = "longitud";
                horario = "";
                url = jsonBancos;
                //icon.setImageResource(R.drawable.pin_recharge);
                idImg = R.drawable.pin_recharge;
                break;
            case 5:
                nombre = "establecimiento";
                descripcion = "bonos";
                direccion = "direcci_n";
                latitud = "latitud";
                longitud = "longitud";
                horario = "horarios";
                url = jsonSupermercados;
                //icon.setImageResource(R.drawable.pin_otros);
                idImg = R.drawable.pin_otros;
                break;
            case 6:
                nombre = "nombre_del_parque";
                descripcion = "";
                direccion = "direcci_n";
                latitud = "latitud";
                longitud = "longitud";
                horario = "";
                url = jsonParques;
                //icon.setImageResource(R.drawable.pin_parque);
                idImg = R.drawable.pin_parque;
                break;
            case 7:
                nombre = "parroquia";
                descripcion = "barrio";
                direccion = "direcci_n";
                latitud = "georeferencia";
                longitud = "georeferencia";
                horario = "horario_de_eucarist_as";
                url = jsonIglesias;
                //icon.setImageResource(R.drawable.pin_iglesias);
                idImg = R.drawable.pin_iglesias;
                break;
            case 8:
                idImg = R.drawable.pin_home;
                break;
        }

        return idImg;
    }

    private void leerJsonOnline(String uri) {
        //System.out.println(nombre + " " + direccion + " " + latitud + " " + longitud + " EL ZELDDAAAA " + uri);
        try {
            datos = new ArrayList<>();

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL url = null;
            HttpURLConnection conn;

            url = new URL(uri);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            String json = "";

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            json = response.toString();

            JSONArray jarrau = null;

            jarrau = new JSONArray(json);

            JSONObject obj2;
            JSONArray locacion;

            //databaseReference= FirebaseDatabase.getInstance().getReference();

            for (int i = 0; i < jarrau.length(); i++) {

                Lugar lugar = new Lugar();
                JSONObject object = jarrau.getJSONObject(i);

                lugar.setNombre(object.optString(nombre));

                if(tipoCat==1){
                    lugar.setNombre(object.getString(nombre));
                    lugar.setDescripcion("");
                    lugar.setDireccion(object.getString(direccion));
                    lugar.setHorario(object.getString(horario));
                }
                if(tipoCat==2){
                    lugar.setNombre(object.getString(nombre));
                    lugar.setDescripcion("");
                    lugar.setDireccion(object.getString(direccion));
                    lugar.setHorario("");
                }
                if (tipoCat == 3) {
                    lugar.setNombre(object.getString(nombre));
                    lugar.setDescripcion("Rutas: "+object.getString(descripcion));
                    lugar.setDireccion("Local: "+object.getString(direccion));
                    lugar.setHorario(object.getString(horario));
                }
                if(tipoCat==4){
                    lugar.setNombre(object.getString(nombre));
                    lugar.setDescripcion("");
                    lugar.setDireccion(object.getString(direccion));
                    lugar.setHorario("");
                }
                if (tipoCat == 5) {
                    lugar.setNombre(object.getString(nombre));
                    lugar.setDescripcion("Bonos: "+object.getString(descripcion));
                    lugar.setDireccion(object.getString(direccion));
                    lugar.setHorario(object.getString(horario));
                }
                if(tipoCat==6){
                    lugar.setNombre(object.getString(nombre));
                    lugar.setDescripcion("");
                    lugar.setDireccion(object.getString(direccion));
                    lugar.setHorario("");
                }
                if (tipoCat == 7) {
                    lugar.setNombre(object.getString(nombre));
                    lugar.setDescripcion("Barrio: "+object.getString(descripcion));
                    lugar.setDireccion(object.getString(direccion));
                    lugar.setHorario(object.getString(horario));
                }

                if (tipoCat == 1 || tipoCat == 7) {
                    obj2 = object.optJSONObject(latitud);
                    locacion = obj2.getJSONArray("coordinates");
                    lugar.setLongitud(locacion.get(0).toString());
                    lugar.setLatitud(locacion.get(1).toString());
                } else {
                    lugar.setLatitud(object.optString(latitud));
                    lugar.setLongitud(object.optString(longitud));
                }

                lugar.setId(object.getString(nombre));
                lugar.setCalificacion(0);
                lugar.setFotosurl(null);
                lugar.setEmail(null);
                lugar.setSitioweb(null);
                lugar.setTelefonos(null);
                lugar.setCategoria(null);


                //databaseReference.child("lugar").child(object.getString(nombre)).setValue(lugar);
                /*if(databaseReference.child("lugar").setValue(lugar).isSuccessful()){
                    System.out.println("Si ");
                }else{
                    System.out.println("LLORELO");
                }*/

                datos.add(lugar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verTodos(View v) {
        Intent ir = new Intent(InfoCategoriasActivity.this, MapaActivity.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        ir.putExtra("Datos", datos);
        startActivity(ir);
    }

    public void readJson() {
        datos = new ArrayList<>();
        String jsonString = IOHelper.stringFromAsset(this, "restaurantes.json");
        try {
            JSONArray restaurant = new JSONArray(jsonString);

            String result = "";
            for (int i = 0; i < restaurant.length(); i++) {

                Lugar lugar = new Lugar();

                JSONObject object = restaurant.getJSONObject(i);

                lugar.setId(object.getString("nombre"));
                lugar.setNombre(object.getString("nombre"));
                lugar.setDescripcion("Telefono: " + object.getString("telefono"));
                lugar.setDireccion(object.getString("direccion"));
                lugar.setFotosurl(null);
                lugar.setCalificacion(0);
                lugar.setCategoria(null);
                lugar.setEmail(null);
                lugar.setSitioweb(null);
                lugar.setTelefonos(null);
                lugar.setHorario(object.getString("horario"));
                lugar.setLatitud(object.getString("latitud"));
                lugar.setLongitud(object.getString("longitud"));

                datos.add(lugar);

            }

            /*for (int i = 0; i < datos.size(); i++) {
                System.out.println("Nombre " + datos.get(i).getNombre() + " Descripcion " + datos.get(i).getDescripcion() + " Direccion " + datos.get(i).getDireccion() + " Horario " + datos.get(i).getHorario() + " Latitud " + datos.get(i).getLatitud() + " Longitud " + datos.get(i).getLongitud());
            }*/

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
