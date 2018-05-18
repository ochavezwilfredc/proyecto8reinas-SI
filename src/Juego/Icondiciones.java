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
    
    // Array que contendra todas las poblaciones
    public static ArrayList<Cromosoma> poblacion = new ArrayList<Cromosoma>();
    
    
    //Condiciones Selección Ruleta
    public static int CANT_PADRES_RULETA = 2;
}
