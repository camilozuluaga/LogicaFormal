/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.satisfacibilidad;

import java.util.ArrayList;

/**
 *
 * @author BRAYHAN JARAMILLO
 */
public class Ejemplo {
    
    public static void main(String[] args) {
        
        
        
        
        
        
        
        
        
    }
    
    
        public static void obtenerFormulas() {

        ArrayList<String> formulas = new ArrayList<>();
        formulas.add("((P)->(Q))^(P)");
        formulas.add("(P)<->(Q)");
        formulas.add("~((P)v(Q))");
        
        String primera= "((P)->(Q))^(P)";
        
        
            for (int i = 0; i < primera.length(); i++) {
                
            }

    }
        
        
        public static ArrayList<String> retornar(String formula){
             ArrayList<String> formulaConjunto= new ArrayList<>();
             
             for (int i = 0; i < formula.length(); i++) {
                
            }
             
             return formulaConjunto;
        }
   
}
