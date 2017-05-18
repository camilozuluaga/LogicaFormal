/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.satisfacibilidad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

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
    Queue<Integer> posiciones = new LinkedList<>();
    /**
     * cola donde guardamos la palabra descompuesta en cada uno de sus
     * caracteres para poder saber donde hay un (), para ubicar mas adelante las
     * ObtenerLetras proposicionales, automaticamente
     */
    Queue<Character> letra = new LinkedList<>();

    /**
     * pila que nos sirve para guardar cuando se presiona un boton lo que se
     * hace con esto es para poder eliminar mas adelante cuando el usuario vaya
     * a borrar
     */
    Stack<String> letrasAgregadas = new Stack<>();
    /**
     * pila que nos sirve para guardar la formula que se agrega cuando le dan en
     * el boton agregar. y saber si han agregado minimo tres.
     */
    Queue<String> agregarFormula = new LinkedList<>();

    /**
     * cola que nos permite saber los valores de o y 1 para cada letra
     */
    Queue<String> agregarValorPLetra = new LinkedList<>();

    Queue<Character> agregarLetraProposicional = new LinkedList<>();

    DefaultTableModel modeloTabla = new DefaultTableModel();

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public void setModeloTabla(DefaultTableModel modeloTabla) {
        this.modeloTabla = modeloTabla;
    }

    Stack<String> ordenSimbolo = new Stack<>();

    Queue<Character> letrasFormula = new LinkedList<>();

    String negacionPrincipio = "";
    int contador = 0;
    int posicionesBoton = 1;
    String satisfacible = "";
    boolean verInterfazTabla = false;
    boolean verMensajeSatisfacible = false;
    ArrayList<String> retornoNegacion = new ArrayList<>();

    public boolean isVerMensajeSatisfacible() {
        return verMensajeSatisfacible;
    }

    public void setVerMensajeSatisfacible(boolean verMensajeSatisfacible) {
        this.verMensajeSatisfacible = verMensajeSatisfacible;
    }

    public boolean isVerInterfazTabla() {
        return verInterfazTabla;
    }

    public void setVerInterfazTabla(boolean verInterfazTabla) {
        this.verInterfazTabla = verInterfazTabla;
    }

    public Queue<String> getAgregarFormula() {
        return agregarFormula;
    }

    public void setAgregarFormula(Queue<String> agregarFormula) {
        this.agregarFormula = agregarFormula;
    }

    public String getSatisfacible() {
        return satisfacible;
    }

    public void setSatisfacible(String satisfacible) {
        this.satisfacible = satisfacible;
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

    public void obtenerLetras(Queue<String> formulas) {
        int contadorLetras = 0;
        ArrayList<Character> letras = new ArrayList<>();
        while (!formulas.isEmpty()) {
            String formula = formulas.poll();
            char[] formulaDescompuesta = formula.toCharArray();
            for (int i = 0; i < formulaDescompuesta.length; i++) {
                if (obtenerLetra(formulaDescompuesta[i])) {
                    if (!letras.contains(formulaDescompuesta[i])) {
                        letras.add(formulaDescompuesta[i]);
                        contadorLetras++;

                    }
                }
            }

            if (contadorLetras > letraYvalor.getNumeroDeLetrasProp()) {
                letraYvalor.setNumeroDeLetrasProp(contadorLetras);
            }
        }
        Collections.sort(letras);
        for (int i = 0; i < letras.size(); i++) {
            this.letrasFormula.add(letras.get(i));
            agregarLetraProposicional.add(letras.get(i));
        }

    }

    /**
     * metodo que nos permite saber cuales son las letras que tienen las
     * formulas proposicionales
     *
     */
    public void ObtenerLetras() {
        int contadorParentesis = 0;

        while (!this.agregarFormula.isEmpty()) {
            Queue<String> formulasAgregadas = new LinkedList<>(this.agregarFormula);
            obtenerLetras(formulasAgregadas);
            String formula = this.agregarFormula.poll();
            char[] formulas = formula.toCharArray();
            for (int i = 0; i < formulas.length; i++) {
                if (formulas[0] == '~') {
                    obtenerSimbolos(formula, "~");
                }
                if (formulas[i] == '(') {
                    ++contadorParentesis;
                }
                if (formulas[i] == ')') {
                    --contadorParentesis;
                }
                if (contadorParentesis == 0) {
                    ordenSimbolo.add(obtenerSimbolo(formulas[i + 1]));
                    obtenerSimbolos(formula, ordenSimbolo.peek());
                    break;
                }
            }
            contadorParentesis = 0;
        }
    }

    public String obtenerOperadorPrincipal(String formula) {
        int contadorParentesis = 0;
        char[] formulas = formula.toCharArray();
        for (int i = 0; i < formulas.length; i++) {
            if (formulas[i] == '(') {
                ++contadorParentesis;
            }
            if (formulas[i] == ')') {
                --contadorParentesis;
            }
            if (contadorParentesis == 0) {
                ordenSimbolo.add(obtenerSimbolo(formulas[i + 1]));
                return ordenSimbolo.peek();
            }
        }
        return "";
    }

    /**
     * metodo que nos permite llamar los metodos anteriores con los cuales vamos
     * a evaluar la formula, vamos a guardar el resultado de la evaluacion
     *
     * @param formula, la formula ingresada por el usuario.
     * @param simboloPrincipal, el simbolo principal de la formula
     */
    public void obtenerSimbolos(String formula, String simboloPrincipal) {

        letraYvalor.getFormulas().add(formula);
        letraYvalor.setParteFinalEvaluada(new ArrayList<>());
        letraYvalor.setParteInicialEvaluada(new ArrayList<>());
        String restoFormula;
        String finalFormula;
        String negacionSimboloPrincipal = "";

        if (letraYvalor.getValor().isEmpty()) {
            asociarNumerosALetra();
        }

        if (simboloPrincipal.length() > 0) {

            if (!ordenSimbolo.isEmpty()) {
                ordenSimbolo.pop();
            }

            if (simboloPrincipal.equals("~")) {
                formula = formula.substring(formula.indexOf("(") + 1, formula.length());
                formula = formula.substring(0, formula.lastIndexOf(")"));
                negacionSimboloPrincipal = "~";
                simboloPrincipal = obtenerOperadorPrincipal(formula);
            }

            restoFormula = formula.substring(0, formula.indexOf(simboloPrincipal));
            finalFormula = formula.substring(formula.indexOf(simboloPrincipal), formula.length());
            finalFormula = finalFormula.substring(finalFormula.indexOf("("), finalFormula.length());

            letraYvalor.setParteFinalEvaluada(evaluarParteFormula(finalFormula));
            letraYvalor.setParteInicialEvaluada(evaluarParteFormula(restoFormula));

            if (!negacionSimboloPrincipal.isEmpty() && !simboloPrincipal.isEmpty()) {
                letraYvalor.setResultadoEvaluacion(llamarMetodo(simboloPrincipal, "", "", ""));
            }

            if (!negacionSimboloPrincipal.isEmpty()) {
                resultadoFormula(negacionSimboloPrincipal);
            } else {
                resultadoFormula(simboloPrincipal);
            }

        }
    }

    /**
     * Metodo que permite evaluar la formula, de acuerdo a una parte de la
     * formula
     *
     * @param fragmentoFormula, el fragmento de la formula antes o despues de
     * operador principal
     * @return la evaluacion de la formula
     */
    public ArrayList<String> evaluarParteFormula(String fragmentoFormula) {
        ArrayList<String> retorno = new ArrayList<>();
        letraYvalor.setResultadoEvaluacion(new ArrayList<>());
        ordenSimbolo.add(" ");
        String verificarFormula = fragmentoFormula;
        verificarFormula = verificarFormula.replace("(", "");
        verificarFormula = verificarFormula.replace(")", "");

        llenarPilaOrdenFormula(fragmentoFormula);

        if (fragmentoFormula.length() > 3) {

            if ((fragmentoFormula.toCharArray()[1] == '~' && fragmentoFormula.toCharArray()[3] == '~')
                    || (fragmentoFormula.toCharArray()[1] == '~' && obtenerLetra(fragmentoFormula.toCharArray()[3]))) {
                fragmentoFormula = fragmentoFormula.replace("(", "");
                fragmentoFormula = fragmentoFormula.replace(")", "");
            } else if (obtenerLetra(fragmentoFormula.toCharArray()[1])) {
                fragmentoFormula = fragmentoFormula.replace("(", "");
                fragmentoFormula = fragmentoFormula.replace(")", "");
            }
        }

        if (fragmentoFormula.length() <= 3) {
            ordenSimbolo.removeAllElements();
            if (fragmentoFormula.toCharArray()[0] == '~' && fragmentoFormula.toCharArray()[1] == '~') {
                String negacion = "~" + fragmentoFormula.toCharArray()[2];
                retorno = llamarMetodo("~", String.valueOf(fragmentoFormula.toCharArray()[2]), "", "");
                letraYvalor.getValor().put(negacion, retorno);
                retorno = llamarMetodo("~", negacion, "", "");
            } else if (fragmentoFormula.toCharArray()[0] == '~') {
                retorno = llamarMetodo("~", String.valueOf(fragmentoFormula.toCharArray()[1]), "", "");
            } else {
                fragmentoFormula = fragmentoFormula.replace("(", "");
                fragmentoFormula = fragmentoFormula.replace(")", "");
                retorno = letraYvalor.getValor().get(fragmentoFormula);
            }

        } else {
            buscarNegacionEnFormula(verificarFormula.toCharArray(), 0);
            retorno = evaluarFormula();
        }
        return retorno;
    }

    public void buscarNegacionEnFormula(char[] formula, int i) {

        if (i == formula.length) {

        } else if (formula[i] == '~') {
            if (obtenerLetra(formula[i + 1])) {
                retornoNegacion = resultadoSimboloNegacion(String.valueOf(formula[i + 1]), "");
                retornoNegacion.add(String.valueOf(formula[i + 1]));
                buscarNegacionEnFormula(formula, i = formula.length);
            } else {
                buscarNegacionEnFormula(formula, i + 1);
            }
        } else {
            buscarNegacionEnFormula(formula, i + 1);
        }
    }

    /**
     * metodo que nos permite cargar una pila, con la formula de manera que en
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
                if (obtenerLetra(finalFormula.charAt(i))) {
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
     * metodo que nos permite evaluar si es un simbolo o no
     *
     * @param inicial, el simbolo
     * @return verdadero si es simbolo de lo contrario falso
     */
    public boolean obtenerSimboloBooleano(String inicial) {
        switch (inicial) {
            case "~":
                return false;
            case "->":
                return true;
            case "<->":
                return true;
            case "^":
                return true;
            case "v":
                return true;
            default:
                return false;
        }
    }

    public boolean obtenerLetra(char letra) {
        switch (letra) {
            case 'P':
                return true;
            case 'Q':
                return true;
            case 'R':
                return true;
            case 'S':
                return true;
            case 'T':
                return true;
            default:
                return false;
        }
    }

    /**
     * metodo que nos permite generar la forma de la tabla, de acuerdo al numero
     * de letras que tiene la formula
     */
    public void GenerarNumerosPorLetra() {
        Queue<Character> letras = this.letrasFormula;
        double a = Math.pow(2, letraYvalor.getNumeroDeLetrasProp());
        this.contador = 1;
        while (!letras.isEmpty()) {
            char clave = letras.poll();
            a = a / 2;
            if (a == 1) {
                this.contador = (int) Math.pow(2, letraYvalor.getNumeroDeLetrasProp());
            }
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
            agregarValorPLetra.add("1");
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
            agregarValorPLetra.add("0");
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
            String letraA = String.valueOf(agregarLetraProposicional.poll());
            letraYvalor.getValor().put(letraA, new ArrayList());
            ArrayList<String> datos = new ArrayList<>();
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
     * @param negacion
     * @return el arreglo con los valores ya procesados
     */
    public ArrayList<String> resultadoSimboloO(String letraUno, String letraDos, String negacion) {
        ArrayList<String> retorno = new ArrayList<>();
        ArrayList<String> valoresPrimeraLetra = letraYvalor.getValor().get(letraUno);
        ArrayList<String> valoresSegundaLetra = letraYvalor.getValor().get(letraDos);
        if (saberNegacion(letraUno, negacion)) {
            valoresPrimeraLetra = letraYvalor.getValor().get(negacion);
        }

        if (saberNegacion(letraDos, negacion)) {
            valoresSegundaLetra = letraYvalor.getValor().get(negacion);
        }

        if (!letraYvalor.getResultadoEvaluacion().isEmpty()) {
            valoresSegundaLetra = letraYvalor.getResultadoEvaluacion();
        }

        if (!letraYvalor.getParteInicialEvaluada().isEmpty() && !letraYvalor.getParteFinalEvaluada().isEmpty()) {
            valoresPrimeraLetra = letraYvalor.getParteInicialEvaluada();
            valoresSegundaLetra = letraYvalor.getParteFinalEvaluada();
        }

        if (!retornoNegacion.isEmpty()) {
            if (retornoNegacion.get(retornoNegacion.size() - 1).equals(letraUno)) {
                retornoNegacion.remove(retornoNegacion.size() - 1);
                valoresPrimeraLetra = retornoNegacion;
                ordenSimbolo.pop();
            }
        }

        if (!retornoNegacion.isEmpty()) {
            if (retornoNegacion.get(retornoNegacion.size() - 1).equals(letraDos)) {
                retornoNegacion.remove(retornoNegacion.size() - 1);
                valoresSegundaLetra = retornoNegacion;
                ordenSimbolo.pop();
            }
        }

        for (int i = 0; i < valoresPrimeraLetra.size(); i++) {

            if (valoresPrimeraLetra.get(i).equals("0") && valoresSegundaLetra.get(i).equals("0")) {
                retorno.add("0");
                continue;
            }
            retorno.add("1");
        }
        return retorno;
    }

    /**
     * metodo que nos permite devolver el valor del simbolo y
     *
     * @param letraUno, la letra para obtener los vlores
     * @param letraDos, la letra para obtener los valores
     * @param negacion
     * @return el arreglo con los valores ya procesados
     */
    public ArrayList<String> resultadoSimboloY(String letraUno, String letraDos, String negacion) {
        ArrayList<String> retorno = new ArrayList<>();
        ArrayList<String> valoresPrimeraLetra = letraYvalor.getValor().get(letraUno);
        ArrayList<String> valoresSegundaLetra = letraYvalor.getValor().get(letraDos);
        if (saberNegacion(letraUno, negacion)) {
            valoresPrimeraLetra = letraYvalor.getValor().get(negacion);
        }

        if (saberNegacion(letraDos, negacion)) {
            valoresSegundaLetra = letraYvalor.getValor().get(negacion);
        }

        if (!letraYvalor.getResultadoEvaluacion().isEmpty()) {
            valoresSegundaLetra = letraYvalor.getResultadoEvaluacion();
        }

        if (!letraYvalor.getParteInicialEvaluada().isEmpty() && !letraYvalor.getParteFinalEvaluada().isEmpty()) {
            valoresPrimeraLetra = letraYvalor.getParteInicialEvaluada();
            valoresSegundaLetra = letraYvalor.getParteFinalEvaluada();
        }

        if (!retornoNegacion.isEmpty()) {
            if (retornoNegacion.get(retornoNegacion.size() - 1).equals(letraUno)) {
                retornoNegacion.remove(retornoNegacion.size() - 1);
                valoresPrimeraLetra = retornoNegacion;
                ordenSimbolo.pop();
            }
        }

        if (!retornoNegacion.isEmpty()) {
            if (retornoNegacion.get(retornoNegacion.size() - 1).equals(letraDos)) {
                retornoNegacion.remove(retornoNegacion.size() - 1);
                valoresSegundaLetra = retornoNegacion;
                ordenSimbolo.pop();
            }
        }

        for (int i = 0; i < valoresPrimeraLetra.size(); i++) {
            if (valoresPrimeraLetra.get(i).equals("1") && valoresSegundaLetra.get(i).equals("1")) {
                retorno.add("1");
                continue;
            }
            retorno.add("0");
        }
        return retorno;
    }

    /**
     * metodo que nos permite devolver el valor del simbolo entonces
     *
     * @param letraUno, la letra para obtener los vlores
     * @param letraDos, la letra para obtener los valores
     * @param negacion
     * @return el arreglo con los valores ya procesados
     */
    public ArrayList<String> resultadoSimboloEntonces(String letraUno, String letraDos, String negacion) {
        ArrayList<String> retorno = new ArrayList<>();
        ArrayList<String> valoresPrimeraLetra = letraYvalor.getValor().get(letraUno);
        ArrayList<String> valoresSegundaLetra = letraYvalor.getValor().get(letraDos);

        if (saberNegacion(letraUno, negacion)) {
            valoresPrimeraLetra = letraYvalor.getValor().get(negacion);
        }

        if (saberNegacion(letraDos, negacion)) {
            valoresSegundaLetra = letraYvalor.getValor().get(negacion);
        }

        if (!letraYvalor.getResultadoEvaluacion().isEmpty()) {
            valoresSegundaLetra = letraYvalor.getResultadoEvaluacion();
        }

        if (!letraYvalor.getParteInicialEvaluada().isEmpty() && !letraYvalor.getParteFinalEvaluada().isEmpty()) {
            valoresPrimeraLetra = letraYvalor.getParteInicialEvaluada();
            valoresSegundaLetra = letraYvalor.getParteFinalEvaluada();
        }

        if (!retornoNegacion.isEmpty()) {
            if (retornoNegacion.get(retornoNegacion.size() - 1).equals(letraUno)) {
                retornoNegacion.remove(retornoNegacion.size() - 1);
                valoresPrimeraLetra = retornoNegacion;
                ordenSimbolo.pop();
            }
        }

        if (!retornoNegacion.isEmpty()) {
            if (retornoNegacion.get(retornoNegacion.size() - 1).equals(letraDos)) {
                retornoNegacion.remove(retornoNegacion.size() - 1);
                valoresSegundaLetra = retornoNegacion;
                ordenSimbolo.pop();
            }
        }

        for (int i = 0; i < valoresPrimeraLetra.size(); i++) {
            if (valoresPrimeraLetra.get(i).equals("1") && valoresSegundaLetra.get(i).equals("0")) {
                retorno.add("0");
                continue;
            }
            retorno.add("1");
        }
        return retorno;
    }

    /**
     * metodo que nos permite devolver el valor de la doble implicacion
     *
     * @param letraUno, para obtener el valor de las letras
     * @param letraDos, para obtener el valor de la segunda letra
     * @param negacion
     * @return el arreglo con los valores de acuerdo a la doble implicacion
     */
    public ArrayList<String> resultadoSimboloDobleImplicacion(String letraUno, String letraDos, String negacion) {
        ArrayList<String> retorno = new ArrayList<>();
        ArrayList<String> valoresPrimeraLetra = letraYvalor.getValor().get(letraUno);
        ArrayList<String> valoresSegundaLetra = letraYvalor.getValor().get(letraDos);

        if (saberNegacion(letraUno, negacion)) {
            valoresPrimeraLetra = letraYvalor.getValor().get(negacion);
        }

        if (saberNegacion(letraDos, negacion)) {
            valoresSegundaLetra = letraYvalor.getValor().get(negacion);
        }

        if (!letraYvalor.getResultadoEvaluacion().isEmpty()) {
            valoresSegundaLetra = letraYvalor.getResultadoEvaluacion();
        }

        if (!letraYvalor.getParteInicialEvaluada().isEmpty() && !letraYvalor.getParteFinalEvaluada().isEmpty()) {
            valoresPrimeraLetra = letraYvalor.getParteInicialEvaluada();
            valoresSegundaLetra = letraYvalor.getParteFinalEvaluada();
        }

        if (!retornoNegacion.isEmpty()) {
            if (retornoNegacion.get(retornoNegacion.size() - 1).equals(letraUno)) {
                retornoNegacion.remove(retornoNegacion.size() - 1);
                valoresPrimeraLetra = retornoNegacion;
                ordenSimbolo.pop();
            }
        }

        if (!retornoNegacion.isEmpty()) {
            if (retornoNegacion.get(retornoNegacion.size() - 1).equals(letraDos)) {
                retornoNegacion.remove(retornoNegacion.size() - 1);
                valoresSegundaLetra = retornoNegacion;
                ordenSimbolo.pop();
            }
        }

        for (int i = 0; i < valoresPrimeraLetra.size(); i++) {
            if ((valoresPrimeraLetra.get(i).equals("1") || valoresSegundaLetra.get(i).equals("1"))
                    && (valoresPrimeraLetra.get(i).equals("0") || valoresSegundaLetra.get(i).equals("0"))) {
                retorno.add("0");
                continue;
            }
            retorno.add("1");
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
    public ArrayList<String> resultadoSimboloNegacion(String letraUno, String negacion) {
        ArrayList<String> retorno = new ArrayList<>();
        ArrayList<String> valoresPrimeraLetra = letraYvalor.getValor().get(letraUno);
        if (negacion.length() > 0) {
            valoresPrimeraLetra = letraYvalor.getResultadoEvaluacion();
        }

        if (!letraYvalor.getResultadoEvaluacion().isEmpty()) {
            valoresPrimeraLetra = letraYvalor.getResultadoEvaluacion();
        }

        for (int i = 0; i < valoresPrimeraLetra.size(); i++) {
            if (valoresPrimeraLetra.get(i).equals("0")) {
                retorno.add("1");
            } else {
                retorno.add("0");
            }
        }
        return retorno;
    }

    public ArrayList<String> resultadoSimboloNegacion() {
        ArrayList<String> retorno = new ArrayList<>();
        ArrayList<String> valoresPrimeraLetra = new ArrayList<>();
        valoresPrimeraLetra = letraYvalor.getResultadoEvaluacion();

        for (int i = 0; i < valoresPrimeraLetra.size(); i++) {
            if (valoresPrimeraLetra.get(i).equals("0")) {
                retorno.add("1");
            } else {
                retorno.add("0");
            }
        }
        letraYvalor.setResultadoEvaluacion(retorno);
        return letraYvalor.getResultadoEvaluacion();
    }

    /**
     * Metodo que nos permite evaluar la formula por partes evaluamos la formula
     *
     * @return el resultado en un arreglo de la formula evaluada
     */
    public ArrayList<String> evaluarFormula() {
        String cabeza = ordenSimbolo.pop();
        String aux = cabeza;
        String aux2 = "";
        String negacion = "";
        while (!ordenSimbolo.isEmpty()) {
            if (!cabeza.equals(aux)) {

                if (cabeza.equals("~")) {
                    if (!letraYvalor.getResultadoEvaluacion().isEmpty()) {
                        resultadoSimboloNegacion();
                    }
                    negacion = cabeza + aux;
                    letraYvalor.getValor().put(negacion, resultadoSimboloNegacion(aux, ""));

                }

                if (obtenerSimboloBooleano(cabeza)) {
                    if (negacion.length() > 0) {
                        aux2 = negacion.substring(1);
                    } else {
                        aux2 = aux;
                        if (!this.negacionPrincipio.isEmpty()) {
                            negacion = this.negacionPrincipio;
                        }
                    }
                }

                if (!obtenerSimboloBooleano(cabeza) && obtenerSimboloBooleano(aux)) {
                    letraYvalor.setResultadoEvaluacion(llamarMetodo(aux, cabeza, aux2, negacion));
                }

                if (!letraYvalor.getResultadoEvaluacion().isEmpty()) {
                    negacion = "";
                }

            }

            aux = cabeza;
            cabeza = ordenSimbolo.pop();
        }

        return letraYvalor.getResultadoEvaluacion();

    }

    /**
     * metodo que nos permite saber en base al simbolo de la formula, cual de
     * los metodos nor sirve para llamar dicho metodo
     *
     * @param inicial, el simbolo de la formula
     * @param letraUno, la primera letra que se obtiene al recorrer la formula
     * @param letraDos, la segunda letra que se obtiene al recorrer la formula
     * @param negacion, si la palabra viene negada entonces para saber si
     * buscamos los valores de la letra negada
     * @return el resultado de la evaluacion de acuerdo al simbolo
     */
    public ArrayList<String> llamarMetodo(String inicial, String letraUno, String letraDos, String negacion) {
        switch (inicial) {
            case "~":
                return resultadoSimboloNegacion(letraUno, negacion);
            case "->":
                return resultadoSimboloEntonces(letraUno, letraDos, negacion);
            case "<->":
                return resultadoSimboloDobleImplicacion(letraUno, letraDos, negacion);
            case "^":
                return resultadoSimboloY(letraUno, letraDos, negacion);
            case "v":
                return resultadoSimboloO(letraUno, letraDos, negacion);
            default:
                return new ArrayList<>();
        }
    }

    /**
     * metodo que nos permite saber si hay una negacion o no, en caso de que la
     * halla se retorna la letra la cual se esta negando
     *
     * @param letra, la letra que necesitamos saber si esta negada
     * @param negacion, la negacion que es la letra mas el simbolo de la
     * negacion
     * @return verdadero si la letra esta negada y falso si es lo contrario
     */
    public boolean saberNegacion(String letra, String negacion) {
        if (negacion.length() > 0) {
            return negacion.substring(1).equals(letra);
        }
        return false;
    }

    /**
     * metodo que nos permite agregar a un arraylist el resultado de cada una de
     * las formulas agregadas
     *
     * @param operadorPrincipal, el operador principal para de acuerdo a la
     * evaluacion de los fragemntos ssaber el resultado
     */
    public void resultadoFormula(String operadorPrincipal) {
        System.out.println("el resultado de la formula es " + llamarMetodo(operadorPrincipal, "", "", ""));
        letraYvalor.setResultadoEvaluacion(llamarMetodo(operadorPrincipal, "", "", ""));
        letraYvalor.getResultadoEvaluacionFomulas().add(llamarMetodo(operadorPrincipal, "", "", ""));

    }

    /**
     * metodo que nos permite cargar la tabla
     *
     * @param datos, la tabla que se va a cargar
     */
    public void cargarTabla(JTable datos) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        datos.removeAll();
        ArrayList<String> formulas = new ArrayList<>();
        if (!letraYvalor.getFormulas().isEmpty()) {
            for (int i = 1; i < letraYvalor.getFormulas().size(); i++) {
                if (formulas.isEmpty()) {
                    formulas.add(letraYvalor.getFormulas().get(i));
                } else if (!formulas.contains(letraYvalor.getFormulas().get(i))) {
                    formulas.add(letraYvalor.getFormulas().get(i));
                }
            }
            letraYvalor.getFormulas().clear();
            letraYvalor.setFormulas(formulas);
        }

        if (letraYvalor.getResultadoEvaluacionFomulas().size() >= 3) {

            for (int i = 0; i < letraYvalor.getFormulas().size(); i++) {
                modeloTabla.addColumn(letraYvalor.getFormulas().get(i));
            }
            datos.removeAll();

            String[] fila = new String[letraYvalor.getFormulas().size()];

            int contador = 0;
            int iterador = 0;

            for (int i = 0; i < letraYvalor.getFormulas().size(); i++) {

                while (contador < letraYvalor.getResultadoEvaluacionFomulas().get(i).size()) {
                    fila[i] = letraYvalor.getResultadoEvaluacionFomulas().get(i).get(contador);
                    modeloTabla.addRow(fila);
                    contador++;
                }
                contador = 0;
            }
            datos.setModel(modeloTabla);
            int tamanio = letraYvalor.getResultadoEvaluacionFomulas().get(0).size();
            int resul = tamanio * (letraYvalor.getFormulas().size() - 1);
            for (int i = 0; i < resul; i++) {
                modeloTabla.removeRow(0);
            }

            String[] filaDos = new String[letraYvalor.getFormulas().size()];
            int contadorDos = 0;
            int iteradorDos = 0;

            for (int i = 0; i < letraYvalor.getFormulas().size(); i++) {
                while (contadorDos < letraYvalor.getResultadoEvaluacionFomulas().get(i).size()) {
                    filaDos[i] = letraYvalor.getResultadoEvaluacionFomulas().get(i).get(contadorDos);
                    modeloTabla.setValueAt(filaDos[i], contadorDos, i);
                    contadorDos++;
                }
                contadorDos = 0;
            }
            datos.setModel(modeloTabla);
            this.verInterfazTabla = true;
        } else {
            this.verInterfazTabla = false;
            JOptionPane.showMessageDialog(null, "Debe de ingresar por lo menos tres formulas proposicionales");
        }
    }

    /**
     * metodo que nos permite evaluar la satisfacibilidad del conjunto de
     * formulas
     */
    public void generarSatisfacibilidad() {
        ArrayList<Integer> posicionesUnos = new ArrayList<>();

        int contadorSatisfacisbilidad = 0;
        if (letraYvalor.getResultadoEvaluacionFomulas().size() >= 3) {

            for (int i = 0; i < letraYvalor.getResultadoEvaluacionFomulas().size(); i++) {
                for (int j = 0; j < letraYvalor.getResultadoEvaluacionFomulas().get(i).size(); j++) {
                    if (letraYvalor.getResultadoEvaluacionFomulas().get(i).get(j).equals("1")) {
                        posicionesUnos.add(j);
                    }
                }
            }

            for (int i = 0; i < posicionesUnos.size(); i++) {
                for (int j = 0; j < posicionesUnos.size(); j++) {
                    if (posicionesUnos.get(j).equals(posicionesUnos.get(i))) {
                        contadorSatisfacisbilidad++;
                    }
                }
                if (contadorSatisfacisbilidad == letraYvalor.getFormulas().size()) {
                    this.satisfacible = "El conjunto de formulas es Satisfacible";
                    break;
                } else {
                    contadorSatisfacisbilidad = 0;
                }
            }

            if (this.satisfacible.isEmpty()) {
                this.satisfacible = "El conjunto de formulas es Insatisfacible";
            }
            this.verMensajeSatisfacible = true;
        } else {
            JOptionPane.showMessageDialog(null, "Debe de ingresar por lo menos tres formulas proposicionales");
        }
    }
}
