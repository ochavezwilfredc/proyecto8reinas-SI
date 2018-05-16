/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import static Juego.Genetica.numMutaciones;
import static Juego.Genetica.poblacion;
import static Juego.Icondiciones.ANCHO_TABLERO;
import Recursos.Recursos;

/**
 *
 * @author mendoza
 */
public class Mutacion implements Icondiciones {

    /**
     * intercambia una mutación que suceda en los genes Cambia la posición de
     * las reinas en un cromosoma al azar de acuerdo con el número de
     * intercambios NOTA: siempre tienen que ser dos genes porque si es uno
     * saldria esta figura
     *
     * 1,0,0,0,0,0,0,0, 0,1,0,0,0,0,0,0, 0,0,1,0,0,0,0,0, 0,0,0,1,0,0,0,0,
     * 0,0,0,1,0,0,0,0, 0,0,0,0,0,1,0,0, 0,0,0,0,0,0,1,0, 0,0,0,0,0,0,0,1,
     *
     * entonces tienen que ser dos genes para cambiar entre si posiciones
     *
     * @param indice
     * @param intercambio
     */
    public void intercambiarOrden(int indice, int intercambio) {

        int i = 0;
        int tempGen1, tempGen2, posGen1, posGen2;
        boolean terminado = false;
        Cromosoma cromosoma;
        Recursos recursos;
        cromosoma = poblacion.get(indice);

        while (!terminado) {

            // se crea un objeto recursos
            recursos = new Recursos();

            //Face del cruce en dos puntos
            posGen1 = recursos.getAleatorio(0, ANCHO_TABLERO - 1);
            posGen2 = recursos.getAleatorioExclusivo(ANCHO_TABLERO - 1, posGen1);

            // Cambia los genes seleccionados cruzadamente
            tempGen1 = cromosoma.getVecSolucion(posGen1);
            tempGen2 = cromosoma.getVecSolucion(posGen2);

            cromosoma.setVecSolucion(posGen1, tempGen2);
            cromosoma.setVecSolucion(posGen2, tempGen1);

            if (i == intercambio) {
                terminado = true;
            }
            i++;
        }
        numMutaciones++;
    }
    
    public Cromosoma intercambiarOrden(Cromosoma hijo) {

        Recursos rand = new Recursos();
        int intercambio = rand.getAleatorio(0, ANCHO_TABLERO-1);
        int i = 0;
        int tempGen1, tempGen2, posGen1, posGen2;
        boolean terminado = false;
        Recursos recursos;

        while (!terminado) {

            // se crea un objeto recursos
            recursos = new Recursos();

            //Face del cruce en dos puntos
            posGen1 = recursos.getAleatorio(0, ANCHO_TABLERO - 1);
            posGen2 = recursos.getAleatorioExclusivo(ANCHO_TABLERO - 1, posGen1);

            // Cambia los genes seleccionados cruzadamente
            tempGen1 = hijo.getVecSolucion(posGen1);
            tempGen2 = hijo.getVecSolucion(posGen2);

            hijo.setVecSolucion(posGen1, tempGen2);
            hijo.setVecSolucion(posGen2, tempGen1);

            if (i == intercambio) {
                terminado = true;
            }
            i++;
        }
        numMutaciones++;
        
        return hijo;
    }

    /**
     * Inversion de genes se utiliza para cambiar o mutar a otro valort los ya
     * contenidos en los genes.
     *
     * @param hijo
     * @return
     */
    public Cromosoma inversionGenes(Cromosoma hijo) {

        int intercambio = new Recursos().getAleatorio(0, ANCHO_TABLERO - 1);
        int nuevoGen, posGen, i = 0;
        boolean terminado = false;
        Recursos recursos;

        while (!terminado) {

            // se crea un objeto recursos
            recursos = new Recursos();

            //Face del cruce en dos puntos
            posGen = recursos.getAleatorio(0, ANCHO_TABLERO - 1);
            nuevoGen = recursos.getAleatorio(0, ANCHO_TABLERO - 1);

            // Setea el valor con el gen mutado y la posicion
            hijo.setVecSolucion(posGen, nuevoGen);

            if (i == intercambio) {
                terminado = true;
            }
            i++;
        }
        numMutaciones++;

        return hijo;
    }

    /**
     * Modificaciones de genes Se realizan pequeñas modificaciones en los genes.
     * Por ejemplo en una codificación basada en numeros reales se realizan
     * sumas de números muy pequeños positivos o negativos.
     *
     * @param hijo
     * @return
     */
    public Cromosoma modificacionGenes(Cromosoma hijo) {

        int cambio = new Recursos().getAleatorio(0, ANCHO_TABLERO - 1);
        int nuevoGen, posGen, valGen, valPos;
        boolean terminado = false;
        Recursos recursos;
        int sumGen = 1;
        int resGen = -1;

        for (int i = 0; i < cambio; i++) {

            if (i == cambio) {
                terminado = true;

            } else {

                // se crea un objeto recursos
                recursos = new Recursos();

                //Face del cruce en dos puntos
                posGen = recursos.getAleatorio(0, ANCHO_TABLERO - 1);
                valPos = hijo.getVecSolucion(posGen);
                
                if (valPos % 2 == 0 ) {
                    
                    nuevoGen = valPos + sumGen;
                    // Setea el valor con el gen mutado y la posicion
                    hijo.setVecSolucion(posGen, nuevoGen);
                } else {
                    
                    nuevoGen = valPos + resGen;
                    // Setea el valor con el gen mutado y la posicion
                    hijo.setVecSolucion(posGen, nuevoGen);
                }

            }
        }

        numMutaciones++;

        return hijo;

    }

}
