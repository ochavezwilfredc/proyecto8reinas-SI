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

    // --------------------------------------------------------------------------------------------------------------------------------------------
}
