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
public class Seleccion implements Icondiciones {

   //int vecFitness = 0;
    double sumTotalProbabilidad;
    double inidiceAleatorio;
    Cromosoma cromosoma, comosomaTemp;
    boolean isTerminado;
    Recursos recursos;
    // Array que contendra todas las poblaciones
    int[] vecFitness;

    public Seleccion() {
        vecFitness = new int[POBLACION_INICIAL];
        this.recursos = new Recursos();
    }

    // Seleccion de cromosomas de la generacion
    public void ruleta() {

        int elemenI, sumActitud;//Elemento(cromosoma) de la población
         
        int sumaTotal = 0;

        // esta es la cantidad de padres que se seleccionara  
        isTerminado = false;
        int vecSelecionados[] = new int[CANT_PADRES_RULETA];

        for (int i = 0; i < poblacion.size(); i++) {
            cromosoma = poblacion.get(i);
            sumActitud = cromosoma.getConflictos() + vecFitness[i];
            vecFitness[i] = cromosoma.getConflictos();
        }
        sumaTotal = poblacion.stream().map((cromo) -> cromo.getConflictos()).reduce(sumaTotal, Integer::sum);
        
        // inidiceAleatorio padres
        for (int i = 0; i < CANT_PADRES_RULETA; i++) {
            // El azar a inidiceAleatorio es la suma de todas las colisiones 
            inidiceAleatorio = recursos.getAleatorio(0, sumaTotal);
            isTerminado = false;
            elemenI = 0;
            sumTotalProbabilidad = 0;

            for (int j = 0; j < poblacion.size(); j++) {//se recorre la aptitud acumulada para identificar que individuo supera al ramdon
                if (vecFitness[j] > inidiceAleatorio) {//Se compara el aleatorio contra la aptitud acumulada
                    vecSelecionados[i] = j; //si es mayor se selecciona el individuo
                    break;//Se rompe el for para generar un nuevo aleatorio
                }
            }
        }

        for (int x = 0; x < CANT_PADRES_RULETA; x++) {
            poblacion.get(x).setSeleccionado(true);
        }

    }

}
