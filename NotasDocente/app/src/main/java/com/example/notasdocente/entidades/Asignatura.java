package com.example.notasdocente.entidades;

public class Asignatura {
    private String codigo;
    private String nombre;
    private Programa carrera;

    public Asignatura() { }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Programa getCarrera() { return carrera; }
    public void setCarrera(Programa carrera) { this.carrera = carrera; }
}