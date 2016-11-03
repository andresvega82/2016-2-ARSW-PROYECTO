package edu.eci.arsw.RISKA.modelo;

import java.util.ArrayList;
import java.util.HashMap;

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
    public HashMap<Integer, Integer> tropas;
    /**
     * Crear un jugador.
     * @param nombre nombre del jugador.
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.tropas = new HashMap<Integer, Integer>();
    }
    public void serColor(String col){
        this.color = col+"";
    }
    public void setMision(Mision m){
        this.mision = m;     
    }
    
    public void agregarTropa(int pais){
        if( contieneTropa(pais)){
            int cantTropas = tropas.get(pais) +1;
            tropas.put(pais, cantTropas);
            
        }else{
            tropas.put(pais, 1);
        }
    }
    
    public Boolean contieneTropa(int pais){
        return tropas.containsKey(pais);        
    }
    
}
