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
public class Genetica implements Icondiciones {

    // numero de mutaciones
    public static int numMutaciones = 0;

    // numero de generaciones
    public static int generacion = 0;

    // numero de hijos
    public static int numHijos = 0;

    Recursos recursos;
    Seleccion seleccion;
    Fitness fitness;
    Cromosoma cromosoma;
    Cruce cruce;
    Mutacion mutacion;
    NuevaPoblacion nuevaPoblacion;

    private Cromosoma c_result;

    public Genetica() {
        this.recursos = new Recursos();
        this.seleccion = new Seleccion();
        this.fitness = new Fitness();
        this.cruce = new Cruce();
        this.mutacion = new Mutacion();
        this.nuevaPoblacion = new NuevaPoblacion();
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
                        + " #Coliciones: " + cromosoma.cantConflictos
                        + " #Poblacion Final : " + poblacion.size());
                this.recursos.imprimirSolucionFinal(cromosoma);

            }
        }

        System.out.println("-------------------------\n"
                + "Resuelto en:\n "
                + "\tGeneraciones: " + generacion + "\n"
                + "\tNro. Hijos Creados: " + numHijos + "\n"
                + " #Poblacion Final : " + poblacion.size());
        return this.c_result;
    }

    /**
     * Método genera la población inicial
     */
    public void generarPoblacionInicial() {
        int barajar;
        Cromosoma nuevoCromosoma ;
        Mutacion mutacion = null;
        int inidiceCromosoma;

        for (int i = 0; i < POBLACION_INICIAL; i++) {
            // se genera un cromosoma nuevo
            nuevoCromosoma = new Cromosoma(ANCHO_TABLERO);

            // se agrega el cromosoma a la lista(población)
            poblacion.add(nuevoCromosoma);

            // Escoja al azar el tamaño de baraja realizar.
            barajar = recursos.getAleatorio(MIN_BARAJA, MAX_BARAJA);

            // intercambia una mutacion (¿Donde y Porque?)
            mutacion = new Mutacion();
            mutacion.intercambiarOrden(poblacion.size() - 1, barajar);

            // obtiene la cantidad de cantConflictos del cromosoma generado
            poblacion.get(poblacion.size() - 1).calcularConflictos();

        }
    }


    // Produce una nueva generacion
    public void generarReproduccion() {

        int getRand, posPadreA, posPadreB, indiceCromoHijoPA, indiceCromoHijoPB;
        Cromosoma cromoHijoPA, cromoHijoPB, auxH1, auxH2;

        // se crean dos nuevos cromosomas 
        cromoHijoPA = new Cromosoma(ANCHO_TABLERO);
        cromoHijoPB = new Cromosoma(ANCHO_TABLERO);
        
        // aqui se selecciona un padre 
        posPadreA = this.seleccionarPadre();

        // se obtiene un nuevo padre que no sea igual al padre A
        posPadreB = this.seleccionarPadre(posPadreA);
        
        /**
         * Aqui se genera el cruce 
         */
        
        // Elige uno o ambos de los siguientes: ahora con objetos 
        cruce.cruceParcial(posPadreA, posPadreB, cromoHijoPA, cromoHijoPB);
        
        
        /**
         * Aqui empieza la mutacion
         */
        
        // de inversion de Genes
        // ------------> auxH1 = mutacion.inversionGenes(cromoHijoPA);
        // ------------> auxH2 = mutacion.inversionGenes(cromoHijoPB);
        
        // de modificacion de genes
        auxH1 = mutacion.modificacionGenes(cromoHijoPA);
        auxH2 = mutacion.modificacionGenes(cromoHijoPB);
        
        /**
         * Aqui empieza seleccion para la nueva generacion
         */
        
        // *********************** de aceptacion total ***********************
        // ------------>  nuevaPoblacion.aceptacionTotal(auxH1, auxH2);
        
        // *********************** de mejora ***********************
        // ------------> nuevaPoblacion.deMejora(posPadreA, posPadreB, auxH1, auxH2);
        
        // *********************** por torneo ***********************
        nuevaPoblacion.porTorneo(auxH1, auxH2);
        
        /**
         * Aqui se agrega agrega el numero de hijos
         */
        
        numHijos += 2;
  
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
