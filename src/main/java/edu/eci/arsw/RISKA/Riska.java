package edu.eci.arsw.RISKA;


import edu.eci.arsw.RISKA.exceptions.RiskaException;
import edu.eci.arsw.RISKA.modelo.Jugador;
import edu.eci.arsw.RISKA.modelo.Lobby;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Tatiana Higuera, Andres Vega, Nicolas Moreno. 
 * 
 */

public class Riska {
    @Autowired
    EstadoJuegoRiska ejr = null;
    
    int lastId;

    public Riska() {
        lastId = ejr.crearLobby();
    }
    
    /**
     * Un jugador entra a un Lobby.
     * @param j Jugador que quiere entrar a un lobby.
     * @return numero de id del lobbby que el jugador j entro.
     * @exception RiskaException.
     */
    public int entrarLobby(Jugador j)throws RiskaException{
        Lobby l = ejr.getLobby(lastId);
        if(l.cantidadJu()<4){
            l.inserJu(j);
            empezarPar(lastId);
        }else{
            lastId = ejr.crearLobby();
        }
        return lastId;
    }

    public void empezarPar(int idLobby) throws RiskaException{
        Lobby l = ejr.getLobby(idLobby);
        
    }
    
    public ArrayList<Lobby> getLobbys(){
        return ejr.getLobbys();
    }
}
