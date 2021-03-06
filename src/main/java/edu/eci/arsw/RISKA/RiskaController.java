package edu.eci.arsw.RISKA;


import edu.eci.arsw.RISKA.exceptions.RiskaException;
import edu.eci.arsw.RISKA.modelo.Jugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Tatiana Higuera, Andres Vega, Nicolas Moreno.
 */


@RestController 
@RequestMapping(value = "/riska")
public class RiskaController {
    
    @Autowired
    Riska risk;
    @Autowired
    SimpMessagingTemplate msgt;
       
    @RequestMapping(method = RequestMethod.GET,path = "/getLobby.{j}")
    public  ResponseEntity<?> ingresarLobby(@PathVariable String j)throws Exception{
        try{
            int idLobby = risk.entrarLobby(new Jugador(j));
            msgt.convertAndSend("/topic/lobby."+idLobby,idLobby);
            if(risk.getCantidadJugLobby(idLobby)==4 && risk.getLobbyActivo(idLobby)){
                int idPart = risk.empezarPar(idLobby);
                msgt.convertAndSend("/topic/lobbyPartida."+idLobby,idPart);
            }
            return new ResponseEntity<>(idLobby,HttpStatus.ACCEPTED);
        }catch(RiskaException e){
            return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(method = RequestMethod.GET,path = "/getLobbyPlayers.{idLobby}")
    public  ResponseEntity<?> getJugadoresLobby(@PathVariable int idLobby)throws Exception{
        return new ResponseEntity<>(risk.getJugadoresLobbyById(idLobby),HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(method = RequestMethod.GET,path = "/ultimo.{idLobby}")
    public  ResponseEntity<?> soyElCuarto(@PathVariable int idLobby)throws Exception{
        if(risk.getCantidadJugLobby(idLobby)==4 && risk.getLobbyActivo(idLobby)){
                return new ResponseEntity<>(1,HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(0,HttpStatus.ACCEPTED);
        }
        
    }
    @RequestMapping(method = RequestMethod.PUT,path = "/agregarTropa.{idLobby}/{nombre}")
    public  ResponseEntity<?> AgregarTropa(@PathVariable("idLobby") int idLobby,@PathVariable("nombre") String nombre,@RequestBody String pais)throws Exception{
        risk.posicionarTropa(idLobby,nombre,pais);
        msgt.convertAndSend("/topic/partidaTropas."+idLobby,pais);
        return new ResponseEntity<>(0,HttpStatus.ACCEPTED);

    }
    
    @RequestMapping(method = RequestMethod.PUT,path = "/tropas.{idLobby}/{pais}")
    public  ResponseEntity<?> PintarTropa(@RequestBody String nombre,@PathVariable("pais")  String pais,@PathVariable("idLobby") int idLobby)throws Exception{
        
        nombre = nombre.substring(1, nombre.length()-1);
        risk.posicionarTropa(idLobby, nombre, pais);
        
        String[] datos = risk.getDatosTerritorio(idLobby, pais).split(",");
        pais = pais.replaceAll(" ", "");
        msgt.convertAndSend("/topic/partidaTropas."+idLobby,idLobby+","+pais+","+datos[0]+","+datos[1]);   
        
        if(risk.hayTurnosRestantes(idLobby)){
            msgt.convertAndSend("/topic/inicioPartida."+idLobby, idLobby);
        }
        return new ResponseEntity<>(0,HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(method = RequestMethod.GET,path = "/color.{idLobby}/{nombre}")
    public  ResponseEntity<?> consultarColor(@PathVariable("nombre")  String nombre,@PathVariable("idLobby") int idLobby)throws Exception{
        String colorJugadorPartida = risk.colorJugadorPartida(idLobby, nombre);
        return new ResponseEntity<>(colorJugadorPartida,HttpStatus.ACCEPTED);    
    }
    
    @RequestMapping(method = RequestMethod.GET,path = "/turno.{idLobby}")
    public  ResponseEntity<?> consultarTurno(@PathVariable("idLobby") int idLobby)throws Exception{
        String turno = risk.nombreTurnoJugador(idLobby);
        return new ResponseEntity<>(turno,HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(method = RequestMethod.GET,path = "/mision.{idLobby}/{nombre}")
    public  ResponseEntity<?> consultarMision(@PathVariable("nombre")  String nombre,@PathVariable("idLobby") int idLobby)throws Exception{
        return new ResponseEntity<>(risk.getEnunciadoMisionesPorJugador(idLobby, nombre),HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(method = RequestMethod.PUT,path = "/tropas.{idLobby}/{pais}/{second}")
    public  ResponseEntity<?> jugar(@PathVariable("second") String second,@RequestBody String nombre,@PathVariable("pais")  String pais,@PathVariable("idLobby") int idPartida)throws Exception{
        boolean tmp = risk.moverTropa(pais, second, nombre, idPartida);
        if(tmp){
            String[] datos = risk.getDatosTerritorio(idPartida, pais).split(",");
            pais = pais.replaceAll(" ", "");
            msgt.convertAndSend("/topic/partidaTropas."+idPartida,idPartida+","+pais+","+datos[0]+","+datos[1]);
            String[] datosSecond = risk.getDatosTerritorio(idPartida, second).split(",");
            second = second.replaceAll(" ", "");
            System.out.println("sapoColor"+datos[0]);
            System.out.println("datosSecond"+datosSecond[1]);
            msgt.convertAndSend("/topic/partidaTropas."+idPartida,idPartida+","+second+","+datos[0]+","+datosSecond[1]);
        }
        return new ResponseEntity<>(0,HttpStatus.ACCEPTED);
    }
    
}
