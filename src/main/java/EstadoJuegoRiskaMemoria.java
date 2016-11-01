import edu.eci.arsw.RISKA.modelo.Lobby;
import edu.eci.arsw.RISKA.exceptions.RiskaException;
import edu.eci.arsw.RISKA.modelo.Partida;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tatiana Higuera, Andres Vega, Nicolas Moreno
 */
@Service
public class EstadoJuegoRiskaMemoria implements EstadoJuegoRiska{
    
    private final ConcurrentHashMap<Integer, Lobby> lobbys;
    private AtomicInteger contLob;
    private final ConcurrentHashMap<Integer, Partida> partidas;
    private AtomicInteger contPar;

    public EstadoJuegoRiskaMemoria() {
        this.lobbys = new ConcurrentHashMap<>();
        this.contLob = new AtomicInteger(-1);
        this.contPar =  new AtomicInteger(-1);
        this.partidas = new ConcurrentHashMap<>();
    }
   
    @Override
    public int crearLobby() {
        contLob.addAndGet(1);
        lobbys.put(contLob.get(), new Lobby());
        return contLob.get();
    }

    @Override
    public Lobby getLobby(int idLobby) throws RiskaException{
        if(!lobbys.contains(idLobby))throw new RiskaException("Lobby no encontrado");
        return lobbys.get(idLobby);
    }

    @Override
    public int crearPartida() {
        contPar.addAndGet(1);
        partidas.put(contPar.get(), new Partida());
        return contPar.get();
    }
}
