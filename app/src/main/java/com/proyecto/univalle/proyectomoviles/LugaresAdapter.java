package com.proyecto.univalle.proyectomoviles;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.proyecto.univalle.proyectomoviles.modelo.Categoria;

import java.util.List;

public class LugaresAdapter extends RecyclerView.Adapter<LugaresAdapter.ViewHolder>{

    List<Categoria> lista;
    Context context;

    public LugaresAdapter(Context context,List<Categoria> lista){
        this.context=context;
        this.lista=lista;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.lugares,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.lugar.setText(lista.get(position).getNombre());
        holder.descripcion.setText(lista.get(position).getDescripcion());

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView lugar,descripcion;
        public ViewHolder(View view) {
            super(view);
            cardView=(CardView)view.findViewById(R.id.cvlugares);
            lugar=(TextView)view.findViewById(R.id.lugar);
            descripcion=(TextView)view.findViewById(R.id.descripcion);
        }
    }



}
