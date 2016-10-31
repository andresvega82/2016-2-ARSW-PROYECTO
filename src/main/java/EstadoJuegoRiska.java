
import edu.eci.arsw.RISKA.modelo.Jugador;
import edu.eci.arsw.RISKA.exceptions.RiskaException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nicolas M
 */
public interface EstadoJuegoRiska {
    
    public void entrarLobby(Jugador j);
    
    public void salirLobby(Jugador j)throws RiskaException;
}
