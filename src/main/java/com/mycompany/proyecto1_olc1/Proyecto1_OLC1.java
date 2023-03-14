

package com.mycompany.proyecto1_olc1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Proyecto1_OLC1 {
    public static void main(String[] args) {
        List<Integer> lista1=new ArrayList<Integer>();
        lista1.add(1);
        lista1.add(2);
        lista1.add(3);

        
        List<Integer> lista2=new ArrayList<Integer>();
        lista2.add(1);
        lista2.add(2);
        lista2.add(3);
        lista2.add(3);
        
        System.out.println(lista1.equals(lista2));
    }
}
