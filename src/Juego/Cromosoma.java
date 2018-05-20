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
    public int posicion = -1;            
    public int cantConflictos = 0;              // cantidad de colisiones
    public boolean seleccionado = false;    // si se selecciona para aparearse
    public String tablero[][];
    Recursos recursos;
    public boolean isSolucion = false;

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
        boolean terminado;

        // Verifica si ha  y diagonal 
        // emparejado con dx para comprobar diagonal
        int dx[] = new int[]{-1, 1, -1, 1};
        int dy[] = new int[]{-1, 1, 1, -1};
        
        // verifica las verticales
        int v[] = new int [] {-1,1};
        
        // verifica las horizontales
        int h[] = new int [] {-1,1};

        //Inicializa el tablero
        this.recursos.inicializarTablero(tablero, "0");

        //setea las diagonales del tablero con 1 = reina
        for (int i = 0; i < anchoTablero; i++) {
            tablero[i][this.vec_solucion[i]] = "1";
        }

        // Camina a través de cada una de las Reinas y calcule el número de cantConflictos.
        // Solo en las diagonales
        for (int i = 0; i < anchoTablero; i++) {
            x = i;
            y = this.vec_solucion[i];
           
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
        
        // Camina a través de cada una de las Reinas y calcule el número de cantConflictos.
        // Solo en las verticales
        for (int i = 0; i < anchoTablero; i++) {
            x = i;
            y = this.vec_solucion[i];
           
            // debido a dx y dy donde hay 4 direcciones para la búsqueda diagonal de cantConflictos
            for (int j = 0; j < 2; j++) {
                tempX = x;
                tempY = y;
                terminado = false;
                // atravesar las diagonales
                while (!terminado) {
                    tempX += v[j];
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
        
        // Camina a través de cada una de las Reinas y calcule el número de cantConflictos.
        // Solo en las horizontales
        for (int i = 0; i < anchoTablero; i++) {
            x = i;
            y = this.vec_solucion[i];
           
            // debido a dx y dy donde hay 4 direcciones para la búsqueda diagonal de cantConflictos
            for (int j = 0; j < 2; j++) {
                tempX = x;
                tempY = y;
                terminado = false;
                // atravesar las diagonales
                while (!terminado) {
                    tempY += h[j];
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


        // establecer cantConflictos de este cromosoma  
        this.cantConflictos = numeroConflictos;

    }

    public int getVecSolucion(int indice) {
        return this.vec_solucion[indice];
    }

    public void setVecSolucion(int indice, int valor) {
        this.vec_solucion[indice] = valor;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
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
    public boolean getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
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

    public boolean isIsSolucion() {
        return isSolucion;
    }

    public void setIsSolucion(boolean isSolucion) {
        this.isSolucion = isSolucion;
    }

    @Override
    public String toString() {
        return "Cromosoma{" + "anchoTablero=" + anchoTablero + ", vec_solucion=" + vec_solucion + ", posicion=" + posicion + ", cantConflictos=" + cantConflictos + ", seleccionado=" + seleccionado + ", tablero=" + tablero + ", recursos=" + recursos + ", isSolucion=" + isSolucion + '}';
    }

    

}
