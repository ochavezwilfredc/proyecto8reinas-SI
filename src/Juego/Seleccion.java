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

    Recursos recursos;    

    public Seleccion() {
        this.recursos = new Recursos();
    }

    public void ruleta () {
    
        int sumActitud = 0, seleccionar;
        
        // esta es la cantidad de padres que se seleccionara  
        int vecSelecionados[] = new int[CANT_PADRES_RULETA];
        int sumFitnessTotal[] = new int [POBLACION_INICIAL];
        
        // suma secuencial de los fitness de la poblacion
        for (int i = 0; i < poblacion.size(); i++) {
            
            sumActitud += poblacion.get(i).getConflictos();
            sumFitnessTotal[i] = sumActitud;

        }

        // seleccionar padres
        for (int i = 0; i < CANT_PADRES_RULETA; i++) {

            // El azar a seleccionar es la suma de todas las colisiones 
            seleccionar = recursos.getAleatorio(0, sumActitud);
            
            //se recorre la aptitud acumulada para identificar que individuo mayor al ramdon
            for (int j = 0; j < poblacion.size(); j++) {
                
                //Se compara el numero aleatorio generado contra la aptitud acumulada
                if (sumFitnessTotal[j] > seleccionar) {
                    
                    //si es mayor se selecciona el individuo
                    vecSelecionados[i] = j;
                    
                    //Se rompe el for para generar un nuevo aleatorio
                    break;

                }
            }
        }
        
        for (int i = 0; i < CANT_PADRES_RULETA; i++) {
            
            poblacion.get(vecSelecionados[i]).setSeleccionado(true);   
            System.out.println("Seleccion padre "+ vecSelecionados[i]+ "suma Act "+sumActitud+" ultimo "+sumFitnessTotal[POBLACION_INICIAL-1]);
        }
    }
}
