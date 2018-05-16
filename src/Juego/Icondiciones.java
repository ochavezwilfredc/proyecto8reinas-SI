/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.lang.reflect.Array;
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
    public static int POBLACION_INICIAL = 50;

    // Probabilidad de reproduccion entre dos cromosomas. rango: 0.0 < probMutacion < 1.0
    public static double PROB_APAREAMIENTO = 0.7;

    // Minimo de padres para la seleccion.
    public static int MIN_SELECCION = 10;

    // Maximo de padres para la seleccion. rango: MIN_SELECCION < MAX_SELECCION < POBLACION_INICIAL
    public static int MAX_SELECCION = 20;

    // Cantidad desendencia por generacion. rango: 0 < NUM_DESENDENCIA < MAX_SELECCION.
    //public static int NUM_DESENDENCIA = 15;

    // Rango para generar aleatorios
    public static int MIN_BARAJA = 8;

    // Para aleatorizar cromosomas de partida
    public static int MAX_BARAJA = 20;

    // Maximo de puntos de cruce. rango: 0 < PTS_CRUCE < 8 (x > 8 no es bueno).
    public static int PTS_CRUCE = 4;
    
    // Array que contendra todas las poblaciones
    public static ArrayList<Cromosoma> poblacion = new ArrayList<Cromosoma>();
    
       

}
