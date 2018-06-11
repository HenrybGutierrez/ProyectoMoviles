

package com.proyecto.univalle.proyectomoviles.conexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.proyecto.univalle.proyectomoviles.modelo.Categoria;
import com.proyecto.univalle.proyectomoviles.modelo.Lugar;

import java.util.List;

public class LugarBD extends ConexionBD{
    private SQLiteDatabase db;
    private List<String>fotosurl;
    private List<String> email;
    private List<String> sitioweb;
    private List<String> telefonos;
    private Context context;

    public  LugarBD (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
        db=super.getWritableDatabase();
    }

    public void lugar() {

        Lugar l = null;
        CategoriaBD cbd = new CategoriaBD(context, "Proyeto", null, 1);

        String query = "select * from lugar ";
        Cursor fila = db.rawQuery(query, null);
        if (fila.moveToFirst()) {
            do {
                String id = fila.getString(0);
                String nombre = fila.getString(1);
                String descripcion = fila.getString(2);

                String query1 = "select * from fotosurl where idLugar='"+id+"'";
                Cursor fila1 = db.rawQuery(query, null);

                if (fila1.moveToFirst()) {
                    do {
                        fotosurl.add(fila1.getString(2));
                    } while (fila1.moveToNext());
                }
                double calificacion = fila.getDouble(3);
                String idcategoria = fila.getString(4);
                Categoria categoria = cbd.categotia(idcategoria);

                String query2 = "select * from email where idLugar='"+id+"'";
                Cursor fila2 = db.rawQuery(query, null);

                    if (fila2.moveToFirst()) {
                        do {
                            email.add(fila2.getString(2));
                        } while (fila2.moveToNext());
                    }


                String query3 = "select * from sitioweb where idLugar='"+id+"'";
                Cursor fila3 = db.rawQuery(query, null);

                        if (fila3.moveToFirst()) {
                            do {
                                sitioweb.add(fila3.getString(2));
                            } while (fila3.moveToNext());
                        }

                String query4 = "select * from telefono where idLugar='"+id+"'";
                Cursor fila4 = db.rawQuery(query, null);

                if (fila4.moveToFirst()) {
                    do {
                        telefonos.add(fila4.getString(2));
                    } while (fila4.moveToNext());
                }
                String horarioAbierto = fila.getString(5);
                String horarioCerrado = fila.getString(6);
                String latitud = fila.getString(7);
                String longitud = fila.getString(8);


                l = new Lugar(id,nombre,descripcion,fotosurl,calificacion,categoria,email,sitioweb,telefonos,horarioAbierto,horarioCerrado,latitud,longitud);
            }while (fila.moveToNext());

        }

    }

    public Lugar buscarLugar(String idlugar) {

        Lugar l = null;
        CategoriaBD cbd = new CategoriaBD(context, "Proyeto", null, 1);

        String query = "select * from lugar where id='"+idlugar+"' ";
        Cursor fila = db.rawQuery(query, null);
        if (fila.moveToFirst()) {
            do {
                String id = fila.getString(0);
                String nombre = fila.getString(1);
                String descripcion = fila.getString(2);
                //     String fotosurl = fila.getString(3);

                String query1 = "select * from fotosurl where idLugar='"+id+"'";
                Cursor fila1 = db.rawQuery(query, null);

                if (fila1.moveToFirst()) {
                    do {
                        fotosurl.add(fila1.getString(2));
                    } while (fila1.moveToNext());
                }
                    double calificacion = fila.getDouble(3);
                    String idcategoria = fila.getString(4);
                    Categoria categoria = cbd.categotia(idcategoria);
                    // String email = fila.getString(6);

                    String query2 = "select * from email where idLugar='"+id+"'";
                    Cursor fila2 = db.rawQuery(query, null);

                    if (fila2.moveToFirst()) {
                        do {
                            email.add(fila2.getString(2));
                        } while (fila2.moveToNext());
                    }
                        //     String sitioweb = fila.getString(7);

                        String query3 = "select * from sitioweb where idLugar='"+id+"'";
                        Cursor fila3 = db.rawQuery(query, null);

                        if (fila3.moveToFirst()) {
                            do {
                                sitioweb.add(fila3.getString(2));
                            } while (fila3.moveToNext());
                        }

                            String query4 = "select * from telefono where idLugar='"+id+"'";
                            Cursor fila4 = db.rawQuery(query, null);

                            if (fila4.moveToFirst()) {
                                do {
                                    telefonos.add(fila4.getString(2));
                                } while (fila4.moveToNext());
                            }
                                String horarioAbierto = fila.getString(5);
                                String horarioCerrado = fila.getString(6);
                                String latitud = fila.getString(7);
                                String longitud = fila.getString(8);


                                l = new Lugar(id,nombre,descripcion,fotosurl,calificacion,categoria,email,sitioweb,telefonos,horarioAbierto,horarioCerrado,latitud,longitud);
                            } while (fila.moveToNext());

        }
        return l;
    }

    public boolean insertarLugar(Lugar l){
        ContentValues registro = new ContentValues();
        registro.put("id",l.getId());
        registro.put("nombre",l.getNombre());
        registro.put("descripcion",l.getDescripcion());
        registro.put("calificacion",l.getCalificacion());
        registro.put("categoria",l.getCategoria().getId());
        registro.put("horaAbierto",l.getHoraabierto());
        registro.put("horaCerrado",l.getHoracerrado());
        registro.put("latitud",l.getLatitud());
        registro.put("longitud",l.getLongitud());

        db.insert("categoria", null, registro);

        ContentValues registro1 = new ContentValues();
        for (int i = 0; i <l.getFotosurl().size() ; i++) {
            registro.put("idLugar",l.getId());
            registro1.put("descripcion",l.getFotosurl().get(i));
            db.insert("fotosurl", null, registro1);
        }

        ContentValues registro2 = new ContentValues();
        for (int i = 0; i <l.getFotosurl().size() ; i++) {
            registro.put("idLugar",l.getId());
            registro1.put("descripcion",l.getFotosurl().get(i));
            db.insert("email", null, registro2);
        }

        ContentValues registro3 = new ContentValues();
        for (int i = 0; i <l.getFotosurl().size() ; i++) {
            registro.put("idLugar",l.getId());
            registro1.put("descripcion",l.getFotosurl().get(i));
            db.insert("sitioweb", null, registro3);
        }

        ContentValues registro4 = new ContentValues();
        for (int i = 0; i <l.getFotosurl().size() ; i++) {
            registro.put("idLugar",l.getId());
            registro1.put("descripcion",l.getFotosurl().get(i));
            db.insert("telefono", null, registro4);
        }

        db.close();

        return true;
    }


}