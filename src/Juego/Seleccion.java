/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Recursos.Recursos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    public void ruleta() {
        int sumActitud = 0, seleccionar;
        // esta es la cantidad de padres que se seleccionara  
        int vecSelecionados[] = new int[CANT_PADRES];
        int sumFitnessTotal[] = new int[TAM_POBLACION];

        // suma secuencial de los fitness de la poblacion
        for (int i = 0; i < poblacion.size(); i++) {
            sumActitud += poblacion.get(i).getConflictos();
            sumFitnessTotal[i] = sumActitud;
        }

        // seleccionar padres
        for (int i = 0; i < CANT_PADRES; i++) {
            // El azar a seleccionar es la suma de todas las colisiones 
            seleccionar = recursos.getAleatorio(0, sumActitud);
            //se recorre la aptitud acumulada para identificar que individuo mayor al ramdon
            for (int j = 0; j < poblacion.size(); j++) {
                //Se compara el número aleatorio generado contra la aptitud acumulada
                if (sumFitnessTotal[j] > seleccionar) {
                    //si es mayor se selecciona el individuo
                    vecSelecionados[i] = j;
                    //Se rompe el for para generar un nuevo aleatorio
                    break;
                }
            }
        }

        for (int i = 0; i < CANT_PADRES; i++) {
            poblacion.get(vecSelecionados[i]).setSeleccionado(true);
            //System.out.println("Seleccion padre " + vecSelecionados[i] + " suma Act " + sumActitud + " ultimo " + sumFitnessTotal[TAM_POBLACION - 1]);
        }
    }

    public void torneo() {

        int excepto = -1;
        int menorConflic[] = new int[CANT_PADRES];
        int posMenConflic[] = new int[CANT_PADRES];
        Cromosoma posIndivMenConflic[] = new Cromosoma[CANT_PADRES];
        int vecPosicionesCromo[] = new int[CANT_INDIVIDUOS_TORNEO];


        ArrayList<Cromosoma> individuosTorneo = new ArrayList<>();

        for (int i = 0; i < CANT_INDIVIDUOS_TORNEO; i++) {
            vecPosicionesCromo[i] = recursos.getAleatorioExclusivo(0, poblacion.size() - 1, excepto);
            excepto = vecPosicionesCromo[i];
        }

        for (int i = 0; i < CANT_INDIVIDUOS_TORNEO; i++) {
            poblacion.get(vecPosicionesCromo[i]).setPosicion(vecPosicionesCromo[i]);
            individuosTorneo.add(poblacion.get(vecPosicionesCromo[i]));
        }

        System.out.println("Las posiciones de los individuos seleccionados al azar son : " + Arrays.toString(vecPosicionesCromo));

        for (int i = 0; i < individuosTorneo.size(); i++) {
            System.out.println(" -> Los individuos a realizar el torneo pos (" + individuosTorneo.get(i).getPosicion() + ") colisiones (" + individuosTorneo.get(i).getConflictos() + ") : ");
        }

        for (int i = 0; i < CANT_PADRES; i++) {
            menorConflic[i] = individuosTorneo.get(0).getConflictos();
            for (Cromosoma individuo : individuosTorneo) {
                if (individuo.getConflictos() < menorConflic[i]) {
                    menorConflic[i] = individuo.getConflictos();
                    posMenConflic[i] = individuosTorneo.indexOf(individuo);

                }
            }
            posIndivMenConflic[i] = individuosTorneo.get(posMenConflic[i]);
            individuosTorneo.remove(posMenConflic[i]);
        }

        for (Cromosoma posIndivMenConflic1 : posIndivMenConflic) {
            System.out.println("Las posiciones de los individuos que menos colisiones son la posición : " + (posIndivMenConflic1.getPosicion()));
            poblacion.get(posIndivMenConflic1.getPosicion()).setSeleccionado(true);
        }

    }

    /**
     * El metodo de ruleta funciona mal cuando existan grandes diferencias entre
     * los fitness de los individuos de la población. Por ejemplo si un
     * cromosoma ocupa el 90% de la ruleta el resto de los cromosomas tienen muy
     * pocas posibilidades de ser elegidos. La selección por ranking da solución
     * a este problema.
     *
     * Los individuos son ordenados de acuerdo a su ranking de fitness. De esta
     * manera si tenemos n cromosomas el individuo con peor fitness se le
     * asignará un 1 y el que tenga el mejor fitness se le asignará la n.
     *
     *  * fuente: http://www.aic.uniovi.es/ssii/Tutorial/Seleccion.htm
     */
    public void ranking() {

        //ordeno la lista de menor a mayor por el valor
        Collections.sort(poblacion, (Cromosoma c, Cromosoma c1) -> {
            return new Integer(c.getConflictos()).compareTo(new Integer(c1.getConflictos()));
        });

        //mejor es el primero
        poblacion.get(0).setSeleccionado(true);
        //peor el ultimo
        poblacion.get(poblacion.size() - 1).setSeleccionado(true);

    }

    /**
     * Este concepto expresa la idea de que el mejor individuo de la actual
     * generación pase sin modificar a la siguiente generación. De esta forma no
     * se perderá el mejor cromosoma. Al resto de la población se le aplica la
     * reproducción normalmente.
     *
     * fuente: http://www.aic.uniovi.es/ssii/Tutorial/Seleccion.htm
     * http://sabia.tic.udc.es/mgestal/cv/AAGGtutorial/node11.html
     */
    public void elitista() {
        //ordeno la lista de menor a mayor por el valor
        Collections.sort(poblacion, (Cromosoma c, Cromosoma c1) -> {
            return new Integer(c.getConflictos()).compareTo(new Integer(c1.getConflictos()));
        });

        //seleccionamos los dos mejores
        poblacion.get(0).setSeleccionado(true);
        poblacion.get(1).setSeleccionado(true);

    }
}
