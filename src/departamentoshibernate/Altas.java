/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departamentoshibernate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import org.hibernate.Session;
import Pojos.Departamentos;
import Pojos.Empleados;
import Pojos.Vehiculos;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @author a16pablogb
 */
public class Altas {

    public static void menuAltas(SessionFactory sessionFactory) throws IOException, ClassNotFoundException, SQLException, ParseException {
        byte op = 0;
        do {
            BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Seleccione una opcion \n"
                    + "1--Alta Departamento \n"
                    + "2--Alta Empleado \n"
                    + "0--Salir");
            op = Byte.parseByte(lee.readLine());

            switch (op) {
                case 1:
                    altaDepartamento(sessionFactory);
                    break;
                case 2:
                    altaEmpleado(sessionFactory);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no válida");
                    break;

            }

        } while (op != 0);

    }

    public static void altaDepartamento(SessionFactory sessionFactory) throws IOException, ClassNotFoundException, SQLException {
        Session session = NewHibernateUtil.getSession();
        Departamentos dep = null;
        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Introduzca nombre del departamento");
        String nombre = lee.readLine();
        System.out.println("Introduzca la localidad del departamento");
        String localidad = lee.readLine();

        dep = new Departamentos(nombre, localidad);

        session.beginTransaction();
        if (dep != null) {
            session.saveOrUpdate(dep);
        }
        session.getTransaction().commit();
        session.close();

    }

    public static void altaEmpleado(SessionFactory sessionFactory) throws ClassNotFoundException, SQLException, IOException, ParseException {

        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        Departamentos d;
        Empleados emp;
        Vehiculos veh;
        List<Object> listado = Busquedas.buscaTuplas(Departamentos.class);
        if (listado.isEmpty()) {
            System.out.println("ERROR: Todavía no se ha registrado ningún Departamento\n");
        } else {

            System.out.println("Introduzca el dni del empleado(7 Digitos y una Letra)");
            String dni = Validaciones.validaDni();
            System.out.println("Introduzca nombre del empleado");
            String nombre = lee.readLine();
            System.out.println("Introduzca oficio del empleado");
            String oficio = lee.readLine();
            System.out.println("Introduzca direccion del empleado");
            String direccion = lee.readLine();
            System.out.println("Introduzca la fecha de alta del empleado (dd/MM/yyyy");
            String fecha = lee.readLine();

            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date date = sdf1.parse(fecha);
            java.sql.Date fechaSql = new java.sql.Date(date.getTime());
            
            //Datos del cliente
            System.out.println("Indique el salario del empleado");
            float salario = Float.parseFloat(lee.readLine());
            System.out.println("Indique la comision del empleado");
            float comision = Float.parseFloat(lee.readLine());
            System.out.println("Indique el numero de departamento al que  asigna el empleado");
            Busquedas.consultaDepartamentos();
            System.out.println("NUMERO de departamento");
            int numero = Integer.parseInt(lee.readLine());
            d = Busquedas.buscaDepartamento(numero);
            
            //Datos del Vehiculo
            System.out.println("Indique la matricula del vehiculo el empleado(7 Caracteres)");
            String matricula = Validaciones.validaMatricula();
            System.out.println("Indique la marca del vehiculo");
            String marca = lee.readLine();
            System.out.println("Introduce el modelo del vehiculo");
            String modelo = lee.readLine();
            System.out.println("Introduzca la fecha de compra del vehiculo (dd/MM/yyyy");
            String fecha2 = lee.readLine();

            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date date2 = sdf2.parse(fecha2);
            java.sql.Date fechaSql2 = new java.sql.Date(date2.getTime());

            Session session = NewHibernateUtil.getSession();
            emp = new Empleados(dni, nombre, oficio, direccion, fechaSql, salario, comision, d);
            emp.setDepartamento(d);

            veh = new Vehiculos(dni, marca, matricula, modelo, fechaSql2);
            emp.setVehiculo(veh);
            veh.setEmpleado(emp);

            session.beginTransaction();
            if (emp != null) {

                session.save(emp);
            }

            session.getTransaction().commit();

            session.beginTransaction();
            if (veh != null) {

                session.save(veh);

            }
            session.getTransaction().commit();
            session.close();
        }

    }
}
