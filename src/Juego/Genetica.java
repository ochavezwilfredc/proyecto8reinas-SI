/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Recursos.Recursos;
import java.util.Arrays;

/**
 *
 * @author mendoza
 */
public class Genetica implements Icondiciones{

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
    Aceptacion nuevaPoblacion;
    Reproduccion reproduccion;

    private Cromosoma cromoResult;

    //Vector para la configuracion
    int[] vecConfig;

    //Tiempo
    private long tiempoInicial;

    public Genetica(int v[]) {
        this.recursos = new Recursos();
        this.seleccion = new Seleccion();
        this.fitness = new Fitness();
        this.cruce = new Cruce();
        this.mutacion = new Mutacion();
        this.nuevaPoblacion = new Aceptacion();

        //recibo el vector de configuracion de la prueba desde la interfaz
        this.reproduccion = new Reproduccion(v);
        this.vecConfig = v;
        
        //Tiempo inicial de referencia
        this.tiempoInicial= System.currentTimeMillis();
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

                //Método que evalua la condición de la fase 1 = selección
                this.condicionesPruebaSeleccion();

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

        for (int i = 0; i < POBLACION; i++) {

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

    /**
     * Método que evalua la condiciones de la prueba
     */
    private void condicionesPruebaSeleccion() {
        //Configuraciones Fase1 = selección
        switch (vecConfig[0]) {
            case 0://ruleta
                this.seleccion.ruleta();
                break;
            case 1://torneo
                this.seleccion.torneo();
                break;
            case 2://ranking
                this.seleccion.ranking();
                break;
            case 3://Elitista
                this.seleccion.elitista();
                break;
        }
    }
    
    //GET AND SET

    public static int getNumMutaciones() {
        return numMutaciones;
    }

    public static void setNumMutaciones(int numMutaciones) {
        Genetica.numMutaciones = numMutaciones;
    }

    public static int getGeneracion() {
        return generacion;
    }

    public static void setGeneracion(int generacion) {
        Genetica.generacion = generacion;
    }

    public static int getNumHijos() {
        return numHijos;
    }

    public static void setNumHijos(int numHijos) {
        Genetica.numHijos = numHijos;
    }

    public Recursos getRecursos() {
        return recursos;
    }

    public void setRecursos(Recursos recursos) {
        this.recursos = recursos;
    }

    public Seleccion getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(Seleccion seleccion) {
        this.seleccion = seleccion;
    }

    public Fitness getFitness() {
        return fitness;
    }

    public void setFitness(Fitness fitness) {
        this.fitness = fitness;
    }

    public Cromosoma getCromosoma() {
        return cromosoma;
    }

    public void setCromosoma(Cromosoma cromosoma) {
        this.cromosoma = cromosoma;
    }

    public Cruce getCruce() {
        return cruce;
    }

    public void setCruce(Cruce cruce) {
        this.cruce = cruce;
    }

    public Mutacion getMutacion() {
        return mutacion;
    }

    public void setMutacion(Mutacion mutacion) {
        this.mutacion = mutacion;
    }

    public Aceptacion getNuevaPoblacion() {
        return nuevaPoblacion;
    }

    public void setNuevaPoblacion(Aceptacion nuevaPoblacion) {
        this.nuevaPoblacion = nuevaPoblacion;
    }

    public Reproduccion getReproduccion() {
        return reproduccion;
    }

    public void setReproduccion(Reproduccion reproduccion) {
        this.reproduccion = reproduccion;
    }

    public Cromosoma getCromoResult() {
        return cromoResult;
    }

    public void setCromoResult(Cromosoma cromoResult) {
        this.cromoResult = cromoResult;
    }

    public int[] getVecConfig() {
        return vecConfig;
    }

    public void setVecConfig(int[] vecConfig) {
        this.vecConfig = vecConfig;
    }

    public long getTiempoInicial() {
        return tiempoInicial;
    }

    public void setTiempoInicial(long tiempoInicial) {
        this.tiempoInicial = tiempoInicial;
    }
    
    

}
