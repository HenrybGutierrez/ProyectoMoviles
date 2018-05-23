package com.proyecto.univalle.proyectomoviles.modelo;

import java.util.List;

public class Categoria {
    private String id;
    private String nombre;
    private String descripcion;
    private List<Subcategoria>subcategorias;

    public Categoria(String id, String nombre, String descripcion, List<Subcategoria> subcategorias) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.subcategorias = subcategorias;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Subcategoria> getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(List<Subcategoria> subcategorias) {
        this.subcategorias = subcategorias;
    }
}
