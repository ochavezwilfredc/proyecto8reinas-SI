/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Recursos.Recursos;
import static Juego.Icondiciones.ANCHO_TABLERO;
import static Juego.Icondiciones.poblacion;
import java.util.Arrays;

/**
 *
 * @author mendoza
 */
public class Cruce {

    Recursos recursos;

    public Cruce() {
        this.recursos = new Recursos();
    }

    public Cromosoma[] cruceUnPunto(int posPadres[], Cromosoma cromosomasHijos[]) {

        Cromosoma Padre1, Padre2;
        int puntoCruceHijo;
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < posPadres.length; i++) {

            puntoCruceHijo = recursos.getAleatorio(0, ANCHO_TABLERO - 1);

            if (i % 2 == 0) {
                Padre1 = poblacion.get(posPadres[0]);
                Padre2 = poblacion.get(posPadres[1]);
            } else {
                Padre1 = poblacion.get(posPadres[1]);
                Padre2 = poblacion.get(posPadres[0]);
            }

            System.out.println("Padre 1 " + Arrays.toString(Padre1.getVec_genes()));
            System.out.println("Padre 2 " + Arrays.toString(Padre2.getVec_genes()));

            for (int j = 0; j < ANCHO_TABLERO; j++) {

                if (j <= puntoCruceHijo) {
                    cromosomasHijos[i].setVecSolucion(j, Padre1.getVecSolucion(j));
                } else {
                    cromosomasHijos[i].setVecSolucion(j, Padre2.getVecSolucion(j));
                }
            }
        }

        System.out.println("Hijo 1 " + Arrays.toString(cromosomasHijos[0].getVec_genes()));
        System.out.println("Hijo 2 " + Arrays.toString(cromosomasHijos[1].getVec_genes()));
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");

        return cromosomasHijos;
    }

    public Cromosoma[] cruceDosPuntos(int posPadres[], Cromosoma cromosomasHijos[]) {

        Cromosoma Padre1, Padre2;
        int punto1, punto2, indice;
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < cromosomasHijos.length; i++) {

            // se le da limites de aleatoriedad
            punto1 = recursos.getAleatorio(0, 2);
            punto2 = recursos.getAleatorioExclusivo(5, ANCHO_TABLERO - 1, punto1);

            if (punto2 < punto1) {
                indice = punto1;
                punto1 = punto2;
                punto2 = indice;
            }

            if (i % 2 == 0) {
                Padre1 = poblacion.get(posPadres[0]);
                Padre2 = poblacion.get(posPadres[1]);
            } else {
                Padre1 = poblacion.get(posPadres[1]);
                Padre2 = poblacion.get(posPadres[0]);
            }

            System.out.println("Padre 1 " + Arrays.toString(Padre1.getVec_genes()));
            System.out.println("Padre 2 " + Arrays.toString(Padre2.getVec_genes()));

            for (int j = 0; j < ANCHO_TABLERO; j++) {

                if (j <= punto1) {
                    cromosomasHijos[i].setVecSolucion(j, Padre1.getVecSolucion(j));
                } else if (j >= punto2) {
                    cromosomasHijos[i].setVecSolucion(j, Padre1.getVecSolucion(j));
                } else {
                    cromosomasHijos[i].setVecSolucion(j, Padre2.getVecSolucion(j));
                }

            }
        }
        System.out.println("Hijo 1 " + Arrays.toString(cromosomasHijos[0].getVec_genes()));
        System.out.println("Hijo 2 " + Arrays.toString(cromosomasHijos[1].getVec_genes()));
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");
        return cromosomasHijos;
    }

    public Cromosoma[] cruceUniforme(int posPadres[], Cromosoma cromosomasHijos[]) {

        Cromosoma Padre1, Padre2;

        /*Cromosoma auxPadre1, auxPadre2;
        int posDiferentes[] = new int[5];
        int CromosomaTemp1[] = new int[4];
        int CromosomaTemp2[] = new int[4];
        int cromosomaDescend[] = new int[ANCHO_TABLERO];
        int x,y;
        //ArrayList<Integer> totalCromosomas = new ArrayList<Integer>();

        int pos, genPos;
        posDiferentes[0] = -1;
        /*
        Cromosoma padres[] = new Cromosoma[2];
        
        padres[0] = poblacion.get(posPadres[0]);
        padres[1] = poblacion.get(posPadres[1]);
        System.out.println("Padrees para Genes extraidos 1 " + Arrays.toString(padres[0].getVec_genes()));
        System.out.println("Padrees para Genes extraidos 2 " + Arrays.toString(padres[1].getVec_genes()));

        /*
        for (int i = 0; i < cromosomasHijos.length; i++) {
            
            
            for (int j = 0; j < posPadres.length; j++) {

                for (int k = 1; k <= 4; k++) {

                    pos = recursos.getAleatorioExclusivo(0, ANCHO_TABLERO - 1, posDiferentes[k - 1]);
                    genPos = padres[j].getVecSolucion(pos);

                    if (j % 2 == 0) {

                        CromosomaTemp1[k-1] = genPos;
                    } else {

                        CromosomaTemp2[k-1] = genPos;
                    }

                    posDiferentes[j] = pos;
                }

            }
            
            x=y=0;
            
            for (int j = 0; j < ANCHO_TABLERO; j++) {
                
                if (j%2 == 0) {
                    cromosomasHijos[i].setVecSolucion(j, CromosomaTemp1[x]);
                    x++;
                } else {
                    cromosomasHijos[i].setVecSolucion(j, CromosomaTemp2[y]);
                    y++;
                }
            }
            
            
            
            
        }
    
        for (int i = 0; i < 2; i++) {

            if (i % 2 == 0) {

                System.out.println("Genes extraidos en el vector 1" + Arrays.toString(CromosomaTemp1));

            } else {

                System.out.println("Genes extraidos en el vector 1" + Arrays.toString(CromosomaTemp2));
            }

        }

        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");
         */
        for (int i = 0; i < cromosomasHijos.length; i++) {

            if (i % 2 == 0) {
                Padre1 = poblacion.get(posPadres[0]);
                Padre2 = poblacion.get(posPadres[1]);
            } else {
                Padre1 = poblacion.get(posPadres[1]);
                Padre2 = poblacion.get(posPadres[0]);
            }

            System.out.println("Padre 1 " + Arrays.toString(Padre1.getVec_genes()));
            System.out.println("Padre 2 " + Arrays.toString(Padre2.getVec_genes()));

            for (int j = 0; j < ANCHO_TABLERO; j++) {

                if (j % 2 == 0) {
                    cromosomasHijos[i].setVecSolucion(j, Padre1.getVecSolucion(j));
                } else {
                    cromosomasHijos[i].setVecSolucion(j, Padre2.getVecSolucion(j));
                }
            }
        }

        System.out.println("Hijo 1 " + Arrays.toString(cromosomasHijos[0].getVec_genes()));
        System.out.println("Hijo 2 " + Arrays.toString(cromosomasHijos[1].getVec_genes()));
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");
        return cromosomasHijos;
    }

    public Cromosoma[] cruceAritmetico(int posPadres[], Cromosoma cromosomasHijos[]) {

        Binario binario = new Binario();
        Cromosoma Padres[] = new Cromosoma[2];
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");

        String vectorResultado[] = new String[ANCHO_TABLERO];
        int vectorFinal[] = new int[ANCHO_TABLERO];
        String vectorAuxP1[] = new String[ANCHO_TABLERO];
        String vectorAuxP2[] = new String[ANCHO_TABLERO];
        String temp1[] = new String[3];
        String temp2[] = new String[3];
        String tempVR[] = new String[3];
        String tempResultado = "";
        int tempFinal[] = new int[3];
        
        Padres[0] = poblacion.get(posPadres[0]);
        Padres[1] = poblacion.get(posPadres[1]);

        for (int i = 0; i < Padres.length; i++) {

            if (i % 2 == 0) {
                for (int j = 0; j < ANCHO_TABLERO; j++) {
                    vectorAuxP1[j] = binario.obtenerBinario(Padres[i].getVecSolucion(j));
                }
            } else {
                for (int j = 0; j < ANCHO_TABLERO; j++) {
                    vectorAuxP2[j] = binario.obtenerBinario(Padres[i].getVecSolucion(j));
                }
            }

        }
        System.out.println("Padre 1 " + Arrays.toString(Padres[0].getVec_genes()));
        System.out.println("Padre 2 " + Arrays.toString(Padres[1].getVec_genes()));
        System.out.println("-> Padres 1 Binario " + Arrays.toString(vectorAuxP1));
        System.out.println("-> Padres 2 Binario " + Arrays.toString(vectorAuxP2));
        
        for (int i = 0; i < ANCHO_TABLERO; i++) {
            tempResultado = "";
            temp1 = vectorAuxP1[i].split("");
            temp2 = vectorAuxP2[i].split("");
            
            for (int j = 0; j < temp1.length; j++) {
                tempResultado += Integer.toString( Integer.parseInt(temp1[j]) * Integer.parseInt(temp2[j]));
            }
               
            vectorResultado[i] = tempResultado;
               
        }
        
        System.out.println("-> Vector resultado " + Arrays.toString(vectorResultado));
        
        
        for (int i = 0; i < ANCHO_TABLERO; i++) {
            
            tempVR = vectorResultado[i].split("");
            
            for (int j = 0; j < temp1.length; j++) {
                tempFinal[j] = Integer.parseInt(tempVR[j]);
            }            
            vectorFinal[i] = binario.binarioDecimal(tempFinal, tempFinal.length-1);
            
               
        }
        
        System.out.println("-> Vector resultado Decimal " + Arrays.toString(vectorFinal));
        
        for (int i = 0; i < cromosomasHijos.length; i++) {
            
            for (int j = 0; j < ANCHO_TABLERO; j++) {
                cromosomasHijos[i].setVecSolucion(j, vectorFinal[j]);
            }
            
        }
        System.out.println("Hijo 1 " + Arrays.toString(cromosomasHijos[0].getVec_genes()));
        System.out.println("Hijo 2 " + Arrays.toString(cromosomasHijos[1].getVec_genes()));
        System.out.println("// --------------------------------------------------------------------------------------------------------------------------------------------");

        return cromosomasHijos;

    }
}
