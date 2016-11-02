package edu.eci.arsw.RISKA.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import sun.nio.cs.ext.MSISO2022JP;

/**
 *
 * @author Tatiana Higuera, Andres Vega, Nicolas Moreno.
 */
public class Partida {
    ArrayList<Jugador> jugadores;
    ArrayList<Mision> misiones;
    Grafo graph;
    HashMap<Integer, String> nombrePaises ;

    public Partida() {
        jugadores = new ArrayList<>();
        misiones = new ArrayList<>();
        cargarMisiones();
        
    }
    
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = (ArrayList<Jugador>) jugadores.clone();
    }
    
    public void setMisiones(){
        Random r = new Random();
        for (Jugador j : jugadores) {
            j.setMision(misiones.remove(r.nextInt(misiones.size())));
        }
    }
    
    public void prepararMapa(){
        nombrePaises = new HashMap<Integer, String>();
        nombrePaises.put(0, "ALASKA");
        nombrePaises.put(1, "NORTH WEST TERRITORY");
        nombrePaises.put(2, "ALBERTA");
        nombrePaises.put(3, "ONTARIO");
        nombrePaises.put(4, "QUEBEC");
        nombrePaises.put(5, "WESTERN UNITED STATES");
        nombrePaises.put(6, "EASTERN UNITED STATES");
        nombrePaises.put(7, "CENTRAL AMERICA");
        nombrePaises.put(8, "VENEZUELA");
        nombrePaises.put(9, "PERU");
        nombrePaises.put(10, "BRAZIL");
        nombrePaises.put(11, "ARGENTINA");
        nombrePaises.put(12, "GREENLAND");
        nombrePaises.put(13, "GREAT BRITAIN");
        nombrePaises.put(14, "ICELAND");
        nombrePaises.put(15, "WESTERN EUROPE");
        nombrePaises.put(16, "NORTH AFRICA");
        nombrePaises.put(17, "SCANDLNAVLA");
        nombrePaises.put(18, "NORTHERN EUROPE");
        nombrePaises.put(19, "SOUTHERN EUROPE");
        nombrePaises.put(20, "EGYPT");
        nombrePaises.put(21, "EAST AFRICA");
        nombrePaises.put(22, "CONGO");
        nombrePaises.put(23, "SOUTH AFRICA");
        nombrePaises.put(24, "UKRAINE");
        nombrePaises.put(25, "MIDDLE EAST");
        nombrePaises.put(26, "MADAGASCAR");
        nombrePaises.put(27, "URAL");
        nombrePaises.put(28, "AFGHANISTAN");
        nombrePaises.put(29, "INDIA");
        nombrePaises.put(30, "INDONESIA");
        nombrePaises.put(31, "SIBERIA");
        nombrePaises.put(32, "CHINA");
        nombrePaises.put(33, "SLAM");
        nombrePaises.put(34, "WESTERN AUSTRALIA");
        nombrePaises.put(35, "YAKUTSK");
        nombrePaises.put(36, "IRKUTSK");
        nombrePaises.put(37, "MONGOLIA");
        nombrePaises.put(38, "KAMCHTKA");
        nombrePaises.put(39, "JAPAN");
        nombrePaises.put(40, "NEW GUINEA");
        nombrePaises.put(41, "EASTERN AUSTRALIA");

        
        
        
        graph = new Grafo();
        graph.insertaArista(0, 1);
        graph.insertaArista(0, 2);
        
        graph.insertaArista(1, 2);
        graph.insertaArista(1, 3);
        graph.insertaArista(1, 12);
        
        graph.insertaArista(2, 3);
        graph.insertaArista(2, 5);
        
        graph.insertaArista(3, 12);
        graph.insertaArista(3, 4);
        graph.insertaArista(3, 5);
        graph.insertaArista(3, 6);
        
        graph.insertaArista(4, 12);
        graph.insertaArista(4, 6);
        
        graph.insertaArista(5, 6);
        graph.insertaArista(5, 7);
        
        graph.insertaArista(7, 8);
        
        graph.insertaArista(8, 9);
        graph.insertaArista(8, 10);
        
        graph.insertaArista(9, 11);
        
        graph.insertaArista(10, 11);
        
        graph.insertaArista(12, 14);
        
        graph.insertaArista(13, 14);
        graph.insertaArista(13, 17);
        graph.insertaArista(13, 18);
        graph.insertaArista(13, 15);
        
        graph.insertaArista(14, 17);
        
        graph.insertaArista(15, 18);
        graph.insertaArista(15, 19);
        
        graph.insertaArista(17, 18);
        graph.insertaArista(17, 24);
        
        graph.insertaArista(18, 24);
        graph.insertaArista(18, 19);
        
        graph.insertaArista(19, 16);
        graph.insertaArista(19, 20);
        graph.insertaArista(19, 24);
        
        graph.insertaArista(16, 20);
        graph.insertaArista(16, 21);
        graph.insertaArista(16, 22);
        
        graph.insertaArista(20, 21);
        graph.insertaArista(20, 25);
        
        graph.insertaArista(21, 22);
        graph.insertaArista(21, 23);
        graph.insertaArista(21, 26);
        
        graph.insertaArista(22, 23);
        
        graph.insertaArista(23, 26);
        
        graph.insertaArista(25, 29);
        
        graph.insertaArista(24, 27);
        graph.insertaArista(24, 28);
        
        graph.insertaArista(27, 31);
        graph.insertaArista(27, 32);
        graph.insertaArista(27, 28);
        
        graph.insertaArista(28, 32);
        graph.insertaArista(28, 29);
        
        graph.insertaArista(29, 33);
        graph.insertaArista(29, 32);
        
        graph.insertaArista(31, 35);
        graph.insertaArista(31, 36);
        graph.insertaArista(31, 37);
        graph.insertaArista(31, 32);
        
        graph.insertaArista(32, 33);
        
        graph.insertaArista(33, 30);
        
        graph.insertaArista(38, 35);
        graph.insertaArista(38, 36);
        graph.insertaArista(38, 37);
        graph.insertaArista(38, 39);
        
        graph.insertaArista(39, 37);
        
        graph.insertaArista(30, 40);
        graph.insertaArista(30, 34);
        
        graph.insertaArista(40, 34);
        graph.insertaArista(40, 41);
        
        graph.insertaArista(41, 34);
    }

    private void cargarMisiones() {
        Mision m1 = new Mision("Conquistar Europa");
        Mision m2 = new Mision("Conquistar 15 territorios");
        Mision m3 = new Mision("Conquistar America del sur");
        Mision m4 = new Mision("Conquistar Asia");
        Mision m5 = new Mision("Conquistar America del norte");
        misiones.add(m5);
        misiones.add(m4);
        misiones.add(m3);
        misiones.add(m2);
        misiones.add(m1);        
    }

    
    
    
    
    //----------------------------GRAFO DEL MAPA DE LA PARTIDA
public final class Grafo{
    private final int nroAristas;
    private final int grafo[][];
    private final int MAX_VERTICES =10;
    private final int MAX_ARISTAS =10;
    
    
    public Grafo(){
        this.nroAristas = 0;
        grafo = new int[MAX_VERTICES][MAX_VERTICES];

        for(int i = 0; i < getMAX_VERTICES();i++){
                for (int j = 0; j < getMAX_VERTICES(); j++){
                        grafo[i][j] = 0;
                }
        }
    }
    
    //Inserta una arista entre dirigida del vertice v1 al vertice v2 y del vertice v2 al vertice v1
    public void insertaArista(int v1, int v2)throws ArrayIndexOutOfBoundsException, UnsupportedOperationException
    {
        if(v1 >= MAX_VERTICES || v2 >= MAX_VERTICES){ 
            throw new ArrayIndexOutOfBoundsException("Vertices inválidos, fuera de rango"+
                "\nRango de vertices: 0 - " + (getMAX_VERTICES() - 1));
        }
        else if(nroAristas == MAX_ARISTAS){
            throw new UnsupportedOperationException("No se puede añadir más aristas");
        }		
        else{
            grafo[v1][v2] = 1;
            grafo[v2][v1] = 1;
        }
    }
    //Retorna verdadero si existe una arista dirigida entre los vertices v1 y v2
    public boolean existeArista(int v1, int v2){
        if(v1 >= MAX_VERTICES || v2 >= MAX_VERTICES){ 
            throw new ArrayIndexOutOfBoundsException("Vertices inválidos, fuera de rango"+
                            "\nRango de vertices: 0 - " + (getMAX_VERTICES() - 1));
        }
        else if(grafo[v1][v2] == 1){
            return true;
        }		
        return false;
    }
    
    public int getMAX_VERTICES()
    {
            return MAX_VERTICES;
    }

    public int getMAX_ARISTAS()
    {
            return MAX_ARISTAS;
    }
    
}
}

