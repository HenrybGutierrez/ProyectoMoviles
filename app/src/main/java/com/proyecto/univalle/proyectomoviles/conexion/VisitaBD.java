package com.proyecto.univalle.proyectomoviles.conexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.proyecto.univalle.proyectomoviles.modelo.Visita;
import com.proyecto.univalle.proyectomoviles.modelo.Usuario;
import com.proyecto.univalle.proyectomoviles.modelo.Lugar;
import com.proyecto.univalle.proyectomoviles.conexion.UsuarioBD;

public class VisitaBD extends ConexionBD{
    private SQLiteDatabase db;
    private Context context;

    public  VisitaBD (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
        db=super.getWritableDatabase();
    }

    public void visita() {

        Visita v = null;
        UsuarioBD ubd = new UsuarioBD(context, "Proyeto", null, 1);
        LugarBD lbd = new LugarBD(context, "Proyecto", null, 1);
        Lugar l=null;
        Usuario u = null;

        String query = "select * from visita ";
        Cursor fila = db.rawQuery(query, null);
        if (fila.moveToFirst()) {
            do {
                String id = fila.getString(0);
                String idUsuario=fila.getString(1);
                Usuario usuario = ubd.usuario(idUsuario);
                String idLugar = fila.getString(2);
                Lugar lugar = lbd.buscarLugar(idLugar);
                String fecha = fila.getString(3);
                v = new Visita(id,usuario,lugar,fecha);
            }while (fila.moveToNext());

        }

    }

    public boolean insertarVisita(Visita v){
        ContentValues registro = new ContentValues();
        registro.put("id",v.getId());
        registro.put("usuario",v.getUsuario().getId());
        registro.put("lugar",v.getLugar().getId());
        registro.put("fecha",v.getFecha());


        db.insert("visita", null, registro);
        db.close();

        return true;
    }


}
