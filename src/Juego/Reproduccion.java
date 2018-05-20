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
    SiguientePoblacion siguientePoblacion;

    public Reproduccion() {
        this.recursos = new Recursos();
        this.seleccion = new Seleccion();
        this.fitness = new Fitness();
        this.cruce = new Cruce();
        this.mutacion = new Mutacion();
        this.siguientePoblacion = new SiguientePoblacion();
    }

    // Produce una nueva generacion
    public void generarReproduccion() {

        int posPadres [] = new int[2];

        Cromosoma cromosomasHijos [] = new Cromosoma[2];
        Cromosoma auxCruceHijos [] = new Cromosoma[2];
        Cromosoma auxMutacionHijos[] = new Cromosoma[2];
  
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
        
            //*********************** cruce en un punto ***********************
            // ------------> auxCruceHijos = cruce.cruceUnPunto(posPadres,cromosomasHijos);

            // *********************** cruce en dos punto ***********************
            // ------------> auxCruceHijo = cruce.cruceDosPuntos(posPadres,cromosomasHijos);

            // *********************** cruce uniforme ***********************
            auxCruceHijos = cruce.cruceUniforme(posPadres,cromosomasHijos);
            
            // *********************** cruce Aritmetico *********************** (ESTE CRUCE ES EL QUE NO DEBE DE UTILIZARCE PERO PARA QUE VEA EL PROFE QUE SI LO HEMOS HECHO AQUI ESTA)
            // ------------> auxCruceHijos = cruce.cruceAritmetico(posPadres,cromosomasHijos);
        
        System.out.println("Recibo - > "+Arrays.toString(auxCruceHijos));
        
        /**
         * Aqui empieza la mutacion
         */
            // *********************** de inversion de Genes ***********************
            // ------------> auxMutacionHijos = mutacion.inversionGenes(auxCruceHijos);

            // *********************** de cambio de orden ***********************
            auxMutacionHijos = mutacion.intercambiarOrden(auxCruceHijos);

            // *********************** de modificacion de genes ***********************
            // ------------> auxMutacionHijos = mutacion.modificacionGenes(auxCruceHijos);
        
        /**
         * Aqui empieza seleccion para la nueva generacion
         */
            // *********************** de aceptacion total ***********************
            // ------------> siguientePoblacion.aceptacionTotal(auxMutacionHijos);

            // *********************** de mejora ***********************
            // ------------> siguientePoblacion.deMejora(auxMutacionHijos);

            // *********************** por torneo ***********************
            siguientePoblacion.porTorneo(auxMutacionHijos);
        
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
            c.setPosicion(-1);
        });

    }

}
