package com.proyecto.univalle.proyectomoviles.modelo;

import java.util.List;

public class Lugar {
    private String id;
    private String nombre;
    private String descripcion;

    private List<String>fotosurl;

    private double calificacion;
    private Subcategoria subcategoria;
    private List<String> email;
    private List<String> sitioweb;
    private List<String> telefonos;
    private String horaabierto;
    private String horacerrado;

    public Lugar(String id, String nombre, String descripcion, List<String> fotosurl, double calificacion, Subcategoria subcategoria, List<String> email, List<String> sitioweb, List<String> telefonos, String horaabierto, String horacerrado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fotosurl = fotosurl;
        this.calificacion = calificacion;
        this.subcategoria = subcategoria;
        this.email = email;
        this.sitioweb = sitioweb;
        this.telefonos = telefonos;
        this.horaabierto = horaabierto;
        this.horacerrado = horacerrado;
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

    public List<String> getFotosurl() {
        return fotosurl;
    }

    public void setFotosurl(List<String> fotosurl) {
        this.fotosurl = fotosurl;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getSitioweb() {
        return sitioweb;
    }

    public void setSitioweb(List<String> sitioweb) {
        this.sitioweb = sitioweb;
    }

    public List<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<String> telefonos) {
        this.telefonos = telefonos;
    }

    public String getHoraabierto() {
        return horaabierto;
    }

    public void setHoraabierto(String horaabierto) {
        this.horaabierto = horaabierto;
    }

    public String getHoracerrado() {
        return horacerrado;
    }

    public void setHoracerrado(String horacerrado) {
        this.horacerrado = horacerrado;
    }
}
