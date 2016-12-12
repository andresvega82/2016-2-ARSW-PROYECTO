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
        insertar();
        
    }
    
    
    @Override
    public int crearLobby() {
        Jedis jedis = JedisUtil.getPool().getResource();
        int ultimo = Integer.parseInt(jedis.get("ultimoLobby"));
        ultimo ++;
        jedis.set("ultimoLobby", ultimo+"");
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
        System.out.println("LOBBYYYYY"+ idLobby);
        Lobby lobby =  new Lobby();
        Jedis jedis = JedisUtil.getPool().getResource();        
        Map<String,String> dic = jedis.hgetAll("lobby"+idLobby);
        jedis.close();

        boolean band = false;
        for (int i = 1; i <= 4 && !band; i++) {
            System.out.println("diccccc"+dic.get("jugador"+i));
            String jug = dic.get("jugador"+i);
            if(!jug.equals("null")){
                Jugador j = new Jugador(jug);
                System.out.println("entro.........................................");
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
        jedis.set("ultimaPartida", ultimo+"");
        Partida p = new Partida();
        Gson gson = new Gson();
        String partida = gson.toJson(p);
        jedis.set("partida."+ultimo, partida);
        jedis.close();
        return ultimo;
        
    }
    
    
    @Override
    public Partida getPartida(int idPart) throws RiskaException {
        Jedis jedis = JedisUtil.getPool().getResource();
        Gson gson = new Gson();
        Partida partida = gson.fromJson(jedis.get("partida."+idPart), Partida.class);
        jedis.close();
        return partida;
    }
    
    
    @Override
    public ArrayList<Lobby> getLobbys() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   
    
    @Override
    public int getLastLobby(){
        
        Jedis jedis = JedisUtil.getPool().getResource();
        int resp = Integer.parseInt(jedis.get("ultimoLobby"));
        System.out.println("resp ---------------"+ resp);
        jedis.close();
        return resp;
    }
    
    
    
    
    private void insertar(){
        Jedis jedis = JedisUtil.getPool().getResource();
        jedis.set("ultimoLobby", 1000+"");
        Map<String,String> dic = new HashMap<>();
        dic.put("jugador1", "null");
        dic.put("jugador2", "null");
        dic.put("jugador3", "null");
        dic.put("jugador4", "null");
        jedis.hmset("lobby"+1000, dic);
        jedis.set("ultimaPartida",1000+"");
        jedis.close();
    }

    @Override
    public void actualizarLobby(Lobby lobby, int idLobby) {
        Map<String,String> dic = new HashMap<>();
        int i = 1;
        for (Jugador j : lobby.participantes) {
            dic.put("jugador"+i,j.nombre);
            i++;
        }
        for (int j = i; j < 5; j++) {
            dic.put("jugador"+j, "null");
        }
        Jedis jedis = JedisUtil.getPool().getResource();
        jedis.hmset("lobby"+idLobby, dic);
        
    }
}
