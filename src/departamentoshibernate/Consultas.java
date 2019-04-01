/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departamentoshibernate;

import Pojos.Departamentos;
import Pojos.Empleados;
import static departamentoshibernate.Bajas.bajaDepartamento;
import static departamentoshibernate.Bajas.bajaEmpleado;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Pablo
 */
public class Consultas {

    public static void menuConsultas(SessionFactory sessionFactory) throws IOException, ClassNotFoundException, SQLException {

        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Seleccione una opcion \n"
                + "1--Consulta de empleados por departamento \n"
                + "2--Consulta de departamento por empleado \n"
                + "0--Salir");
        int op = Validaciones.validaMenu(0, 2);

        switch (op) {
            case 1:

                consulta1();

                break;
            case 2:

                consulta2();

                break;
            case 0:
                break;

        }

    }

    public static void consulta1() throws ClassNotFoundException, SQLException, IOException {
        Session session = NewHibernateUtil.getSession();
        String aux = "";

        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        Busquedas.consultaDepartamentos();
        System.out.println("Introduce el nombre del departamento de que quieres saber los empleados");
        String nombre = lee.readLine();
        try {
            int dep = (int) session.createQuery("SELECT nu_dep FROM " + Departamentos.class.getName() + " WHERE nombre = '" + nombre + "'").uniqueResult();

            List<Object> empleados = session.createQuery("FROM " + Empleados.class.getName() + " WHERE departamento = " + dep).list();
            if (empleados.isEmpty()) {
                System.out.println("Ese departamento no tiene asignado ningun empleado aun");
            } else {
                System.out.println(""
                        + "                              INFORMACION DE LOS EMPLEADOS EMPLEADOS                                        \n"
                        + "----------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("|  %-9s  |  %-17s  |  %-10s  |  %-10s   |  %-10s  |  %-10s  |  %-25s  |  %-10s   %n", "DNI", "NOMBRE","OFICIO", "SALARIO", "COMISION","FECHA ALTA","DIRECCION","MAT VEHICULO");

                for (Object emp : empleados) {

                    System.out.printf("|  %-9s  |  %-17s  |  %-10s  |  %-10s   |  %-10s  |  %-10s  |  %-25s  |  %-10s      %n", ((Empleados) emp).getDni(), ((Empleados) emp).getNombre(),((Empleados)emp).getOficio(),
                     ((Empleados) emp).getSalario(), ((Empleados) emp).getComision(),((Empleados)emp).getFechaAlta(),((Empleados)emp).getDireccion(),((Empleados)emp).getVehiculo().getMatricula(), aux);
                }
            }
        } catch (Exception e) {
            System.out.println("Introduzca un nombre valido");
        }

        session.close();

    }

    public static void consulta2() throws IOException, ClassNotFoundException, SQLException {
        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        Empleados emp;
        
        Busquedas.consultaEmpleado();
        
        System.out.println("Introduce el dni del empleado para saber los datos de su departamento");
        String dni = lee.readLine();
        Session session = NewHibernateUtil.getSession();
        try{
        emp = (Empleados) session.createQuery("FROM " + Empleados.class.getName() + " WHERE dni = '" + dni + "'").uniqueResult();
        if(emp==null){System.out.println("Empleado no encontrado");}
        else{
        System.out.println(""
                + "                        INFORMACÓN DEL DEPARTAMENTO                           \n"
                + "-------------------------------------------------------------------------");
        System.out.printf("|  %-9s  |  %-15s         |  %-10s %n", "Numero", "NOMBRE", "LOCALIDAD");
        System.out.printf("|  %-9s  |  %-15s         |  %-10s %n", emp.getDepartamento().getNu_dep(), emp.getDepartamento().getNombre(), emp.getDepartamento().getLocalidad());
        System.out.println("-------------------------------------------------------------------------\n");
        }
        }catch(Exception e){
            System.out.println("Introduzca un dni valido");}
        session.close();
    }
}
