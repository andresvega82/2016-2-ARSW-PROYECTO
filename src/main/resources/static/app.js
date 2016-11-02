var stompClient = null;
var nombre = "";
var v = 1;
var f = 0;
function connect() {
    var socket = new SockJS('/stompendpoint');
    stompClient = Stomp.over(socket);
    var estado = sessionStorage.getItem('si');
    alert("sapo2"+estado);
    if(estado==1){
        alert("sapo"+estado);
        stompClient.connect({},function(frame){
        var n = sessionStorage.getItem('idLobby');
        stompClient.subscribe('/topic/idlobby.'+n,function(data){
           var id = data.body;
           alert(id);
            });
        }); 
        nombre = sessionStorage.getItem('idLobby');
        alert("sapo3"+nombre);
        stompClient.send('/app/ingresarLobby',{},nombre);
    }
       
}

function getNom(){
    return nombre;
}

function login(){
    
    nombre = $("#IngresoNombre").val();
    sessionStorage.setItem('idLobby',nombre);
    sessionStorage.setItem('si',v);
    w = window.open("lobby.html","_self");     
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
    
    stompClient.connect({}, function (frame) {
        
        console.log('Connected: ' + frame);
        
        
        stompClient.subscribe('/topic/newPartida.'+$("#gameid").val(), function (data) {
            //subscribe 
        })
        
    });
    
}

$(document).ready(
    function () {
        connect();
    }                
);
