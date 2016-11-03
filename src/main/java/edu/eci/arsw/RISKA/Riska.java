package edu.eci.arsw.RISKA;


import edu.eci.arsw.RISKA.exceptions.RiskaException;
import edu.eci.arsw.RISKA.modelo.Jugador;
import edu.eci.arsw.RISKA.modelo.Lobby;
import edu.eci.arsw.RISKA.modelo.Partida;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tatiana Higuera, Andres Vega, Nicolas Moreno. 
 * 
 */
@Service
public class Riska {
    //@Autowired
    EstadoJuegoRiska ejr = new EstadoJuegoRiskaMemoria();
    
    int lastId = 0;

    public Riska() {

    }
    
    public int getCantidadJugLobby(int idLobby)throws RiskaException{
        return ejr.getLobby(idLobby).cantidadJu();
    }
    
    /**
     * Retorna los jugadores de un lobby
     * @param idLobby
     * @return Lista de jugadores de un lobby.
     * @throws RiskaException 
     */
    public ArrayList<String> getJugadoresLobbyById(int idLobby)throws RiskaException{
        Lobby l = ejr.getLobby(idLobby);
        ArrayList<String> salida = new ArrayList();
        for (Jugador jugador : l.getPar()) {
            salida.add(jugador.nombre);
        }
        return salida;
    }
    
    /**
     * Un jugador entra a un Lobby.
     * @param j Jugador que quiere entrar a un lobby.
     * @return numero de id del lobbby que el jugador j entro.
     * @throws edu.eci.arsw.RISKA.exceptions.RiskaException
     */
    public int entrarLobby(Jugador j)throws RiskaException{
        Lobby l = ejr.getLobby(lastId);
        if(l.cantidadJu()<4 && l.activo()){
            l.inserJu(j);
        }else{
            lastId = ejr.crearLobby();
        }
        return lastId;
    }
    
    /**
     * Empieza una partida cuando se completen los espacios requeridos.
     * @param idLobby
     * @return id Partida
     * @throws RiskaException 
     */
    public int empezarPar(int idLobby) throws RiskaException{
        Lobby l = ejr.getLobby(idLobby);
        int idPart = ejr.crearPartida();
        Partida p = ejr.getPartida(idPart);
        p.setJugadores(l.getPar());
        p.setMisiones();
        return idPart;
    }
    
    /**
     * Devuelve los lobbys que hayan.
     * @return 
     */
    public ArrayList<Lobby> getLobbys(){
        return ejr.getLobbys();
    }
    
    /**
     * 
     * @return 
     */
    public EstadoJuegoRiska getEjr() {
        return ejr;
    }
    
    /**
     * 
     * @param ejr 
     */
    public void setEjr(EstadoJuegoRiska ejr) {
        this.ejr = ejr;
    }
}
