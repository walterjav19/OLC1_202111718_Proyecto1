
package Estructuras;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Arbol {
        
    public NodeArbol raiz;
    public HashMap<Integer, List<Integer>> Siguientes = new HashMap<Integer, List<Integer>>();//por cada hoja un nodo y cada nodo tiene un conjunto o una lista
    public ArrayList<NodeArbol> leaves = new ArrayList<NodeArbol>();
    public ArrayList<String> alfabeto = new ArrayList<String>();
    public ArrayList<T_Siguientes> t_Siguientes = new ArrayList<T_Siguientes>();
    public ArrayList<Estado> AFD = new ArrayList<Estado>();
    public Arbol(NodeArbol raiz) {
        this.raiz = raiz;
    }
    
    public void Agregar_Sim(NodeArbol nodo) {
    if (nodo.hijos.size() == 0) { // Es una hoja
        leaves.add(nodo);
        if(!alfabeto.contains(nodo.token)){
            alfabeto.add(nodo.token);
        }
    } else { // Es un nodo interno
        for (NodeArbol hijo : nodo.hijos) {
            Agregar_Sim(hijo);
        }
    }
}
    
    public void GenerarTablaSiguientes(){
        int i=0;
        Agregar_Sim(this.raiz);
        for(Map.Entry<Integer, List<Integer>> entry : Siguientes.entrySet()) {
            T_Siguientes fila=new T_Siguientes(leaves.get(i).token,entry.getKey(),entry.getValue());
            t_Siguientes.add(fila);
            i++;
        }
    }
    public void imprimirsiguientes(){
        System.out.println("============================ Siguientes ==============================================");
        for(T_Siguientes f:t_Siguientes){
            System.out.println(f.toString());
        }
        System.out.println("======================================================================================");
    }
    
    
    public void GenerarDot(){
        String cabeza="digraph T_Siguientes{\n" +
"  node [shape=plaintext]\n" +
"  mytable [style=filled fillcolor=\"#A3F0CF\" \n" +
"    label=<<table border=\"0\" cellborder=\"1\"  cellspacing=\"0\">\n" +
"    <tr><td>Simbolo</td><td>Nodo</td><td>Siguientes</td></tr>";
        String cuerpo="";
        String footer="  </table>>];\n" +
"label=\"Tabla de Siguientes de la Expresion: "+raiz.NombreExpresion+"\";}";
        for(T_Siguientes f:t_Siguientes){
            if(f.Siguientes.size()!=0){
                cuerpo+="<tr><td>"+f.simbolo+"</td><td>"+String.valueOf(f.nodo)+"</td><td>"+f.Siguientes.toString()+"</td></tr>\n";
            }else{
                cuerpo+="<tr><td>"+f.simbolo+"</td><td>"+String.valueOf(f.nodo)+"</td><td>------</td></tr>\n";
            }
        }
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try{
            fichero = new FileWriter("C:\\Users\\USUARIO\\Documents\\NetBeansProjects\\Proyecto1_OLC1\\src\\main\\java\\Graphviz\\"+"Siguiente_"+raiz.NombreExpresion+".dot");
            escritor = new PrintWriter(fichero);
            escritor.println(cabeza+cuerpo+footer);
            escritor.close();
            fichero.close();
        } catch (Exception e) {
            System.out.println("error en generar dot");
        }
        
                
        String file_input_path = "Siguiente_"+raiz.NombreExpresion+".dot";
        
        String file_get_path =  "C:\\Users\\USUARIO\\Documents\\NetBeansProjects\\Proyecto1_OLC1\\src\\main\\java\\Siguientes_202111718\\" +"Siguiente_"+raiz.NombreExpresion+".png" ;
        try {
        String rutaArchivo = "C:\\Users\\USUARIO\\Documents\\NetBeansProjects\\Proyecto1_OLC1\\src\\main\\java\\Graphviz\\" + file_input_path;
        String comando = "dot -Tpng " + rutaArchivo + " -o " + file_get_path;
        Runtime.getRuntime().exec(comando);
        } catch (IOException e) {
            System.out.println("Error al generar la imagen: " + e.getMessage());
        }
    }

    public List<Integer> BuscarSiguiente(int i){
        for(T_Siguientes fila:t_Siguientes){
            if(fila.nodo==i){
                return fila.Siguientes;
            }
        }
        return null;
    }


    
public List<NodeArbol> seleccionarHojas(List<Integer> valores, String letra) {
    List<NodeArbol> hojasSeleccionadas = new ArrayList<>();
    for (NodeArbol hoja : leaves) {
        if (hoja.token.equals(letra) && valores.contains(hoja.id)) {
            hojasSeleccionadas.add(hoja);
        }
    }
    return hojasSeleccionadas;
}


    
    public void CalcularTransiciones(){
        Estado e=new Estado("S0",raiz.primeros);
        System.out.println(e.toString());
        List<List<Integer>> estad=new ArrayList<List<Integer>>();
        estad.add(raiz.primeros);
        
        
        //prueba
        for(String letra: alfabeto){
            System.out.println("Transiciones al simbolo: "+letra);
            List<Integer> conjunto=new ArrayList<Integer>();
            List<NodeArbol> n_c=seleccionarHojas(estad.get(0),letra);
            for(NodeArbol nod:n_c){
                conjunto.addAll(BuscarSiguiente(nod.id));
            }
            System.out.println(conjunto.toString());
        }
        
    
 }   
    
    public void calcularSiguientes(NodeArbol nodo) {
    if (nodo.hijos.size() == 0) { // Es una hoja
        this.Siguientes.put(nodo.id,new ArrayList<Integer>());
    } else { // Es un nodo interno
        for (NodeArbol hijo : nodo.hijos) {
            calcularSiguientes(hijo);
        }
        if (nodo.token.equals("*") || nodo.token.equals("+")) {
            for (int i = 0; i < nodo.ultimos.size(); i++) {
                this.Siguientes.get(nodo.ultimos.get(i)).addAll(nodo.primeros);
            }
        }else if(nodo.token.equals(".") && nodo.hijos.size()!=0){// no es hoja
            
            for(int i=0;i<nodo.hijoIzq.ultimos.size();i++){
                this.Siguientes.get(nodo.hijoIzq.ultimos.get(i)).addAll(nodo.hijos.get(1).primeros);
            }
        } 
    }
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
"node [shape=Mrecord style =filled];\nlabel =\"Arbol de la Expresion: "+this.raiz.NombreExpresion+"\"" + GraficaNodos(this.raiz, "0") + "\n\n}";        
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
            fichero = new FileWriter("C:\\Users\\USUARIO\\Documents\\NetBeansProjects\\Proyecto1_OLC1\\src\\main\\java\\Graphviz\\"+"Arbol_Sintactico"+i+".dot");
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
        
        String file_get_path =  "C:\\Users\\USUARIO\\Documents\\NetBeansProjects\\Proyecto1_OLC1\\src\\main\\java\\Arboles_202111718\\" +"Arbol_Sintactico"+i+".png" ;
        try {
        String rutaArchivo = "C:\\Users\\USUARIO\\Documents\\NetBeansProjects\\Proyecto1_OLC1\\src\\main\\java\\Graphviz\\" + file_input_path;
        String comando = "dot -Tpng " + rutaArchivo + " -o " + file_get_path;
        Runtime.getRuntime().exec(comando);
        } catch (IOException e) {
            System.out.println("Error al generar la imagen: " + e.getMessage());
        }
      
    }
}
