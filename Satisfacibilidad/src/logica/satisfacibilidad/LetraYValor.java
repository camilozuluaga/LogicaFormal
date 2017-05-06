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

    private Map<Character, ArrayList> valor;

    public LetraYValor() {
        valor = new HashMap<>();
    }

    public Map<Character, ArrayList> getValor() {
        return valor;
    }

    public void setValor(Map<Character, ArrayList> valor) {
        this.valor = valor;
    }

}
