package edu.eci.arsw.RISKA;


import ch.qos.logback.core.CoreConstants;
import edu.eci.arsw.RISKA.exceptions.RiskaException;
import edu.eci.arsw.RISKA.modelo.Jugador;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
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
            msgt.convertAndSend("/topic/lobby."+idLobby,risk.getJugadoresLobbyById(idLobby));
            if(risk.getCantidadJugLobby(idLobby)==4){
                int idPart = risk.empezarPar(idLobby);

            }
            return new ResponseEntity<>(idLobby,HttpStatus.ACCEPTED);
        }catch(RiskaException e){
            System.out.println("Mal");
            return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(method = RequestMethod.GET,path = "/getLobbyPlayers.{idLobby}")
    public  ResponseEntity<?> getJugadoresLobby(@PathVariable int idLobby)throws Exception{
        return new ResponseEntity<>(risk.getJugadoresLobbyById(idLobby),HttpStatus.ACCEPTED);
    }
}
