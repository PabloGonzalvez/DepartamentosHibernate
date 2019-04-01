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
public class Bajas {

    public static void menuBajas(SessionFactory sessionFactory) throws IOException, ClassNotFoundException, SQLException {

        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Seleccione una opcion \n"
                + "1--Baja Departamento \n"
                + "2--Baja Empleado \n"
                + "0--Salir");
        int op = Validaciones.validaMenu(0, 2);

        switch (op) {
            case 1:
                List<Object> listado = Busquedas.buscaTuplas(Departamentos.class);
                if (listado.isEmpty()) {
                    System.out.println("ERROR: Todavía no se ha registrado ningún departamento\n");
                } else {
                    bajaDepartamento(sessionFactory);
                }
                break;
            case 2:
                listado = Busquedas.buscaTuplas(Empleados.class);
                if (listado.isEmpty()) {
                    System.out.println("ERROR: Todavía no se ha registrado ningún empleado\n");
                } else {
                    bajaEmpleado(sessionFactory);
                }
                break;
            case 0:
                break;

        }

    }

    public static void bajaDepartamento(SessionFactory sessionFactory) throws ClassNotFoundException, SQLException, IOException {
        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        Session session = NewHibernateUtil.getSession();
        Departamentos dep = null;

        Busquedas.consultaDepartamentos();
        System.out.println("Numero del departamento a eliminar");
        int numero = Integer.parseInt(lee.readLine());

        dep = Busquedas.buscaDepartamento(numero);
        System.out.println("\n¿Está seguro de que desea eliminar el departamento seleccionado?\n"
                + " [1] Sí\n"
                + " [0] No");
        int op = Validaciones.validaMenu(0, 1);

        if (op == 1) {
            session = NewHibernateUtil.getSession();
            session.beginTransaction();
            session.delete(dep);
            session.getTransaction().commit();
            session.close();
            System.out.println("\nEliminación completada con éxito\n");
        } else {
            System.out.println("\nOperación cancelada\n");
        }

    }

    public static void bajaEmpleado(SessionFactory sessionFactor) throws IOException, ClassNotFoundException, SQLException {
        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        Empleados emp = null;
        Session session = NewHibernateUtil.getSession();
        Busquedas.consultaEmpleado();
        System.out.println("DNI del empleado a eliminar:");
        String dni = lee.readLine();
        emp = Busquedas.buscaEmpleado(dni);
        System.out.println("\n¿Está seguro de que desea eliminar el empleado seleccionado?\n"
                + " [1] Sí\n"
                + " [0] No");
        int op = Validaciones.validaMenu(0, 1);

        if (op == 1) {
            session = NewHibernateUtil.getSession();
            session.beginTransaction();
            session.delete(emp);
            session.getTransaction().commit();
            session.close();
            System.out.println("\nEliminación completada con éxito\n");
        } else {
            System.out.println("\nOperación cancelada\n");
        }
    }
}
