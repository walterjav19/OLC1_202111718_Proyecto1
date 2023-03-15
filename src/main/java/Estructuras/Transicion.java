
package Estructuras;


public class Transicion {
    public String letra;
    public Estado destino;

    public Transicion(String letra, Estado destino) {
        this.letra = letra;
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "\nCon el simbolo " + letra + " se mueve a" + destino.nombre + "\n";
    }
    
    
    
   
}
