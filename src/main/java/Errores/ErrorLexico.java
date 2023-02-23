
package Errores;

import java.io.FileWriter;
import java.io.PrintWriter;





public class ErrorLexico {
    public String Lexema;
    public String Descripcion="Caracter No Reconocido por el Lenguaje";
    public int Linea;
    public int Columna;
    
    public static void escribir(String path,String TextField2){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(path);
            pw = new PrintWriter(fichero);
            String texto=TextField2;
               
            pw.println(texto);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
}
    
    
    public ErrorLexico(String Lexema, int Linea, int Columna) {
        this.Lexema = Lexema;
        this.Linea = Linea;
        this.Columna = Columna;
       
    }
    

    

    
    
    
}
