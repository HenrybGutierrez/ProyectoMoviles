package com.proyecto.univalle.proyectomoviles.modelo;

public class Visita {
    private String id;
    private Usuario usuario;
    private Lugar lugar;
    private String fecha;

    public Visita(String id, Usuario usuario, Lugar lugar, String fecha) {
        this.id = id;
        this.usuario = usuario;
        this.lugar = lugar;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
