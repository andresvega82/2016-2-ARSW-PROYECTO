/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.RISKA.test;

import edu.eci.arsw.RISKA.Riska;
import edu.eci.arsw.RISKA.exceptions.RiskaException;
import edu.eci.arsw.RISKA.modelo.Jugador;
import edu.eci.arsw.RISKA.modelo.Lobby;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Andres Vega, Nicolas Moreno, Tatiana Higuera
 * Pruebas de historia de Usuario 2:
 *          Iniciar una partida
 *          Como usuario, Quiero que al iniciar una partida se me asigne una 
 *          misión y quiero poder colocar mis tropas iniciales.
 * 
 * CLASES DE EQUIVALENCIA:
 * C1: Todas las partidas deben contener la informacion completa
 * C2: A todos los usuarios de la partida se les debe asignar sus misiones 
 *     correspondientes
 * C3: Todo jugador tendra un turno asignado para ubicar sus tropas
 * C4: Despues de que cada jugar halla ubicado todas las tropas se dara inicio 
 *     a el juego.
 * 
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class IniciarUnaPartidaH2Test {
        
    @Before
    public void setUp() {
    }
    
    
    @After
    public void cargarBaseDeDatos(){
    }
    
    
    @Test
    public void informacionCompletaParaCrearUnaNuevaPartidaTest() {
    }
    
    
    @Test
    public void todosLosUsuariosDentroDeLaPartidaDebenAsignarseleMisionesTest() {
        boolean posible = false;
        Riska rk = new Riska();
        Jugador j = new Jugador("Jugardor Prueba");
        Jugador j1 = new Jugador("Jugardor Prueba");
        Jugador j2 = new Jugador("Jugardor Prueba");
        Jugador j3 = new Jugador("Jugardor Prueba");
        try{
            int lastId = rk.entrarLobby(j);
            rk.entrarLobby(j1);
            rk.entrarLobby(j2);
            rk.entrarLobby(j3);
            
            int partidaId = rk.empezarPar(lastId);
            if(!rk.getMisiones(partidaId).isEmpty()){
                posible = true;
            }
            
            
        }catch(RiskaException ex){
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Test
    public void cadaJugadorPodraUbicarSusTropasTest() {
        
        
    }
    
    
    @Test
    public void despuesDeQueCadaJugarUbicoSusTropasSeIniciaraUnJuegoTest() {
    }
    
    
    @Test
    public void noSeDebeIniciarUnJuegoSiNoSeHanUbicadoLasTropasDeCadaJugadorTest() {
    }
    
}
