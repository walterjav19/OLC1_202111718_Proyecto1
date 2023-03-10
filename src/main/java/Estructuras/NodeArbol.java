
package Estructuras;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NodeArbol {
    public String token;
    public String lexema,Expresion,NombreExpresion;
    public int id; 
    public NodeArbol hijoIzq;
    public NodeArbol hijoDer;
    public Boolean anulable=false;
    
    
    public ArrayList<NodeArbol> hijos = new ArrayList<NodeArbol>();
    public ArrayList<Integer> primeros = new ArrayList<Integer>();
    public ArrayList<Integer> ultimos = new ArrayList<Integer>();
    public ArrayList<String> token_leaves = new ArrayList<String>();
    
    public NodeArbol(String token, String lexema,String Expresion, int id, NodeArbol hijoIzq, NodeArbol hijoDer){
        this.token = token;
        this.lexema = lexema;
        this.Expresion=Expresion;
        this.id=id;
        this.hijoIzq = hijoIzq;
        
        
        if(hijoIzq != null){
        this.hijos.add(hijoIzq);
        }
        if(hijoDer != null){
            this.hijos.add(hijoDer);
        }
        
        
        
        
    }
    
    public void SetNexpresion(String nombre){
        this.NombreExpresion=nombre;
    }
    
    public void Anulable(){
        this.anulable=true;
    }
    public void NoAnulable(){
        this.anulable=false;
    }
}
