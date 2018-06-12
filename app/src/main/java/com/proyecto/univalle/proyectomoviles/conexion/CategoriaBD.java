package com.proyecto.univalle.proyectomoviles.conexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.proyecto.univalle.proyectomoviles.modelo.Categoria;

public class CategoriaBD extends ConexionBD{
    private SQLiteDatabase db;

    public  CategoriaBD (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db=super.getWritableDatabase();
    }

    public void categotias() {

        Categoria c = null;

        String query = "select * from categoria ";
        Cursor fila = db.rawQuery(query, null);
        if (fila.moveToFirst()) {
            do {
                String id = fila.getString(0);
                String nombre = fila.getString(1);
                String descripcion = fila.getString(2);

                c = new Categoria(id,nombre,descripcion);
            }while (fila.moveToNext());

        }

    }

    public Categoria categotia( String idCategoria) {

        Categoria c = null;

        String query = "select * from categoria where id='"+idCategoria+"' ";
        Cursor fila = db.rawQuery(query, null);
        if (fila.moveToFirst()) {

                String id = fila.getString(0);
                String nombre = fila.getString(1);
                String descripcion = fila.getString(2);

                c = new Categoria(id,nombre,descripcion);


        }
        return c;
    }

    public boolean insertarCategoria(Categoria c){
        ContentValues registro = new ContentValues();
        registro.put("id",c.getId());
        registro.put("nombre",c.getNombre());
        registro.put("apellido",c.getDescripcion());

        db.insert("categoria", null, registro);
        db.close();

        return true;
    }


}
