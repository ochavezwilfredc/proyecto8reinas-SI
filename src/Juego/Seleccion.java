/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Recursos.Recursos;
import static Juego.Genetica.poblacion;
import static Juego.Genetica.MIN_SELECCION;
import static Juego.Genetica.MAX_SELECCION;

/**
 *
 * @author mendoza
 */
public class Seleccion{

    int tamPoblacion;
    double sumFitnessTotal = 0.0;
    double sumTotalProbabilidad;
    int maximoSeleccionar;
    double seleccionar;
    Cromosoma cromosoma, comosomaTemp;
    boolean isTerminado;
    Recursos recursos;

    public Seleccion() {
        this.recursos = new Recursos();
    }

    // Seleccion de cromosomas de la generacion
    public void ruleta() {
        int elemenI;//Elemento(cromosoma) de la población
        tamPoblacion = poblacion.size();
        sumFitnessTotal = 0.0;
        maximoSeleccionar = recursos.getAleatorio(MIN_SELECCION, MAX_SELECCION);
        isTerminado = false;

        // se almacena en la variable sumFitnessTotal donde contendra la suma del fitness 
        //de toda la poblacion
        //obtiene la aptitud total
        for (int i = 0; i < tamPoblacion; i++) {
            cromosoma = poblacion.get(i);
            sumFitnessTotal += cromosoma.getFitness();
        }

        // se multiplica por el 0.01 que genera redondeo
        sumFitnessTotal *= 0.01;

        //  se multiplica por el 0.01 que genera intervalos de 0/1
        for (int i = 0; i < tamPoblacion; i++) {
            cromosoma = poblacion.get(i);
            // establecer la probabilidad de selección. cuanto más se ajuste,
           //mejor será la probabilidad de selección
            cromosoma.setProbabilidad(cromosoma.getFitness() / sumFitnessTotal);
        }

        // seleccionar padres
        for (int i = 0; i < maximoSeleccionar; i++) {

            // El azar a seleccionar es solo hasta 99 porque la suma de las 
            // probabilidades de los cromosomas 
            // es hasta 99.9999 y no llega a 100 como para que
            // rompa la condicion
            seleccionar = recursos.getAleatorio(0, 99);
            isTerminado = false;
            elemenI = 0;
            sumTotalProbabilidad = 0;

            while (!isTerminado) {

                // empieza con la poblacion desde el individuo n°1 hasta el ultimo
                cromosoma = poblacion.get(elemenI);

                // aqui se almacena el total de la probabilidad de seleccion 
                // la cual llega a un 99.9999...
                sumTotalProbabilidad += cromosoma.getProbabilidad();

                if (sumTotalProbabilidad >= seleccionar) {
                    if (elemenI == 0) {
                        // toma el primero
                        comosomaTemp = poblacion.get(elemenI);
                    } else if (elemenI >= tamPoblacion - 1) {
                        // toma el ultimo
                        comosomaTemp = poblacion.get(tamPoblacion - 1);
                    } else {
                        // toma en una posicion determinada
                        comosomaTemp = poblacion.get(elemenI - 1);
                    }

                    comosomaTemp.setSeleccionado(true);
                    isTerminado = true;

                } else {
                    elemenI++;
                }
            }
        }
    }

}
