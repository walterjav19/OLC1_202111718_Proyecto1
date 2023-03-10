
package Estructuras;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;

public class Arbol {
        
    public NodeArbol raiz;
    
    public Arbol(NodeArbol raiz) {
        this.raiz = raiz;
    }
    
    public void calcularPrimerosYUltimos(NodeArbol nodo) {
    if (nodo.hijos.size() == 0) { // Es una hoja
        nodo.primeros.add(nodo.id);
        nodo.ultimos.add(nodo.id);
    } else { // Es un nodo interno
        for (NodeArbol hijo : nodo.hijos) {
            calcularPrimerosYUltimos(hijo);
        }
        if (nodo.token.equals("|")) {
            nodo.primeros.addAll(nodo.hijos.get(0).primeros);
            nodo.primeros.addAll(nodo.hijos.get(1).primeros);
            nodo.ultimos.addAll(nodo.hijos.get(0).ultimos);
            nodo.ultimos.addAll(nodo.hijos.get(1).ultimos);
            Collections.sort(nodo.primeros);
            Collections.sort(nodo.ultimos);
        } else if (nodo.token.equals(".")) {
            nodo.primeros.addAll(nodo.hijos.get(0).primeros);
            if (nodo.hijos.get(0).anulable) {
                nodo.primeros.addAll(nodo.hijos.get(1).primeros);
            }
            nodo.ultimos.addAll(nodo.hijos.get(1).ultimos);
            if (nodo.hijos.get(1).anulable) {
                nodo.ultimos.addAll(nodo.hijos.get(0).ultimos);
            }
            Collections.sort(nodo.primeros);
            Collections.sort(nodo.ultimos);
        } else if (nodo.token.equals("*")) {
            nodo.primeros.addAll(nodo.hijos.get(0).primeros);
            nodo.ultimos.addAll(nodo.hijos.get(0).ultimos);
            Collections.sort(nodo.primeros);
            Collections.sort(nodo.ultimos);
        }else if (nodo.token.equals("?")) {
            nodo.primeros.addAll(nodo.hijos.get(0).primeros);
            nodo.ultimos.addAll(nodo.hijos.get(0).ultimos);
            Collections.sort(nodo.primeros);
            Collections.sort(nodo.ultimos);
        }else if(nodo.token.equals("+")){
            nodo.primeros.addAll(nodo.hijos.get(0).primeros);
            nodo.ultimos.addAll(nodo.hijos.get(0).ultimos);
            Collections.sort(nodo.primeros);
            Collections.sort(nodo.ultimos);
        }
    }
}

    
    public void asignarAnulable(NodeArbol nodo) {
    if (nodo.hijos.size() == 0) { // Es una hoja
        if (nodo.token.equals("ε")) {
            nodo.anulable = true;
        } else {
            nodo.anulable = false;
        }
    } else { // Es un nodo interno
        for (NodeArbol hijo : nodo.hijos) {
            asignarAnulable(hijo);
        }
        if (nodo.token.equals("|")&& nodo.hijos.size()!=0) {
            nodo.anulable = nodo.hijos.get(0).anulable || nodo.hijos.get(1).anulable;
        } else if (nodo.token.equals(".")&& nodo.hijos.size()!=0) {
            nodo.anulable = nodo.hijos.get(0).anulable && nodo.hijos.get(1).anulable;
        } else if (nodo.token.equals("*")&& nodo.hijos.size()!=0) {
            nodo.anulable = true;
        }else if (nodo.token.equals("?")&& nodo.hijos.size()!=0) {
            nodo.anulable = true;
        }else if(nodo.token.equals("+")&& nodo.hijos.size()!=0){
            nodo.anulable = nodo.hijos.get(0).anulable;
        }
        else {
            nodo.anulable = false;
        }
    }
}


     public void GraficarSintactico(String i){
        String grafica = "digraph Arbol_Sintactico{\n  bgcolor = \"#A3F0CF\"\n" +
"node [shape=Mrecord style =filled];\n" + GraficaNodos(this.raiz, "0") + "\n\n}";        
        GenerarDot(grafica, i);
    }
    
    public Boolean ValidarCadena(String input){
        // Compilar la expresión regular
        Pattern pattern = Pattern.compile(this.raiz.Expresion);

        // Crear un Matcher para buscar la expresión regular en la cadena de entrada
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return true;
        }
        
        return false;
        
    }
    
    
     
     
    private String GraficaNodos(NodeArbol nodo, String i){
        int k=0; 
        String r = "";
        String nodoTerm = nodo.token;
        if(!nodoTerm.equals("\\\\\\\"")){
           nodoTerm = nodoTerm.replace("\"", "");
        }
        if (nodoTerm.equals("|")){
            nodoTerm="\\"+nodoTerm;
        }
        String a="";
        if (nodo.anulable){
            a="anulable";
        }else{
            a="No anulable";
        }
        String ultimos="";
        String primeros="";
        if(nodo.primeros.size()!=0){
            for(int n=0;n<nodo.primeros.size();n++){     
                if(n!=nodo.primeros.size()-1){
                    primeros+=String.valueOf(nodo.primeros.get(n))+",";
                }else{
                    primeros+=String.valueOf(nodo.primeros.get(n));
                }
            }
        }
        
        
        if(nodo.ultimos.size()!=0){
            for(int n=0;n<nodo.ultimos.size();n++){     
                if(n!=nodo.ultimos.size()-1){
                    ultimos+=String.valueOf(nodo.ultimos.get(n))+",";
                }else{
                    ultimos+=String.valueOf(nodo.ultimos.get(n));
                }
            }
        }
        
        if(nodo.id!=-1){
            r= "node" + i + "[label=\"{{["+primeros+"]|"+nodoTerm+"|["+ultimos+"]}|{"+a+"|"+String.valueOf(nodo.id)+"}}\"];\n";
        }else{
            r= "node" + i + "[label=\"{{["+primeros+"]|"+nodoTerm+"|["+ultimos+"]}|{"+a+"|-}}\"];\n";
        }
        
   
        for(int j =0 ; j<=nodo.hijos.size()-1; j++){
            r = r + "node" + i + " -> node" + i + k + "\n";
       
            r= r + GraficaNodos(nodo.hijos.get(j), ""+i+k);
            k++;
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
