package edu.eci.arsw.RISKA.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tatiana Higuera, Andres Vega, Nicolas Moreno
 */
public class Jugador {
    public String nombre;
    public String color;
    public Mision mision;
    /**
     * Crear un jugador.
     * @param nombre nombre del jugador.
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
    }
    public void serColor(String col){
        this.color = col+"";
    }
    public void setMision(Mision m){
        this.mision = m;     
    }
}
