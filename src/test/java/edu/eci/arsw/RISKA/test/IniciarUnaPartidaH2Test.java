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
 * C5: Solo el jugador adecuado al turno correspondiente podra realizar jugadas
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
        boolean posible = false;
        Riska rk = new Riska();
        Jugador j = new Jugador("Jugardor Prueba 1");
        Jugador j1 = new Jugador("Jugardor Prueba 2");
        Jugador j2 = new Jugador("Jugardor Prueba 3");
        Jugador j3 = new Jugador("Jugardor Prueba 4");
        Jugador j4 = new Jugador("Jugardor Prueba 5");
        try{
            int lastId = rk.entrarLobby(j);
            rk.entrarLobby(j1);
            rk.entrarLobby(j2);
            rk.entrarLobby(j3);
            rk.entrarLobby(j4);
            
            
            int partidaId = rk.empezarPar(lastId);
            if(!rk.getMisiones(partidaId).isEmpty() && !rk.getLobbyActivo(lastId) && rk.hasMoreTurn(lastId)){
                posible = true;
            }
            
        }catch(RiskaException ex){
            Logger.getLogger(Riska.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertTrue("La informacion para crear partida esta incompleta o no es correcto",posible);
    }
    
    
    @Test
    public void todosLosUsuariosDentroDeLaPartidaDebenAsignarseleMisionesTest() {
        boolean posible = false;
        Riska rk = new Riska();
        Jugador j = new Jugador("Jugardor Prueba 1");
        Jugador j1 = new Jugador("Jugardor Prueba 2");
        Jugador j2 = new Jugador("Jugardor Prueba 3");
        Jugador j3 = new Jugador("Jugardor Prueba 4");
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
            Logger.getLogger(Riska.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertTrue("Las misiones de cada jugador no han sido asignadas o no es correcto",posible);
    }
    
    
    @Test
    public void unJugadorPodraJugarSiLeCorrespondeElTurnoTest() {
        boolean posible = false;
        Riska rk = new Riska();
        Jugador j = new Jugador("Jugardor Prueba 1");
        Jugador j1 = new Jugador("Jugardor Prueba 2");
        Jugador j2 = new Jugador("Jugardor Prueba 3");
        Jugador j3 = new Jugador("Jugardor Prueba 4");
        try{
            int lastId = rk.entrarLobby(j);
            rk.entrarLobby(j1);
            rk.entrarLobby(j2);
            rk.entrarLobby(j3);
            
            int partidaId = rk.empezarPar(lastId);
            rk.posicionarTropa(lastId, "Jugardor Prueba 1", "ALASKA");
            if(rk.nombreTurnoJugador(lastId).equals("Jugardor Prueba 2")){
                posible = true;            
            }
            
            }catch(RiskaException ex){
            Logger.getLogger(Riska.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertTrue("El turno del jugador no es correcto",posible);
    }
    
    @Test
    public void unJugadorNoPodraJugarSiNoEsSuTurnoTest() {
        boolean posible = false;
        Riska rk = new Riska();
        Jugador j = new Jugador("Jugardor Prueba 1");
        Jugador j1 = new Jugador("Jugardor Prueba 2");
        Jugador j2 = new Jugador("Jugardor Prueba 3");
        Jugador j3 = new Jugador("Jugardor Prueba 4");
        try{
            int lastId = rk.entrarLobby(j);
            rk.entrarLobby(j1);
            rk.entrarLobby(j2);
            rk.entrarLobby(j3);
            
            int partidaId = rk.empezarPar(lastId);
            rk.posicionarTropa(lastId, "Jugardor Prueba 3", "ALASKA");
            if(rk.nombreTurnoJugador(lastId).equals("Jugardor Prueba 2")){
                posible = true;            
            }
            
            }catch(RiskaException ex){
            Logger.getLogger(Riska.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Assert.assertFalse("El turno del jugador no es correcto",posible);
    }
    
    
    @Test
    public void cadaJugadorPodraUbicarSusTropasTest() {
        boolean posible = false;
        Riska rk = new Riska();
        Jugador j = new Jugador("Jugardor Prueba 1");
        Jugador j1 = new Jugador("Jugardor Prueba 2");
        Jugador j2 = new Jugador("Jugardor Prueba 3");
        Jugador j3 = new Jugador("Jugardor Prueba 4");
        try{
            int lastId = rk.entrarLobby(j);
            rk.entrarLobby(j1);
            rk.entrarLobby(j2);
            rk.entrarLobby(j3);
            
            int partidaId = rk.empezarPar(lastId);
            rk.posicionarTropa(lastId, "Jugardor Prueba 1", "ALASKA");
            rk.posicionarTropa(lastId, "Jugardor Prueba 2", "ALBERTA");
            rk.posicionarTropa(lastId, "Jugardor Prueba 3", "NORTH WEST TERRITORY");
            rk.posicionarTropa(lastId, "Jugardor Prueba 4", "ONTARIO");
            
            rk.posicionarTropa(lastId, "Jugardor Prueba 1", "QUEBEC");
            rk.posicionarTropa(lastId, "Jugardor Prueba 2", "WESTERN UNITED STATES");
            rk.posicionarTropa(lastId, "Jugardor Prueba 3", "EASTERN UNITED STATES");
            rk.posicionarTropa(lastId, "Jugardor Prueba 4", "CENTRAL AMERICA");
            
            rk.posicionarTropa(lastId, "Jugardor Prueba 1", "VENEZUELA");
            rk.posicionarTropa(lastId, "Jugardor Prueba 2", "PERU");
            rk.posicionarTropa(lastId, "Jugardor Prueba 3", "BRAZIL");
            rk.posicionarTropa(lastId, "Jugardor Prueba 4", "ARGENTINA");
            
            rk.posicionarTropa(lastId, "Jugardor Prueba 1", "GREENLAND");
            rk.posicionarTropa(lastId, "Jugardor Prueba 2", "GREAT BRITAIN");
            rk.posicionarTropa(lastId, "Jugardor Prueba 3", "ICELAND");
            rk.posicionarTropa(lastId, "Jugardor Prueba 4", "WESTERN EUROPE");
            
            rk.posicionarTropa(lastId, "Jugardor Prueba 1", "NORTH AFRICA");
            rk.posicionarTropa(lastId, "Jugardor Prueba 2", "SCANDLNAVLA");
            rk.posicionarTropa(lastId, "Jugardor Prueba 3", "NORTHERN EUROPE");
            rk.posicionarTropa(lastId, "Jugardor Prueba 4", "SOUTHERN EUROPE");
             
            
            rk.posicionarTropa(lastId, "Jugardor Prueba 1", "EGYPT");
            rk.posicionarTropa(lastId, "Jugardor Prueba 2", "EAST AFRICA");
            rk.posicionarTropa(lastId, "Jugardor Prueba 3", "CONGO");
            rk.posicionarTropa(lastId, "Jugardor Prueba 4", "SOUTH AFRICA");
            
            rk.posicionarTropa(lastId, "Jugardor Prueba 1", "UKRAINE");
            rk.posicionarTropa(lastId, "Jugardor Prueba 2", "MIDDLE EAST");
            rk.posicionarTropa(lastId, "Jugardor Prueba 3", "MADAGASCAR");
            rk.posicionarTropa(lastId, "Jugardor Prueba 4", "URAL");
            
            rk.posicionarTropa(lastId, "Jugardor Prueba 1", "AFGHANISTAN");
            rk.posicionarTropa(lastId, "Jugardor Prueba 2", "INDIA");
            rk.posicionarTropa(lastId, "Jugardor Prueba 3", "INDONESIA");
            rk.posicionarTropa(lastId, "Jugardor Prueba 4", "SIBERIA");
            
            rk.posicionarTropa(lastId, "Jugardor Prueba 1", "CHINA");
            rk.posicionarTropa(lastId, "Jugardor Prueba 2", "SLAM");
            rk.posicionarTropa(lastId, "Jugardor Prueba 3", "WESTERN AUSTRALIA");
            rk.posicionarTropa(lastId, "Jugardor Prueba 4", "YAKUTSK");
            
            rk.posicionarTropa(lastId, "Jugardor Prueba 1", "IRKUTSK");
            rk.posicionarTropa(lastId, "Jugardor Prueba 2", "MONGOLIA");
            rk.posicionarTropa(lastId, "Jugardor Prueba 3", "NEW GUINEA");
            rk.posicionarTropa(lastId, "Jugardor Prueba 4", "JAPAN");
            
            if(!rk.hasMoreTurn(lastId)){
                posible = true;
            }
            
        }catch(RiskaException ex){
            Logger.getLogger(Riska.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Assert.assertTrue("No se han ubicado todas las tropas de cada jugador",posible);
    }
    
    
    @Test
    public void despuesDeQueCadaJugarUbicoSusTropasSeIniciaraUnJuegoTest() {
    }
    
    
    @Test
    public void noSeDebeIniciarUnJuegoSiNoSeHanUbicadoLasTropasDeCadaJugadorTest() {
    }
    
}
