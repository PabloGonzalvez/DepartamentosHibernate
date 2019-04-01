/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author a16pablogb
 */
public class Departamentos implements Serializable {
    private int nu_dep;
    private String nombre;
    private String localidad;
    Set<Empleados> empleados;

    public Departamentos() {
    }

    public Departamentos( String nombre, String localidad) {
        this.nu_dep = nu_dep;
        this.nombre = nombre;
        this.localidad = localidad;
        this.empleados= new HashSet<>();
    }

  

    public int getNu_dep() {
        return nu_dep;
    }

    public void setNu_dep(int nu_dep) {
        this.nu_dep = nu_dep;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Set<Empleados> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<Empleados> empleados) {
        this.empleados = empleados;
    }
    
    
    
}
