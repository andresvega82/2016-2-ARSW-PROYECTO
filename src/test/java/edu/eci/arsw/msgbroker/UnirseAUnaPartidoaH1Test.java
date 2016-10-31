/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker;

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
@RunWith(SpringRunner.class)
@SpringBootTest
public class UnirseAUnaPartidoaH1Test {
    
    
    @Before
    public void cargarBaseDeDatos() {
    }
    
    
    @After
    public void limpiarBaseDeDatos(){
    }
    
    
    @Test
    public void agregarUsuarioAUnLobbyTest() {
    }
    
    
    @Test
    public void unLobbyDebeTenerMaximo4UsuariosTest() {
    }
    
    
    @Test
    public void crearUnNuevoLobbySiNoHayUnoSDisponibleTest() {
    }
    
    
    @Test
    public void datosDelUsuarioCompletosTest() {
    }
}
