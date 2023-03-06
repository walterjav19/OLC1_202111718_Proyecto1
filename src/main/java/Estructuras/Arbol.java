
package Estructuras;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Arbol {
        
    public NodeArbol raiz;
    
    public Arbol(NodeArbol raiz) {
        this.raiz = raiz;
    }
    
     public void GraficarSintactico(String i){
  
        String grafica = "digraph Arbol_Sintactico{\n\n" + GraficaNodos(this.raiz, "0") + "\n\n}";        
        GenerarDot(grafica, i);

    }
    
    private String GraficaNodos(NodeArbol nodo, String i){
        int k=0; 
        String r = "";
        String nodoTerm = nodo.token;
     
        nodoTerm = nodoTerm.replace("\"", "");
        r= "node" + i + "[label = \"" + nodoTerm + "\"];\n";
   
        for(int j =0 ; j<=nodo.hijos.size()-1; j++){
            r = r + "node" + i + " -> node" + i + k + "\n";
       
            r= r + GraficaNodos(nodo.hijos.get(j), ""+i+k);
            k++;
        }
        
        if( !(nodo.lexema.equals("")) ){
            String nodoToken = nodo.lexema;
          
            nodoToken = nodoToken.replace("\"", "");
            r += "node" + i + "c[label = \"" + nodoToken + "\"];\n";
            r += "node" + i + " -> node" + i + "c\n";
        }
        
        return r;
    }
    
    private void GenerarDot(String cadena, String i){
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try{
            fichero = new FileWriter("C:\\Users\\USUARIO\\Documents\\NetBeansProjects\\Proyecto1_OLC1\\src\\main\\java\\Arboles\\"+"Arbol_Sintactico"+i+".dot");
            escritor = new PrintWriter(fichero);
            escritor.println(cadena);
            escritor.close();
            fichero.close();
            reportar(i);
        } catch (Exception e) {
            System.out.println("error en generar dot");
        }
    }
    
    public void reportar(String i) throws IOException{
        
        String file_input_path = "Arbol_Sintactico"+i+".dot";
        
        String file_get_path =  "C:\\Users\\USUARIO\\Documents\\NetBeansProjects\\Proyecto1_OLC1\\src\\main\\java\\Arboles\\" +"Arbol_Sintactico"+i+".png" ;
        try {
        String rutaArchivo = "C:\\Users\\USUARIO\\Documents\\NetBeansProjects\\Proyecto1_OLC1\\src\\main\\java\\Arboles\\" + file_input_path;
        String comando = "dot -Tpng " + rutaArchivo + " -o " + file_get_path;
        Runtime.getRuntime().exec(comando);
        } catch (IOException e) {
            System.out.println("Error al generar la imagen: " + e.getMessage());
        }
      
    }
}
