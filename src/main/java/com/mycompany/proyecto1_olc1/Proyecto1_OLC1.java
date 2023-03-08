

package com.mycompany.proyecto1_olc1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Proyecto1_OLC1 {
    public static void main(String[] args) {
        /*String regex = "\"[a-z]\""; // expresión regular a buscar
        String input = "\"a\""; // cadena en la que buscar

        // Compilar la expresión regular
        Pattern pattern = Pattern.compile(regex);

        // Crear un Matcher para buscar la expresión regular en la cadena de entrada
        Matcher matcher = pattern.matcher(input);

        // Si la expresión regular hace match con la cadena de entrada, se imprime un mensaje
        if (matcher.matches()) {
            System.out.println("La cadena hace match con la expresión regular");
        } else {
            System.out.println("La cadena no hace match con la expresión regular");
        }*/
        String a="a\\n";
        //a=a.replace("\\n", "\n");
  
        System.out.println(a);
 
    }
}
