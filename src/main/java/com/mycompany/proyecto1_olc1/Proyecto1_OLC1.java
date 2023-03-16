

package com.mycompany.proyecto1_olc1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Proyecto1_OLC1 {
    public static void main(String[] args) {
        List<Integer> siguiente_asociado=new ArrayList<Integer>();
        siguiente_asociado.add(1);
        siguiente_asociado.add(2);
        List<Integer> siguiente_asociado2=new ArrayList<Integer>();
        siguiente_asociado2.add(1);
        siguiente_asociado2.add(2);
        
        System.out.println(siguiente_asociado.equals(siguiente_asociado2));
    }
}
