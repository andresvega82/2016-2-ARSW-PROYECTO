package edu.eci.arsw.RISKA;

import com.google.gson.Gson;
import edu.eci.arsw.RISKA.exceptions.RiskaException;
import edu.eci.arsw.RISKA.modelo.Jugador;
import edu.eci.arsw.RISKA.modelo.Lobby;
import edu.eci.arsw.RISKA.modelo.Partida;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;
/**
 *
 * @author Tatiana Higuera, Andres Vega, Nicolas Moreno
 */
@Service
public class EstadoJuegoRiskaREDIS implements EstadoJuegoRiska{
    
    private AtomicInteger contLob;
    private AtomicInteger contPar;

    public EstadoJuegoRiskaREDIS() {
        this.contLob =  new AtomicInteger(0);
        this.contPar =  new AtomicInteger(0);
    }
    
    
    @Override
    public int crearLobby() {
        Jedis jedis = JedisUtil.getPool().getResource();
        int ultimo = Integer.parseInt(jedis.get("ultimoLobby"));
        ultimo ++;
        jedis.append("ultimoLobby", ultimo+"");
        Map<String,String> dic = new HashMap<>();
        dic.put("jugador1", "null");
        dic.put("jugador2", "null");
        dic.put("jugador3", "null");
        dic.put("jugador4", "null");
        jedis.hmset("lobby"+ultimo, dic);
        jedis.close();
        return ultimo;
    }

    @Override
    public Lobby getLobby(int idLobby) throws RiskaException {
        Lobby lobby =  new Lobby();
        Jedis jedis = JedisUtil.getPool().getResource();
        jedis.watch("lobby"+idLobby);
        Transaction  t = jedis.multi();
        Response<Map<String,String>> ent = t.hgetAll("lobby"+idLobby);
        Map<String,String> dic = ent.get();
        jedis.close();
        boolean band = false;
        for (int i = 1; i <= 4 && !band; i++) {
            String jug = dic.get("jugador"+i);
            if(!jug.equals("null")){
                Jugador j = new Jugador(jug);
                lobby.inserJu(j);
            }else{
                band = true;
            }
        }
        return lobby;
    }

    @Override
    public int crearPartida() {
        Jedis jedis = JedisUtil.getPool().getResource();
        int ultimo = Integer.parseInt(jedis.get("ultimaPartida"));
        ultimo ++;
        jedis.append("ultimaPartida", ultimo+"");
        Partida p = new Partida();
        Gson gson = new Gson();
        String partida = gson.toJson(p);
        jedis.set("partida."+ultimo, partida);
        return ultimo;
        
    }
    
    
    @Override
    public Partida getPartida(int idPart) throws RiskaException {
        Jedis jedis = JedisUtil.getPool().getResource();
        Gson gson = new Gson();
        Partida partida = gson.fromJson(jedis.get("partida."+idPart), Partida.class);
        
        return partida;
    }
    
    
    @Override
    public ArrayList<Lobby> getLobbys() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
