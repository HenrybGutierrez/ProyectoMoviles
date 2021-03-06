package com.proyecto.univalle.proyectomoviles.modelo;

import java.io.Serializable;
import java.util.List;

public class Lugar implements Serializable {
    private String id;
    private String nombre;
    private String descripcion;
    private List<String>fotosurl;
    private double calificacion;
    private Categoria categoria;
    private List<String> email;
    private List<String> sitioweb;
    private List<String> telefonos;
    private String horario;
    private String latitud;
    private String longitud;
    private String direccion;

    public Lugar(){

    }

    public Lugar(String id, String nombre, String descripcion, String direccion, List<String> fotosurl, double calificacion, Categoria categoria, List<String> email, List<String> sitioweb, List<String> telefonos, String horario, String latitud, String longitud) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fotosurl = fotosurl;
        this.calificacion = calificacion;
        this.categoria = categoria;
        this.email = email;
        this.sitioweb = sitioweb;
        this.telefonos = telefonos;
        this.horario = horario;
        this.latitud=latitud;
        this.longitud=longitud;
        this.direccion=direccion;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
