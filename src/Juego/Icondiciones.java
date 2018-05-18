/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.util.ArrayList;

/**
 *
 * @author cristobal
 */
public interface Icondiciones {
    //Constantes de la prueba
    
    // Ancho del tablero.
    public static int ANCHO_TABLERO = 8;

    // Tamaño de la poblacion 
    public static int POBLACION_INICIAL = 100;

    // Cantidad desendencia por generacion. rango: 0 < NUM_DESENDENCIA < MAX_SELECCION.
    //public static int NUM_DESENDENCIA = 15;

    // Rango para generar aleatorios
    public static int MIN_BARAJA = 8;

    // Para aleatorizar cromosomas de partida
    public static int MAX_BARAJA = 20;
    
    // Array que contendra todas las poblaciones
    public static ArrayList<Cromosoma> poblacion = new ArrayList<Cromosoma>();
    
    
    //Condiciones Selección Ruleta
    public static int CANT_PADRES_RULETA = 2;
}
