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
 * Pruebas de historia de Usuario 3:
 *          Batallar por un campo de juego
 *          Como jugador, Quiero poder combatir por el campo de juego contra 
 *          otro jugador, en la partida de juego.
 * 
 * CLASES DE EQUIVALENCIA:
 * C1: Para poder combatir es necesario que hallan solamente 2 jugadores
 * C2: mientras exista un combate entre dos jugadores, otro jugador no puede 
 *     combatir con nunguno de estos jugadores.
 * C3: Se debe asignar siempre un numero al azar a cada jugador para combatir
 * C4: Se debe terminar una batalla cuando a alguno de los jugadores se le acabe
 *     el numero de dados.
 * C5: en una batalla se debe disminuir en uno el numero de dados al jugador que 
 *     halla perdido el combate
 * C6: se debe realizar la debida asignacion de dados para un combate a cada jugador
 * C7: No se puede hacer modificaciones a los terrenos de los jugadores, hasta 
 *     que se termine la batalla.
 * 
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BatallarPorUnCampoDeJuegoH3Test {
    
            
    @Before
    public void setUp() {
    }
    
    
    @After
    public void cargarBaseDeDatos(){
    }
    
    
    @Test
    public void iniciarBatallaConSoloDosJugadoresTest() {
    }

    
    @Test
    public void aCadaJugadorSeLeAsignaraUnaCantidadDeDadosTest() {
    }
    
    @Test
    public void noSeDebeModificarLosTerrenosDeLosJugadoresHastaTerminarLaBatallaTest() {
    }
    
    
    @Test
    public void unJugadorNoPuedeCombatirConOtroQueEsteEnUnCombateTest() {
    }
    
    
    @Test
    public void unJugadorPuedeCombatirConOtroQueNoEsteEnCombateTest() {
    }
    
    
    @Test
    public void terminarBatallaCuandoUnJugadorAcabeConElNumeroDeDadosTest() {
    }
    
    
    @Test
    public void seDebeDisminuirElNumeroDeDadosAlJugadorQuePerdioLaBatallaTest() {
    }
    
    
    @Test
    public void noDebeDisminuirElNumeroDeDadosAlJugadorQuePerdioLaBatallaTest() {
    }
   
}
