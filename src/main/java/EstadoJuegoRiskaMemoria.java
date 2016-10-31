
import edu.eci.arsw.RISKA.modelo.Jugador;
import edu.eci.arsw.RISKA.modelo.Lobby;
import edu.eci.arsw.RISKA.exceptions.RiskaException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Nicolas M
 */
public class EstadoJuegoRiskaMemoria implements EstadoJuegoRiska{
    
    private final ConcurrentHashMap<Integer, Lobby> lobbys;
    private AtomicInteger cont;

    public EstadoJuegoRiskaMemoria() {
        this.lobbys = new ConcurrentHashMap<>();
        this.cont = new AtomicInteger(0);
    }
   
    @Override
    public void entrarLobby(Jugador j) {
        if(lobbys.get(cont.get()).cantidadPar()<4){
            lobbys.get(cont.get()).inserPar(j);
        }else{
            cont.addAndGet(1);
            lobbys.put(cont.get(), new Lobby());
        }
    }

    @Override
    public void salirLobby(Jugador j) throws RiskaException{
        boolean encontro = false;
        for (Map.Entry<Integer, Lobby> entry : lobbys.entrySet()) {
            try {
                entry.getValue().elimPar(j);
                encontro = true;
            } catch (RiskaException e) {
            }
        }
        if(!encontro){
            throw new RiskaException("No se encuentra al jugador "+j.nombre+" que quiere salir del Lobby");
        }
    }
}
