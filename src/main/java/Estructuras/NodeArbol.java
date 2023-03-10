
package Estructuras;


import java.util.ArrayList;

public class NodeArbol {
    public String token;
    public String lexema,Expresion;
    public int id; 
    public NodeArbol hijoIzq;
    public NodeArbol hijoDer;
    public Boolean anulable=false;
    
    
    public ArrayList<NodeArbol> hijos = new ArrayList<NodeArbol>();
    public ArrayList<Integer> primeros = new ArrayList<Integer>();
    public ArrayList<Integer> ultimos = new ArrayList<Integer>();

    
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
   
    public void Anulable(){
        this.anulable=true;
    }
    public void NoAnulable(){
        this.anulable=false;
    }
}
