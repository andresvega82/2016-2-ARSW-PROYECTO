package edu.eci.arsw.RISKA.modelo;


import edu.eci.arsw.RISKA.exceptions.RiskaException;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author Tatiana Higuera, Andres Vega, Nicolas Moreno.
 */
public class Lobby {
    public ConcurrentLinkedDeque<Jugador> participantes;

    public Lobby() {
        participantes = new ConcurrentLinkedDeque();
    }
    
    public void inserPar(Jugador j){
        participantes.add(j);
    }
    
    public void elimPar(Jugador j)throws RiskaException{
        if(participantes.contains(j))participantes.remove(j);
        else throw new RiskaException("Jugador no encontrado en el Lobby");
    }
    
    public int cantidadPar(){
        return participantes.size();
    }
}
