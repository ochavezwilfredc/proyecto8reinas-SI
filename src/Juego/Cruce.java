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
public class Cruce implements Icondiciones {

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
        int j ,item1, item2, pos1 ,pos2;
        pos1=pos2=0;

        Cromosoma cromosomaAux1 = poblacion.get(chromA);
        Cromosoma cromosomaAux2 = poblacion.get(chromB);
        Cromosoma newChromo1 = poblacion.get(hijo1);
        Cromosoma newChromo2 = poblacion.get(hijo2);

        int crossPoint1 = recursos.getAleatorio(0, ANCHO_TABLERO - 1);
        int crossPoint2 = recursos.getAleatorioExclusivo(ANCHO_TABLERO - 1, crossPoint1);

        if (crossPoint2 < crossPoint1) {
            j = crossPoint1;
            crossPoint1 = crossPoint2;
            crossPoint2 = j;
        }

        // Copia los genes de padres a hijos.
        for (int i = 0; i < ANCHO_TABLERO; i++) {
            newChromo1.setGene(i, cromosomaAux1.getGene(i));
            newChromo2.setGene(i, cromosomaAux2.getGene(i));
        }

        for (int i = crossPoint1; i <= crossPoint2; i++) {
            // Obtener los dos elementos que intercambian.
            item1 = cromosomaAux1.getGene(i);
            item2 = cromosomaAux2.getGene(i);

            // Obtiene los items, posiciones en la descendencia.
            for (j = 0; j < ANCHO_TABLERO; j++) {
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
            for (j = 0; j < ANCHO_TABLERO; j++) {
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

        int k, numPoints;
        int tempArray1[] = new int[ANCHO_TABLERO];
        int tempArray2[] = new int[ANCHO_TABLERO];
        boolean matchFound = false;

        Cromosoma cromosoma = poblacion.get(chromA);
        Cromosoma comosomaAux = poblacion.get(chromB);
        Cromosoma newChromo1 = poblacion.get(hijo1);
        Cromosoma newChromo2 = poblacion.get(hijo2);


        // Elegir y ordenar los puntos de cruce.
        numPoints = recursos.getAleatorio(0, PTS_CRUCE);

        int crossPoints[] = new int[numPoints];

        for (int i = 0; i < numPoints; i++) {
            crossPoints[i] = recursos.getNumeroAleatorio(0, ANCHO_TABLERO - 1, crossPoints);
        } // i

        // Obtenga no elegidos de los padres 2
        k = 0;
        for (int i = 0; i < ANCHO_TABLERO; i++) {
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
        for (int i = 0; i < ANCHO_TABLERO; i++) {
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
        for (int i = 0; i < ANCHO_TABLERO; i++) {
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
        for (int i = 0; i < ANCHO_TABLERO; i++) {
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
