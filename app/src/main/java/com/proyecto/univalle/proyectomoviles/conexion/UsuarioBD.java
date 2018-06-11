package com.proyecto.univalle.proyectomoviles.conexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.proyecto.univalle.proyectomoviles.modelo.Usuario;

public class UsuarioBD extends ConexionBD{
    private SQLiteDatabase db;

    public  UsuarioBD (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db=super.getWritableDatabase();
    }

    public void usuarios() {

        Usuario u = null;

        String query = "select * from usuario ";
        Cursor fila = db.rawQuery(query, null);
        if (fila.moveToFirst()) {
            do {
                String id = fila.getString(0);
                String nombre = fila.getString(1);
                String apellido = fila.getString(2);
                String email = fila.getString(3);
                String urlphoto = fila.getString(4);
                String telefono = fila.getString(5);
                String fechaNacimiento = fila.getString(6);
                u = new Usuario(id,nombre,apellido,email,urlphoto,telefono,fechaNacimiento);
            }while (fila.moveToNext());

        }

    }

    public Usuario usuario(String idUsuario) {

        Usuario u = null;

        String query = "select * from usuario where id='"+idUsuario+"' ";
        Cursor fila = db.rawQuery(query, null);
        if (fila.moveToFirst()) {

                String id = fila.getString(0);
                String nombre = fila.getString(1);
                String apellido = fila.getString(2);
                String email = fila.getString(3);
                String urlphoto = fila.getString(4);
                String telefono = fila.getString(5);
                String fechaNacimiento = fila.getString(6);
                u = new Usuario(id,nombre,apellido,email,urlphoto,telefono,fechaNacimiento);


        }
        return u;
    }

    public boolean insertarUsuario(Usuario u){
        ContentValues registro = new ContentValues();
        registro.put("id",u.getId());
        registro.put("nombre",u.getNombre());
        registro.put("apellido",u.getApellido());
        registro.put("email",u.getEmail());
        registro.put("urlphoto",u.getUrlphoto());
        registro.put("telefono",u.getTelefono());
        registro.put("fechanacimiento",u.getFechanacimiento());


        db.insert("usuario", null, registro);
        db.close();

        return true;
    }


}