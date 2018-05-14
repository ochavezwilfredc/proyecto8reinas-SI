/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Recursos.Recursos;
import java.util.Arrays;

/**
 *
 * @author mendoza
 */
public class Cromosoma {

    public int anchoTablero = 0;            // n tamaño
    public int[] vec_solucion;                 // contiene la ubicación de cada reina
    public double fitness = 0.0;            // la aptitud de este cromosoma para la solución
    public double probSeleccion = 0.0;      // seleccionado para aparearse en la ruleta
    public int cantConflictos = 0;              // cantidad de colisiones
    public boolean seleccionado = false;    // si se selecciona para aparearse
    public String tablero[][];
    Recursos recursos;

    public Cromosoma(int longitud) {
        this.anchoTablero = longitud;
        this.vec_solucion = new int[longitud];
        this.recursos = new Recursos();

        // Inicializar el vector de genes
        this.recursos.inicializarVector(vec_solucion);

    }

    /**
     * calcula el número de cantConflictos para calcular la aptitud los
     * cantConflictos se dan 7 por cada cromosoma ejem 0 tiene 7 cantConflictos,
     * 1 tiene 7 cantConflictos... por eso el total de cantConflictos son ¡56!
     * como maximo!!
     */
    public void calcularConflictos() {

        int x, y, tempX, tempY, numeroConflictos;
        numeroConflictos = 0;

        // se crea el tablero
        tablero = new String[anchoTablero][anchoTablero];

        // verifica si la diagonal está fuera de los límites
        boolean terminado = false;

        // Verifica si ha  y diagonal 
        // emparejado con dx para comprobar diagonal
        int dx[] = new int[]{-1, 1, -1, 1};
        int dy[] = new int[]{-1, 1, 1, -1};

        //Inicializa el tablero
        this.recursos.inicializarTablero(tablero, "0");

        //setea las diagonales del tablero con 1 = reina
        for (int i = 0; i < anchoTablero; i++) {
            tablero[i][this.vec_solucion[i]] = "1";
        }

        //Imprime el tablero
        //this.recursos.imprimirTablero(tablero, "");
        // Recorre cada una de las reinas y cálcula el numero de cantConflictos
        // Camina a través de cada una de las Reinas y calcule el número de cantConflictos.
        for (int i = 0; i < anchoTablero; i++) {
            x = i;
            y = this.vec_solucion[i];

            // Evalua las diagonales (¿porque?)
            // Ver diagonales.
            // debido a dx y dy donde hay 4 direcciones para la búsqueda diagonal de cantConflictos
            for (int j = 0; j < 4; j++) {

                tempX = x;
                tempY = y;
                terminado = false;

                // atravesar las diagonales
                while (!terminado) {

                    tempX += dx[j];
                    tempY += dy[j];

                    // si excede el tablero
                    if ((tempX < 0 || tempX >= anchoTablero) || (tempY < 0 || tempY >= anchoTablero)) {
                        terminado = true;
                    } else {

                        if (tablero[tempX][tempY].compareToIgnoreCase("1") == 0) {
                            numeroConflictos++;
                        }

                    }

                }

            }

        }

        // aqui termina la comparacion de diagonales
        // establecer cantConflictos de este cromosoma  
        this.cantConflictos = numeroConflictos;

    }

    public int getVecSolucion(int index) {
        return this.vec_solucion[index];
    }

    public void setVecSolucion(int index, int position) {
        this.vec_solucion[index] = position;
    }

    // Obtiene la aptitud de un cromosoma.
    public double getFitness() {
        return fitness;
    }

    // Establece la aptitud del cromosoma.
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    // Obtiene los cantConflictos del cromosoma.
    public int getConflictos() {
        return cantConflictos;
    }

    // Establece los cantConflictos del cromosoma.
    public void setConflictos(int conflictos) {
        this.cantConflictos = conflictos;
    }

    // Obtiene si el cromosoma está seleccionado.
    public boolean isSelected() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    // Obtiene la probabilidad de selección del cromosoma.
    public double getProbabilidad() {
        return probSeleccion;
    }

    // establece la probabilidad de selección del cromosoma.
    public void setProbabilidad(double probSeleccion) {
        this.probSeleccion = probSeleccion;
    }

    // Obtiene la longitud máxima.
    public int getAnchoTablero() {
        return this.anchoTablero;
    }

    public String[][] getTablero() {
        return tablero;
    }

    public int[] getVec_genes() {
        return vec_solucion;
    }

    @Override
    public String toString() {
        return "Cromosoma{" + "anchoTablero=" + anchoTablero + ", genes=" + Arrays.toString(vec_solucion) + ", fitness=" + fitness + ", probSeleccion=" + probSeleccion + ", conflictos=" + cantConflictos + ", seleccionado=" + seleccionado + ", tablero=" + tablero + ", recursos=" + recursos + '}';
    }

}
