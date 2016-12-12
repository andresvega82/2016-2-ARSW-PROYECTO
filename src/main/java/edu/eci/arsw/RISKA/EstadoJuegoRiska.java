package edu.eci.arsw.RISKA;

import edu.eci.arsw.RISKA.exceptions.RiskaException;
import edu.eci.arsw.RISKA.modelo.Lobby;
import edu.eci.arsw.RISKA.modelo.Partida;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Tatiana Higuera, Andres Vega, Nicolas Moreno
 */
public interface EstadoJuegoRiska {
    
    public int crearLobby();
    
    public Lobby getLobby(int idLobby)throws RiskaException;
    
    public int crearPartida();
    
    public ArrayList<Lobby> getLobbys();
    
    public Partida getPartida(int idPart)throws RiskaException;
    
    public int getLastLobby();
    
    public void actualizarLobby(Lobby Lobby, int idLobby);
}
