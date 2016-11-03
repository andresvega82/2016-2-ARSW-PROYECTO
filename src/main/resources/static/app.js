var stompClient = null;
var nombre = "";
var v = 1;
var f = 0;
var lobbyId = 0;
var idSus;
function connect() {
    var socket = new SockJS('/stompendpoint');
    stompClient = Stomp.over(socket);
    var estado = sessionStorage.getItem('si');
    if(estado==1){
        
        stompClient.connect({},function(frame){
        nombre = sessionStorage.getItem('idLobby');
        $.get("/riska/getLobby."+nombre,function (data){
            setIdSus(data);
            setId(data);
            createTopic(data); 
            $.get("/riska/ultimo."+data,function(data){  
                
                if(data == 1){
                    window.open("partida.html","_self"); 
                }
            });
        });
        sessionStorage.clear();
    });
    }
    
            
}

function getIdSus(){
    return idSus;
}

function setIdSus(num){
    idSus = num;
}

function createTopic(id){
    idSus= id;
        stompClient.subscribe('/topic/lobby.'+getIdSus(),function(data){
               setId(data.body);
               
        });
        stompClient.subscribe('/topic/lobbyPartida.'+getIdSus(),function(data){
               window.open("partida.html","_self"); 
        });
    
    
}

function setId(num){
    lobbyId = num;
    $.get("/riska/getLobbyPlayers."+lobbyId,function(data){
        var lista = JSON.parse(JSON.stringify(data));
        for(var x = 0; x <lista.length; x++){
            $("#NombreJugador"+(x+1)).html(lista[x]);
        }
        
        
    });
}

function getNom(){
    return nombre;
}

function login(){
    
    nombre = $("#IngresoNombre").val();
    sessionStorage.setItem('idLobby',nombre);
    sessionStorage.setItem('si',v);
    w = window.open("lobby.html","_self"); 
    //stompClient.send('/app/ingresarLobby',{},nombre);
}

function jugar(){
    sessionStorage.clear();
    sessionStorage.setItem('si',f);
    w = window.open("login.html","_self"); 
}


function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
    sessionStorage.clear();    
}

$(document).ready(
    function () {
        connect();
    }                
);
