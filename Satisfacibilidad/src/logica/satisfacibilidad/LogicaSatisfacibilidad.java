/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.satisfacibilidad;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.swing.JTextArea;

/**
 *
 * @author PC
 */
public class LogicaSatisfacibilidad {

    /**
     * cola en la que guardamos las posiciones donde haya un (), para saber
     * donde puedo ubicar la proxima letra proposicional
     */
    Queue<Integer> posiciones;
    /**
     * cola donde guardamos la palabra descompuesta en cada uno de sus
     * caracteres para poder saber donde hay un (), para ubicar mas adelante las
     * ObtenerLetras proposicionales, automaticamente
     */
    Queue<Character> letra;
    /**
     * pila que nos sirve para guardar cuando se presiona un boton lo que se
     * hace con esto es para poder eliminar mas adelante cuando el usuario vaya
     * a borrar
     */
    Stack<String> letrasAgregadas;
    /**
     * pila que nos sirve para guardar la formula que se agrega cuando le dan en
     * el boton agregar. y saber si han agregado minimo tres.
     */
    Queue<String> agregarFormula;

    public Queue<String> getAgregarFormula() {
        return agregarFormula;
    }

    public void setAgregarFormula(Queue<String> agregarFormula) {
        this.agregarFormula = agregarFormula;
    }

    public Stack<String> getLetrasAgregadas() {
        return letrasAgregadas;
    }

    public void setLetrasAgregadas(Stack<String> letrasAgregadas) {
        this.letrasAgregadas = letrasAgregadas;
    }
    int contador;

    public LogicaSatisfacibilidad() {
        posiciones = new LinkedList<>();
        letra = new LinkedList<>();
        agregarFormula = new LinkedList<>();
        letrasAgregadas = new Stack<>();
        letrasAgregadas.add("");
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
            pos = posiciones.poll();
        }
        return pos;
    }

    /**
     * metodo que nos permite saber en que posicion hay un parentesis que abre,
     * relacionado con uno que cierra, con lo que nos permite, poner la letra en
     * el lugar adecuado.
     *
     * @param txtInsertarFormula, para iterar y poder saber las posiciones de
     * las ObtenerLetras
     */
    public void obtenerPosiciones(JTextArea txtInsertarFormula) {
        posiciones.clear();
        char[] arregloLetras = txtInsertarFormula.getText().toCharArray();
        int posicion = 0;

        for (int i = 0; i < arregloLetras.length; i++) {
            letra.add(arregloLetras[i]);
        }

        char primerCaracter = letra.poll();
        char aux1 = primerCaracter;
        do {
            if (primerCaracter == ')' && aux1 == '(') {
                posiciones.add(posicion);
            }
            aux1 = primerCaracter;
            primerCaracter = letra.poll();
            posicion++;
        } while (!letra.isEmpty());

        posiciones.add(posicion);
    }

    public Queue<Character> ObtenerLetras() {
        Queue<Character> letras = new LinkedList<>();
        letras.add(' ');
        while (!this.agregarFormula.isEmpty()) {
            char[] formulas = this.agregarFormula.poll().toCharArray();
            for (int i = 0; i < formulas.length; i++) {
                if ((formulas[i] == 'P') || (formulas[i] == 'Q')
                        || (formulas[i] == 'R') || (formulas[i] == 'S')
                        || (formulas[i] == 'T')) {
                    if (letras.peek() != formulas[i]) {
                        letras.add(formulas[i]);
                    }

                }
            }
        }
        return letras;
    }

    public void ponerTabla() {
        Queue<Character> letras = ObtenerLetras();
        double a = Math.pow(2, letras.size() - 1);
        double longitud = a;
        letras.poll();
        while (!letras.isEmpty()) {
            System.out.print("\t \t" + letras.poll());
            for (int i = 0; i < longitud; i++) {
                System.out.println("1");
            }
            a = a / 2;

        }
    }

}
