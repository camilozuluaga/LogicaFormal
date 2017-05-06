/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.satisfacibilidad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PC
 */
public class LetraYValor {

    private String letra;
    private Map<String, String[]> valor;

    public LetraYValor() {
        valor = new HashMap<>();
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public Map<String, String[]> getValor() {
        return valor;
    }

    public void setValor(Map<String, String[]> valor) {
        this.valor = valor;
    }

}
