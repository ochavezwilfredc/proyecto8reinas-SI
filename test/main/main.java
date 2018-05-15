/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Juego.Cromosoma;
import Juego.Genetica;

/**
 *
 * @author mendoza
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Genetica reinas = new Genetica();
        Cromosoma c = reinas.algoritmoGenetico();
        //System.out.println(c.toString());

    }

}
