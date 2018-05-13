/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Recursos.Recursos;
import static Juego.OchoReinas.maxSeleccion;
import static Juego.OchoReinas.minSeleccion;
import static Juego.OchoReinas.poblacion;

/**
 *
 * @author mendoza
 */
public class Seleccion {
    
    // Seleccion de cromosomas de la generacion
    public void ruletaSeleccion() {

        Recursos rand1 = new Recursos();
        Recursos rand2 = null;
        
        int j = 0;
        int tamPoblacion = poblacion.size();
        double genTotal = 0.0;
        double selTotal = 0.0;
        int maximoSeleccionar = rand1.NumeroAleatorio(minSeleccion, maxSeleccion);
        double seleccionar = 0.0;
        Cromosoma cromosoma = null;
        Cromosoma comosomaTemp = null;
        boolean terminado = false;
        
        // se almacena en la variable genTotal donde contendra la suma del fitness de toda la poblacion
        //obtiene la actitud total
        
        for (int i = 0; i < tamPoblacion; i++) {
            cromosoma = poblacion.get(i);
            genTotal += cromosoma.getFitness();
        }
        
        // se multiplica por el 0.01 que genera redondeo
        genTotal *= 0.01;

        //  se multiplica por el 0.01 que genera intervalos de 0/1
        for (int i = 0; i < tamPoblacion; i++) {
            cromosoma = poblacion.get(i);
            
            // establecer la probabilidad de selección. cuanto más se ajuste, mejor será la probabilidad de selección
            cromosoma.setSelectionProbability(cromosoma.getFitness() / genTotal);
        }

        // seleccionar padres
        for (int i = 0; i < maximoSeleccionar; i++) {
            
            // el azar a seleccionar es solo hasta 99 porque la suma de las probabilidades de los cromosomas 
            // es hasta 99.9999999999999999999 y no llega a 100 como para que rompa la condicion
            rand2 = new Recursos();
            seleccionar = rand2.NumeroAleatorio(0, 99);
            terminado = false;
            j = 0;
            selTotal = 0;
            
            while (!terminado) {
                
                // empieza con la poblacion desde el iondividuo n° 1 hasta el ultimo
                cromosoma = poblacion.get(j);
                
                // aqui se almacena el total de la probabilidad de seleccion la cual llega a un 99.9999...
                selTotal += cromosoma.getSelectionProbability();
                //System.out.println("El selTotal es ( "+selTotal+" ) y seleccionar es  ( "+seleccionar+" )");
                
                if (selTotal >= seleccionar) {
                    
                    if (j == 0) {
                        // toma el primero
                        comosomaTemp = poblacion.get(j);
                    } else if (j >= tamPoblacion - 1) {
                        // toma el ultimo
                        comosomaTemp = poblacion.get(tamPoblacion - 1);
                    } else {
                        // toma en una posicion determinada
                        comosomaTemp = poblacion.get(j - 1);
                    }
                    
                    comosomaTemp.setSelected(true);
                    terminado = true;
                
                } else {
                    j++;
                }
            }
        }
    }
    
}
