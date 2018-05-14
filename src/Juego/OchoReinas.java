/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Recursos.Recursos;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author mendoza
 */
public class OchoReinas implements Icondiciones {

    // numero de mutaciones
    public static int numMutaciones = 0;

    // info de mutaciones
    public static int sigMutacion = 0;

    // numero de generaciones
    public static int generacion = 0;

    // numero de hijos
    public static int numHijos = 0;

    // Array que contendra todas las poblaciones
    public static ArrayList<Cromosoma> poblacion = new ArrayList<Cromosoma>();
    
    Recursos recursos;
    Seleccion seleccion;
    Fitness fitness;
    Cromosoma cromosoma;
    Cruce cruce;

    private Cromosoma c_result;

    public OchoReinas() {
        this.recursos = new Recursos();
        this.seleccion = new Seleccion();
        this.fitness = new Fitness();
        this.cruce = new Cruce();

    }

    /**
     * Método principal para la simulación AG
     *
     * @return
     */
    public Cromosoma algoritmoGenetico() {
        boolean terminado = false;
        // este paso genera la poblacion inicial a usar
        GenerarPoblacionInicial();

        // setea en numero de mutaciones a 0 
        numMutaciones = 0;

        // setea un valor random para la siguiente mutación a relaizar
        sigMutacion = recursos.getAleatorio(0, (int) Math.round(1.0 / TASA_MUTACION));

        while (!terminado) {
            for (int i = 0; i < poblacion.size(); i++) {
                // obtengo el cromosoma con su contenido 
                cromosoma = poblacion.get(i);

                // si el conflicto == 0 es porque ese es el ganador y la solucion
                if (cromosoma.getConflictos() == 0) {
                    terminado = true;
                }
            }

            //Evalua el fitness por individuo
            fitness.evaluar();

            //Selecciona los padres de la siguiente generación
            seleccion.ruletaSeleccion();

            //Realiza el cruce parcial de los padres
            this.reproduccion();

            //Selecciona los hijos de la siguiente generación
            this.prepararSiguienteGeneracion();

            generacion++;

            //Imprime una generación completa
            this.recursos.imprimirGeneracion(poblacion, generacion);

        }

        for (int i = 0; i < poblacion.size(); i++) {
            cromosoma = poblacion.get(i);
            if (cromosoma.getConflictos() == 0) {
                this.c_result = cromosoma;
                System.out.println("\nCromosoma Solución:\n"
                        + Arrays.toString(cromosoma.getVec_genes())
                        + " Fitness: " + cromosoma.fitness
                        + " #Coliciones: " + cromosoma.cantConflictos);
                this.recursos.imprimirSolucionFinal(cromosoma);

            }
        }

        System.out.println("\n-------------------------\n"
                + "Resuelto en:\n "
                + "\tGeneraciones: " + generacion + "\n"
                + "\tMutaciones: " + numMutaciones + "\n"
                + "\tNro. Hijos: " + numHijos + "\n");
        return this.c_result;
    }

    /**
     * Método genera la población inicial
     */
    public void GenerarPoblacionInicial() {
        int barajar;
        Cromosoma nuevoCromosoma;
        int inidiceCromosoma;

        for (int i = 0; i < POBLACION_INICIAL; i++) {
            // se genera un cromosoma nuevo
            nuevoCromosoma = new Cromosoma(ANCHO_TABLERO);

            // se agrega el cromosoma a la lista(población)
            poblacion.add(nuevoCromosoma);

            // se captura la posicion del cromosoma en la lista
            inidiceCromosoma = poblacion.indexOf(nuevoCromosoma);

            // Escoja al azar el tamaño de baraja realizar.
            barajar = recursos.getAleatorio(MIN_BARAJA, MAX_BARAJA);

            // intercambia una mutacion (¿Donde y Porque?)
            this.intercambiarMutacion(inidiceCromosoma, barajar);

            // obtiene la cantidad de cantConflictos del cromosoma generado
            poblacion.get(inidiceCromosoma).calcularConflictos();

        }

    }

    /* intercambia una mutación que suceda en los genes 
    *Cambia la posición de las reinas en un cromosoma al azar de acuerdo con el número de intercambios
    *NOTA: siempre tienen que ser dos genes porque si es uno saldria esta figura
    *
    *    1,0,0,0,0,0,0,0,
    *    0,1,0,0,0,0,0,0,
    *    0,0,1,0,0,0,0,0,
    *    0,0,0,1,0,0,0,0,
    *    0,0,0,1,0,0,0,0,
    *    0,0,0,0,0,1,0,0,
    *    0,0,0,0,0,0,1,0,
    *    0,0,0,0,0,0,0,1,
    *
    * entonces tienen que ser dos genes para cambiar entre si posiciones
     */
    public void intercambiarMutacion(int indice, int intercambio) {

        int i = 0;
        int tempGen1, tempGen2, gen1, gen2;
        boolean terminado = false;
        cromosoma = poblacion.get(indice);

        while (!terminado) {

            //Face del cruce en dos puntos
            gen1 = recursos.getAleatorio(0, ANCHO_TABLERO - 1);
            gen2 = recursos.getAleatorioExclusivo(ANCHO_TABLERO - 1, gen1);
            //System.out.println("El gen1: " + gen1 + " gen2:" + gen2);

            // Cambia los genes seleccionados cruzadamente
            tempGen1 = cromosoma.getVecSolucion(gen1);
            tempGen2 = cromosoma.getVecSolucion(gen2);

            cromosoma.setVecSolucion(gen1, tempGen2);
            cromosoma.setVecSolucion(gen2, tempGen1);

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
        for (int i = 0; i < NUM_DESENDENCIA; i++) {

            // aqui se selecciona un padre 
            padreA = SeleccionarPadre();

            // Probabilidad de prueba de reproduccion.
            getRand = recursos.getAleatorio(0, 100);

            if (getRand <= (PROB_APAREAMIENTO * 100)) {

                // se obtiene un nuevo padre que no sea igual al padre A
                padreB = SeleccionarPadre(padreA);

                // se crean dos nuevos cromosomas 
                newCromosoma1 = new Cromosoma(ANCHO_TABLERO);
                newCromosoma2 = new Cromosoma(ANCHO_TABLERO);

                // se agrega el cromosoma creado a la poblacion 
                poblacion.add(newCromosoma1);

                // se obtiene el indice del cromosoma creado
                newIndex1 = poblacion.indexOf(newCromosoma1);

                // se agrega el cromosoma creado a la poblacion 
                poblacion.add(newCromosoma2);

                // se obtiene el indice del cromosoma creado
                newIndex2 = poblacion.indexOf(newCromosoma2);

                // Elige uno o ambos de los siguientes: ahora con objetos 
                cruce.cruceParcial(padreA, padreB, newIndex1, newIndex2);

                // se compara el numero de hijos con el de la sig mutacion 
                if (numHijos - 1 == sigMutacion) {

                    // se genera una mutacion al azar y de baraja solo una vaz
                    intercambiarMutacion(newIndex1, 1);
                } else if (numHijos == sigMutacion) {

                    // se genera una mutacion al azar y de baraja solo una vez
                    intercambiarMutacion(newIndex2, 1);
                }

                poblacion.get(newIndex1).calcularConflictos();
                poblacion.get(newIndex2).calcularConflictos();

                numHijos += 2;

                // Programa la siguiente mutacion.
                if (numHijos % (int) Math.round(1.0 / TASA_MUTACION) == 0) {
                    sigMutacion = numHijos + recursos.getAleatorio(0, (int) Math.round(1.0 / TASA_MUTACION));
                }
            }
        }
    }

    // Seleccionar los padres para la reproduccion
    public int SeleccionarPadre() {
        // Función encargada de, vea tambien "choosepadre (ByVal Parenta As Integer)".
        int padre = 0;
        boolean terminado = false;

        while (!terminado) {
            // Elige al azar un padre elegible.
            padre = recursos.getAleatorio(0, poblacion.size() - 1);
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
            padre = recursos.getAleatorio(0, poblacion.size() - 1);
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
    public void prepararSiguienteGeneracion() {
        // Restaura estado de cromosoma
        for (Cromosoma c : poblacion) {
            c.setSeleccionado(false);
        }

    }

}
