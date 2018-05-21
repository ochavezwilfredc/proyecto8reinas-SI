/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Recursos.Recursos;
import static Juego.Icondiciones.poblacion;
import java.util.ArrayList;
import java.util.Arrays;

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
        int vecSelecionados[] = new int[CANT_PADRES_RULETA];
        int sumFitnessTotal[] = new int[POBLACION_INICIAL];

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
            System.out.println("Seleccion padre " + vecSelecionados[i] + " suma Act " + sumActitud + " ultimo " + sumFitnessTotal[POBLACION_INICIAL - 1]);
        }
    }

    public void torneo() {

        int posIndividuosTorneo[] = new int[CANT_INDIVIDUOS_TORNEO];

        int posPadres[] = new int[CANT_PADRES_RULETA];

        int excepto = -1;

        int menorConflic[] = new int[2];
        int posMenConflic[] = new int[2];
        Cromosoma posIndivMenConflic[] = new Cromosoma[2];
        int posIndivMenConflicAux[] = new int[2];

        ArrayList<Cromosoma> individuosTorneo = new ArrayList<>();

        for (int i = 0; i < CANT_INDIVIDUOS_TORNEO; i++) {

            posIndividuosTorneo[i] = recursos.getAleatorioExclusivo(0, poblacion.size() - 1, excepto);
            excepto = posIndividuosTorneo[i];

        }

        for (int i = 0; i < CANT_INDIVIDUOS_TORNEO; i++) {

            poblacion.get(posIndividuosTorneo[i]).setPosicion(posIndividuosTorneo[i]);
            individuosTorneo.add(poblacion.get(posIndividuosTorneo[i]));
        }

        System.out.println("Las posiciones de los individuos seleccionados al azar son : " + Arrays.toString(posIndividuosTorneo));

        for (int i = 0; i < individuosTorneo.size(); i++) {
            System.out.println(" -> Los individuos a realizar el torneo pos (" + individuosTorneo.get(i).getPosicion() + ") colisiones (" + individuosTorneo.get(i).getConflictos() + ") : ");
        }

        for (int i = 0; i < CANT_PADRES_RULETA; i++) {

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
}
