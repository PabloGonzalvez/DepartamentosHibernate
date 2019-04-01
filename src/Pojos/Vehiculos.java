/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import java.io.Serializable;
import java.sql.Date;


public class Vehiculos implements Serializable{
    private String dni;
    private String marca;
    private String matricula;
    private String modelo;
    private Date fechaCompra;
    private Empleados empleado;

    public Vehiculos() {
    }

    public Vehiculos(String dni, String marca, String matricula, String modelo, Date fechaCompra) {
        this.dni = dni;
        this.marca = marca;
        this.matricula = matricula;
        this.modelo = modelo;
        this.fechaCompra = fechaCompra;
       
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }
        
}
