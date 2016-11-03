/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.RISKA;

import edu.eci.arsw.RISKA.modelo.Jugador;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Tatiana Higuera, Nicolas Moreno, Andres Vega
 */
@Controller
public class STOMPMessagesRiska {
    @Autowired
    SimpMessagingTemplate msgt;
    
    @Autowired
    Riska risk;
    
    @MessageMapping("/ingresarLobby")  
    public void ingresarPartida(String j) throws Exception {
        System.out.println("LLEGO HASTA AQUí");
        int idLobby = risk.entrarLobby(new Jugador(j));
        msgt.convertAndSend("/topic/lobby."+idLobby,risk.getJugadoresLobbyById(idLobby));
        if(risk.getCantidadJugLobby(idLobby)==4){
            int idPart = risk.empezarPar(idLobby);
            
        }
        System.out.println(idLobby+" "+j);
        msgt.convertAndSend("/topic/idlobby."+j,idLobby);
    }
}
