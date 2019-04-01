/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departamentoshibernate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import Pojos.Departamentos;
import Pojos.Empleados;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author a16pablogb
 */
public class Busquedas {

    public static List<Object> buscaTuplas(Class tipo) throws ClassNotFoundException, SQLException {

        List<Object> listado = null;
        try {
            Session session = NewHibernateUtil.getSession();
            listado = session.createCriteria(tipo).list();
            session.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }

        return listado;
    }

    public static void consultaDepartamentos() throws ClassNotFoundException, SQLException {
        List<Object> deps = Busquedas.buscaTuplas(Departamentos.class);
        Session session = NewHibernateUtil.getSession();
        String aux = "";
        if (deps.isEmpty()) {
            System.out.println("No hay edpartamentos registrados");
        } else {
            System.out.println(""
                    + "                              LISTADO DEPARTAMENTOS                                       \n"
                    + "---------------------------------------------------------------------------");
            System.out.printf("|  %-20s  |  %-9s         |  %-10s    |%n", "NUMERO DEPARTAMENTO", "NOMBRE", "LOCALIDAD");

            for (Object d : deps) {

                System.out.printf("|  %-20s  |  %-9s         |  %-10s    |%n", ((Departamentos) d).getNu_dep(), ((Departamentos) d).getNombre(), ((Departamentos) d).getLocalidad(), aux);
            }
            System.out.println("---------------------------------------------------------------------------\n");
            session.close();
        }
    }

    public static Departamentos buscaDepartamento(int numero) throws IOException {
        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        Session session;
        Departamentos d = null;
        boolean er = false;
        do {
            er = false;
            try {
                session = NewHibernateUtil.getSession();
                d = (Departamentos) session.get(Departamentos.class, numero);
                session.close();
            } catch (HibernateException e) {
                System.out.println(e.getMessage());
            }
            if (d == null) {
                er = true;
                System.out.println("\nERROR: Numero no válido\n\nVuelva a intentarlo:");
                numero = Integer.parseInt(lee.readLine());
            }
        } while (er == true);
        return d;
    }

    public static Empleados buscaEmpleado(String dni) throws IOException {
        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        Session session;
        Empleados emp = null;
        boolean er = false;
        do {
            er = false;
            try {
                session = NewHibernateUtil.getSession();
                emp = (Empleados) session.get(Empleados.class, dni);
                session.close();
            } catch (HibernateException e) {
                System.out.println(e.getMessage());
            }
            if (emp == null) {
                er = true;
                System.out.println("\nERROR: DNI no válido\n\nVuelva a intentarlo:");
                dni = lee.readLine();
            }
        } while (er == true);
        return emp;
    }

    public static void consultaEmpleado() throws ClassNotFoundException, SQLException {
        List<Object> empleados = Busquedas.buscaTuplas(Empleados.class);
        Session session = NewHibernateUtil.getSession();
        String aux = "";
        if (empleados.isEmpty()) {
            System.out.println("Todavia no hay empleados registrados");
        } else {
            System.out.println(""
                    + "                              LISTADO EMPLEADOS                                        \n"
                    + "---------------------------------------------------------------------------");
            System.out.printf("|  %-9s  |  %-17s     |  %-10s   |  %-10s   %n", "DNI", "NOMBRE", "SALARIO", "COMISION");

            for (Object emp : empleados) {

                System.out.printf("|  %-9s  |  %-17s     |  %-10s   |  %-10s      %n", ((Empleados) emp).getDni(), ((Empleados) emp).getNombre(), ((Empleados) emp).getSalario(), ((Empleados) emp).getComision(), aux);
            }
            System.out.println("---------------------------------------------------------------------------\n");
            session.close();
        }
    }

    public static Empleados buscaEmpleadoNombre(String nombre) throws IOException {
        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        Session session;
        Empleados emp = null;
        boolean er = false;
        do {
            er = false;
            try {

                session = NewHibernateUtil.getSession();
                emp = (Empleados) session.createQuery("FROM " + Empleados.class.getName() + " WHERE nombre = '" + nombre + "'").uniqueResult();
                //emp = (Empleados) session.createQuery("SELECT e FROM Empleados e WHERE e.nombre='"+nombre+"'").uniqueResult();
                session.close();
            } catch (HibernateException e) {
                System.out.println(e.getMessage());
            }
            if (emp == null) {
                er = true;
                System.out.println("\nERROR: Empleado no encontrado \n\nVuelva a intentarlo:");
                nombre = lee.readLine();
            }
        } while (er == true);
        return emp;
    }

    public static Departamentos buscaDepartamentoNombre(String nombre) throws IOException {
        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        Session session;
        Departamentos dep = null;
        boolean er = false;
        do {
            er = false;
            try {

                session = NewHibernateUtil.getSession();
                dep = (Departamentos) session.createQuery("FROM " + Departamentos.class.getName() + " WHERE nombre = '" + nombre + "'").uniqueResult();

                session.close();
            } catch (HibernateException e) {
                System.out.println(e.getMessage());
            }
            if (dep == null) {
                er = true;
                System.out.println("\nERROR: Departamento no encontrado \n\nVuelva a intentarlo:");
                nombre = lee.readLine();
            }
        } while (er == true);
        return dep;
    }
}
