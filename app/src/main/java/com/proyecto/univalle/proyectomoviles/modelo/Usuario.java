package com.proyecto.univalle.proyectomoviles.modelo;

public class Usuario {
    private String id;
    private String nombre;
    private String apellido;
    private String email;
    private String urlphoto;
    private String telefono;
    private String fechanacimiento;

    public Usuario(String id, String nombre, String apellido, String email, String urlphoto, String telefono, String fechanacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.urlphoto = urlphoto;
        this.telefono = telefono;
        this.fechanacimiento = fechanacimiento;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrlphoto() {
        return urlphoto;
    }

    public void setUrlphoto(String urlphoto) {
        this.urlphoto = urlphoto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
}
