/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departamentoshibernate;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author a16pablogb
 */
public class Tablas {

    public static void crearTablas(Statement sentencia) {
        try {

            sentencia.execute(""
                    + "CREATE TABLE IF NOT EXISTS departamentos("
                    + " nu_dep INT(3) ZEROFILL AUTO_INCREMENT NOT NULL,"
                    + " nombre  VARCHAR(30) NOT NULL,"
                    + " localidad  VARCHAR(30) NOT NULL,"
                    + " PRIMARY KEY(nu_dep)"
                    + ")"
                    + "ENGINE INNODB;");

            sentencia.execute(""
                    + " CREATE TABLE IF NOT EXISTS empleados("
                    + " dni  CHAR(9) NOT NULL,"
                    + " nombre  VARCHAR(30) NOT NULL,"
                    + " oficio VARCHAR(15) NOT NULL,"
                    + " direccion VARCHAR(50) NOT NULL,"
                    + " fechaAlta DATE  NOT NULL,"
                    + " salario  FLOAT NOT NULL,"
                    + " comision FLOAT NOT NULL,"
                    + " departamento int(3) ZEROFILL NOT NULL,"
                    + " PRIMARY KEY(dni),"
                    + " CONSTRAINT FK_DEPARTAMENTO"
                    + "     FOREIGN KEY (departamento) REFERENCES departamentos (nu_dep)"
                    + "                   ON DELETE CASCADE ON UPDATE CASCADE,"
                    + " INDEX FK_DEPARTAMENTO(departamento) "
                    + ")"
                    + "ENGINE INNODB;");

            sentencia.execute(""
                    + "CREATE TABLE IF NOT EXISTS vehiculos("
                    + " dni CHAR(9) NOT NULL,"
                    + " marca  VARCHAR(30) NOT NULL,"
                    + " matricula char(7) NOT NULL,"
                    + " modelo  VARCHAR(30) NOT NULL,"
                    + " fechaCompra DATE NOT NULL,"
                    + " PRIMARY KEY(dni),"
                    + " CONSTRAINT FK_EMPLEADO"
                    + " FOREIGN KEY (dni) REFERENCES empleados (dni)"
                    + "             ON DELETE CASCADE ON UPDATE CASCADE,"
                    + "INDEX FK_EMPLEADO(dni),"
                    + " UNIQUE INDEX(matricula)"
                    + ")"
                    + "ENGINE INNODB;");

        } catch (SQLException e) {
            System.out.println(e);

        }
    }
}
