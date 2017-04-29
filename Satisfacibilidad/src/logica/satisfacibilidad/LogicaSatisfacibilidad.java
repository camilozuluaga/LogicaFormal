/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.satisfacibilidad;

import java.util.Stack;
import javax.swing.JTextArea;

/**
 *
 * @author PC
 */
public class LogicaSatisfacibilidad {

    Stack<Integer> posiciones;
    Stack<Character> letra;
    Stack<String> agrego;

    public Stack<String> getAgrego() {
        return agrego;
    }

    public void setAgrego(Stack<String> agrego) {
        this.agrego = agrego;
    }
    int contador;

    public LogicaSatisfacibilidad() {
        posiciones = new Stack<>();
        letra = new Stack<>();
        agrego = new Stack<>();
        agrego.add("");
        this.contador = 0;

    }

    /**
     * metodo que nos permite ubicar los parentesis con sus operadores logicos,
     * para que sea mas organizado
     *
     * @param txtInsertarFormula, donde obtenemos los caracteres para seguir un
     * patron.s
     */
    public void ubicarCursor(JTextArea txtInsertarFormula) {
        String texto = txtInsertarFormula.getText();
        txtInsertarFormula.setCaretPosition(texto.indexOf(")"));
    }

    /**
     * metodo que retorna en que posicion se va a poner la letra siguente se
     * llama al metodo anterior para saber las posiciones
     *
     * @param txtInsertarFormula, para pasarselo al metodo anterios, para que el
     * recorra y nos diga la posicion
     * @return la posicion en donde se va a poner la siguiente letra
     */
    public int ubicarCursorConLetra(JTextArea txtInsertarFormula) {
        obtenerPosiciones(txtInsertarFormula);
        int pos = 0;
        if (!posiciones.isEmpty()) {
            pos = posiciones.pop();
        }
        return txtInsertarFormula.getText().length() - pos;
    }

    /**
     * metodo que nos permite saber en que posicion hay un parentesis que abre,
     * relacionado con uno que cierra, con lo que nos permite, poner la letra en
     * el lugar adecuado.
     *
     * @param txtInsertarFormula, para iterar y poder saber las posiciones de
     * las letras
     */
    public void obtenerPosiciones(JTextArea txtInsertarFormula) {
        posiciones.clear();
        char[] arregloLetras = txtInsertarFormula.getText().toCharArray();
        int posicion = 0;

        for (int i = 0; i < arregloLetras.length; i++) {
            letra.push(arregloLetras[i]);
        }

        char primerCaracter = letra.pop();
        char aux1 = primerCaracter;
        while (!letra.isEmpty()) {
            if (primerCaracter == '(' && aux1 == ')') {
                posiciones.add(posicion);
            }
            aux1 = primerCaracter;
            primerCaracter = letra.pop();
            posicion++;
        }

    }

}
