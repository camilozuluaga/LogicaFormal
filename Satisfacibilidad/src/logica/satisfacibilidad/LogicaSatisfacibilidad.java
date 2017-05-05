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
import javax.swing.table.DefaultTableModel;

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

    Queue<Character> agregarValorPLetra;

    Queue<Character> agregarLetraProposicional;

    DefaultTableModel modeloTabla;

    int contador;
    int posicionesBoton;

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

    public int getPosicionesBoton() {
        return posicionesBoton;
    }

    public void setPosicionesBoton(int posicionesBoton) {
        this.posicionesBoton = posicionesBoton;
    }

    public LogicaSatisfacibilidad() {
        posiciones = new LinkedList<>();
        letra = new LinkedList<>();
        agregarFormula = new LinkedList<>();
        letrasAgregadas = new Stack<>();
        letrasAgregadas.add("");
        this.contador = 0;
        modeloTabla = new DefaultTableModel();
        this.posicionesBoton = 1;
        agregarValorPLetra = new LinkedList<>();
        agregarLetraProposicional = new LinkedList<>();
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
        this.posiciones = obtenerPosiciones(txtInsertarFormula);
        int pos = 0;
        if (!posiciones.isEmpty()) {
            pos = posiciones.poll();
        }
        return pos;
    }

    public int ubicarCursor(JTextArea txtInsertarFormula) {

        if (txtInsertarFormula.getText().length() > 0) {
            return ubicarCursorConLetra(txtInsertarFormula);
        }
        return 0;
    }

    /**
     * metodo que retorna en que posicion se va a poner la letra siguente se
     * llama al metodo anterior para saber las posiciones
     *
     * @param txtInsertarFormula, para pasarselo al metodo anterios, para que el
     * recorra y nos diga la posicion
     * @return la posicion en donde se va a poner la siguiente letra
     */
    public Object ubicarCursorConBoton(JTextArea txtInsertarFormula) {
        Queue<Integer> posicionesBotonCola = obtenerPosiciones(txtInsertarFormula);
        if (!posicionesBotonCola.isEmpty()) {
            Object[] posicionesArreglo = posicionesBotonCola.toArray();
            return (int) posicionesArreglo[sumarContador(posicionesBotonCola)];
        }
        return this.posicionesBoton;
    }

    /**
     * metodo que nos permite saber si podemos sumar el contador, para poder
     * obtener las posiciones donde podemos ubicar el cursos
     *
     * @param posicionesBotonCola, cola donde sabemos cual es el size
     * @return el contador sumado o cero si no puede sumar mas
     */
    public int sumarContador(Queue<Integer> posicionesBotonCola) {

        if (this.posicionesBoton <= posicionesBotonCola.size() - 1) {
            return this.posicionesBoton++;
        }
        this.posicionesBoton = 0;
        return this.posicionesBoton;

    }

    /**
     * metodo que nos permite saber en que posicion hay un parentesis que abre,
     * relacionado con uno que cierra, con lo que nos permite, poner la letra en
     * el lugar adecuado.
     *
     * @param txtInsertarFormula, para iterar y poder saber las posiciones de
     * las ObtenerLetras
     * @return
     */
    public Queue<Integer> obtenerPosiciones(JTextArea txtInsertarFormula) {
        Queue<Integer> posicionMetodo = new LinkedList<>();
        char[] arregloLetras = txtInsertarFormula.getText().toCharArray();
        int posicion = 0;

        for (int i = 0; i < arregloLetras.length; i++) {
            letra.add(arregloLetras[i]);
        }
        if (!letra.isEmpty()) {
            letra.add(' ');
            char primerCaracter = letra.poll();
            char aux1 = primerCaracter;
            while (!letra.isEmpty()) {
                if (primerCaracter != ' ') {
                    if (primerCaracter == ')' && aux1 == '(') {
                        posicionMetodo.add(posicion);
                    }
                }
                aux1 = primerCaracter;
                primerCaracter = letra.poll();
                posicion++;
            }
            return posicionMetodo;
        }
        return posicionMetodo;
    }

    /**
     * metodo que nos permite saber cuales son las letras que tienen las
     * formulas proposicionales
     *
     * @return una cola, con las letras proposicionales
     */
    public Queue<Character> ObtenerLetras() {
        Queue<Character> letras = new LinkedList<>();
        while (!this.agregarFormula.isEmpty()) {
            char[] formulas = this.agregarFormula.poll().toCharArray();
            for (int i = 0; i < formulas.length; i++) {
                if ((formulas[i] == 'P') || (formulas[i] == 'Q')
                        || (formulas[i] == 'R') || (formulas[i] == 'S')
                        || (formulas[i] == 'T')) {
                    if (!letras.contains(formulas[i])) {
                        letras.add(formulas[i]);
                        agregarLetraProposicional.add(formulas[i]);
                    }
                }
                //obtenerSimbolo(formulas[i]);
            }
        }
        return letras;
    }

    /**
     * metodo que nos permite saber cual es el simbolo que se ingreso en las
     * formulas
     *
     * @param inicial, el caracter para saber cual es el simbolo
     */
    /* 
public void obtenerSimbolo(char inicial) {
        switch (inicial) {
            case '~':
                this.signosFormulas.add("~");
                break;
            case '-':
                this.signosFormulas.add("->");
                break;
            case '<':
                this.signosFormulas.add("<->");
                break;
            case '^':
                this.signosFormulas.add("^");
                break;
            case 'v':
                this.signosFormulas.add("v");
                break;
            default:
                break;
        }
    }*/
    /**
     * metodo que nos permite generar la forma de la tabla, de acuerdo al numero
     * de letras que tiene la formula
     */
    public void ponerTabla() {
        Queue<Character> letras = ObtenerLetras();
        double a = Math.pow(2, letras.size());
        this.contador = 1;
        while (!letras.isEmpty()) {
            char clave = letras.poll();
            a = a / 2;
            for (int i = 0; i < this.contador; i++) {
                ponerUnos(a);
                ponerCeros(a);
            }
            this.contador++;
        }
    }

    /**
     * metodo que nos permite generar los unos que van en la tabla de verdad.
     *
     * @param veces, el numero de veces que se va a poner el numero
     */
    public void ponerUnos(double veces) {
        if (veces == 0) {
            System.out.print("");
        } else {
            ponerUnos(veces - 1);
            agregarValorPLetra.add('1');
        }
    }

    /**
     * metodo que nos permite generar los ceros que van en la tabla de verdad.
     *
     * @param veces, el numero de veces que se va a poner el numero
     */
    public void ponerCeros(double veces) {
        if (veces == 0) {
            System.out.print("");
        } else {
            ponerCeros(veces - 1);
            agregarValorPLetra.add('0');
        }
    }

    public void imprimirMapa() {
        ponerTabla();
        double hasta = Math.pow(2, agregarLetraProposicional.size());
        int posi = 0;
        while (!agregarLetraProposicional.isEmpty()) {
            System.out.print(agregarLetraProposicional.poll() + " ");
            while (posi < hasta) {
                if (!agregarValorPLetra.isEmpty()) {
                    System.out.print(agregarValorPLetra.poll());
                }
                posi++;
            }
            System.out.println("");
            posi = 0;
        }

    }

}
