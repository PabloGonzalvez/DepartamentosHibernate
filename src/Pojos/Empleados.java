/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author a16pablogb
 */
public class Empleados implements Serializable {
    
    private String dni;
    private String nombre;
    private String oficio;
    private String direccion;
    private Date fechaAlta;
    private float salario; 
    private float comision;
    private Departamentos departamento;
    private Vehiculos vehiculo;

    public Empleados() {
    }

    public Empleados(String dni, String nombre, String oficio, String direccion, Date fechaAlta, float salario, float comision,Departamentos d) {
        this.dni = dni;
        this.nombre = nombre;
        this.oficio = oficio;
        this.direccion = direccion;
        this.fechaAlta = fechaAlta;
        this.salario = salario;
        this.comision = comision;
        this.departamento=departamento;
    }
    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }

    public Departamentos getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamentos departamento) {
        this.departamento = departamento;
    }

    public Vehiculos getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculos vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    
}
