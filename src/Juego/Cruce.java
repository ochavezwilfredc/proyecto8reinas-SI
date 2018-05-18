/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import static Juego.Genetica.poblacion;
import Recursos.Recursos;
import static Juego.Genetica.ANCHO_TABLERO;
import static Juego.Icondiciones.ANCHO_TABLERO;
import static Juego.Icondiciones.poblacion;
import java.util.Arrays;

/**
 *
 * @author mendoza
 */
public class Cruce {

    Recursos recursos;

    public Cruce() {
        this.recursos = new Recursos();
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------
    public Cromosoma cruceUnPunto(int chromA, int chromB, Cromosoma hijo) {

        //int inidice, genCromoA, genCromoB, pos1, pos2;
        Cromosoma cromoA;
        Cromosoma cromoB;

        int valor = recursos.getAleatorio(0, 1);

        int puntoCruceHijo = recursos.getAleatorio(0, ANCHO_TABLERO - 1);

        if (valor == 0) {
            cromoA = poblacion.get(chromA);
            cromoB = poblacion.get(chromB);
        } else {
            cromoA = poblacion.get(chromB);
            cromoB = poblacion.get(chromA);
        }

        for (int i = 0; i < ANCHO_TABLERO; i++) {

            if (i <= puntoCruceHijo) {
                hijo.setVecSolucion(i, cromoA.getVecSolucion(i));
            } else {
                hijo.setVecSolucion(i, cromoB.getVecSolucion(i));
            }

        }
        return hijo;
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------
    public Cromosoma[] cruceDosPuntos(int posPadres[], Cromosoma cromosomasHijos[]) {

        Cromosoma Padre1, Padre2;
        int punto1, punto2, indice;

        for (int i = 0; i < cromosomasHijos.length; i++) {
            
            // se le da limites de aleatoriedad
            punto1 = recursos.getAleatorio(1, ANCHO_TABLERO-2);
            punto2 = recursos.getAleatorioExclusivo(1,ANCHO_TABLERO-2, punto1);
            // punto2 = recursos.getAleatorioExclusivo(0,ANCHO_TABLERO - 1, punto1);

            if (punto2 < punto1) {
                indice = punto1;
                punto1 = punto2;
                punto2 = indice;
            }

            if (i == 0) {
                Padre1 = poblacion.get(posPadres[0]);
                Padre2 = poblacion.get(posPadres[1]);
            } else {
                Padre1 = poblacion.get(posPadres[1]);
                Padre2 = poblacion.get(posPadres[0]);
            }

            for (int j = 0; j < ANCHO_TABLERO; j++) {

                if (j <= punto1) {
                    cromosomasHijos[i].setVecSolucion(j, Padre1.getVecSolucion(j));
                } else if (j >= punto2) {
                    cromosomasHijos[i].setVecSolucion(j, Padre1.getVecSolucion(j));
                } else {
                    cromosomasHijos[i].setVecSolucion(j, Padre2.getVecSolucion(j));
                }

            }
        }
        System.out.println("pos padres  " + posPadres[0] + " padre 2 " + posPadres[1]);
        System.out.println("El tamaño del cromosoma padre es " + posPadres.length + " e hijos " + cromosomasHijos.length);
        System.out.println("devuelvo -Z " + Arrays.toString(cromosomasHijos));
        return cromosomasHijos;
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------
    public Cromosoma cruceUniforme(int chromA, int chromB, Cromosoma hijo) {

        Cromosoma cromoA;
        Cromosoma cromoB;

        int valor = recursos.getAleatorio(0, 1);

        if (valor == 0) {
            cromoA = poblacion.get(chromA);
            cromoB = poblacion.get(chromB);
        } else {
            cromoA = poblacion.get(chromB);
            cromoB = poblacion.get(chromA);
        }

        for (int i = 0; i < ANCHO_TABLERO; i++) {

            if (i % 2 == 0) {
                hijo.setVecSolucion(i, cromoA.getVecSolucion(i));
            } else {
                hijo.setVecSolucion(i, cromoB.getVecSolucion(i));
            }
        }
        return hijo;
    }

//    public Cromosoma[] cruceDosPuntos(int posPadres[], Cromosoma cromosomasHijos[]) {
//
//        Cromosoma Padre1, Padre2;
//        int punto1, punto2, valor, indice;
//
//        for (int i = 0; i < cromosomasHijos.length; i++) {
//
//            punto1 = recursos.getAleatorio(0, ANCHO_TABLERO - 1);
//            punto2 = recursos.getAleatorioExclusivo(ANCHO_TABLERO - 1, punto1);
//
//            if (punto2 < punto1) {
//                indice = punto1;
//                punto1 = punto2;
//                punto2 = indice;
//            }
//            
//            valor = recursos.getAleatorio(0, 1);
//
//            if (valor == 0) {
//                Padre1 = poblacion.get(posPadres[0]);
//                Padre2 = poblacion.get(posPadres[1]);
//            } else {
//                Padre1 = poblacion.get(posPadres[1]);
//                Padre2 = poblacion.get(posPadres[0]);
//            }
//
//            for (int j = 0; j < ANCHO_TABLERO; j++) {
//
//                    if (i <= punto1) {
//                        cromosomasHijos[i].setVecSolucion(i, Padre1.getVecSolucion(i));
//                    } else if (i >= punto2) {
//                        cromosomasHijos[i].setVecSolucion(i, Padre1.getVecSolucion(i));
//                    } else {
//                        cromosomasHijos[i].setVecSolucion(i, Padre2.getVecSolucion(i));
//                    }
//
//
//            }
//
//        }
//        System.out.println("El tamaño del cromosoma padre es " + posPadres.length + " e hijos " + cromosomasHijos.length);
//        return cromosomasHijos;
//    }
}
