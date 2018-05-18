/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Recursos.Recursos;
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

    public Cromosoma[] cruceUnPunto(int posPadres[], Cromosoma cromosomasHijos[]) {

        Cromosoma Padre1, Padre2;
        int puntoCruceHijo;
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < posPadres.length; i++) {

            puntoCruceHijo = recursos.getAleatorio(0, ANCHO_TABLERO - 1);

            if (i % 2 == 0) {
                Padre1 = poblacion.get(posPadres[0]);
                Padre2 = poblacion.get(posPadres[1]);
            } else {
                Padre1 = poblacion.get(posPadres[1]);
                Padre2 = poblacion.get(posPadres[0]);
            }
            
            System.out.println("Padre 1 " + Arrays.toString(Padre1.getVec_genes()));
            System.out.println("Padre 2 " + Arrays.toString(Padre2.getVec_genes()));

            for (int j = 0; j < ANCHO_TABLERO; j++) {

                if (j <= puntoCruceHijo) {
                    cromosomasHijos[i].setVecSolucion(j, Padre1.getVecSolucion(j));
                } else {
                    cromosomasHijos[i].setVecSolucion(j, Padre2.getVecSolucion(j));
                }
            }
        }
        
        System.out.println("Hijo 1 " + Arrays.toString(cromosomasHijos[0].getVec_genes()));
        System.out.println("Hijo 2 " + Arrays.toString(cromosomasHijos[1].getVec_genes()));
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");
        
        return cromosomasHijos;
    }

    public Cromosoma[] cruceDosPuntos(int posPadres[], Cromosoma cromosomasHijos[]) {

        Cromosoma Padre1, Padre2;
        int punto1, punto2, indice;
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < cromosomasHijos.length; i++) {

            // se le da limites de aleatoriedad
            punto1 = recursos.getAleatorio(0, 2);
            punto2 = recursos.getAleatorioExclusivo(5, ANCHO_TABLERO - 1, punto1);

            if (punto2 < punto1) {
                indice = punto1;
                punto1 = punto2;
                punto2 = indice;
            }

            if (i % 2 == 0) {
                Padre1 = poblacion.get(posPadres[0]);
                Padre2 = poblacion.get(posPadres[1]);
            } else {
                Padre1 = poblacion.get(posPadres[1]);
                Padre2 = poblacion.get(posPadres[0]);
            }
            
            System.out.println("Padre 1 " + Arrays.toString(Padre1.getVec_genes()));
            System.out.println("Padre 2 " + Arrays.toString(Padre2.getVec_genes()));

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
        System.out.println("Hijo 1 " + Arrays.toString(cromosomasHijos[0].getVec_genes()));
        System.out.println("Hijo 2 " + Arrays.toString(cromosomasHijos[1].getVec_genes()));
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");
        return cromosomasHijos;
    }

    public Cromosoma[] cruceUniforme(int posPadres[], Cromosoma cromosomasHijos[]) {

        Cromosoma Padre1, Padre2;
        
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");
        
        for (int i = 0; i < cromosomasHijos.length; i++) {

            if (i % 2 == 0) {
                Padre1 = poblacion.get(posPadres[0]);
                Padre2 = poblacion.get(posPadres[1]);
            } else {
                Padre1 = poblacion.get(posPadres[1]);
                Padre2 = poblacion.get(posPadres[0]);
            }
            
            System.out.println("Padre 1 " + Arrays.toString(Padre1.getVec_genes()));
            System.out.println("Padre 2 " + Arrays.toString(Padre2.getVec_genes()));
            
            for (int j = 0; j < ANCHO_TABLERO; j++) {

                if (j % 2 == 0) {
                    cromosomasHijos[i].setVecSolucion(j, Padre1.getVecSolucion(j));
                } else {
                    cromosomasHijos[i].setVecSolucion(j, Padre2.getVecSolucion(j));
                }
            }
        }
        System.out.println("Hijo 1 " + Arrays.toString(cromosomasHijos[0].getVec_genes()));
        System.out.println("Hijo 2 " + Arrays.toString(cromosomasHijos[1].getVec_genes()));
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");
        return cromosomasHijos;
    }
}
