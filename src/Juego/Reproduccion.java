/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import static Juego.Genetica.numHijos;
import static Juego.Icondiciones.ANCHO_TABLERO;
import static Juego.Icondiciones.poblacion;
import Recursos.Recursos;
import java.util.Arrays;

/**
 *
 * @author mendoza
 */
public class Reproduccion {

    Recursos recursos;
    Seleccion seleccion;
    Fitness fitness;
    Cromosoma cromosoma;
    Cruce cruce;
    Mutacion mutacion;
    NuevaPoblacion nuevaPoblacion;

    public Reproduccion() {
        this.recursos = new Recursos();
        this.seleccion = new Seleccion();
        this.fitness = new Fitness();
        this.cruce = new Cruce();
        this.mutacion = new Mutacion();
        this.nuevaPoblacion = new NuevaPoblacion();
    }

    // Produce una nueva generacion
    public void generarReproduccion() {

        int posPadreA, posPadreB;
        int posPadres [] = new int[2];
        Cromosoma cromoHijoPA, cromoHijoPB, auxMutacionH1, auxMutacionH2, auxCruceH1, auxCruceH2;
        Cromosoma cromosomasHijos [] = new Cromosoma[2];
        Cromosoma auxCruceHijo [] = new Cromosoma[2];
  
        // se crean dos nuevos cromosomas 
        cromosomasHijos[0] = new Cromosoma(ANCHO_TABLERO);
        cromosomasHijos[1] = new Cromosoma(ANCHO_TABLERO);

        // aqui se selecciona un padre 
        posPadres[0] = this.seleccionarPadre();

        // se obtiene un nuevo padre que no sea igual al padre A
        posPadres[1] = this.seleccionarPadre(posPadres[0]);

        /**
         * Aqui se genera el cruce
         */
        // Elige uno o ambos de los siguientes: ahora con objetos 
        //cruce.cruceParcial(posPadreA, posPadreB, cromoHijoPA, cromoHijoPB);
        // cruce en un punto
        // ------------> auxCruceH1 = cruce.cruceUnPunto(posPadreA, posPadreB, cromoHijoPA);
        // ------------> auxCruceH2 = cruce.cruceUnPunto(posPadreA, posPadreB, cromoHijoPB);
        // cruce en dos punto
        auxCruceHijo = cruce.cruceDosPuntos(posPadres,cromosomasHijos);
        System.out.println("Recibo - > "+Arrays.toString(auxCruceHijo));
        //auxCruceH2 = cruce.cruceDosPuntos(posPadreA, posPadreB, cromoHijoPB);
        // cruce uniforme ( este tipo de cruce demota muchisimo con la ruleta y cualquier tipo de mutacipn)
        // ------------> auxCruceH1 = cruce.cruceUniforme(posPadreA, posPadreB, cromoHijoPA);
        // ------------> auxCruceH2 = cruce.cruceUniforme(posPadreA, posPadreB, cromoHijoPB);

        /**
         * Aqui empieza la mutacion
         */
        // de inversion de Genes
        // ------------> auxMutacionH1 = mutacion.inversionGenes(cromoHijoPA);
        // ------------> auxMutacionH2 = mutacion.inversionGenes(cromoHijoPB);
        // de cambio de orden
        auxMutacionH1 = mutacion.intercambiarOrden(auxCruceHijo[0]);
        auxMutacionH2 = mutacion.intercambiarOrden(auxCruceHijo[1]);

        // de modificacion de genes
        // ------------> auxMutacionH1 = mutacion.modificacionGenes(auxCruceHijo[0]);
        // ------------> auxMutacionH2 = mutacion.modificacionGenes(auxCruceHijo[1]);
        /**
         * Aqui empieza seleccion para la nueva generacion
         */
        // *********************** de aceptacion total ***********************
        // ------------> nuevaPoblacion.aceptacionTotal(auxMutacionH1, auxMutacionH2);

        // *********************** de mejora ***********************
        nuevaPoblacion.deMejora(posPadres[0], posPadres[1], auxMutacionH1, auxMutacionH2);
        // *********************** por torneo ***********************
        // ------------> nuevaPoblacion.porTorneo(auxMutacionH1, auxMutacionH2);
        /**
         * Aqui se agrega agrega el numero de hijos
         */
        numHijos += 2;

    }

    // Seleccionar los padres para la generarReproduccion
    public int seleccionarPadre() {
        int posPadre = 0;
        boolean terminado = false;

        for (int i = 0; i < poblacion.size(); i++) {

            cromosoma = poblacion.get(i);
            
            if (cromosoma.getSeleccionado()) {
                
                posPadre = i;
                terminado = true;
            }
        }
        return posPadre;
    }

    // Seleccionar los padres para la generarReproduccion, diferente al seleccionado
    public int seleccionarPadre(int padreA) {
        int posPadre = 0;
        boolean terminado = false;

        for (int i = 0; i < poblacion.size(); i++) {

            cromosoma = poblacion.get(i);
            
            if (i != padreA) {
                
                if (cromosoma.getSeleccionado()) {
                    
                    posPadre = i;
                    terminado = true;
                }
            }
        }
        return posPadre;
    }

    // Prepara la poblacion de la siguiente generacion
    public void prepararSiguienteGeneracion() {
        // Restaura estado de cromosoma
        poblacion.forEach((c) -> {
            c.setSeleccionado(false);
        });

    }

}
