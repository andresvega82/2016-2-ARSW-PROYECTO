package edu.eci.arsw.RISKA.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Tatiana Higuera, Andres Vega, Nicolas Moreno.
 */


public class Partida {
    ArrayList<Jugador> jugadores;
    ArrayList<Mision> misiones;
    Grafo graph;
    HashMap<Integer, Boolean> PaisesConTropas;
    HashMap<String, Integer> nombrePaises ;
    int turno;
    
    public Partida() {
        jugadores = new ArrayList<>();
        misiones = new ArrayList<>();
        cargarMisiones();
        turno = 0;
    }
    
    public String colorJugador(String nombre){
        String color = "";
        for (int i = 0; i < jugadores.size(); i++) {
            if(jugadores.get(i).nombre.equalsIgnoreCase(nombre))color = jugadores.get(i).color;
        }
        return color;
    }
    
    public Jugador getTurno(){
        Jugador j = jugadores.get(turno%4);
        return j;
    }
    
    public boolean hasNextTurn(){
        return turno<=39;
    }
    
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    
    public String getColorTerritorio(String pais){
        return graph.grafo[nombrePaises.get(pais)].color;
    }
    
    public int getNumeroTropasTerritorio(String pais){
        return graph.grafo[nombrePaises.get(pais)].getCantTropas();
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
    
    public ArrayList<Mision> getMisiones(){
        return misiones;
    }
    
    public Boolean puedoUbicar(String nombreJugador, String pais){
        Boolean band = false;
        if(!jugadores.get(turno).nombre.equals(nombreJugador)){
            return false;
        }
        if(!graph.grafo[nombrePaises.get(pais)].ocupado){
            //graph.grafo[nombrePaises.get(pais)].setOcupado(true);
            //graph.grafo[nombrePaises.get(pais)].setQuienOcupa(nombreJugador);
            //graph.grafo[nombrePaises.get(pais)].setCantTropas(1);
            band = true;
        }else{
            if(graph.grafo[nombrePaises.get(pais)].getQuienOcupa().equalsIgnoreCase(nombreJugador)){
                //graph.grafo[nombrePaises.get(pais)].setCantTropas(graph.grafo[nombrePaises.get(pais)].cantTropas+1);
                band = true;
            }
        }
        return band;
    }
    
    public void ubicarTropa(String pais, String nombre){
        graph.grafo[nombrePaises.get(pais)].setOcupado(true);
        graph.grafo[nombrePaises.get(pais)].setCantTropas(graph.grafo[nombrePaises.get(pais)].getCantTropas()+1);
        String color = "";
        for (int i = 0; i < 4; i++) {
            if(jugadores.get(i).nombre.equalsIgnoreCase(nombre))color = jugadores.get(i).color;
        }
        graph.grafo[nombrePaises.get(pais)].setColor(color);
        turno++;
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
    
    public void prepararMapa(){
        nombrePaises = new HashMap<String, Integer>();
        nombrePaises.put("ALASKA",0);
        nombrePaises.put("NORTH WEST TERRITORY",1);
        nombrePaises.put("ALBERTA",2);
        nombrePaises.put("ONTARIO",3);
        nombrePaises.put("QUEBEC",4);
        nombrePaises.put("WESTERN UNITED STATES",5);
        nombrePaises.put("EASTERN UNITED STATES",6);
        nombrePaises.put("CENTRAL AMERICA",7);
        nombrePaises.put("VENEZUELA",8);
        nombrePaises.put("PERU",9);
        nombrePaises.put("BRAZIL",10);
        nombrePaises.put("ARGENTINA",11);
        nombrePaises.put("GREENLAND",12);
        nombrePaises.put("GREAT BRITAIN",13);
        nombrePaises.put("ICELAND",14);
        nombrePaises.put("WESTERN EUROPE",15);
        nombrePaises.put("NORTH AFRICA",16);
        nombrePaises.put("SCANDLNAVLA",17);
        nombrePaises.put("NORTHERN EUROPE",18);
        nombrePaises.put("SOUTHERN EUROPE",19);
        nombrePaises.put("EGYPT",20);
        nombrePaises.put("EAST AFRICA",21);
        nombrePaises.put("CONGO",22);
        nombrePaises.put("SOUTH AFRICA",23);
        nombrePaises.put("UKRAINE",24);
        nombrePaises.put("MIDDLE EAST",25);
        nombrePaises.put("MADAGASCAR",26);
        nombrePaises.put("URAL",27);
        nombrePaises.put("AFGHANISTAN",28);
        nombrePaises.put("INDIA",29);
        nombrePaises.put("INDONESIA",30);
        nombrePaises.put("SIBERIA",31);
        nombrePaises.put("CHINA",32);
        nombrePaises.put("SLAM",33);
        nombrePaises.put("WESTERN AUSTRALIA",34);
        nombrePaises.put("YAKUTSK",35);
        nombrePaises.put("IRKUTSK",36);
        nombrePaises.put("MONGOLIA",37);
        nombrePaises.put("KAMCHTKA",38);
        nombrePaises.put("JAPAN",39);
        nombrePaises.put("NEW GUINEA",40);
        nombrePaises.put("EASTERN AUSTRALIA",41);

        
        
        
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

    public void asignarColores() {
        for (int i = 0; i < jugadores.size(); i++) {
            if(i==0){
                jugadores.get(i).serColor("Rojo");
            }else if(i==1){
                jugadores.get(i).serColor("Azul");
            }else if(i==2){
                jugadores.get(i).serColor("Verde");
            }else if(i==3){
                jugadores.get(i).serColor("Amarillo");
            }
        }
    }
    
    
    
    
    //----------------------------GRAFO DEL MAPA DE LA PARTIDA
    public final class Grafo{
        private final int nroAristas;
        private final Nodo[] grafo;
        private final int MAX_VERTICES =42;
        private final int MAX_ARISTAS =100000;


        public Grafo(){
            nroAristas = 0;
            grafo = new Nodo[MAX_VERTICES];
            for (int i = 0; i < grafo.length; i++) {
                grafo[i] = new Nodo(i);
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
               grafo[v1].addAdj(v2);
               grafo[v2].addAdj(v1);
            }
        }
        //Retorna verdadero si existe una arista dirigida entre los vertices v1 y v2
        public boolean existeArista(int v1, int v2){
            if(v1 >= MAX_VERTICES || v2 >= MAX_VERTICES){ 
                throw new ArrayIndexOutOfBoundsException("Vertices inválidos, fuera de rango"+
                                "\nRango de vertices: 0 - " + (getMAX_VERTICES() - 1));
            }
            else if(grafo[v1].adj.contains(v2)){
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
    public class Nodo{
        int id;
        String quienOcupa;
        boolean ocupado;
        ArrayList<Integer> adj;
        int cantTropas;
        String color;

        public Nodo(int id) {
            this.id = id;
            this.quienOcupa = "";
            this.ocupado = false;
            this.adj = new ArrayList<>();
            this.cantTropas = 0;
            this.color = "";
        }
        
        public void addAdj(int n){
            adj.add(n);
        }

        public String getQuienOcupa() {
            return quienOcupa;
        }

        public void setQuienOcupa(String quienOcupa) {
            this.quienOcupa = quienOcupa;
        }

        public boolean isOcupado() {
            return ocupado;
        }

        public void setOcupado(boolean ocupado) {
            this.ocupado = ocupado;
        }

        public int getCantTropas() {
            return cantTropas;
        }

        public void setCantTropas(int cantTropas) {
            this.cantTropas = cantTropas;
        }
        
        public void setColor(String color){
            this.color = color;
        }
    }
}

