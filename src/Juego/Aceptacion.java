/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Recursos.Recursos;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author mendoza
 */
public class Aceptacion implements Icondiciones {
    
    Recursos recursos;
    
    public Aceptacion() {
        recursos = new Recursos();
    }
    
    public void aceptacionTotal(Cromosoma cromosomasHijos[]) {
        
        int mayorConflic[] = new int[2];
        int posMayConflic[] = new int[2];
        
        mayorConflic[0] = poblacion.get(0).getConflictos();
        mayorConflic[1] = poblacion.get(0).getConflictos();
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < cromosomasHijos.length; i++) {
            
            for (Cromosoma poblacionTemp : poblacion) {
                
                if (poblacionTemp.getConflictos() > mayorConflic[i]) {
                    
                    mayorConflic[i] = poblacionTemp.getConflictos();
                    posMayConflic[i] = poblacion.indexOf(poblacionTemp);
                    
                }
            }
            System.out.println("Padres eliminados en la posicion " + posMayConflic[i] + " conflic (" + poblacion.get(posMayConflic[i]).getConflictos() + ")" + " - > " + Arrays.toString(poblacion.get(posMayConflic[i]).getVec_genes()));
            poblacion.remove(posMayConflic[i]);
        }
        
        for (int i = 0; i < cromosomasHijos.length; i++) {
            cromosomasHijos[i].calcularConflictos();
            poblacion.add(cromosomasHijos[i]);
        }
        System.out.println("SiguientePoblacion en el Hijo 1 " + Arrays.toString(cromosomasHijos[0].getVec_genes()));
        System.out.println("SiguientePoblacion en el Hijo 2 " + Arrays.toString(cromosomasHijos[1].getVec_genes()));
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");
        
    }
    
    public void deMejora(Cromosoma cromosomasHijos[]) {
        
        int mayorConflic[] = new int[2];
        int posMayConflic[] = new int[2];
        int menorConflic[] = new int[2];
        int posMenConflic[] = new int[2];
        Cromosoma cromosomaTemp[] = new Cromosoma[2];
        ArrayList<Cromosoma> totalCromosomas = new ArrayList<>();
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");
        // cromosoma del hijo se va al arreglo y calcular conflictos 
        for (Cromosoma cromosomasHijo : cromosomasHijos) {
            cromosomasHijo.calcularConflictos();
        }
        
        for (int i = 0; i < cromosomasHijos.length; i++) {
            
            mayorConflic[i] = poblacion.get(0).getConflictos();
            
            for (Cromosoma poblacionTemp : poblacion) {
                
                if (poblacionTemp.getConflictos() > mayorConflic[i]) {
                    
                    mayorConflic[i] = poblacionTemp.getConflictos();
                    posMayConflic[i] = poblacion.indexOf(poblacionTemp);
                }
            }
            cromosomaTemp[i] = poblacion.get(posMayConflic[i]);
            poblacion.remove(posMayConflic[i]);
        }
        
        for (Cromosoma cromosomasHijo : cromosomaTemp) {
            cromosomasHijo.calcularConflictos();
        }
        
        System.out.println("Padres " + posMayConflic[0] + " conflictos ( " + cromosomaTemp[0].getConflictos() + " )" + " - > " + Arrays.toString(cromosomaTemp[0].getVec_genes()));
        System.out.println("Padres " + posMayConflic[1] + " conflictos ( " + cromosomaTemp[1].getConflictos() + " )" + " - > " + Arrays.toString(cromosomaTemp[1].getVec_genes()));
        System.out.println("Hijo 1  ( " + cromosomasHijos[0].getConflictos() + " ) y " + Arrays.toString(cromosomasHijos[0].getVec_genes()));
        System.out.println("Hijo 2  ( " + cromosomasHijos[1].getConflictos() + " ) y " + Arrays.toString(cromosomasHijos[1].getVec_genes()));
        
        for (int i = 0; i < cromosomasHijos.length; i++) {
            totalCromosomas.add(cromosomasHijos[i]);
            totalCromosomas.add(cromosomaTemp[i]);
        }
        
        totalCromosomas.forEach((totalCromosoma) -> {
            System.out.println(" Participa en la busqueda del menor " + totalCromosoma.getConflictos() + " " + Arrays.toString(totalCromosoma.getVec_genes()));
        });
        
        for (int i = 0; i < cromosomasHijos.length; i++) {
            
            menorConflic[i] = totalCromosomas.get(0).getConflictos();
            
            for (Cromosoma poblacionTemp : totalCromosomas) {
                
                if (poblacionTemp.getConflictos() < menorConflic[i]) {
                    
                    menorConflic[i] = poblacionTemp.getConflictos();
                    posMenConflic[i] = totalCromosomas.indexOf(poblacionTemp);
                    System.out.println(" Pos del menor -> " + posMenConflic[i]);
                }
            }
            System.out.println(" Pasan a SiguientePoblacion en el Hijo " + i + " menor colisiones " + totalCromosomas.get(posMenConflic[i]).getConflictos() + " " + Arrays.toString(totalCromosomas.get(posMenConflic[i]).getVec_genes()));
            
            poblacion.add(totalCromosomas.get(posMenConflic[i]));
            totalCromosomas.remove(posMenConflic[i]);
        }
        
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    public void porTorneo(Cromosoma cromosomasHijos[]) {
        
        int menorConflic[] = new int[2];
        int posMenConflic[] = new int[2];
        
        int posPadresRandom[] = new int[2];
        Cromosoma padresRandom[] = new Cromosoma[2];
        
        posPadresRandom[0] = recursos.getAleatorio(0, poblacion.size() - 1);
        padresRandom[0] = poblacion.get(posPadresRandom[0]);
        poblacion.remove(posPadresRandom[0]);
        
        posPadresRandom[1] = recursos.getAleatorio(0, poblacion.size() - 1);
        padresRandom[1] = poblacion.get(posPadresRandom[1]);      
        poblacion.remove(posPadresRandom[1]);

        // eliminamos a los padres ya guardados en las variables 
        
        ArrayList<Cromosoma> totalCromosomas = new ArrayList<>();
        
        for (Cromosoma cromosomasHijo : cromosomasHijos) {
            cromosomasHijo.calcularConflictos();
        }
        
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");
        
        System.out.println("Padres " + posPadresRandom[0] + " conflictos ( " + padresRandom[0].getConflictos() + " )" + " - > " + Arrays.toString(padresRandom[0].getVec_genes()));
        System.out.println("Padres " + posPadresRandom[1] + " conflictos ( " + padresRandom[1].getConflictos() + " )" + " - > " + Arrays.toString(padresRandom[1].getVec_genes()));
        System.out.println("Hijo 1  ( " + cromosomasHijos[0].getConflictos() + " ) y " + Arrays.toString(cromosomasHijos[0].getVec_genes()));
        System.out.println("Hijo 2  ( " + cromosomasHijos[1].getConflictos() + " ) y " + Arrays.toString(cromosomasHijos[1].getVec_genes()));
        
        
        
        for (int i = 0; i < cromosomasHijos.length; i++) {
            totalCromosomas.add(cromosomasHijos[i]);
            totalCromosomas.add(padresRandom[i]);
        }
        
        for (int i = 0; i < cromosomasHijos.length; i++) {
            
            menorConflic[i] = totalCromosomas.get(0).getConflictos();
            
            for (Cromosoma poblacionTemp : totalCromosomas) {
                
                if (poblacionTemp.getConflictos() < menorConflic[i]) {
                    
                    menorConflic[i] = poblacionTemp.getConflictos();
                    posMenConflic[i] = totalCromosomas.indexOf(poblacionTemp);
                    System.out.println(" Pos del menor -> " + posMenConflic[i]);
                }
            }
            System.out.println(" Pasan a SiguientePoblacion en el Hijo " + i + " menor colisiones " + totalCromosomas.get(posMenConflic[i]).getConflictos() + " " + Arrays.toString(totalCromosomas.get(posMenConflic[i]).getVec_genes()));
            
            poblacion.add(totalCromosomas.get(posMenConflic[i]));
            totalCromosomas.remove(posMenConflic[i]);
        }
        
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");
        
    }
    
}
