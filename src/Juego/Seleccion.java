/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Recursos.Recursos;
import static Juego.OchoReinas.poblacion;
import static Juego.OchoReinas.MIN_SELECCION;
import static Juego.OchoReinas.MAX_SELECCION;

/**
 *
 * @author mendoza
 */
public class Seleccion implements Icondiciones {

    int tamPoblacion;
    double genTotal = 0.0;
    double selTotal;
    int maximoSeleccionar;
    double seleccionar;
    Cromosoma cromosoma, comosomaTemp;
    boolean terminado;
    Recursos recursos;

    public Seleccion() {
        this.recursos = new Recursos();
    }

    // Seleccion de cromosomas de la generacion
    public void ruletaSeleccion() {
        int elemenP;//Elemento(cromosoma) de la población
        tamPoblacion = poblacion.size();
        genTotal = 0.0;
        maximoSeleccionar = recursos.getAleatorio(MIN_SELECCION, MAX_SELECCION);
        terminado = false;

        // se almacena en la variable genTotal donde contendra la suma del fitness de toda la poblacion
        //obtiene la actitud total
        for (int i = 0; i < tamPoblacion; i++) {
            cromosoma = poblacion.get(i);
            genTotal += cromosoma.getFitness();
        }

        // se multiplica por el 0.01 que genera redondeo
        genTotal *= 0.01;

        //  se multiplica por el 0.01 que genera intervalos de 0/1
        for (int i = 0; i < tamPoblacion; i++) {
            cromosoma = poblacion.get(i);

            // establecer la probabilidad de selección. cuanto más se ajuste, mejor será la probabilidad de selección
            cromosoma.setSeleccionProbabilidad(cromosoma.getFitness() / genTotal);
        }

        // seleccionar padres
        for (int i = 0; i < maximoSeleccionar; i++) {

            // el azar a seleccionar es solo hasta 99 porque la suma de las probabilidades de los cromosomas 
            // es hasta 99.9999999999999999999 y no llega a 100 como para que rompa la condicion
            seleccionar = recursos.getAleatorio(0, 99);
            terminado = false;
            elemenP = 0;
            selTotal = 0;

            while (!terminado) {

                // empieza con la poblacion desde el iondividuo n° 1 hasta el ultimo
                cromosoma = poblacion.get(elemenP);

                // aqui se almacena el total de la probabilidad de seleccion la cual llega a un 99.9999...
                selTotal += cromosoma.getSeleccionProbabilidad();
                //System.out.println("El selTotal es ( "+selTotal+" ) y seleccionar es  ( "+seleccionar+" )");

                if (selTotal >= seleccionar) {

                    if (elemenP == 0) {
                        // toma el primero
                        comosomaTemp = poblacion.get(elemenP);
                    } else if (elemenP >= tamPoblacion - 1) {
                        // toma el ultimo
                        comosomaTemp = poblacion.get(tamPoblacion - 1);
                    } else {
                        // toma en una posicion determinada
                        comosomaTemp = poblacion.get(elemenP - 1);
                    }

                    comosomaTemp.setSeleccionado(true);
                    terminado = true;

                } else {
                    elemenP++;
                }
            }
        }
    }

}
