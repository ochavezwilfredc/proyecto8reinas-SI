/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Recursos.Recursos;
import java.util.ArrayList;

/**
 *
 * @author mendoza
 */
public class OchoReinas implements Condiciones {

    // numero de mutaciones
    public static int numMutaciones = 0;

    // info de mutaciones
    public static int sigMutacion = 0;

    // numero de generaciones
    public static int generacion = 0;

    // numero de hijos
    public static int numHijos = 0;

    Recursos recursos;
    Seleccion seleccion;
    Fitness fitness;
    Cromosoma cromosoma;
    Cruce cruce;

    // Array que contendra todas las poblaciones
    public static ArrayList<Cromosoma> poblacion = new ArrayList<Cromosoma>();

    public OchoReinas() {
        this.recursos = new Recursos();
        this.seleccion = new Seleccion();
        this.fitness = new Fitness();
        this.cruce = new Cruce();

    }

    public void algoritmoGenetico() {
        int tamPoblacion;
        boolean terminado = false;
        // este paso genera la poblacion inicial a usar
        GenerarPoblacionInicial();

        // setea en numero de mutaciones a 0 
        numMutaciones = 0;

        // setea un valor random para la mutacion que se debe de dar
        sigMutacion = recursos.NumeroAleatorio(0, (int) Math.round(1.0 / tasaMutacion));

        while (!terminado) {
            // tomamos el tamaño de la poblacion para realizar el 
            tamPoblacion = poblacion.size();

            for (int i = 0; i < tamPoblacion; i++) {
                // obtengo el cromosoma con su contenido 
                cromosoma = poblacion.get(i);

                // si el conflicto == 0 es porque ese es el ganador y la solucion
                if (cromosoma.getConflicts() == 0) {
                    terminado = true;
                }
            }

            // ahora tenemos qe seguir con el fitness para calcular la actitud de cada individuo
            fitness.evaluar();

            //Selecciona los padres de la siguiente generación
            seleccion.ruletaSeleccion();

            //Realiza el cruce parcial de los padres
            reproduccion();

            //Selecciona los hijos de la siguiente generación
            PrepararSiguienteGeneracion();

            generacion++;

        }

        tamPoblacion = poblacion.size();
        for (int i = 0; i < tamPoblacion; i++) {
            cromosoma = poblacion.get(i);
            if (cromosoma.getConflicts() == 0) {
                ImprimirSolucion(cromosoma, i + 1);
            }
        }

        System.out.println("Resuelto en " + generacion + " generaciones \nencontrado con " + numMutaciones + " mutaciones \nen el " + numHijos + " cromosoma y las sgMutaciones " + sigMutacion);
    }

    public void GenerarPoblacionInicial() {

        int barajar;
        Cromosoma newCromosoma;
        int cromosomaIndex;
        Recursos rand;

        for (int i = 0; i < poblacionIni; i++) {

            // se genera un cromosoma nuevo
            newCromosoma = new Cromosoma(anchoTablero);

            // se agrega el cromosoma al arreglo
            poblacion.add(newCromosoma);

            // se captura la posicion del cromosoma en el arreglo
            cromosomaIndex = poblacion.indexOf(newCromosoma);

            // Escoja al azar el tamaño de baraja realizar.
            barajar = recursos.NumeroAleatorio(minBaraja, maxBaraja);

            // intercambia una mutacion (¿Donde y Porque?)
            IntercambiarMutacion(cromosomaIndex, barajar);

            // obtiene la cantidad de conflictos del cromosoma generado
            poblacion.get(cromosomaIndex).calcularConflictos();
        }

    }

    // intercambia una mutación que suceda en los genes 
    // Cambia la posición de las reinas en un cromosoma al azar de acuerdo con el número de intercambios
    // NOTA: siempre tienen que ser dos genes porque si es uno saldria esta figura
    /*
        1,0,0,0,0,0,0,0,
        0,1,0,0,0,0,0,0,
        0,0,1,0,0,0,0,0,
        0,0,0,1,0,0,0,0,
        0,0,0,1,0,0,0,0,
        0,0,0,0,0,1,0,0,
        0,0,0,0,0,0,1,0,
        0,0,0,0,0,0,0,1,
     */
    // entonces tienen que ser dos genes para cambiar entre si posiciones
    public void IntercambiarMutacion(int indice, int intercambio) {

        int i = 0;
        int tempGen1, tempGen2, gen1, gen2;
        boolean terminado = false;

        cromosoma = poblacion.get(indice);

        while (!terminado) {

            //Face del cruce en dos puntos
            gen1 = recursos.NumeroAleatorio(0, anchoTablero - 1);
            gen2 = recursos.NumeroAleatorioExclusivo(anchoTablero - 1, gen1);
            System.out.println("El gen1: " + gen1 + " gen2:" + gen2);

            // Cambia los genes seleccionados cruzadamente
            tempGen1 = cromosoma.getGene(gen1);
            tempGen2 = cromosoma.getGene(gen2);

            cromosoma.setGene(gen1, tempGen2);
            cromosoma.setGene(gen2, tempGen1);

            if (i == intercambio) {
                terminado = true;
            }
            i++;
        }
        numMutaciones++;
    }

    // Produce una nueva generacion
    public void reproduccion() {

        int getRand, padreA, padreB, newIndex1, newIndex2;
        Cromosoma newCromosoma1, newCromosoma2;

        // este for es del numero de descendencias que genera la reproduccion 
        for (int i = 0; i < numDesendencia; i++) {

            // aqui se selecciona un padre 
            padreA = SeleccionarPadre();

            // Probabilidad de prueba de reproduccion.
            getRand = recursos.NumeroAleatorio(0, 100);

            if (getRand <= (probApareamiento * 100)) {

                // se obtiene un nuevo padre que no sea igual al padre A
                padreB = SeleccionarPadre(padreA);

                // se crean dos nuevos cromosomas 
                newCromosoma1 = new Cromosoma(anchoTablero);
                newCromosoma2 = new Cromosoma(anchoTablero);

                // se agrega el cromosoma creado a la poblacion 
                poblacion.add(newCromosoma1);

                // se obtiene el indice del cromosoma creado
                newIndex1 = poblacion.indexOf(newCromosoma1);

                // se agrega el cromosoma creado a la poblacion 
                poblacion.add(newCromosoma2);

                // se obtiene el indice del cromosoma creado
                newIndex2 = poblacion.indexOf(newCromosoma2);

                // Elige uno o ambos de los siguientes: ahora con objetos 
                cruce.CruceParcial(padreA, padreB, newIndex1, newIndex2);

                // se compara el numero de hijos con el de la sig mutacion 
                if (numHijos - 1 == sigMutacion) {

                    // se genera una mutacion al azar y de baraja solo una vaz
                    IntercambiarMutacion(newIndex1, 1);
                } else if (numHijos == sigMutacion) {

                    // se genera una mutacion al azar y de baraja solo una vez
                    IntercambiarMutacion(newIndex2, 1);
                }

                poblacion.get(newIndex1).calcularConflictos();
                poblacion.get(newIndex2).calcularConflictos();

                numHijos += 2;

                // Programa la siguiente mutacion.
                if (numHijos % (int) Math.round(1.0 / tasaMutacion) == 0) {
                    sigMutacion = numHijos + recursos.NumeroAleatorio(0, (int) Math.round(1.0 / tasaMutacion));
                }
            }
        } // i
    }

    // Seleccionar los padres para la reproduccion
    public int SeleccionarPadre() {

        // Función encargada de, vea tambien "choosepadre (ByVal Parenta As Integer)".
        int padre = 0;
        boolean terminado = false;

        while (!terminado) {
            // Elige al azar un padre elegible.
            padre = recursos.NumeroAleatorio(0, poblacion.size() - 1);
            cromosoma = poblacion.get(padre);
            if (cromosoma.isSelected() == true) {
                terminado = true;
            }
        }

        return padre;
    }

    // Seleccionar los padres para la reproduccion, diferente al seleccionado
    // Función encargada, consulta "choosepadre()".
    public int SeleccionarPadre(int padreA) {
        int padre = 0;
        boolean terminado = false;

        while (!terminado) {

            // Elige al azar un padre elegible.
            padre = recursos.NumeroAleatorio(0, poblacion.size() - 1);
            if (padre != padreA) {
                cromosoma = poblacion.get(padre);
                if (cromosoma.isSelected() == true) {
                    terminado = true;
                }
            }
        }

        return padre;
    }

    // Prepara la poblacion de la siguiente generacion
    public void PrepararSiguienteGeneracion() {
        int tamanioPoblacion = 0;

        // Restaura estado de cromosoma
        tamanioPoblacion = poblacion.size();
        for (int i = 0; i < tamanioPoblacion; i++) {
            cromosoma = poblacion.get(i);
            cromosoma.setSelected(false);
        }
    }

    /**
     * Imprime la mejor solucion
     *
     * @param mejorSolucion
     * @param i
     */
    public void ImprimirSolucion(Cromosoma mejorSolucion, int i) {
        String tablero[][] = new String[anchoTablero][anchoTablero];
        this.recursos.inicializarTablero(tablero, "0");

        //1 = reina 
        for (int x = 0; x < anchoTablero; x++) {
            tablero[x][mejorSolucion.getGene(x)] = "1";

        }
        //Imprime la soción
        this.recursos.imprimirTablero(tablero, "Solución " + i);
    }

}
