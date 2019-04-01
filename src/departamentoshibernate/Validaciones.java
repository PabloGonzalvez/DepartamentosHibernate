/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departamentoshibernate;

import Pojos.Empleados;
import Pojos.Vehiculos;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import org.hibernate.Session;

/**
 *
 * @author a16pablogb
 */
public class Validaciones {

    public static String validaDni() throws IOException {
        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        Session session = NewHibernateUtil.getSession();
        Empleados emp = null;
        boolean er = false;
        String dni = null;

        do {
            er = false;
            dni = lee.readLine();
            emp = (Empleados) session.createQuery("FROM " + Empleados.class.getName() + " WHERE dni = '" + dni + "'").uniqueResult();
            if (emp == null) {
                try {
                    if (dni.length() != 9) {
                        er = true;
                        throw new MisExcepciones("\nERROR: Formato no válido\n\nVuelva a intentarlo:");
                    }
                    if (!dni.substring(0, 8).matches("[0-9]*")) {
                        er = true;
                        throw new MisExcepciones("\nERROR: Formato no válido\n\nVuelva a intentarlo:");
                    }
                    if (!dni.substring(8).matches("[A-Za-z]")) {
                        er = true;
                        throw new MisExcepciones("\nERROR: Formato no válido\n\nVuelva a intentarlo:");
                    }
                } catch (MisExcepciones e) {
                    System.out.println(e.getMessage());
                }
            } else {
                er = true;
                System.out.println("\nERROR: DNI ya registrado\n\nVuelva a intentarlo:");
            }
        } while (er == true);
        session.close();
        return dni;
    }

    public static String validaMatricula() throws IOException {
        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        Session session = NewHibernateUtil.getSession();
        Vehiculos V = null;
        String matricula = null;
        boolean er = false;

        do {
            er = false;
            matricula = lee.readLine();
            V = (Vehiculos) session.createQuery("FROM " + Vehiculos.class.getName() + " WHERE matricula = '" + matricula + "'").uniqueResult();
            if (V == null) {
                try {
                    if (matricula.length() != 7) {
                        er = true;
                        throw new MisExcepciones("\nERROR: Formato no válido\n\nVuelva a intentarlo:");
                    }
                   
                } catch (MisExcepciones e) {
                    System.out.println(e.getMessage());
                }
            } else {
                er = true;
                System.out.println("\nERROR: Matricula ya registrada\n\nVuelva a intentarlo:");
            }
        } while (er == true);

        return matricula;
    }

    public static int validaMenu(int min, int max) throws IOException {
        int op = 0;
        boolean er = false;
        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        do {
            er = false;
            try {
                op = Integer.parseInt(lee.readLine());
                if (op < min || op > max) {
                    er = true;
                    System.out.println("\nERROR: Formato no válido\n\nVuelva a intentarlo:");
                }
            } catch (NumberFormatException e) {
                er = true;
                System.out.println("\nERROR: Formato no válido\n\nVuelva a intentarlo:");
            }
        } while (er == true);

        return op;
    }
}
