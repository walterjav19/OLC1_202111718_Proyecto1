/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyecto1_olc1;

import Analizadores.sym;
import Errores.ErrorLexico;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class Ventana extends javax.swing.JFrame {

    /**
     * Creates new form Ventana
     */
    public Ventana() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    
    
    
//Inicio de Metodos de Lectura y Escritura
public static String leer(String path){
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      String texto="";
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (path);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null)
            texto+=linea+"\n";
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{

         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      
    return texto;
}


//Fin de Metodos de Lectura y Escritura

public static void escribir(String path,String TextField2){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(path);
            pw = new PrintWriter(fichero);
            String texto=TextField2;
               
            pw.println(texto);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
}


    public static void GenerarHtml(List<ErrorLexico> lista){
        PrintWriter escribir=null;
        try {
            String cabecera="<html>\n" +
                    "  <head>\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <table border=\"7\">\n" +
                    "      <tr>\n" +
                    "        <th>Lexema</th>\n" +
                    "        <th>Línea</th>\n" +
                    "        <th>Columna</th>\n" +
                    "        <th>Descripción</th>\n" +
                    "      </tr>";
            String cuerpo="";
            for (ErrorLexico error:lista) {
                cuerpo+="      <tr>\n" +
                        "        <td>"+error.Lexema+"</td>\n" +
                        "        <td>"+Integer.toString(error.Linea)+"</td>\n" +
                        "        <td>"+Integer.toString(error.Columna)+"</td>\n" +
                        "        <td>"+error.Descripcion+"</td>\n" +
                        "      </tr>";
                
            }   String pie="    </table>\n" +
                    "  </body>\n" +
                    "</html>";  escribir = new PrintWriter(new File("C:\\\\Users\\\\USUARIO\\\\Documents\\\\NetBeansProjects\\\\Proyecto1_OLC1\\\\src\\\\main\\\\java\\\\Errores_202111718\\\\Errores.html"));
                    escribir.print(cabecera+cuerpo+pie);
                    escribir.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro la carpeta");
        } finally {
            escribir.close();
        }
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Generar Automata");

        jButton2.setText("Analizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jMenu1.setText("File");

        jMenuItem1.setText("Open");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Save");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Save as");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(jButton1)
                        .addGap(93, 93, 93)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(233, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    File selectedFile=null;
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        String Texto="";
        if (result == JFileChooser.APPROVE_OPTION) {
        selectedFile = fileChooser.getSelectedFile();
        Texto=leer(selectedFile.getAbsolutePath());
       }
        jTextArea1.setText(Texto);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        String Texto=jTextArea1.getText();
        escribir(selectedFile.getAbsolutePath(),Texto);
        JOptionPane.showMessageDialog(null, "Su Archivo "+selectedFile.getName()+" Fue Guardado Exitosamente", "Archivo Guardado", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save as");
    fileChooser.setSelectedFile(new File("untitled.txt"));
    int result = fileChooser.showSaveDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        escribir(file.getAbsolutePath(),jTextArea1.getText());
        selectedFile=file;
        JOptionPane.showMessageDialog(null, "Su Archivo "+file.getName()+" Fue Guardado Exitosamente", "Archivo Guardado", JOptionPane.INFORMATION_MESSAGE);
    }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Analizadores.Sintactico pars;
        jTextArea2.setText("");
        try {
            PrintWriter escribir=new PrintWriter(new File("archivo.txt"));
            escribir.print(jTextArea1.getText());
            escribir.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No se puede escribir archivo");
        }
        
        try {
            Analizadores.Lexico lex=new Analizadores.Lexico(new FileReader("archivo.txt"));
            pars=new Analizadores.Sintactico(lex);
            pars.parse();
            System.out.println("TokenID  Token              Linea Columna Valor");
            for(Symbol tok : lex.T_SIMBOLOS) {
                String tokenName = sym.terminalNames[tok.sym];
                String tokenValue = (tok.value != null) ? tok.value.toString() : "";
                System.out.format("%-9d%-20s%-6d%-8d%-20s\n", tok.sym, tokenName, tok.left, tok.right, tokenValue);
            }
            for (Map.Entry<String, String> entrada : pars.conjuntos.entrySet()) {
            // Obtener la llave y el valor asociado a la entrada actual
            String llave = entrada.getKey();
            String valor = entrada.getValue();

            // Imprimir el nombre del conjunto y su valor asociado
            System.out.println("Conjunto: " + llave + ", Valor: " + valor);
            }
            
            
            for(String linea: pars.resultados ){
                jTextArea2.setText(linea);
            }
            if (lex.ErroresLexicos.size()!=0){
                JOptionPane.showMessageDialog(null, "Hay errores Lexicos en la Compilacion del Programa revise la carpeta de errores","Errores Lexicos",JOptionPane.INFORMATION_MESSAGE);
                GenerarHtml(lex.ErroresLexicos);
            }
            
        } catch (Exception ex) {
            System.out.println("Error fatal en compilación de entrada");
            System.out.println("Causa: "+ex.getCause());
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
