package com.proyecto.univalle.proyectomoviles.conexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.proyecto.univalle.proyectomoviles.modelo.Comentario;
import com.proyecto.univalle.proyectomoviles.modelo.Lugar;
import com.proyecto.univalle.proyectomoviles.modelo.Usuario;

public class ComentarioBD extends ConexionBD{
    private SQLiteDatabase db;
    private Context context;

    public  ComentarioBD (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
        db=super.getWritableDatabase();
    }

    public void categotia() {

        Comentario c = null;
        Lugar l=null;
        Usuario u = null;
        UsuarioBD ubd = new UsuarioBD(context, "Proyeto", null, 1);
        LugarBD lbd = new LugarBD(context, "Proyecto", null, 1);

        String query = "select * from comentario ";
        Cursor fila = db.rawQuery(query, null);
        if (fila.moveToFirst()) {
            do {
                String id = fila.getString(0);
                String mensaje = fila.getString(1);
                String idUsuario = fila.getString(2);
                Usuario usuario = ubd.usuario(idUsuario);
                String idLugar = fila.getString(3);
                Lugar lugar = lbd.buscarLugar(idLugar);
                String fecha = fila.getString(4);
                int calificacion = fila.getInt(5);
                String titulo = fila.getString(6);

                c = new Comentario(id,mensaje,usuario,lugar,fecha,calificacion,titulo);
            }while (fila.moveToNext());

        }

    }

    public boolean insertarComentario(Comentario c){
        ContentValues registro = new ContentValues();
        registro.put("id",c.getId());
        registro.put("mensaje",c.getMensaje());
        registro.put("usuario",c.getUsuario().getId());
        registro.put("Lugar",c.getLugar().getId());
        registro.put("fecha",c.getFecha());
        registro.put("calificacion",c.getCalificacion());
        registro.put("titulo",c.getTitulo());

        db.insert("comentario", null, registro);
        db.close();

        return true;
    }


}

