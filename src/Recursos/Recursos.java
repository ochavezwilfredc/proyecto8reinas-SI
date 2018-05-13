/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import java.util.Arrays;
import java.util.Random;
import Juego.Icondiciones;
import static Juego.Icondiciones.ANCHO_TABLERO;

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
     * @param low
     * @param high
     * @param except
     * @return
     */
    public int getNumeroAleatorio(int low, int high, int[] except) {
        boolean terminado = false;
        int getRand = 0;

        if (high != low) {
            while (!terminado) {
                terminado = true;
                getRand = (int) Math.round((high - low) * new Random().nextDouble() + low);
                for (int i = 0; i < except.length; i++) //UBound(except)
                {
                    if (getRand == except[i]) {
                        terminado = false;
                    }
                } // i
            }
            return getRand;
        } else {
            return high; // or low (it doesn't matter).
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
     * @param vec_genes 
     */
    public void inicializarVector(int[] vec_genes){
        for (int i = 0; i < vec_genes.length; i++) {
            vec_genes[i] = i;
        }
    }

}
