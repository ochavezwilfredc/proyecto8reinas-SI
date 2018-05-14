/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import Juego.Cromosoma;
import java.util.Arrays;
import java.util.Random;
import Juego.Icondiciones;
import static Juego.Icondiciones.ANCHO_TABLERO;
import java.util.ArrayList;

/**
 *
 * @author mendoza
 */
public class Recursos implements Icondiciones {

    Random random;

    public Recursos() {
        random = new Random();
    }

    /**
     * genera el número aleatorio Obtiene un número aleatorio en el rango de los
     * parámetros
     *
     * @param min
     * @param max
     * @return
     */
    public int getAleatorio(int min, int max) {
        return (int) Math.round((max - min) * random.nextDouble() + min);
    }

    /**
     * genera el numero aleatorio exclusivo porque no puede tener el mismo valor
     * que el gen al azar Obtiene un número aleatorio con la excepción del
     * parámetro
     *
     * @param max
     * @param except
     * @return
     */
    public int getAleatorioExclusivo(int max, int except) {
        boolean terminado = false;
        int getRand = 0;

        while (!terminado) {
            getRand = random.nextInt(max);
            if (getRand != except) {
                terminado = true;
            }
        }

        return getRand;
    }

    /**
     * Obtener numero aleatorio fuera del rango
     *
     * @param bajo
     * @param alto
     * @param v_puntosCruce
     * @return
     */
    public int getNumeroAleatorio(int bajo, int alto, int[] v_puntosCruce) {
        boolean terminado = false;
        int getRand = 0;

        if (alto != bajo) {
            while (!terminado) {
                terminado = true;
                getRand = (int) Math.round((alto - bajo) * new Random().nextDouble() + bajo);
                for (int i = 0; i < v_puntosCruce.length; i++) {
                    if (getRand == v_puntosCruce[i]) {
                        terminado = false;
                    }
                }
            }
            return getRand;
        } else {
            return alto;
        }
    }

    public void inicializarTablero(String[][] tablero, String v) {
        // Inicializa el tablero.
        for (int f = 0; f < ANCHO_TABLERO; f++) {
            for (int c = 0; c < ANCHO_TABLERO; c++) {
                tablero[f][c] = v;
            }
        }
    }

    public void imprimirTablero(String[][] tablero, String msj) {
        String aux[];
        System.out.println("\nTablero de " + msj + "\n");

        for (int f = 0; f < ANCHO_TABLERO; f++) {
            aux = new String[ANCHO_TABLERO];
            for (int c = 0; c < ANCHO_TABLERO; c++) {
                aux[c] = tablero[f][c];
            }
            System.out.println(Arrays.toString(aux));
        }
        System.out.println("\n");
    }

    /**
     * Método para inicializar el vector de genes
     *
     * @param vec_genes
     */
    public void inicializarVector(int[] vec_genes) {
        for (int i = 0; i < vec_genes.length; i++) {
            vec_genes[i] = i;
        }
    }

    /**
     * Imprime la mejor solucion
     *
     * @param mejorSolucion
     */
    public void imprimirSolucionFinal(Cromosoma mejorSolucion) {
        String tablero[][] = new String[ANCHO_TABLERO][ANCHO_TABLERO];
        //Inicializa el tablero con todos 0
        this.inicializarTablero(tablero, "0");

        //1 = reina 
        for (int x = 0; x < ANCHO_TABLERO; x++) {
            tablero[x][mejorSolucion.getVecSolucion(x)] = "1";

        }
        //Imprime la soción
        this.imprimirTablero(tablero, "Solución ");
    }

    /**
     * Método para imprimir una generación completa
     *
     * @param poblacion lista con todos los cromosomas existentes
     * @param g inicde de generación
     */
    public void imprimirGeneracion(ArrayList<Cromosoma> poblacion, int g) {
        System.out.println("\n============================== Generación " + g + " ==============================\n");
        poblacion.forEach((c) -> {
            if (c.fitness != 0) {
                System.out.println(Arrays.toString(c.vec_solucion) + "  "
                        + "# Coliciones: " + c.cantConflictos
                        + "\t Fitness: " + ((double) Math.round(c.fitness * 100d) / 100d));

            }
        });

        System.out.println("");

    }

}
