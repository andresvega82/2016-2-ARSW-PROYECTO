package edu.eci.arsw.RISKA;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Tatiana Higuera, Andres Vega, Nicolas Moreno.
 */


@RestController 
@RequestMapping(value = "/juego")
public class ControladorRiska {
    
    
    
    @Autowired
    SimpMessagingTemplate msgt;
    @RequestMapping(method = RequestMethod.POST)
    public void crearJuego(){
        
    }
}
