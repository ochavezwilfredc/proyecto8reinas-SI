/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.util.ArrayList;

/**
 *
 * @author mendoza
 */
public class Binario {

    public String obtenerBinario(int numero) {
        ArrayList<String> binario = new ArrayList<>();
        int resto;
        String binarioString = "";

        if (numero > 1) {

            do {

                resto = numero % 2;
                numero = numero / 2;

                binario.add(0, Integer.toString(resto));

            } while (numero >= 2); //Haremos el bucle hasta que el cociente no se pueda dividir mas

            binario.add(0, Integer.toString(numero)); //Cogeremos el ultimo cociente

            if (binario.size() == 2 && numero == 1) {
                binario.add(0, Integer.toString(0));
            }

        } else if (numero == 1) {
        
            binario.add(0, Integer.toString(1));
            binario.add(0, Integer.toString(0));
            binario.add(0, Integer.toString(0));
            
        } else if (numero == 0) {
            binario.add(0, Integer.toString(0));
            binario.add(0, Integer.toString(0));
            binario.add(0, Integer.toString(0));
        }

        //Cogemos cada uno de los elementos del ArrayList y los juntamos en un String
        for (int i = 0; i < binario.size(); i++) {
            binarioString += binario.get(i);
        }
        return binarioString;
    }
    
    public int binarioDecimal( int[] valorBinario, int index ) {
        
        int acumulado = 0;
        int aux = index;
        
        for (int i = 0; i < valorBinario.length; i++) {
            
            acumulado = (int) (acumulado + valorBinario[i] * (Math.pow(2, aux))) ;
            aux--;
        }

        return acumulado;
  }

   
}
