package com.example.proyectofinalas.controlador;

public class Tarea {
    private String nombre;
    private String fecha;
    private String descripcion;
    private int idtarea;

    public Tarea(){

    }

    public Tarea(int id,String nom,String fec,String desc){
        this.nombre=nom;
        this.fecha = fec;
        this.descripcion = desc;
        this.idtarea = id;

    }

    public int getIdtarea() {
        return idtarea;
    }

    public void setIdtarea(int idTarea) {
        this.idtarea = idTarea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
