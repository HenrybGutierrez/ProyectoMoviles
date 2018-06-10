package com.proyecto.univalle.proyectomoviles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    // Declare Variables
    Context context;
    int imagen;
    LayoutInflater inflater;
    ArrayList<String> cat;

    public ListViewAdapter(Context context, ArrayList<String> cat, int imagen) {
        this.context = context;
        this.cat = cat;
        this.imagen = imagen;
    }

    @Override
    public int getCount() {
        return cat.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // Declare Variables
        TextView txtTitle;
        ImageView imgImg;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.fila_lista, parent, false);

        // Locate the TextViews in listview_item.xml
        txtTitle = (TextView) itemView.findViewById(R.id.nombre_fila_lista);
        imgImg = (ImageView) itemView.findViewById(R.id.icon);

        // Capture position and set to the TextViews
        txtTitle.setText(cat.get(position));
        imgImg.setImageResource(imagen);

        return itemView;
    }
}