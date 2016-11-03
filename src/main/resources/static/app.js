var stompClient = null;
var nombre = "";
var v = 1;
var f = 0;
var lobbyId = 0;
function connect() {
    var socket = new SockJS('/stompendpoint');
    stompClient = Stomp.over(socket);
    var estado = sessionStorage.getItem('si');
    if(estado==1){
        stompClient.connect({},function(frame){
            console.log('Connected: ' + frame);
            nombre = sessionStorage.getItem('idLobby');
            stompClient.subscribe('/topic/idlobby.'+nombre,function(data){
               var id = data.body;
               alert(id);
            });
        });
        nombre = sessionStorage.getItem('idLobby');
        $.get("/riska/getLobby."+nombre,function (data){
            setId(data.body);
        });
    }           
            
}

function setId(num){
    lobbyId = num;
    console.log(lobbyId);
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
