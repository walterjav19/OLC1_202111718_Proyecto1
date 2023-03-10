

package com.mycompany.proyecto1_olc1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Proyecto1_OLC1 {
    public static void main(String[] args) {
        HashMap<Integer, List<Integer>> Siguientes = new HashMap<Integer, List<Integer>>();
        Siguientes.put(1,  new ArrayList<Integer>());
        Siguientes.put(2,  new ArrayList<Integer>());
        Siguientes.put(3,  new ArrayList<Integer>());
        
        //nodo 1
        Siguientes.get(1).add(2);
        Siguientes.get(1).add(3);
        Siguientes.get(1).add(4);
        
        //nodo 2
        Siguientes.get(2).add(5);
        Siguientes.get(2).add(6);
        
        //nodo 3
        Siguientes.get(3).add(7);
        
        
        for(Map.Entry<Integer, List<Integer>> entry : Siguientes.entrySet()) {
            System.out.format("Nodo: %d Siguientes: %s\n",entry.getKey(),entry.getValue().toString());
            
            
        }

    }
}
