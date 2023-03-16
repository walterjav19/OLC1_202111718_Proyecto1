
package Estructuras;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Estado {
    public String nombre;
    public List<Transicion> Transiciones=new ArrayList<Transicion>();
    public List<Integer> siguiente_asociado;
    public Boolean inicial;
    public Boolean fin;

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
    
    public void addTransicion(String simbolo, Estado destino) {
        Transicion t=new Transicion(simbolo,destino);
        Transiciones.add(t);
    }

    @Override
    public String toString() {
        return "Estado: "+ nombre + " " + siguiente_asociado;
    }
    
    
   
}
