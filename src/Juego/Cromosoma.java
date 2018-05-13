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
    public int genes[];                     // contiene la ubicación de cada reina
    public double fitness = 0.0;            // la aptitud de este cromosoma para la solución
    public double probSeleccion = 0.0;      // seleccionado para aparearse en la ruleta
    public int conflictos = 0;              // cantidad de colisiones
    public boolean seleccionado = false;    // si se selecciona para aparearse
    public String tablero[][];
    public Recursos recursos;

    public Cromosoma(int longitud) {
        this.anchoTablero = longitud;
        this.genes = new int[longitud];
        this.recursos = new Recursos();

        // esta linea es para llenar los genes del individuo
        for (int i = 0; i < longitud; i++) {
            this.genes[i] = i;
        }

    }

    // calcula el número de conflictos para calcular la aptitud
    // los conflictos se dan 7 por cada cromosoma ejem
    // 0 tiene 7 conflictos, 1 tiene 7 conflictos...
    // por eso el total de conflictos son ¡56! como maximo!!
    public void calcularConflictos() {

        // esta linea mustra los cromosomas
        System.out.println(Arrays.toString(this.genes));

        int x = 0, y = 0, tempX = 0, tempY = 0, numeroConflictos = 0;

        // se crea el tablero
        tablero = new String[anchoTablero][anchoTablero];

        // verifica si la diagonal está fuera de los límites
        boolean terminado = false;

        // para verificar si hay diagonal 
        // emparejado con dx para comprobar diagonal
        int dx[] = new int[]{-1, 1, -1, 1};
        int dy[] = new int[]{-1, 1, 1, -1};

        //Inicializa el tablero
        this.recursos.inicializarTablero(tablero, "0");

        // este paso llena todas las diagonales del tablero con 1 = reina
        for (int i = 0; i < anchoTablero; i++) {
            tablero[i][this.genes[i]] = "1";
        }

        //Imprime el tablero
        this.recursos.imprimirTablero(tablero, "");

        // Recorre cada una de las reinas y cálcula el numero de conflictos
        // Camina a través de cada una de las Reinas y calcule el número de conflictos.
        for (int i = 0; i < anchoTablero; i++) {
            x = i;
            y = this.genes[i];

            // Evalua las diagonales (¿porque?)
            // Ver diagonales.
            // debido a dx y dy donde hay 4 direcciones para la búsqueda diagonal de conflictos
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
        // establecer conflictos de este cromosoma  
        this.conflictos = numeroConflictos;

    }

    // Obtiene el gen / datos en un índice especificado.
    public int getGene(int index) {
        return this.genes[index];
    }

    // Establece el gen / datos en un índice especificado.
    public void setGene(int index, int position) {
        this.genes[index] = position;
    }

    // Obtiene la aptitud de un cromosoma.
    public double getFitness() {
        return fitness;
    }

    // Establece la aptitud del cromosoma.
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    // Obtiene los conflictos del cromosoma.
    public int getConflicts() {
        return conflictos;
    }

    // Establece los conflictos del cromosoma.
    public void setConflicts(int conflictos) {
        this.conflictos = conflictos;
    }

    // Obtiene si el cromosoma está seleccionado.
    public boolean isSelected() {
        return seleccionado;
    }

    //Obtiene si el cromosoma está seleccionado.
    public void setSelected(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    // Obtiene la probabilidad de selección del cromosoma.
    public double getSelectionProbability() {
        return probSeleccion;
    }

    // establece la probabilidad de selección del cromosoma.
    public void setSelectionProbability(double probSeleccion) {
        this.probSeleccion = probSeleccion;
    }

    // Obtiene la longitud máxima.
    public int getAnchoTablero() {
        return this.anchoTablero;
    }

    public String[][] getTablero() {
        return tablero;
    }

    @Override
    public String toString() {
        return "Cromosoma{" + "anchoTablero=" + anchoTablero + ", genes=" + genes + ", fitness=" + fitness + ", probSeleccion=" + probSeleccion + ", conflictos=" + conflictos + ", seleccionado=" + seleccionado + ", tablero=" + tablero + ", recursos=" + recursos + '}';
    }

}
