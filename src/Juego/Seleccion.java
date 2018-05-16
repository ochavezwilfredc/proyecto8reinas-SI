/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Recursos.Recursos;
import static Juego.Icondiciones.poblacion;

/**
 *
 * @author mendoza
 */
public class Seleccion implements Icondiciones{

    int tamPoblacion;
    //int sumFitnessTotal = 0;
    double sumTotalProbabilidad;
    int maximoSeleccionar;
    double seleccionar;
    Cromosoma cromosoma, comosomaTemp;
    boolean isTerminado;
    Recursos recursos;
    // Array que contendra todas las poblaciones
    int sumFitnessTotal[] = new int [POBLACION_INICIAL];

    public Seleccion() {
        this.recursos = new Recursos();
    }

    // Seleccion de cromosomas de la generacion
    public void ruleta () {
    
        int elemenI;//Elemento(cromosoma) de la población
        tamPoblacion = poblacion.size();
        int sumActitud;
        int sumaTotal = 0;
        
        // esta es la cantidad de padres que se seleccionara  
        maximoSeleccionar = 2;
        isTerminado = false;
        int selecionados[] = new int[maximoSeleccionar];
        
        // se almacena en la variable sumFitnessTotal donde contendra la suma del fitness 
        //de toda la poblacion
        //obtiene la aptitud total
        for (int i = 0; i < tamPoblacion; i++) {
            cromosoma = poblacion.get(i);
            sumActitud = cromosoma.getConflictos() + sumFitnessTotal[i];
            sumFitnessTotal[i] = cromosoma.getConflictos();
        }
        
        for (int i = 0; i < tamPoblacion; i++) {
            
            sumaTotal += sumFitnessTotal[i];
        }
        
        // seleccionar padres
        for (int i = 0; i < maximoSeleccionar; i++) {

            // El azar a seleccionar es la suma de todas las colisiones 
            seleccionar = recursos.getAleatorio(0, sumaTotal);
            isTerminado = false;
            elemenI = 0;
            sumTotalProbabilidad = 0;
            
            for (int j = 0; j < tamPoblacion; j++) {//se recorre la aptitud acumulada para identificar que individuo supera al ramdon
                if (sumFitnessTotal[j] > seleccionar) {//Se compara el aleatorio contra la aptitud acumulada
                    selecionados[i] = j; //si es mayor se selecciona el individuo
                    break;//Se rompe el for para generar un nuevo aleatorio
                }
            }          
        }
        
        for (int x = 0; x < maximoSeleccionar; x++) {
            
            poblacion.get(x).setSeleccionado(true);
            
        }
        
    }

}
