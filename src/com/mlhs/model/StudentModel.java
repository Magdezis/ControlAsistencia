package com.mlhs.model;

import java.time.LocalDate;

public class StudentModel {
    
    private String nombre; 
    private String apellido; 
    private String correo; 
    private String carne; 
    private LocalDate fechaAsistencia; 
    private String asistencia; 
    private String codigoSeccion; 
    private String jornada; 

    public StudentModel(String nombre, String apellido, String correo, String carne, LocalDate fechaAsistencia, String asistencia, String codigoSeccion, String jornada) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.carne = carne;
        this.fechaAsistencia = fechaAsistencia;
        this.asistencia = asistencia;
        this.codigoSeccion = codigoSeccion;
        this.jornada = jornada;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public LocalDate getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(LocalDate fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public String getCodigoSeccion() {
        return codigoSeccion;
    }

    public void setCodigoSeccion(String codigoSeccion) {
        this.codigoSeccion = codigoSeccion;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }
    
    
    
  
    
    
}
