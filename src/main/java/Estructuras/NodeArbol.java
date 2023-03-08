
package Estructuras;

import java.util.ArrayList;

public class NodeArbol {
    public String token;
    public String lexema,Expresion;
    public int id; 
    public NodeArbol hijoIzq;
    public NodeArbol hijoDer;
    public Boolean anulable;
    
    public ArrayList<NodeArbol> hijos = new ArrayList<NodeArbol>();
    
    public NodeArbol(String token, String lexema,String Expresion, int id, NodeArbol hijoIzq, NodeArbol hijoDer){
        this.token = token;
        this.lexema = lexema;
        this.Expresion=Expresion;
        this.hijoIzq = hijoIzq;
        this.hijoDer = hijoDer;
        
        if(hijoIzq != null){
        this.hijos.add(hijoIzq);
        }
        if(hijoDer != null){
            this.hijos.add(hijoDer);
        }
        
    }
    
    
}
