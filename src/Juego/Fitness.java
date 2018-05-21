/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import static Juego.Genetica.poblacion;

/**
 *
 * @author mendoza
 */
public class Fitness {

    // Buscar la mejor aptud 
    public void evaluar() {
        poblacion.forEach((cromosoma) -> {
            cromosoma.calcularConflictos();
        });

    }
}
