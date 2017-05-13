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
    private ArrayList<ArrayList<String>> resultadoEvaluacionFomulas;
    private ArrayList<String> parteFinalEvaluada;
    private ArrayList<String> parteInicialEvaluada;
    private ArrayList<String> formulas;
    private int numeroDeLetrasProp;

    public int getNumeroDeLetrasProp() {
        return numeroDeLetrasProp;
    }

    public void setNumeroDeLetrasProp(int numeroDeLetrasProp) {
        this.numeroDeLetrasProp = numeroDeLetrasProp;
    }

    public ArrayList<String> getFormulas() {
        return formulas;
    }

    public void setFormulas(ArrayList<String> formulas) {
        this.formulas = formulas;
    }

    public ArrayList<String> getParteFinalEvaluada() {
        return parteFinalEvaluada;
    }

    public void setParteFinalEvaluada(ArrayList<String> parteFinalEvaluada) {
        this.parteFinalEvaluada = parteFinalEvaluada;
    }

    public ArrayList<String> getParteInicialEvaluada() {
        return parteInicialEvaluada;
    }

    public void setParteInicialEvaluada(ArrayList<String> parteInicialEvaluada) {
        this.parteInicialEvaluada = parteInicialEvaluada;
    }

    public LetraYValor() {
        valor = new HashMap<>();
        resultadoEvaluacion = new ArrayList<>();
        resultadoEvaluacionFomulas = new ArrayList<>();
        formulas = new ArrayList<>();
        numeroDeLetrasProp = 0;
    }

    public ArrayList<ArrayList<String>> getResultadoEvaluacionFomulas() {
        return resultadoEvaluacionFomulas;
    }

    public void setResultadoEvaluacionFomulas(ArrayList<ArrayList<String>> resultadoEvaluacionFomulas) {
        this.resultadoEvaluacionFomulas = resultadoEvaluacionFomulas;
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
