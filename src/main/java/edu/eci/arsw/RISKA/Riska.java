package edu.eci.arsw.RISKA;


import edu.eci.arsw.RISKA.exceptions.RiskaException;
import edu.eci.arsw.RISKA.modelo.Jugador;
import edu.eci.arsw.RISKA.modelo.Lobby;
import edu.eci.arsw.RISKA.modelo.Mision;
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
   @Autowired
    EstadoJuegoRiska ejr;
    
    int lastId = 0;

    public Riska() {

    }
    
    /**
     * Retorna el color de un jugador en un partida.
     * @param idPart
     * @param nombreJugadr
     * @return 
     */
    public String colorJugadorPartida(int idPart, String nombreJugadr)throws RiskaException{
        return ejr.getPartida(idPart).colorJugador(nombreJugadr);
    }
    
    /**
     * Retorna los datos de un territorio en un String. 
     * @param idPart
     * @param pais
     * @return String concatenado por comas separando primero el color y luego el numero de tropas.
     * @throws RiskaException 
     */
    public String getDatosTerritorio(int idPart, String pais)throws RiskaException{
        Partida p = ejr.getPartida(idPart);
        return p.getColorTerritorio(pais)+","+p.getNumeroTropasTerritorio(pais);
    }
    
    /**
     * retornar el enunciado de una mision de un jugador. 
     * @param idPart
     * @return
     * @throws RiskaException 
     */
    public String getEnunciadoMisionesPorJugador(int idPart, String Jugador)throws RiskaException{
        String enunciado ="";
        for(Jugador j: ejr.getPartida(idPart).getJugadores()){
            if(j.nombre.equalsIgnoreCase(Jugador)){
                enunciado = j.mision.getObjetivo();
            }
        }
        return enunciado;
    }
    
    /**
     * Cuanto jugadores hay en un lobby.
     * @param idLobby
     * @return
     * @throws RiskaException 
     */
    public int getCantidadJugLobby(int idLobby)throws RiskaException{
        return ejr.getLobby(idLobby).cantidadJu();
    }
    
    /**
     * Si un lobby aun no cumple la condicion de esperar 2 minutos de creado para empezar.
     * @param idLobby
     * @return
     * @throws RiskaException 
     */
    public Boolean getLobbyActivo(int idLobby)throws RiskaException{
        return ejr.getLobby(idLobby).activo();
    }
    
    /**
     * Posicionar una tropa en el mapa
     * @param idPart
     * @param nombreJugador
     * @param pais
     * @throws RiskaException 
     */
    public void posicionarTropa(int idPart, String nombreJugador, String pais) throws RiskaException{
        Partida p = ejr.getPartida(idPart);
        if(p.puedoUbicar(nombreJugador, pais)){
            p.ubicarTropa(pais,nombreJugador);
        }
        
    }
    
    /**
     * Si hay mas turnos.
     * @param idPart
     * @return
     * @throws RiskaException 
     */
    public boolean hasMoreTurn(int idPart)throws RiskaException{
        return ejr.getPartida(idPart).hasNextTurn();
    }
    
    /**
     * Nombre del jugador a quien le toca.
     * @param idPart
     * @throws edu.eci.arsw.RISKA.exceptions.RiskaException
     */
    public String nombreTurnoJugador(int idPart)throws RiskaException{
        return ejr.getPartida(idPart).getTurno().nombre;
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
        lastId = ejr.getLastLobby();
        Lobby l = ejr.getLobby(lastId);
        if(l.cantidadJu()<4 && l.activo()){
            l.inserJu(j);
            ejr.actualizarLobby(l, lastId);
        }else{
            l.desactivo();
            lastId = ejr.crearLobby();
            Lobby lobbyNuevo = ejr.getLobby(lastId);
            lobbyNuevo.inserJu(j);
            ejr.actualizarLobby(lobbyNuevo, lastId);
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
        p.asignarColores();
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
     * Devuelve las masiones que exitan en la partida.
     * @param idPartida
     * @return 
     */
    public ArrayList<Mision> getMisiones(int idPartida) throws RiskaException{
        return ejr.getPartida(idPartida).getMisiones();
    }
    
    
    public boolean hayTurnosRestantes(int idPart) throws RiskaException{
        return ejr.getPartida(idPart).hayTurnosRestantes();
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
