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
import java.util.Arrays;

/**
 *
 * @author mendoza
 */
public class Mutacion implements Icondiciones {

    Recursos recursos;

    public Mutacion() {

        this.recursos = new Recursos();

    }

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
            posGen2 = recursos.getAleatorioExclusivo(0, ANCHO_TABLERO - 1, posGen1);

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

    
    public Cromosoma[] inversionGenes(Cromosoma cromosomasHijos[]) {

        int puntoInversionGen, valor;
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < cromosomasHijos.length; i++) {

            puntoInversionGen = recursos.getAleatorio(0, ANCHO_TABLERO - 1);

            valor = recursos.getAleatorio(0, ANCHO_TABLERO - 1); 

            cromosomasHijos[i].setVecSolucion(puntoInversionGen, valor);
        }
        System.out.println("Mutacion en el Hijo 1 " + Arrays.toString(cromosomasHijos[0].getVec_genes()));
        System.out.println("Mutacion en el Hijo 2 " + Arrays.toString(cromosomasHijos[1].getVec_genes()));
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");
        return cromosomasHijos;
    }

    public Cromosoma[] intercambiarOrden(Cromosoma cromosomasHijos[]) {

        int puntoIntercambio1, puntoIntercambio2, genPosIntercambio1, genPosIntercambio2;

        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < cromosomasHijos.length; i++) {

            puntoIntercambio1 = recursos.getAleatorio(0, ANCHO_TABLERO - 1);
            puntoIntercambio2 = recursos.getAleatorioExclusivo(0, ANCHO_TABLERO - 1, puntoIntercambio1);

            genPosIntercambio1 = cromosomasHijos[i].getVecSolucion(puntoIntercambio1);
            genPosIntercambio2 = cromosomasHijos[i].getVecSolucion(puntoIntercambio2);

            cromosomasHijos[i].setVecSolucion(puntoIntercambio1, genPosIntercambio2);
            cromosomasHijos[i].setVecSolucion(puntoIntercambio2, genPosIntercambio1);

        }
        System.out.println("Mutacion en el Hijo 1 " + Arrays.toString(cromosomasHijos[0].getVec_genes()));
        System.out.println("Mutacion en el Hijo 2 " + Arrays.toString(cromosomasHijos[1].getVec_genes()));
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");

        return cromosomasHijos;
    }

    public Cromosoma modificacionGenes(Cromosoma hijo) {

        int cambio = new Recursos().getAleatorio(1, ANCHO_TABLERO - 1);
        int nuevoGen, posGen, valGen, valPos;
        boolean terminado;
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

                if (valPos % 2 == 0) {

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
