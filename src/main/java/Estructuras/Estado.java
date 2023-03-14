
package Estructuras;

import java.util.HashMap;
import java.util.List;


public class Estado {
    public String nombre;
    public List<Integer> siguiente_asociado;
    public Boolean inicial;
    public Boolean fin;
    public HashMap<String, Estado> Transiciones = new HashMap<Integer, Estado>();   

    public Estado(String nombre, List<Integer> siguiente_asociado) {
        this.nombre = nombre;
        this.siguiente_asociado = siguiente_asociado;
    }
    
    public void esInicio(){
        this.inicial=true;
    }
    
    public void esFin(){
        this.fin=false;
    }
    
    public void AgregarTransicion(String terminal,Estado estado){
        this.Transiciones.put(terminal, estado);
    }
    
    
    
   
}
