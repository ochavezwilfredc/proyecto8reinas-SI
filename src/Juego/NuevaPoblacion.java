/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

/**
 *
 * @author mendoza
 */
public class NuevaPoblacion implements Icondiciones{
    
    public void aceptacionTotal (Cromosoma cromoH1, Cromosoma cromoH2) {
        
        int mayorConflic[] = new int[2];
        int posMayConflic[] = new int[2];
        
        
        mayorConflic[0] =  poblacion.get(0).getConflictos();
        mayorConflic[1] =  poblacion.get(0).getConflictos();
        
        for (int i = 0; i < 2; i++) {
            
            for (Cromosoma poblacionTemp : poblacion) {
            
                if (poblacionTemp.getConflictos() > mayorConflic[i]) {
                    
                    mayorConflic[i] = poblacionTemp.getConflictos();
                    posMayConflic[i] = poblacion.lastIndexOf(poblacionTemp);
                    
                }

            }
            
            poblacion.remove(posMayConflic[i]);
        }
        
        cromoH1.calcularConflictos();
        cromoH2.calcularConflictos();
        
        System.out.println(" 12121212122 ->>>>>>>>>>>> "+posMayConflic[0]);
        System.out.println(" 12121212122 ->>>>>>>>>>>> "+posMayConflic[1]);
        
        System.out.println("Cantidad de pobladores  " + poblacion.size());
        
        poblacion.add(cromoH1);
        poblacion.add(cromoH2);
 
    }
    
    public void deMejora (int posPadre1, int posPadre2, Cromosoma cromoH1, Cromosoma cromoH2) {
        
        int mayorConflic[] = new int[2];
        int posMayConflic[] = new int[2];
        int canConflictoIndividuo [] = new int[2];
        Cromosoma canConflictoHijo [] = new Cromosoma[2];
        
        // cromosoma del hijo se va al arreglo y calcular conflictos 
        cromoH1.calcularConflictos();
        cromoH2.calcularConflictos();
        canConflictoHijo[0] = cromoH1;
        canConflictoHijo[1] = cromoH2;        
        
        // se obtiene el mayor inicial para los dos hijos 
        mayorConflic[0] =  poblacion.get(0).getConflictos();
        mayorConflic[1] =  poblacion.get(0).getConflictos();
        
        for (int i = 0; i < 2; i++) {
            
            for (Cromosoma poblacionTemp : poblacion) {
            
                if (poblacionTemp.getConflictos() > mayorConflic[i]) {
                    
                    mayorConflic[i] = poblacionTemp.getConflictos();
                    posMayConflic[i] = poblacion.lastIndexOf(poblacionTemp);
                    
                }
            }
        }
        
        canConflictoIndividuo[0] = poblacion.get(posMayConflic[0]).getConflictos();
        canConflictoIndividuo[1] = poblacion.get(posMayConflic[1]).getConflictos();
        
        for (int i = 0; i < 2; i++) {
            
            for (int j = 0; j < 2; j++) {
                
                if (canConflictoHijo[i].getConflictos() < canConflictoIndividuo[j]) {
                    
                    // no se agrega al hijo y se deja al individuo
                    poblacion.remove(posMayConflic[j]);
                    poblacion.add(canConflictoHijo[i]);
                    
                }               
            }   
        }
    }
    
}
