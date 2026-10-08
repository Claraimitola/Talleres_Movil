package com.example.notasdocente.entidades;

public class Calificacion {
    private Alumno estudiante;
    private Asignatura materia;
    private float nota1 = 0.0f, peso1 = 0.30f;
    private float nota2 = 0.0f, peso2 = 0.30f;
    private float nota3 = 0.0f, peso3 = 0.40f;

    public Calificacion() { }

    public Alumno getEstudiante() { return estudiante; }
    public void setEstudiante(Alumno estudiante) { this.estudiante = estudiante; }
    public Asignatura getMateria() { return materia; }
    public void setMateria(Asignatura materia) { this.materia = materia; }
    public float getNota1() { return nota1; }
    public void setNota1(float nota1) { this.nota1 = nota1; }
    public float getPeso1() { return peso1; }
    public void setPeso1(float peso1) { this.peso1 = peso1; }
    public float getNota2() { return nota2; }
    public void setNota2(float nota2) { this.nota2 = nota2; }
    public float getPeso2() { return peso2; }
    public void setPeso2(float peso2) { this.peso2 = peso2; }
    public float getNota3() { return nota3; }
    public void setNota3(float nota3) { this.nota3 = nota3; }
    public float getPeso3() { return peso3; }
    public void setPeso3(float peso3) { this.peso3 = peso3; }
}