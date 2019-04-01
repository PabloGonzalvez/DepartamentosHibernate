/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departamentoshibernate;

import Pojos.Departamentos;
import Pojos.Empleados;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Pablo
 */
public class Modificaciones {

    public static void menuModificaciones(SessionFactory sessionFactory) throws IOException, ClassNotFoundException, SQLException {

        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Seleccione una opcion \n"
                + "1--Modificar salario de empleado \n"
                + "2--Modificar comision de empleado \n"
                + "3--Modificar localidad de departamento \n"
                + "0--Salir");
        int op = Validaciones.validaMenu(0, 3);

        switch (op) {
            case 1:
                List<Object> listado = Busquedas.buscaTuplas(Empleados.class);
                if (listado.isEmpty()) {
                    System.out.println("ERROR: Todavía no se ha registrado ningún empleado\n");
                } else {
                    modificaSalario();
                }
                break;
            case 2:
                listado = Busquedas.buscaTuplas(Empleados.class);
                if (listado.isEmpty()) {
                    System.out.println("ERROR: Todavía no se ha registrado ningún empleado\n");
                } else {
                    modificaComision();
                }
                break;
            case 3:
                listado = Busquedas.buscaTuplas(Departamentos.class);
                if (listado.isEmpty()) {
                    System.out.println("ERROR: Todavía no se ha registrado ningún departamento\n");
                } else {
               modificaLocalildad();
                }
                break;
            case 0:
                break;

        }

    }

    public static void modificaSalario() throws IOException, ClassNotFoundException, SQLException {
        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        Empleados emp = null;
        Session session = NewHibernateUtil.getSession();
        Busquedas.consultaEmpleado();
        System.out.println("Nombre del empleado");
        String nombre = lee.readLine();
        emp = Busquedas.buscaEmpleadoNombre(nombre);
        System.out.println("Nuevo salario:");
        float precio = Float.parseFloat(lee.readLine());

        System.out.println("\n¿Está seguro de que desea modificar el salario?\n"
                + " [1] Sí\n"
                + " [0] No");
        int op = Validaciones.validaMenu(0, 1);
        if (op == 1) {
            emp.setSalario(precio);
            System.out.println("\nOperación realizada con éxito");
        } else {
            System.out.println("\nOperación cancelada\n");
        }

        session.beginTransaction();
        if (emp != null) {
            session.saveOrUpdate(emp);
        }
        session.getTransaction().commit();
        session.close();
    }

    public static void modificaComision() throws IOException, ClassNotFoundException, SQLException {
        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        Empleados emp = null;
        Session session = NewHibernateUtil.getSession();
        Busquedas.consultaEmpleado();
        System.out.println("Nombre del empleado");
        String nombre = lee.readLine();
        emp = Busquedas.buscaEmpleadoNombre(nombre);
        System.out.println("Nueva comision:");
        float comision = Float.parseFloat(lee.readLine());

        System.out.println("\n¿Está seguro de que desea modificar la comision?\n"
                + " [1] Sí\n"
                + " [0] No");
        int op = Validaciones.validaMenu(0, 1);
        if (op == 1) {
            emp.setComision(comision);
            System.out.println("\nOperación realizada con éxito");
        } else {
            System.out.println("\nOperación cancelada\n");
        }

        session.beginTransaction();
        if (emp != null) {
            session.saveOrUpdate(emp);
        }
        session.getTransaction().commit();
        session.close();
    }
 public static void modificaLocalildad() throws IOException, ClassNotFoundException, SQLException {
        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        Departamentos dep = null;
        Session session = NewHibernateUtil.getSession();
        Busquedas.consultaDepartamentos();
        System.out.println("Nombre del departamento");
        String nombre = lee.readLine();
        dep=Busquedas.buscaDepartamentoNombre(nombre);
        System.out.println("Nueva localidad:");
        String localidad=lee.readLine();

        System.out.println("\n¿Está seguro de que desea modificar la localidad?\n"
                + " [1] Sí\n"
                + " [0] No");
        int op = Validaciones.validaMenu(0, 1);
        if (op == 1) {
            dep.setLocalidad(localidad);
            System.out.println("\nOperación realizada con éxito");
        } else {
            System.out.println("\nOperación cancelada\n");
        }

        session.beginTransaction();
        if (dep != null) {
            session.saveOrUpdate(dep);
        }
        session.getTransaction().commit();
        session.close();
    }
}
