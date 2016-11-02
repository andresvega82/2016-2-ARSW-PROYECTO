package edu.eci.arsw.RISKA.modelo;

import java.util.ArrayList;
import java.util.Random;
import sun.nio.cs.ext.MSISO2022JP;

/**
 *
 * @author Tatiana Higuera, Andres Vega, Nicolas Moreno.
 */
public class Partida {
    ArrayList<Jugador> jugadores;
    ArrayList<Mision> misiones;

    public Partida() {
        jugadores = new ArrayList<>();
        misiones = new ArrayList<>();
        cargarMisiones();
    }
    
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = (ArrayList<Jugador>) jugadores.clone();
    }
    
    public void setMisiones(){
        Random r = new Random();
        for (Jugador j : jugadores) {
            j.setMision(misiones.remove(r.nextInt(misiones.size())));
        }
    }

    private void cargarMisiones() {
        Mision m1 = new Mision("Conquistar Europa");
        Mision m2 = new Mision("Conquistar 15 territorios");
        Mision m3 = new Mision("Conquistar America del sur");
        Mision m4 = new Mision("Conquistar Asia");
        Mision m5 = new Mision("Conquistar America del norte");
        misiones.add(m5);
        misiones.add(m4);
        misiones.add(m3);
        misiones.add(m2);
        misiones.add(m1);        
    }
}
