/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import static Juego.Genetica.numHijos;
import static Juego.Icondiciones.ANCHO_TABLERO;
import Recursos.Recursos;
import java.util.Arrays;
import static Juego.Icondiciones.poblacion;

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
    Aceptacion siguientePoblacion;

    // vectores para el cruzamiento mutacion
    Cromosoma cromosomasHijos[];
    Cromosoma auxCruceHijos[];
    Cromosoma auxMutacionHijos[];
    int posPadres[];

    //Vector para la configuracion
    int[] vecConfig;

    public Reproduccion(int v[]) {
        this.recursos = new Recursos();
        this.seleccion = new Seleccion();
        this.fitness = new Fitness();
        this.cruce = new Cruce();
        this.mutacion = new Mutacion();
        this.siguientePoblacion = new Aceptacion();

        this.vecConfig = v;
    }

    // Produce una nueva generacion
    public void generarReproduccion() {

        this.posPadres = new int[2];

        this.cromosomasHijos = new Cromosoma[2];
        this.auxCruceHijos = new Cromosoma[2];
        this.auxMutacionHijos = new Cromosoma[2];

        // se crean dos nuevos cromosomas 
        cromosomasHijos[0] = new Cromosoma(ANCHO_TABLERO);
        cromosomasHijos[1] = new Cromosoma(ANCHO_TABLERO);

        // aqui se selecciona un padre 
        posPadres[0] = this.seleccionarPadre();

        // se obtiene un nuevo padre que no sea igual al padre A
        posPadres[1] = this.seleccionarPadre(posPadres[0]);

        //Método que evalua la conficurazion del cruce, mutacion y aceptación
        this.configuracionesF2F3F4();
        
        System.out.println("Recibo - > " + Arrays.toString(auxCruceHijos));
                
        // Aqui se agrega agrega el numero de hijos

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

    private void configuracionesF2F3F4() {
        //Configuraciones Fase2 = Cruce
        switch (vecConfig[1]) {
            case 0://1 punto
                auxCruceHijos = cruce.cruceUnPunto(posPadres, cromosomasHijos);
                break;
            case 1://2 puntos
                auxCruceHijos = cruce.cruceDosPuntos(posPadres, cromosomasHijos);
                break;
            case 2://Uniforme
                auxCruceHijos = cruce.cruceUniforme(posPadres, cromosomasHijos);
                break;
            case 3://Arimetico
                auxCruceHijos = cruce.cruceAritmetico(posPadres, cromosomasHijos);
                break;
        }

        //Configuraciones Fase3 = Mutación
        switch (vecConfig[2]) {
            case 0://Inversión de genes
                auxMutacionHijos = mutacion.inversionGenes(auxCruceHijos);
                break;
            case 1://Cambio de orden
                auxMutacionHijos = mutacion.intercambiarOrden(auxCruceHijos);
                break;
            case 2://Modificación de genes
                auxMutacionHijos = mutacion.modificacionGenes(auxCruceHijos);
                break;

        }

        //Configuraciones Fase4 = nueva población
        switch (vecConfig[3]) {
            case 0://Aceptación total
                siguientePoblacion.aceptacionTotal(auxMutacionHijos);
                break;
            case 1://De mejora
                siguientePoblacion.deMejora(auxMutacionHijos);
                break;
            case 2://Por torneo
                siguientePoblacion.porTorneo(auxMutacionHijos);
                break;
        }
    }

}
