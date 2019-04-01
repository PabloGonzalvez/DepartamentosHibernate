/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departamentoshibernate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import org.hibernate.SessionFactory;

/**
 *
 * @author a16pablogb
 */
public class DepartamentosHibernate {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, ParseException {
        String url = "jdbc:mysql://localhost:3307?user=root&password=usbw";
        Statement sentencia = null;
        Connection conexion = null;
        
        try {// Establecer conexion
            conexion = DriverManager.getConnection(url);
            sentencia = conexion.createStatement();
            sentencia.execute("CREATE DATABASE IF NOT EXISTS DepartamentosEmpleadosH;");
            sentencia.execute("USE DepartamentosEmpleadosH;");
            Tablas.crearTablas(sentencia);
            
        } catch (SQLException e) {
            System.out.println("\nAVISO: Ha surgido un problema a la hora de conectar con la base de datos:\n" + e);
        }
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        
        byte op = 0;
        
        do {
            op = Menu.menu();
            
            switch (op) {
                
                case 1:
                    Altas.menuAltas(sessionFactory);
                    break;
                
                case 2:
                    Bajas.menuBajas(sessionFactory);
                    break;
                
                case 3:
                    Consultas.menuConsultas(sessionFactory);
                    break;
                
                case 4:
                    Modificaciones.menuModificaciones(sessionFactory);
                    break;
                
            }
            
        } while (op != 0);
        System.out.println("Hasta Luego");
    }
    
}
