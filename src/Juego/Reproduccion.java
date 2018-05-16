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
        Cromosoma cromoHijoPA, cromoHijoPB, auxMutacionH1, auxMutacionH2, auxCruceH1, auxCruceH2;

        // se crean dos nuevos cromosomas 
        cromoHijoPA = new Cromosoma(ANCHO_TABLERO);
        cromoHijoPB = new Cromosoma(ANCHO_TABLERO);
        
        // aqui se selecciona un padre 
        posPadreA = this.seleccionarPadre();

        // se obtiene un nuevo padre que no sea igual al padre A
        posPadreB = this.seleccionarPadre(posPadreA);
        
        /**
         * Aqui se genera el cruce 
         */
        
        // Elige uno o ambos de los siguientes: ahora con objetos 
        //cruce.cruceParcial(posPadreA, posPadreB, cromoHijoPA, cromoHijoPB);
        
        // cruce en un punto
        // ------------> auxCruceH1 = cruce.cruceUnPunto(posPadreA, posPadreB, cromoHijoPA);
        // ------------> auxCruceH2 = cruce.cruceUnPunto(posPadreA, posPadreB, cromoHijoPB);
        
        // cruce en dos punto
        // ------------> auxCruceH1 = cruce.cruceDosPuntos(posPadreA, posPadreB, cromoHijoPA);
        // ------------> auxCruceH2 = cruce.cruceDosPuntos(posPadreA, posPadreB, cromoHijoPB);
        
        // cruce uniforme
        auxCruceH1 = cruce.cruceDosPuntos(posPadreA, posPadreB, cromoHijoPA);
        auxCruceH2 = cruce.cruceDosPuntos(posPadreA, posPadreB, cromoHijoPB);
        
        /**
         * Aqui empieza la mutacion
         */
        
        // de inversion de Genes
        // ------------> auxMutacionH1 = mutacion.inversionGenes(cromoHijoPA);
        // ------------> auxMutacionH2 = mutacion.inversionGenes(cromoHijoPB);
        
        // de cambio de orden
        auxMutacionH1 = mutacion.intercambiarOrden(auxCruceH1);
        auxMutacionH2 = mutacion.intercambiarOrden(auxCruceH2);
        
        // de modificacion de genes
        // ------------> auxMutacionH1 = mutacion.modificacionGenes(cromoHijoPA);
        // ------------> auxMutacionH2 = mutacion.modificacionGenes(cromoHijoPB);
        
        /**
         * Aqui empieza seleccion para la nueva generacion
         */
        
        // *********************** de aceptacion total ***********************
        nuevaPoblacion.aceptacionTotal(auxMutacionH1, auxMutacionH2);
        
        // *********************** de mejora ***********************
        // ------------> nuevaPoblacion.deMejora(posPadreA, posPadreB, auxH1, auxH2);
        
        // *********************** por torneo ***********************
        // ------------> nuevaPoblacion.porTorneo(auxH1, auxH2);
        
        /**
         * Aqui se agrega agrega el numero de hijos
         */
        
        numHijos += 2;
  
    }

    // Seleccionar los padres para la generarReproduccion
    public int seleccionarPadre() {
        int posPadre = 0;
        boolean terminado = false;

        while (!terminado) {
            // Elige al azar un padre elegible.
            posPadre = recursos.getAleatorio(0, poblacion.size() - 1);
            cromosoma = poblacion.get(posPadre);
            if (cromosoma.getSeleccionado()) {
                terminado = true;
            }
        }

        return posPadre;
    }

    // Seleccionar los padres para la generarReproduccion, diferente al seleccionado
    // Función encargada, consulta "choosepadre()".
    public int seleccionarPadre(int padreA) {
        int padre = 0;
        boolean terminado = false;

        while (!terminado) {

            // Elige al azar un padre elegible.
            padre = recursos.getAleatorio(0, poblacion.size() - 1);
            if (padre != padreA) {
                cromosoma = poblacion.get(padre);
                if (cromosoma.getSeleccionado() == true) {
                    terminado = true;
                }
            }
        }

        return padre;
    }
    
    // Prepara la poblacion de la siguiente generacion
    public void prepararSiguienteGeneracion() {
        // Restaura estado de cromosoma
        poblacion.forEach((c) -> {
            c.setSeleccionado(false);
        });
 
    }
    
}
