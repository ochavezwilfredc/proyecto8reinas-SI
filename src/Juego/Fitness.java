/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import static Juego.OchoReinas.poblacion;

/**
 *
 * @author mendoza
 */
public class Fitness {

    // Buscar la mejor aptud 
    public void evaluar() {

        // se busca el Menor error = 100%, Mayor Error = 0%
        int tamPoblacion = poblacion.size();
        Cromosoma cromosoma;
        double mejor, peor, optimo;

        // La peor puntuación seá el que tiene la mayor numero de conflictos , y el mejor el más bajo.
        peor = poblacion.get(this.maximo()).getConflictos();

        // el mejor 
        mejor = poblacion.get(this.minimo()).getConflictos();

        //se cálcula la posición obtima
        optimo = peor - mejor;

        for (int i = 0; i < tamPoblacion; i++) {
            cromosoma = poblacion.get(i);
            cromosoma.setFitness((peor - cromosoma.getConflictos()) * 100.0 / optimo);
        }
    }

      /**
     * Método para obtener el indice maximo de la poblcación con 
     * numero de coliciones minimo
     * @return 
     */
    public int maximo() {

        // Devuelve el indice de una matriz.
        int tamPoblacion;
        Cromosoma cromosoma, comosomaTemp;
        int ganador = 0;
        boolean nuevoGanador, terminado;
        terminado = false;

        while (!terminado) {
            nuevoGanador = false;
            tamPoblacion = poblacion.size();

            // recorre toda la poblacion
            for (int i = 0; i < tamPoblacion; i++) {

                // Evita la auocomparacion 
                if (i != ganador) {
                    cromosoma = poblacion.get(i);
                    comosomaTemp = poblacion.get(ganador);
                    if (cromosoma.getConflictos() > comosomaTemp.getConflictos()) {
                        ganador = i;
                        nuevoGanador = true;
                    }
                }
            }

            // copara la validez del ganador nuevo
            if (nuevoGanador == false) {
                terminado = true;
            }
        }
        // retorna el ganador 
        return ganador;
    }

    /**
     * Método para obtener el indice minimo de la poblcación con 
     * numero de coliciones minimo
     * @return 
     */
    public int minimo() {
        
        // Devuelve el indice de una matriz.
        Cromosoma cromosoma, comosomaTemp;
        int ganador = 0;
        boolean nuevoGanador, terminado;
        terminado = false;

        while (!terminado) {
            nuevoGanador = false;
            // recorre toda la poblacion
            for (int i = 0; i < poblacion.size(); i++) {
                // Evita la auocomparacion 
                if (i != ganador) {
                    cromosoma = poblacion.get(i);
                    comosomaTemp = poblacion.get(ganador);

                    if (cromosoma.getConflictos() < comosomaTemp.getConflictos()) {
                        ganador = i;
                        nuevoGanador = true;
                    }
                }
            }

            // copara la validez del ganador nuevo
            if (nuevoGanador == false) {
                terminado = true;
            }
        }
        // retorna el ganador 
        return ganador;
    }

}
