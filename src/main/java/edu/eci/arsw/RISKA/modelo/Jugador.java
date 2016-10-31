package edu.eci.arsw.RISKA.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Jugador implements Comparable<Jugador>{
    public String nombre;
    /**
     * Crear un jugador.
     * @param nombre nombre del jugador.
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
    }    

    @Override
    public int compareTo(Jugador o) {
        if(this.nombre.equalsIgnoreCase(o.nombre)){
            return 0;
        }
        else{
            return 1;
        }
    }
}
