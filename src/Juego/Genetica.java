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
    SiguientePoblacion nuevaPoblacion;
    Reproduccion reproduccion;

    private Cromosoma cromoResult;

    public Genetica() {
        this.recursos = new Recursos();
        this.seleccion = new Seleccion();
        this.fitness = new Fitness();
        this.cruce = new Cruce();
        this.mutacion = new Mutacion();
        this.nuevaPoblacion = new SiguientePoblacion();
        this.reproduccion = new Reproduccion();
    }

    /**
     * Método principal para la simulación AG
     *
     * @return
     */
    public Cromosoma algoritmoGenetico() {

        boolean esFinal = false;
        //condición para generar una uniaca poblacion
        if (poblacion.isEmpty()) {
            this.generarPoblacionInicial();
        }
        numMutaciones = 0;

        while (!esFinal) {
            if (this.recursos.haySolucion()) {
                esFinal = true;
            }
            //EvaluaR el fitness por cromosoma significa representar
            //la cantidad de coliciones en un valor porcentual de la solución
            fitness.evaluar();

            if (!esFinal) {

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
        poblacion.stream().filter((c) -> (c.getConflictos() == 0)).map((c) -> {
            this.cromoResult = c;
            return c;
        }).map((c) -> {
            System.out.println("\nCromosoma Solución:\n"
                    + Arrays.toString(c.getVec_genes())
                    + " #Coliciones: " + c.cantConflictos
                    + " #Poblacion: " + poblacion.size());
            return c;
        }).forEachOrdered((c) -> {
            this.recursos.imprimirSolucionFinal(c);
        });

        System.out.println("-------------------------\n"
                + "Resuelto en:\n "
                + "\tGeneraciones: " + generacion + "\n"
                + "\tNro. Hijos Creados: " + numHijos + "\n"
                + " #Poblacion  : " + poblacion.size());
        return this.cromoResult;
    }

    /**
     * Método genera la población inicial
     */
    public void generarPoblacionInicial() {
        
        Cromosoma nuevoCromosoma;
        int azar;

        for (int i = 0; i < POBLACION_INICIAL; i++) {
            
            // se genera un cromosoma nuevo
            nuevoCromosoma = new Cromosoma(ANCHO_TABLERO);
            
            for (int j = 0; j < ANCHO_TABLERO; j++) {
                azar = recursos.getAleatorio(0, ANCHO_TABLERO - 1);
                nuevoCromosoma.setVecSolucion(j, azar);
            }
            // se calculan los conflictos
            nuevoCromosoma.calcularConflictos();
            // se agrega el cromosoma a la lista(población)
            poblacion.add(nuevoCromosoma);
                      

        }
    }

    public static void main(String[] args) {
        Genetica reinas = new Genetica();
        Cromosoma c = reinas.algoritmoGenetico();
    }

}
