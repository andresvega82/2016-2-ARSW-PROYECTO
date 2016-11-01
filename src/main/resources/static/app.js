var stompClient = null;

function connect() {
    var socket = new SockJS('/stompendpoint');
    stompClient = Stomp.over(socket);

    
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


function asd(){
    var json  = { };
    json.nameJugador = $("#name").val();
    var gameid = $("#gameid").val();
    stompClient.send("/app/newPartida."+gameid,{},JSON.stringify(json));  
}







$(document).ready(
    function () {
        connect();
    }
         
                
);
