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

    private Map<String, ArrayList> valor;
    private ArrayList<String> resultadoEvaluacion;

    public LetraYValor() {
        valor = new HashMap<>();
        resultadoEvaluacion = new ArrayList<>();
    }

    public ArrayList<String> getResultadoEvaluacion() {
        return resultadoEvaluacion;
    }

    public void setResultadoEvaluacion(ArrayList<String> resultadoEvaluacion) {
        this.resultadoEvaluacion = resultadoEvaluacion;
    }

    public Map<String, ArrayList> getValor() {
        return valor;
    }

    public void setValor(Map<String, ArrayList> valor) {
        this.valor = valor;
    }

}
