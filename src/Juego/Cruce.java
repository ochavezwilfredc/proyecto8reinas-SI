/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import static Juego.OchoReinas.poblacion;
import Recursos.Recursos;
import static Juego.OchoReinas.ANCHO_TABLERO;
import static Juego.OchoReinas.PTS_CRUCE;

/**
 *
 * @author mendoza
 */
public class Cruce {

    Recursos recursos;

    public Cruce() {
        this.recursos = new Recursos();
    }

    /**
     * Cruza con probabilidad dos individuos obteniendo dos decendientes
     *
     * @param chromA posición del padrea A para generar el cruce
     * @param chromB posición del padrea B para generar el cruce
     * @param hijo1 ""
     * @param hijo2 ""
     */
    public void cruceParcial(int chromA, int chromB, int hijo1, int hijo2) {
        int indice, genCromoA, genCromoB, pos1, pos2;
        pos1 = pos2 = 0;

        Cromosoma cromoA = poblacion.get(chromA);
        Cromosoma cromoB = poblacion.get(chromB);
        Cromosoma cromoH1 = poblacion.get(hijo1);
        Cromosoma cromoH2 = poblacion.get(hijo2);

        int puntoCruce1 = recursos.getAleatorio(0, ANCHO_TABLERO - 1);
        int puntoCruce2 = recursos.getAleatorioExclusivo(ANCHO_TABLERO - 1, puntoCruce1);

        if (puntoCruce2 < puntoCruce1) {
            indice = puntoCruce1;
            puntoCruce1 = puntoCruce2;
            puntoCruce2 = indice;
        }

        // Copia los genes de padres a hijos.
        for (int i = 0; i < ANCHO_TABLERO; i++) {
            cromoH1.setVecSolucion(i, cromoA.getVecSolucion(i));
            cromoH2.setVecSolucion(i, cromoB.getVecSolucion(i));
        }

        for (int i = puntoCruce1; i <= puntoCruce2; i++) {
            // Obtener los dos GENES que se van a intercambiar
            genCromoA = cromoA.getVecSolucion(i);
            genCromoB = cromoB.getVecSolucion(i);

            // Obtiene las posiciones en la descendencia el cromosoma A.
            for (indice = 0; indice < ANCHO_TABLERO; indice++) {
                if (cromoH1.getVecSolucion(indice) == genCromoA) {
                    pos1 = indice;
                } else if (cromoH1.getVecSolucion(indice) == genCromoB) {
                    pos2 = indice;
                }
            }

            // Intercambiar.
            if (genCromoA != genCromoB) {
                cromoH1.setVecSolucion(pos1, genCromoB);
                cromoH1.setVecSolucion(pos2, genCromoA);
            }

            // Obtiene las posiciones en la descendencia para el cromosoma B.
            for (indice = 0; indice < ANCHO_TABLERO; indice++) {
                if (cromoH2.getVecSolucion(indice) == genCromoB) {
                    pos1 = indice;
                } else if (cromoH2.getVecSolucion(indice) == genCromoA) {
                    pos2 = indice;
                }
            }

            // Intercambiar.
            if (genCromoA != genCromoB) {
                cromoH2.setVecSolucion(pos1, genCromoA);
                cromoH2.setVecSolucion(pos2, genCromoB);
            }

        }
    }

    /**
     * Cruza con probabilidad dos individuos obteniendo dos decendientes
     *
     * @param chromA
     * @param chromB
     * @param hijo1
     * @param hijo2
     */
    public void crucePorPosicion(int chromA, int chromB, int hijo1, int hijo2) {

        int k, nroPuntos;
        int tempArray1[] = new int[ANCHO_TABLERO];
        int tempArray2[] = new int[ANCHO_TABLERO];
        boolean coincidencia = false;

        Cromosoma cromoA = poblacion.get(chromA);
        Cromosoma cromoB = poblacion.get(chromB);
        Cromosoma cromoH1 = poblacion.get(hijo1);
        Cromosoma cromoH2 = poblacion.get(hijo2);

        // Elegir y ordenar los puntos de cruce.
        nroPuntos = recursos.getAleatorio(0, PTS_CRUCE);

        int vec_puntosCruce[] = new int[nroPuntos];

        for (int i = 0; i < nroPuntos; i++) {
            vec_puntosCruce[i] = recursos.getNumeroAleatorio(0, ANCHO_TABLERO - 1, vec_puntosCruce);
        } 

        // obtine los no elegidos de los padres 2
        k = 0;
        for (int i = 0; i < ANCHO_TABLERO; i++) {
            coincidencia = false;
            for (int j = 0; j < nroPuntos; j++) {
                if (cromoB.getVecSolucion(i) == cromoA.getVecSolucion(vec_puntosCruce[j])) {
                    coincidencia = true;
                }
            } // j
            if (coincidencia == false) {
                tempArray1[k] = cromoB.getVecSolucion(i);
                k++;
            }
        } // i

        // Insertar elegido al hijo 1.
        for (int i = 0; i < nroPuntos; i++) {
            cromoH1.setVecSolucion(vec_puntosCruce[i], cromoA.getVecSolucion(vec_puntosCruce[i]));
        }

        // llenar los no elegidos para hijos 1.
        k = 0;
        for (int i = 0; i < ANCHO_TABLERO; i++) {
            coincidencia = false;
            for (int j = 0; j < nroPuntos; j++) {
                if (i == vec_puntosCruce[j]) {
                    coincidencia = true;
                }
            } // j
            if (coincidencia == false) {
                cromoH1.setVecSolucion(i, tempArray1[k]);
                k++;
            }
        } // i

        // Obtenga no elegidos de los padres 1
        k = 0;
        for (int i = 0; i < ANCHO_TABLERO; i++) {
            coincidencia = false;
            for (int j = 0; j < nroPuntos; j++) {
                if (cromoA.getVecSolucion(i) == cromoB.getVecSolucion(vec_puntosCruce[j])) {
                    coincidencia = true;
                }
            } // j
            if (coincidencia == false) {
                tempArray2[k] = cromoA.getVecSolucion(i);
                k++;
            }
        } // i

        // Inserte elegido en hijos 2.
        for (int i = 0; i < nroPuntos; i++) {
            cromoH2.setVecSolucion(vec_puntosCruce[i], cromoB.getVecSolucion(vec_puntosCruce[i]));
        }

        // Rellene no elegidos para hijos 2.
        k = 0;
        for (int i = 0; i < ANCHO_TABLERO; i++) {
            coincidencia = false;
            for (int j = 0; j < nroPuntos; j++) {
                if (i == vec_puntosCruce[j]) {
                    coincidencia = true;
                }
            } // j
            if (coincidencia == false) {
                cromoH2.setVecSolucion(i, tempArray2[k]);
                k++;
            }
        } // i
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------
}
