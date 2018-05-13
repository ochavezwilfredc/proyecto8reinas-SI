/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import static Juego.OchoReinas.anchoTablero;
import static Juego.OchoReinas.poblacion;
import static Juego.OchoReinas.ptsCruce;
import Recursos.Recursos;

/**
 *
 * @author mendoza
 */
public class Cruce implements Condiciones {

    Recursos recursos;

    public Cruce() {
        this.recursos = new Recursos();
    }

    /**
     * Cruza con probabilidad dos individuos obteniendo dos decendientes
     *
     * @param chromA
     * @param chromB
     * @param hijo1
     * @param hijo2
     */
    public void CruceParcial(int chromA, int chromB, int hijo1, int hijo2) {
        int j = 0;
        int item1 = 0;
        int item2 = 0;
        int pos1 = 0;
        int pos2 = 0;

        Cromosoma cromosomaAux1 = poblacion.get(chromA);
        Cromosoma cromosomaAux2 = poblacion.get(chromB);
        Cromosoma newChromo1 = poblacion.get(hijo1);
        Cromosoma newChromo2 = poblacion.get(hijo2);

        int crossPoint1 = recursos.NumeroAleatorio(0, anchoTablero - 1);
        int crossPoint2 = recursos.NumeroAleatorioExclusivo(anchoTablero - 1, crossPoint1);

        if (crossPoint2 < crossPoint1) {
            j = crossPoint1;
            crossPoint1 = crossPoint2;
            crossPoint2 = j;
        }

        // Copia los genes de padres a hijos.
        for (int i = 0; i < anchoTablero; i++) {
            newChromo1.setGene(i, cromosomaAux1.getGene(i));
            newChromo2.setGene(i, cromosomaAux2.getGene(i));
        }

        for (int i = crossPoint1; i <= crossPoint2; i++) {
            // Obtener los dos elementos que intercambian.
            item1 = cromosomaAux1.getGene(i);
            item2 = cromosomaAux2.getGene(i);

            // Obtiene los items, posiciones en la descendencia.
            for (j = 0; j < anchoTablero; j++) {
                if (newChromo1.getGene(j) == item1) {
                    pos1 = j;
                } else if (newChromo1.getGene(j) == item2) {
                    pos2 = j;
                }
            }

            // Intercambiar.
            if (item1 != item2) {
                newChromo1.setGene(pos1, item2);
                newChromo1.setGene(pos2, item1);
            }

            // Obtiene los items, posiciones en la descendencia.
            for (j = 0; j < anchoTablero; j++) {
                if (newChromo2.getGene(j) == item2) {
                    pos1 = j;
                } else if (newChromo2.getGene(j) == item1) {
                    pos2 = j;
                }
            }

            // Intercambiar.
            if (item1 != item2) {
                newChromo2.setGene(pos1, item1);
                newChromo2.setGene(pos2, item2);
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
    public void CrucePorPosicion(int chromA, int chromB, int hijo1, int hijo2) {

        int k = 0;
        int numPoints = 0;
        int tempArray1[] = new int[anchoTablero];
        int tempArray2[] = new int[anchoTablero];
        boolean matchFound = false;

        Cromosoma cromosoma = poblacion.get(chromA);
        Cromosoma comosomaAux = poblacion.get(chromB);
        Cromosoma newChromo1 = poblacion.get(hijo1);
        Cromosoma newChromo2 = poblacion.get(hijo2);


        // Elegir y ordenar los puntos de cruce.
        numPoints = recursos.NumeroAleatorio(0, ptsCruce);

        int crossPoints[] = new int[numPoints];

        for (int i = 0; i < numPoints; i++) {
            crossPoints[i] = recursos.NumeroAleatorio(0, anchoTablero - 1, crossPoints);
        } // i

        // Obtenga no elegidos de los padres 2
        k = 0;
        for (int i = 0; i < anchoTablero; i++) {
            matchFound = false;
            for (int j = 0; j < numPoints; j++) {
                if (comosomaAux.getGene(i) == cromosoma.getGene(crossPoints[j])) {
                    matchFound = true;
                }
            } // j
            if (matchFound == false) {
                tempArray1[k] = comosomaAux.getGene(i);
                k++;
            }
        } // i

        // Insertar elegido al hijo 1.
        for (int i = 0; i < numPoints; i++) {
            newChromo1.setGene(crossPoints[i], cromosoma.getGene(crossPoints[i]));
        }

        // Rellene no elegidos para hijos 1.
        k = 0;
        for (int i = 0; i < anchoTablero; i++) {
            matchFound = false;
            for (int j = 0; j < numPoints; j++) {
                if (i == crossPoints[j]) {
                    matchFound = true;
                }
            } // j
            if (matchFound == false) {
                newChromo1.setGene(i, tempArray1[k]);
                k++;
            }
        } // i

        // Obtenga no elegidos de los padres 1
        k = 0;
        for (int i = 0; i < anchoTablero; i++) {
            matchFound = false;
            for (int j = 0; j < numPoints; j++) {
                if (cromosoma.getGene(i) == comosomaAux.getGene(crossPoints[j])) {
                    matchFound = true;
                }
            } // j
            if (matchFound == false) {
                tempArray2[k] = cromosoma.getGene(i);
                k++;
            }
        } // i

        // Inserte elegido en hijos 2.
        for (int i = 0; i < numPoints; i++) {
            newChromo2.setGene(crossPoints[i], comosomaAux.getGene(crossPoints[i]));
        }

        // Rellene no elegidos para hijos 2.
        k = 0;
        for (int i = 0; i < anchoTablero; i++) {
            matchFound = false;
            for (int j = 0; j < numPoints; j++) {
                if (i == crossPoints[j]) {
                    matchFound = true;
                }
            } // j
            if (matchFound == false) {
                newChromo2.setGene(i, tempArray2[k]);
                k++;
            }
        } // i
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------
}
