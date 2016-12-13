package edu.eci.arsw.RISKA;

import edu.eci.arsw.RISKA.modelo.Lobby;
import edu.eci.arsw.RISKA.exceptions.RiskaException;
import edu.eci.arsw.RISKA.modelo.Partida;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tatiana Higuera, Andres Vega, Nicolas Moreno
 */

public class EstadoJuegoRiskaMemoria implements EstadoJuegoRiska{
    
    private final ConcurrentHashMap<Integer, Lobby> lobbys = new ConcurrentHashMap<>();
    private AtomicInteger contLob;
    private final ConcurrentHashMap<Integer, Partida> partidas = new ConcurrentHashMap<>();
    private AtomicInteger contPar;
    
    
    public EstadoJuegoRiskaMemoria() {
        this.contLob = new AtomicInteger(0);
        this.contPar =  new AtomicInteger(0);
        lobbys.put(contLob.get(), new Lobby());
        partidas.put(contPar.get(), new Partida());
    }
   
    @Override
    public int crearLobby() {
        contLob.addAndGet(1);
        lobbys.put(contLob.get(), new Lobby());
        return contLob.get();
    }

    @Override
    public Lobby getLobby(int idLobby) throws RiskaException{
        if(lobbys.get(idLobby)!=null)return lobbys.get(idLobby);
        throw new RiskaException("Lobby no encontrado");
    }

    @Override
    public int crearPartida() {
        partidas.put(contPar.get(), new Partida());
        contPar.addAndGet(1);
        return contPar.get()-1;
    }
    
    @Override
    public ArrayList<Lobby> getLobbys(){
        ArrayList<Lobby> L = new ArrayList<>();
        for (Map.Entry<Integer, Lobby> entry : lobbys.entrySet()) {
            L.add(entry.getValue());
            
        }
        return L;
    }

    @Override
    public Partida getPartida(int idPart) throws RiskaException{
        Partida p = partidas.get(idPart);
        if (p!=null)return p;
        throw new RiskaException("Partida no encontrada con id solicitado "+idPart+".");
    }

    @Override
    public int getLastLobby() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizarLobby(Lobby Lobby, int idLobby) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizarPartida(Partida partida, int idPartida) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
