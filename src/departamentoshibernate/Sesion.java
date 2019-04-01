/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departamentoshibernate;

import java.sql.SQLException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Pablo
 */
public class Sesion {

    public static Session iniciosesionFactory(SessionFactory sessionFactory) throws ClassNotFoundException, SQLException {

        Session session = sessionFactory.openSession();
        if (session != null) {

        } else {
            System.out.println("Fallo en el inicio de sesión");
        }
        return session;
    }

}
