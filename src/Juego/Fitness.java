/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import static Juego.OchoReinas.poblacion;

/**
 *
 * @author mendoza
 */
public class Fitness {
    
    
    // Buscar la mejor acttud 
    public void evaluar() {
        
        // se busca el Menor error = 100%, Mayor Error = 0%
        int tamPoblacion = poblacion.size();
        Cromosoma cromosoma;
        double mejor, peor, optimo;

        // La peor puntuación seá el que tiene la mayor numero de conflictos , y el mejor el más bajo.
        peor = poblacion.get(Maximo()).getConflicts();

        // el mejor 
        mejor = poblacion.get(Minimo()).getConflicts();
        
        optimo = peor - mejor;

        for (int i = 0; i < tamPoblacion; i++) {
            cromosoma = poblacion.get(i);
            cromosoma.setFitness((peor - cromosoma.getConflicts()) * 100.0 / optimo);
            System.out.println("El fitness de la población "+i+" es: "+cromosoma.getFitness());
        }
    }
    
    // ahora toca ver el maximo a obtener el cromosoma 
    public int Maximo() {
        
        // Devuelve el indice de una matriz.
        int tamPoblacion = 0;
        Cromosoma cromosoma, comosomaTemp ;
        int ganador = 0;
        boolean nuevoGanador, terminado;
        terminado = false;

        while (!terminado) {
            
            nuevoGanador = false;
            tamPoblacion = poblacion.size();
            
            // recorre toda la poblacion
            for (int i = 0; i < tamPoblacion; i++) {
                
                // Evita la auocomparacion 
                if (i != ganador) {
                    
                    cromosoma = poblacion.get(i);
                    comosomaTemp = poblacion.get(ganador);
                    
                    if (cromosoma.getConflicts() > comosomaTemp.getConflicts()) {
                        
                        ganador = i;
                        nuevoGanador = true;
                        
                    }
                }
            }
            
            // copara la validez del ganador nuevo
            if (nuevoGanador == false) {
                terminado = true;
            }
        }
        // retorna el ganador 
        return ganador;
    }
    
    // ahora toca ver el minimo a obtener el cromosoma 
    public int Minimo() {
        
        // Devuelve el indice de una matriz.
        int tamPoblacion = 0;
        Cromosoma cromosoma, comosomaTemp;
        int ganador = 0;
        boolean nuevoGanador, terminado;
        terminado = false;

        while (!terminado) {
            
            nuevoGanador = false;
            tamPoblacion = poblacion.size();
            
            // recorre toda la poblacion
            for (int i = 0; i < tamPoblacion; i++) {
                
                // Evita la auocomparacion 
                if (i != ganador) {
                    
                    cromosoma = poblacion.get(i);
                    comosomaTemp = poblacion.get(ganador);
                    
                    if (cromosoma.getConflicts() < comosomaTemp.getConflicts()) {
                        
                        ganador = i;
                        nuevoGanador = true;
                        
                    }
                }
            }
            
            // copara la validez del ganador nuevo
            if (nuevoGanador == false) {
                terminado = true;
            }
        }
        // retorna el ganador 
        return ganador;
    }
    
    
}
