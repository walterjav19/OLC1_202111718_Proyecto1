
package Estructuras;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class Expresiones {
    public String Valor;
    public String Expresion;
    public String Resultado;
    
        
    public Expresiones(String Valor, String Expresion,String Resultado ){
        this.Valor = Valor;
        this.Expresion = Expresion;
        this.Resultado=Resultado;
    }
    
    public void GenerarJson(List<Expresiones> lista){
         FileWriter fichero = null;
        PrintWriter escritor = null;
        String texto="";
        try{
            fichero = new FileWriter("C:\\Users\\USUARIO\\Documents\\NetBeansProjects\\Proyecto1_OLC1\\src\\main\\java\\Salidas_202111718\\salida.json");
            escritor = new PrintWriter(fichero);
            escritor.println("[");
            int i=0;
            for(Expresiones elem:lista){
                if (lista.size()-1!=i){
                   escritor.println(elem.jsonify()+",");
                }else{
                   escritor.println(elem.jsonify());
                }
                i++;
                
            }
            escritor.println("]");
            escritor.close();
            fichero.close();
            
        } catch (Exception e) {
            System.out.println("error en generar json");
        }
        
    }
    
    public String jsonify(){
        return "  {\n" +
"    \"Valor\":"+this.Valor+",\n" +
"    \"ExpresionRegular\":\""+this.Expresion+"\",\n" +
"    \"Resultado\":"+this.Resultado+"\n"+
"  }";
    }
    
    
}
