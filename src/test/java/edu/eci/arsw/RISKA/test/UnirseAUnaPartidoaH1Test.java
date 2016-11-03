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
 * Pruebas de historia de Usuario 1:
 *          Unirse a una partida
 *          Como usuario, debo poder unirme a una partida.
 * 
 * CLASES DE EQUIVALENCIA:
 * C1: Todas las solicitudes de un usuario para agregarse a un Lobby 
 *     deben ser aprobadas
 * C2: Un lobby solamente puede contener 4 usuarios como maximo, agregando a 
 *     los primeros usuarios que hayan realizado la solicitud
 * C3: En caso de no haber lobbys con espacio disponible para agregar un usuario
 *     se debe crear un nuevo lobby
 * C4: Los datos del unirse a una partida por parte del usuario deben estar 
 *     completos y con el formato indicado
 * 
 */

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class UnirseAUnaPartidoaH1Test {
    
    
    @Before
    public void cargarBaseDeDatos() {
    }
    
    
    @After
    public void limpiarBaseDeDatos(){
    }
    
    
    @Test
    public void agregarUsuarioAUnLobbyTest() {
        boolean posible = false;
        Riska rk = new Riska();
        Jugador j = new Jugador("Jugardor Prueba");
        try{
            int res = rk.entrarLobby(j);
            if(res== 0)posible = true;
        }catch(RiskaException ex){
            Logger.getLogger(Riska.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Assert.assertTrue("El usuario o el Lobby no son correctos",posible);
    }
    
    
    @Test
    public void unLobbyDebeTenerMaximo4UsuariosTest() {
        boolean posible = false;
        Riska rk = new Riska();
        Jugador j = new Jugador("Jugardor Prueba 0");
        Jugador j1 = new Jugador("Jugardor Prueba 1");
        Jugador j2 = new Jugador("Jugardor Prueba 2");
        Jugador j3 = new Jugador("Jugardor Prueba 3");
        Jugador j4 = new Jugador("Jugardor Prueba 4");
        try{
            rk.entrarLobby(j);
            rk.entrarLobby(j1);
            rk.entrarLobby(j2);
            rk.entrarLobby(j3);
            rk.entrarLobby(j4);
            ArrayList<Lobby> lobbys = rk.getLobbys();
            for (Lobby lobby : lobbys) {
                if(lobby.cantidadJu()<5){
                    posible = true;
                }else{
                    posible = false;
                }
                
            }
            
        }catch(RiskaException ex){
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Assert.assertTrue("La cantidad de jugadores en el Lobby no son correctos",posible);
    }
    
    
    @Test
    public void crearUnNuevoLobbySiNoHayUnoDisponibleTest() {
        boolean posible = false;
        Riska rk = new Riska();
        Jugador j = new Jugador("Jugardor Prueba 0");
        Jugador j1 = new Jugador("Jugardor Prueba 1");
        Jugador j2 = new Jugador("Jugardor Prueba 2");
        Jugador j3 = new Jugador("Jugardor Prueba 3");
        Jugador j4 = new Jugador("Jugardor Prueba 4");
        try{
            rk.entrarLobby(j);
            rk.entrarLobby(j1);
            rk.entrarLobby(j2);
            rk.entrarLobby(j3);
            rk.entrarLobby(j4);
            ArrayList<Lobby> lobbys = rk.getLobbys();
            if(lobbys.size() == 2){
                posible = true;
            }
            
        }catch(RiskaException ex){
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Assert.assertTrue("La cantidad de Lobbys no son correctos",posible);
    }
    
    @Test
    public void crearUnNuevoLobbySiElTiempoDefinidoParaUnLobbyActivoYaPasoTest() {
        boolean posible = false;
        Riska rk = new Riska();
        Jugador j = new Jugador("Jugardor Prueba 0");
        Jugador j1 = new Jugador("Jugardor Prueba 1");
        Jugador j2 = new Jugador("Jugardor Prueba 2");
        
        Jugador j3 = new Jugador("Jugardor Prueba 3");
        Jugador j4 = new Jugador("Jugardor Prueba 4");
        try{
            rk.entrarLobby(j);
            rk.entrarLobby(j1);
            rk.entrarLobby(j2);
            Thread.sleep(150000);
            rk.entrarLobby(j3);
            rk.entrarLobby(j4);
            ArrayList<Lobby> lobbys = rk.getLobbys();
            if(lobbys.size() == 2){
                posible = true;
            }
            
        }catch(RiskaException ex){
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }catch(InterruptedException ex){
            Logger.getLogger(UnirseAUnaPartidoaH1Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Assert.assertTrue("La cantidad de Lobbys no son correctos",posible);
    }
    
    
    @Test
    public void datosDelUsuarioCompletosTest() {
        boolean posible = false;
        Riska rk = new Riska();
        Jugador j = new Jugador("Jugardor Prueba");
        
        try{
            rk.entrarLobby(j);
            if(!rk.getLobbys().isEmpty())
                posible = true;
        }catch(RiskaException ex){
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }        
        Assert.assertTrue("Los datos del usuario para agregarse a un Lobby no son correctos",posible);
    
    }
}
