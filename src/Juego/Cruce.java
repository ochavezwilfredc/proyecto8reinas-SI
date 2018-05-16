/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import static Juego.Genetica.poblacion;
import Recursos.Recursos;
import static Juego.Genetica.ANCHO_TABLERO;
import static Juego.Icondiciones.ANCHO_TABLERO;
import static Juego.Icondiciones.poblacion;

/**
 *
 * @author mendoza
 */
public class Cruce {

    Recursos recursos;

    public Cruce() {
        this.recursos = new Recursos();
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------
    
    public Cromosoma cruceUnPunto (int chromA, int chromB, Cromosoma hijo) {
        
        //int inidice, genCromoA, genCromoB, pos1, pos2;

        Cromosoma cromoA;
        Cromosoma cromoB;
        
        int valor = recursos.getAleatorio(0, 1);

        int puntoCruceHijo = recursos.getAleatorio(0, ANCHO_TABLERO - 1);
        
        if (valor ==  0) {
            cromoA = poblacion.get(chromA);
            cromoB = poblacion.get(chromB);
        } else {
            cromoA = poblacion.get(chromB);
            cromoB = poblacion.get(chromA);
        }
        
        for (int i = 0; i < ANCHO_TABLERO; i++) {
            
            if (i <= puntoCruceHijo) {
                hijo.setVecSolucion(i, cromoA.getVecSolucion(i));
            } else {
                hijo.setVecSolucion(i, cromoB.getVecSolucion(i));
            }
            
        }
        return hijo;    
    }
    
    // --------------------------------------------------------------------------------------------------------------------------------------------
    
    public Cromosoma cruceDosPuntos (int chromA, int chromB, Cromosoma hijo) {

        Cromosoma cromoA;
        Cromosoma cromoB;
        int indice = 0;
        
        int punto1 = recursos.getAleatorio(0, ANCHO_TABLERO - 1);
        int punto2 = recursos.getAleatorioExclusivo(ANCHO_TABLERO - 1, punto1);
        int valor = recursos.getAleatorio(0, 1);
        
        if (punto2 < punto1) {
            indice = punto1;
            punto1 = punto2;
            punto2 = indice;
        }
        
        if (valor ==  0) {
            cromoA = poblacion.get(chromA);
            cromoB = poblacion.get(chromB);
        } else {
            cromoA = poblacion.get(chromB);
            cromoB = poblacion.get(chromA);
        }
        
        for (int i = 0; i < ANCHO_TABLERO; i++) {
            
            if (i <= punto1) {
                hijo.setVecSolucion(i, cromoA.getVecSolucion(i));
            } else if (i >= punto2){
                hijo.setVecSolucion(i, cromoA.getVecSolucion(i));
            } else {
                hijo.setVecSolucion(i, cromoB.getVecSolucion(i));
            }
            
        }
        return hijo;    
    }
    
    // --------------------------------------------------------------------------------------------------------------------------------------------
    
    public Cromosoma cruceUniforme (int chromA, int chromB, Cromosoma hijo) {

        Cromosoma cromoA;
        Cromosoma cromoB;
        
        int valor = recursos.getAleatorio(0, 1);
        
        if (valor ==  0) {
            cromoA = poblacion.get(chromA);
            cromoB = poblacion.get(chromB);
        } else {
            cromoA = poblacion.get(chromB);
            cromoB = poblacion.get(chromA);
        }
        
        for (int i = 0; i < ANCHO_TABLERO; i++) {
            
            if (i % 2 == 0) {
                hijo.setVecSolucion(i, cromoA.getVecSolucion(i));
            } else {
                hijo.setVecSolucion(i, cromoB.getVecSolucion(i));
            }
            
        }
        return hijo;    
    }
    
    
}
