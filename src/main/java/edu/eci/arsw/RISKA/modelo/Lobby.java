package edu.eci.arsw.RISKA.modelo;


import edu.eci.arsw.RISKA.exceptions.RiskaException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author Tatiana Higuera, Andres Vega, Nicolas Moreno.
 */
public class Lobby {
    public ConcurrentLinkedDeque<Jugador> participantes;
    public String[] colores = {"Rojo","Amarillo","Azul","Verde"};
    public boolean activo;
    
    
    public Lobby() {
        participantes = new ConcurrentLinkedDeque();
        activo = true;
        tiempo();
    }
    
    public void inserJu(Jugador j){
        j.serColor(colores[participantes.size()]);
        participantes.add(j);
        
    }
    
    public void elimJu(Jugador j)throws RiskaException{
        if(participantes.contains(j))participantes.remove(j);
        else throw new RiskaException("Jugador no encontrado en el Lobby");
    }
    
    public int cantidadJu(){
        return participantes.size();
    }
    
    public ArrayList<Jugador> getPar(){
        ArrayList<Jugador> parti = new ArrayList<>();
        for (Jugador j : participantes) {
            parti.add(j);
        }
        return parti;
    }

    public void tiempo(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int time=120000;
            public void run() {
            	time--;
                if (time< 0){
                    activo =false;
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
        timer.cancel();
    }
    
    public boolean activo() {
        return activo;
    }
}
