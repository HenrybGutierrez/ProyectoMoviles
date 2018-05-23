package com.proyecto.univalle.proyectomoviles.modelo;

public class Comentario {
    private String id;
    private String mensaje;
    private Usuario usuario;
    private Lugar lugar;
    private String fecha;
    private int calificacion;
    private String titulo;

    public Comentario(String id, String mensaje, Usuario usuario, Lugar lugar, String fecha, int calificacion, String titulo) {
        this.id = id;
        this.mensaje = mensaje;
        this.usuario = usuario;
        this.lugar = lugar;
        this.fecha = fecha;
        this.calificacion = calificacion;
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
