/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

/**
 *
 * @author cristobal
 */
public interface Condiciones {
    //Constantes de la prueba
    
    // Ancho del tablero.
    public static int anchoTablero = 8;

    // Tamaño de la poblacion 
    public static int poblacionIni = 50;

    // Probabilidad de reproduccion entre dos cromosomas. rango: 0.0 < probMutacion < 1.0
    public static double probApareamiento = 0.7;

    // Tasa de mutacion. rango: 0.0 < tasaMutacion < 1.0
    public static double tasaMutacion = 0.5;

    // Minimo de padres para la seleccion.
    public static int minSeleccion = 10;

    // Maximo de padres para la seleccion. rango: minSeleccion < maxSeleccion < poblacionIni
    public static int maxSeleccion = 20;

    // Cantidad desendencia por generacion. rango: 0 < numDesendencia < maxSeleccion.
    public static int numDesendencia = 15;

    // Rango para generar aleatorios
    public static int minBaraja = 8;

    // Para aleatorizar cromosomas de partida
    public static int maxBaraja = 20;

    // Maximo de puntos de cruce. rango: 0 < ptsCruce < 8 (x > 8 no es bueno).
    public static int ptsCruce = 4;

    

   

    

}
