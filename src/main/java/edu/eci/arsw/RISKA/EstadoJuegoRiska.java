package edu.eci.arsw.RISKA;

import edu.eci.arsw.RISKA.exceptions.RiskaException;
import edu.eci.arsw.RISKA.modelo.Lobby;

/**
 *
 * @author Tatiana Higuera, Andres Vega, Nicolas Moreno
 */
public interface EstadoJuegoRiska {
    
    public int crearLobby();
    
    public Lobby getLobby(int idLobby)throws RiskaException;
    
    public int crearPartida();
}
