package edu.eci.arsw.RISKA.modelo;

import java.util.ArrayList;

/**
 *
 * @author Tatiana Higuera, Andres Vega, Nicolas Moreno.
 */
public class Partida {
    ArrayList<Jugador> jugadores;

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = (ArrayList<Jugador>) jugadores.clone();
    }
    
}
