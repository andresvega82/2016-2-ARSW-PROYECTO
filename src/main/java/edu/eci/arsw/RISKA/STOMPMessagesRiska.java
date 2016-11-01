/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.RISKA;

import edu.eci.arsw.RISKA.modelo.Jugador;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @MessageMapping("/newPartida.{gameid}")  
    public void getnuevaPartida() throws Exception {
        Jugador j = new Jugador("TEMPORAL!!!!");
        risk.entrarLobby(j);
        
        
        msgt.convertAndSend("/topic/newPartida");
    }
    
}
