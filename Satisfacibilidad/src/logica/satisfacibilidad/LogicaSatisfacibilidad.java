/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.satisfacibilidad;

import java.util.ArrayList;
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

    Queue<String> agregarValorPLetra;

    Queue<Character> agregarLetraProposicional;

    DefaultTableModel modeloTabla;

    Stack<String> ordenSimbolo;

    Queue<Character> letrasFormula;

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
        letrasFormula = new LinkedList<>();
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
     */
    public void ObtenerLetras() {
        int contadorParentesis = 0;
        while (!this.agregarFormula.isEmpty()) {
            String formula = this.agregarFormula.poll();
            char[] formulas = formula.toCharArray();
            for (int i = 0; i < formulas.length; i++) {
                if ((formulas[i] == 'P') || (formulas[i] == 'Q')
                        || (formulas[i] == 'R') || (formulas[i] == 'S')
                        || (formulas[i] == 'T')) {
                    if (!this.letrasFormula.contains(formulas[i])) {
                        this.letrasFormula.add(formulas[i]);
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
                    obtenerSimbolos(formula, ordenSimbolo.peek());
                    contadorParentesis = 1;
                }
            }
        }
    }

    /**
     * metodo que nos permite llamar los metodos anteriores con los cuales vamos
     * a evaluar la formula, vamos a guardar el resultado de la evaluacion
     *
     * @param formula, la formula ingresada por el usuario.
     * @param simboloPrincipal, el simbolo principal de la formula
     */
    public void obtenerSimbolos(String formula, String simboloPrincipal) {
        letraYvalor.setParteFinalEvaluada(new ArrayList<>());
        letraYvalor.setParteInicialEvaluada(new ArrayList<>());
        ordenSimbolo.pop();
        String restoFormula = formula.split(simboloPrincipal, 0)[0];
        String finalFormula = formula.substring(formula.indexOf(simboloPrincipal), formula.length());
        finalFormula = finalFormula.substring(finalFormula.indexOf("("), finalFormula.length());
        ordenSimbolo.add(" ");
        llenarPilaOrdenFormula(finalFormula);

        letraYvalor.setParteFinalEvaluada(evaluarFormula());
        if (finalFormula.length() <= 3) {
            finalFormula = finalFormula.replace("(", "");
            finalFormula = finalFormula.replace(")", "");
            letraYvalor.setParteFinalEvaluada(letraYvalor.getValor().get(finalFormula));
        }

        ordenSimbolo.add(" ");
        letraYvalor.setResultadoEvaluacion(new ArrayList<>());
        llenarPilaOrdenFormula(restoFormula);
        letraYvalor.setParteInicialEvaluada(evaluarFormula());
        resultadoFormula(simboloPrincipal);

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

    /**
     * metodo que nos permite generar la forma de la tabla, de acuerdo al numero
     * de letras que tiene la formula
     */
    public void GenerarNumerosPorLetra() {
        Queue<Character> letras = this.letrasFormula;
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

        for (int i = 0; i < valoresPrimeraLetra.size(); i++) {
            if ((valoresPrimeraLetra.get(i).equals("1") && valoresSegundaLetra.get(i).equals("1"))
                    || (valoresPrimeraLetra.get(i).equals("0") && valoresSegundaLetra.get(i).equals("0"))) {
                retorno.add("1");
                continue;
            }
            retorno.add("0");
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
    public ArrayList<String> resultadoSimboloNegacion(String letraUno) {
        ArrayList<String> retorno = new ArrayList<>();
        ArrayList<String> valoresPrimeraLetra = letraYvalor.getValor().get(letraUno);
        for (int i = valoresPrimeraLetra.size() - 1; i >= 0; i--) {
            retorno.add(valoresPrimeraLetra.get(i));
        }
        return retorno;
    }

    /**
     * Metodo que nos permite evaluar la formula por partes evaluamos la formula
     *
     * @return el resultado en un arreglo de la formula evaluada
     */
    public ArrayList<String> evaluarFormula() {
        asociarNumerosALetra();
        String cabeza = ordenSimbolo.pop();
        String aux = cabeza;
        String aux2 = "";
        String negacion = "";
        while (!ordenSimbolo.isEmpty()) {
            if (!cabeza.equals(aux)) {

                if (cabeza.equals("~")) {
                    negacion = cabeza + aux;
                    letraYvalor.getValor().put(negacion, resultadoSimboloNegacion(aux));
                }

                if (obtenerSimboloBooleano(cabeza)) {
                    if (negacion.length() > 0) {
                        aux2 = negacion.substring(1);
                    } else {
                        aux2 = aux;
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

    public void resultadoFormula(String operadorPrincipal) {
        letraYvalor.getResultadoEvaluacionFomulas().add(llamarMetodo(operadorPrincipal, "", "", ""));
    }
}
