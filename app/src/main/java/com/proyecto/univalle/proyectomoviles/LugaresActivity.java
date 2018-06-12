package com.proyecto.univalle.proyectomoviles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyecto.univalle.proyectomoviles.modelo.Categoria;

import java.util.ArrayList;
import java.util.List;

public class LugaresActivity extends AppCompatActivity {

    RecyclerView rv;
    List<Categoria> CategoriaList;
    //LugaresAdapter adapter;
    FirebaseRecyclerAdapter<Categoria,LugaresAdapter.ViewHolder>adapter;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares);
        databaseReference= FirebaseDatabase.getInstance().getReference();


        CategoriaList =new ArrayList<>();


        rv=(RecyclerView) findViewById(R.id.rvlugares);
        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        adapter=new FirebaseRecyclerAdapter<Categoria, LugaresAdapter.ViewHolder >(
                Categoria.class,
                R.layout.lugares,
                LugaresAdapter.ViewHolder.class,
                databaseReference.child("categoria")
        ) {
            @Override
            protected void populateViewHolder(LugaresAdapter.ViewHolder viewHolder, Categoria model, int position) {
                viewHolder.lugar.setText(model.getNombre());
                viewHolder.descripcion.setText(model.getDescripcion());
            }


        };
        rv.setAdapter(adapter);

    }

    private void crearDatos() {
        CategoriaList.add(new Categoria("001","Comida","Comidas sabrosas :v"));
        CategoriaList.add(new Categoria("002","Salud","Comidas sabrosas :v"));
        CategoriaList.add(new Categoria("003","Parques","Comidas sabrosas :v"));
        CategoriaList.add(new Categoria("004","Hoteles","Comidas sabrosas :v"));
        CategoriaList.add(new Categoria("005","Comida","Comidas sabrosas :v"));CategoriaList.add(new Categoria("001","Comida","Comidas sabrosas :v"));
        CategoriaList.add(new Categoria("006","Comida","Comidas sabrosas :v"));
        CategoriaList.add(new Categoria("007","Comida","Comidas sabrosas :v"));
        CategoriaList.add(new Categoria("008","Comida","Comidas sabrosas :v"));

    }
}
