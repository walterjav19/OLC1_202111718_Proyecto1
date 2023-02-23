
package Generador;

import java.io.File;


public class G_Lexer {
    public static void main(String[] args) {
        String ruta="src/main/java/Analizadores/Lexer.jflex";
        generarLexer(ruta);
    }
    
    public static void generarLexer(String ruta){
        File archivo=new File(ruta);
        jflex.Main.generate(archivo);
    }
}
