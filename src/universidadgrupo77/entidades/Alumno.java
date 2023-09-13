/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidadgrupo77.entidades;

import java.time.LocalDate;

/**
 *
 * @author Matias
 */
public class Alumno {

    private int id_Alumno;
    private int dni;
    private String apellido;
    private String nombre;
    private LocalDate date;
    private boolean estado;

    public Alumno() {
    }

    public Alumno(int id_Alumno, int dni, String apellido, String nombre, LocalDate date, boolean estado) {
        this.id_Alumno = id_Alumno;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.date = date;
        this.estado = estado;
    }

    public Alumno(int dni, String apellido, String nombre, LocalDate date, boolean estado) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.date = date;
        this.estado = estado;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getId_Alumno() {
        return id_Alumno;
    }

    public void setId_Alumno(int id_Alumno) {
        this.id_Alumno = id_Alumno;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Alumno{" + "id_Alumno=" + id_Alumno + ", apellido=" + apellido + ", nombre=" + nombre + '}';
    }

}
