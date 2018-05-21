/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

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

    public Cromosoma[] modificacionGenes(Cromosoma cromosomasHijos[]) {

        int posCambio, genPosCambio, nuevoGen, numModificaciones = 4;
        int posDiferentes[] = new int[5];
        posDiferentes[0] = -1;
        int sumGen = 1, resGen = -1;

        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < cromosomasHijos.length; i++) {
            for (int j = 1; j <= 4; j++) {
                posCambio = recursos.getAleatorioExclusivo(0, ANCHO_TABLERO - 1, posDiferentes[j - 1]);
                genPosCambio = cromosomasHijos[i].getVecSolucion(posCambio);
                if (genPosCambio % 2 == 0) {
                    nuevoGen = genPosCambio + sumGen;
                    cromosomasHijos[i].setVecSolucion(posCambio, nuevoGen);
                } else {
                    nuevoGen = genPosCambio + resGen;
                    cromosomasHijos[i].setVecSolucion(posCambio, nuevoGen);
                }
                posDiferentes[j] = posCambio;
            }
        }

        System.out.println("Mutacion en el Hijo 1 " + Arrays.toString(cromosomasHijos[0].getVec_genes()));
        System.out.println("Mutacion en el Hijo 2 " + Arrays.toString(cromosomasHijos[1].getVec_genes()));
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");

        return cromosomasHijos;
    }
}
