
package Estructuras;

import java.util.List;


public class T_Siguientes {
    
    public String simbolo;
    public int nodo;
    public List<Integer> Siguientes;

    
    public T_Siguientes(String simbolo, int nodo, List<Integer> Siguientes) {
        this.simbolo = simbolo;
        this.nodo = nodo;
        this.Siguientes = Siguientes;
    }
    
    public String toString(){
        return "Simbolo: "+this.simbolo+" Nodo: "+this.nodo+" Siguientes: "+this.Siguientes.toString();
    }
    
    
    
}
