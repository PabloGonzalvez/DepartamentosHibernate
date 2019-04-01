/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departamentoshibernate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author a16pablogb
 */
public class Menu {

    public static byte menu() throws IOException {
        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Seleccione una opcion \n"
                + "1--Altas \n"
                + "2--Bajas \n"
                + "3--Consultas \n"
                + "4--Modificaciones \n"
                + "0--Salir");
        byte op = Byte.parseByte(lee.readLine());

        return op;
    }
}
