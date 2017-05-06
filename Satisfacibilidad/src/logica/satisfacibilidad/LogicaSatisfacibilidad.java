/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.satisfacibilidad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.objects.NativeArray.map;

/**
 *
 * @author PC
 */
public class LogicaSatisfacibilidad {

    LetraYValor letraYvalor = new LetraYValor();

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

    Stack<String> ordenSimbolo;

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
        ordenSimbolo = new Stack<>();

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
        int contadorParentesis = 0;
        while (!this.agregarFormula.isEmpty()) {
            String formula = this.agregarFormula.poll();
            char[] formulas = formula.toCharArray();
            for (int i = 0; i < formulas.length; i++) {
                if ((formulas[i] == 'P') || (formulas[i] == 'Q')
                        || (formulas[i] == 'R') || (formulas[i] == 'S')
                        || (formulas[i] == 'T')) {
                    if (!letras.contains(formulas[i])) {
                        letras.add(formulas[i]);
                        agregarLetraProposicional.add(formulas[i]);
                    }
                }
                if (formulas[i] == '(') {
                    ++contadorParentesis;
                }
                if (formulas[i] == ')') {
                    --contadorParentesis;
                }
                if (contadorParentesis == 0) {
                    ordenSimbolo.add(obtenerSimbolo(formulas[i + 1]));
                    obtenerSimbolos(formula, obtenerSimbolo(formulas[i + 1]));
                    contadorParentesis = 1;
                }
            }
        }
        return letras;
    }

    public void obtenerSimbolos(String formula, String simboloPrincipal) {
        ordenSimbolo.pop();
        String restoFormula = formula.split(simboloPrincipal, 0)[0];
        String finalFormula = formula.substring(formula.lastIndexOf(simboloPrincipal) + simboloPrincipal.length(), formula.length());
        llenarPilaOrdenFormula(finalFormula);
        ordenSimbolo.add(simboloPrincipal);
        llenarPilaOrdenFormula(restoFormula);

    }

    /**
     * metodo que nos permite cargar una pila, con la formula de manera que en]
     * la parte de abajo de la pila quede los simbolos y letras proposicionales
     * a que estan a la derecha del simbolo principal, y en la parte de arriba
     * lo que esta a la izquierda del simbolo principal
     *
     * @param finalFormula, la cadena, puede ser la parte de la izquierda del
     * operador principal o tambien la parte mas a la derecha
     */
    public void llenarPilaOrdenFormula(String finalFormula) {
        int contadorSimbolo = 0;
        for (int i = 0; i < finalFormula.length(); i++) {
            if (finalFormula.charAt(i) != ')' && finalFormula.charAt(i) != '(') {
                if ((finalFormula.charAt(i) == 'P') || (finalFormula.charAt(i) == 'Q')
                        || (finalFormula.charAt(i) == 'R') || (finalFormula.charAt(i) == 'S')
                        || (finalFormula.charAt(i) == 'T')) {
                    ordenSimbolo.add(String.valueOf(finalFormula.charAt(i)));
                    contadorSimbolo = 0;
                } else if (obtenerSimbolo(finalFormula.charAt(i)).length() > 0) {
                    if (contadorSimbolo == 0) {
                        ordenSimbolo.add(obtenerSimbolo(finalFormula.charAt(i)));
                        contadorSimbolo++;
                    }
                }
            } else {
                contadorSimbolo = 0;
            }
        }
    }

    /**
     * metodo que nos permite saber cual es el simbolo que se ingreso en las
     * formulas
     *
     * @param inicial, el caracter para saber cual es el simbolo
     * @return el simbolo que representa de acuerdo al primer caracter que se
     * analiza para leer
     */
    public String obtenerSimbolo(char inicial) {
        switch (inicial) {
            case '~':
                return "~";
            case '-':
                return "->";
            case '<':
                return "<->";
            case '^':
                return "^";
            case 'v':
                return "v";
            default:
                return "";
        }
    }

    /**
     * metodo que nos permite generar la forma de la tabla, de acuerdo al numero
     * de letras que tiene la formula
     */
    public void GenerarNumerosPorLetra() {
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

    /**
     * metodo que nos permite asociar los numeros generados, a cada letra. con
     * esto cuando tengamos una letra sabremos cuales son los valores asociados
     */
    public void asociarNumerosALetra() {
        GenerarNumerosPorLetra();
        double hasta = Math.pow(2, agregarLetraProposicional.size());
        int posi = 0;
        while (!agregarLetraProposicional.isEmpty()) {
            char letraA = agregarLetraProposicional.poll();
            letraYvalor.getValor().put(letraA, new ArrayList());
            ArrayList<Character> datos = new ArrayList<>();
            while (posi < hasta) {
                if (!agregarValorPLetra.isEmpty()) {
                    datos.add(agregarValorPLetra.poll());
                }
                posi++;
            }
            letraYvalor.getValor().replace(letraA, datos);
            posi = 0;
        }

    }

    /**
     * metodo que nos permite devolver el valor del simbolo O
     *
     * @param letraUno, la letra para obtener los vlores
     * @param letraDos, la letra para obtener los valores
     * @return el arreglo con los valores ya procesados
     */
    public ArrayList<Character> resultadoSimboloO(char letraUno, char letraDos) {
        asociarNumerosALetra();
        ArrayList<Character> retorno = new ArrayList<>();
        ArrayList<Character> valoresPrimeraLetra = letraYvalor.getValor().get(letraUno);
        ArrayList<Character> valoresSegundaLetra = letraYvalor.getValor().get(letraDos);
        for (int i = 0; i < valoresPrimeraLetra.size(); i++) {
            if (valoresPrimeraLetra.get(i).equals('0') && valoresSegundaLetra.get(i).equals('0')) {
                retorno.add('0');
                continue;
            }
            retorno.add('1');
        }
        return retorno;
    }

    /**
     * metodo que nos permite devolver el valor del simbolo y
     *
     * @param letraUno, la letra para obtener los vlores
     * @param letraDos, la letra para obtener los valores
     * @return el arreglo con los valores ya procesados
     */
    public ArrayList<Character> resultadoSimboloY(char letraUno, char letraDos) {
        asociarNumerosALetra();
        ArrayList<Character> retorno = new ArrayList<>();
        ArrayList<Character> valoresPrimeraLetra = letraYvalor.getValor().get(letraUno);
        ArrayList<Character> valoresSegundaLetra = letraYvalor.getValor().get(letraDos);
        for (int i = 0; i < valoresPrimeraLetra.size(); i++) {
            if (valoresPrimeraLetra.get(i).equals('1') && valoresSegundaLetra.get(i).equals('1')) {
                retorno.add('1');
                continue;
            }
            retorno.add('0');
        }
        return retorno;
    }

    /**
     * metodo que nos permite devolver el valor del simbolo entonces
     *
     * @param letraUno, la letra para obtener los vlores
     * @param letraDos, la letra para obtener los valores
     * @return el arreglo con los valores ya procesados
     */
    public ArrayList<Character> resultadoSimboloEntonces(char letraUno, char letraDos) {
        asociarNumerosALetra();
        ArrayList<Character> retorno = new ArrayList<>();
        ArrayList<Character> valoresPrimeraLetra = letraYvalor.getValor().get(letraUno);
        ArrayList<Character> valoresSegundaLetra = letraYvalor.getValor().get(letraDos);
        for (int i = 0; i < valoresPrimeraLetra.size(); i++) {
            if (valoresPrimeraLetra.get(i).equals('1') && valoresSegundaLetra.get(i).equals('0')) {
                retorno.add('0');
                continue;
            }
            retorno.add('1');
        }
        return retorno;
    }

    /**
     * metodo que nos permite devolver el valor de la doble implicacion
     *
     * @param letraUno, para obtener el valor de las letras
     * @param letraDos, para obtener el valor de la segunda letra
     * @return el arreglo con los valores de acuerdo a la doble implicacion
     */
    public ArrayList<Character> resultadoSimboloDobleImplicacion(char letraUno, char letraDos) {
        asociarNumerosALetra();
        ArrayList<Character> retorno = new ArrayList<>();
        ArrayList<Character> valoresPrimeraLetra = letraYvalor.getValor().get(letraUno);
        ArrayList<Character> valoresSegundaLetra = letraYvalor.getValor().get(letraDos);
        for (int i = 0; i < valoresPrimeraLetra.size(); i++) {
            if ((valoresPrimeraLetra.get(i).equals('1') && valoresSegundaLetra.get(i).equals('1'))
                    || (valoresPrimeraLetra.get(i).equals('0') && valoresSegundaLetra.get(i).equals('0'))) {
                retorno.add('1');
                continue;
            }
            retorno.add('0');
        }
        return retorno;
    }

    /**
     * metodo que nos devuelve un arreglo con los valores que corresponden, a la
     * negacion.
     *
     * @param letraUno, letra para saber cuales son los valores
     * @return el arreglo con los valores que tiene la letra negados
     */
    public ArrayList<Character> resultadoSimboloNegacion(char letraUno) {
        asociarNumerosALetra();
        ArrayList<Character> retorno = new ArrayList<>();
        ArrayList<Character> valoresPrimeraLetra = letraYvalor.getValor().get(letraUno);
        for (int i = valoresPrimeraLetra.size(); i <= 0; i--) {
            retorno.add(valoresPrimeraLetra.get(i));
        }
        return retorno;
    }

}
