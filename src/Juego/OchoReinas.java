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
        this.generarPoblacionInicial();
        numMutaciones = 0;

        while (!terminado) {
            //Recorre toda la población buscando un cromosoma solucion
            for (int i = 0; i < poblacion.size(); i++) {
                
                // si el conflicto == 0 es porque ese es el ganador y la solucion
                if (poblacion.get(i).getConflictos() == 0) {
                    terminado = true;
                }
            }
            //EvaluaR el fitness por cromosoma significa representar
            //la cantidad de coliciones en un valor porcentual de la solución
            fitness.evaluar();

            if (!terminado) {

                //Selecciona los padres de la siguiente generación
                seleccion.ruletaSeleccion();

                //Realiza el cruce parcial de los padres
                this.generarReproduccion();

                //Selecciona los hijos de la siguiente generación
                this.prepararSiguienteGeneracion();

                generacion++;

            }

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
                + "\tNro. Hijos Creados: " + numHijos + "\n");
        return this.c_result;
    }

    /**
     * Método genera la población inicial
     */
    public void generarPoblacionInicial() {
        int barajar;
        Cromosoma nuevoCromosoma;
        int inidiceCromosoma;

        for (int i = 0; i < POBLACION_INICIAL; i++) {
            // se genera un cromosoma nuevo
            nuevoCromosoma = new Cromosoma(ANCHO_TABLERO);

            // se agrega el cromosoma a la lista(población)
            poblacion.add(nuevoCromosoma);

            // Escoja al azar el tamaño de baraja realizar.
            barajar = recursos.getAleatorio(MIN_BARAJA, MAX_BARAJA);

            // intercambia una mutacion (¿Donde y Porque?)
            this.intercambiarMutacion(poblacion.size() - 1, barajar);

            // obtiene la cantidad de cantConflictos del cromosoma generado
            poblacion.get(poblacion.size() - 1).calcularConflictos();

        }
    }

    /**
     * intercambia una mutación que suceda en los genes Cambia la posición de
     * las reinas en un cromosoma al azar de acuerdo con el número de
     * intercambios NOTA: siempre tienen que ser dos genes porque si es uno
     * saldria esta figura
     *
     * 1,0,0,0,0,0,0,0, 0,1,0,0,0,0,0,0, 0,0,1,0,0,0,0,0, 0,0,0,1,0,0,0,0,
     * 0,0,0,1,0,0,0,0, 0,0,0,0,0,1,0,0, 0,0,0,0,0,0,1,0, 0,0,0,0,0,0,0,1,
     *
     * entonces tienen que ser dos genes para cambiar entre si posiciones
     */
    public void intercambiarMutacion(int indice, int intercambio) {

        int i = 0;
        int tempGen1, tempGen2, posGen1, posGen2;
        boolean terminado = false;
        cromosoma = poblacion.get(indice);

        while (!terminado) {

            //Face del cruce en dos puntos
            posGen1 = recursos.getAleatorio(0, ANCHO_TABLERO - 1);
            posGen2 = recursos.getAleatorioExclusivo(ANCHO_TABLERO - 1, posGen1);

            // Cambia los genes seleccionados cruzadamente
            tempGen1 = cromosoma.getVecSolucion(posGen1);
            tempGen2 = cromosoma.getVecSolucion(posGen2);

            cromosoma.setVecSolucion(posGen1, tempGen2);
            cromosoma.setVecSolucion(posGen2, tempGen1);

            if (i == intercambio) {
                terminado = true;
            }
            i++;
        }
        numMutaciones++;
    }

    // Produce una nueva generacion
    public void generarReproduccion() {

        int getRand, posPadreA, posPadreB, indiceCromoHijoPA, indiceCromoHijoPB;
        Cromosoma cromoHijoPA, cromoHijoPB;

        // este for es del numero de descendencias que genera la generarReproduccion 
        for (int i = 0; i < NUM_DESENDENCIA; i++) {

            // aqui se selecciona un padre 
            posPadreA = this.seleccionarPadre();

            // Probabilidad de prueba de generarReproduccion.
            getRand = recursos.getAleatorio(0, 100);

            if (getRand <= (PROB_APAREAMIENTO * 100)) {

                // se obtiene un nuevo padre que no sea igual al padre A
                posPadreB = seleccionarPadre(posPadreA);

                // se crean dos nuevos cromosomas 
                cromoHijoPA = new Cromosoma(ANCHO_TABLERO);
                cromoHijoPB = new Cromosoma(ANCHO_TABLERO);
                

                // se agrega el cromosoma creado a la poblacion 
                poblacion.add(cromoHijoPA);

                // se obtiene el indice del cromosoma creado
                indiceCromoHijoPA = poblacion.indexOf(cromoHijoPA);

                // se agrega el cromosoma creado a la poblacion 
                poblacion.add(cromoHijoPB);

                // se obtiene el indice del cromosoma creado
                indiceCromoHijoPB = poblacion.indexOf(cromoHijoPB);

                // Elige uno o ambos de los siguientes: ahora con objetos 
                cruce.cruceParcial(posPadreA, posPadreB, indiceCromoHijoPA, indiceCromoHijoPB);

                poblacion.get(indiceCromoHijoPA).calcularConflictos();
                poblacion.get(indiceCromoHijoPB).calcularConflictos();

                numHijos += 2;
            }
        }
    }

    // Seleccionar los padres para la generarReproduccion
    public int seleccionarPadre() {
        int posPadre = 0;
        boolean terminado = false;

        while (!terminado) {
            // Elige al azar un padre elegible.
            posPadre = recursos.getAleatorio(0, poblacion.size() - 1);
            cromosoma = poblacion.get(posPadre);
            if (cromosoma.getSeleccionado()) {
                terminado = true;
            }
        }

        return posPadre;
    }

    // Seleccionar los padres para la generarReproduccion, diferente al seleccionado
    // Función encargada, consulta "choosepadre()".
    public int seleccionarPadre(int padreA) {
        int padre = 0;
        boolean terminado = false;

        while (!terminado) {

            // Elige al azar un padre elegible.
            padre = recursos.getAleatorio(0, poblacion.size() - 1);
            if (padre != padreA) {
                cromosoma = poblacion.get(padre);
                if (cromosoma.getSeleccionado() == true) {
                    terminado = true;
                }
            }
        }

        return padre;
    }

    // Prepara la poblacion de la siguiente generacion
    public void prepararSiguienteGeneracion() {
        // Restaura estado de cromosoma
        poblacion.forEach((c) -> {
            c.setSeleccionado(false);
        });

    }

}
