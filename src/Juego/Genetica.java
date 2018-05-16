/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Interfaz.Interfaz;
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
    Reproduccion reproduccion;

    private Cromosoma c_result;
    

    public Genetica() {
        this.recursos = new Recursos();
        this.seleccion = new Seleccion();
        this.fitness = new Fitness();
        this.cruce = new Cruce();
        this.mutacion = new Mutacion();
        this.nuevaPoblacion = new NuevaPoblacion();
        this.reproduccion = new Reproduccion();
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
                    poblacion.get(i).setIsSolucion(true);
                    terminado = true;
                }
            }
            //EvaluaR el fitness por cromosoma significa representar
            //la cantidad de coliciones en un valor porcentual de la solución
            fitness.evaluar();

            if (!terminado) {

                /**
                * Aqui se genera la seleccion 
                */
                // se utiliza la ruleta como algoritmo de seleccion 
                seleccion.ruleta();

                //Realiza el cruce parcial de los padres
                reproduccion.generarReproduccion();

                //Selecciona los hijos de la siguiente generación
                reproduccion.prepararSiguienteGeneracion();

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
                        //+ " Fitness: " + cromosoma.fitness
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
            
            inidiceCromosoma = poblacion.indexOf(nuevoCromosoma);
            
            // intercambia una mutacion (¿Donde y Porque?)
            mutacion = new Mutacion();
            mutacion.intercambiarOrden(inidiceCromosoma, barajar);

            // obtiene la cantidad de cantConflictos del cromosoma generado
            poblacion.get(poblacion.size() - 1).calcularConflictos();

        }
    }    

}
