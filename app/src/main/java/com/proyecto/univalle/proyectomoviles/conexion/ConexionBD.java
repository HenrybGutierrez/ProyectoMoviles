package com.proyecto.univalle.proyectomoviles.conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionBD extends SQLiteOpenHelper {

    String query1="create table usuario (id string primary key autoincrement, nombre varchar, apellido varchar, email varchar,urlphoto varchar, telefono integer, fechaNacimiento varchar);";
    String query2 ="create table categoria(id integer primary key autoincrement, nombre varchar, descripcion varchar);";
    String query3 ="create table lugar(id integer primary key autoincrement, nombre varchar, descripcion varchar, calidicacion integer, categoria integer, horarioabierto integer, horariocerrado integer, latitud integer, longitud double, latitud double);";
    String query4 ="create table fotosurl(id integer primary key autoincrement, idLugar varchar, descripcion varchar );";
    String query5 ="create table email(id integer primary key autoincrement, idLugar varchar, descripcion varchar );";
    String query6 ="create table sitioweb(id integer primary key autoincrement, idLugar varchar, descripcion varchar );";
    String query7 ="create table telefono(id integer primary key autoincrement, idLugar varchar, descripcion varchar );";
    String query8 ="create table comentario(id integer primary key autoincrement, mensaje varchar, usuario string, lugar integer,fecha varchar, calificacion integer);";
    String query9 ="create table visita(id integer primary key autoincrement, usuario string, lugar integer, fecha varchar);";

    public ConexionBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
        db.execSQL(query6);
        db.execSQL(query7);
        db.execSQL(query8);
        db.execSQL(query9);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
