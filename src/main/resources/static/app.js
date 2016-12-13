var stompClient = null;
var nombre = "";
var v = 1;
var f = 0;
var lobbyId = 0;
var idSus;
var partida;
var contadorDeClicks = 0;
var clicks = ["",""];
var inicioPartida = false;
var colores = {'Rojo':'#FF0000','Verde':'#008000','Azul':'#00BFFF','Amarillo':'#FFFF00'};
var countrys =[
new PaisMundo('iceland',100,430,'#CCCCCC'),
new PaisMundo('NorthAfrica',405,450,'#CCCCCC'),
new PaisMundo('Venezuela',395,205,'#CCCCCC'),
new PaisMundo('NorthwestTerritory',70,220,'#CCCCCC'),
new PaisMundo('Greenland',80,340,'#CCCCCC'),
new PaisMundo('Alaska',115,35,'#CCCCCC'),
new PaisMundo('Alberta',130,120,'#CCCCCC'),
new PaisMundo('Ontario',140,192,'#CCCCCC'),
new PaisMundo('Quebec',140,270,'#CCCCCC'),
new PaisMundo('WesternUnitedStates',250,140,'#CCCCCC'),
new PaisMundo('EasternUnitedStates',240,245,'#CCCCCC'),
new PaisMundo('CentralAmerica',320,183,'#CCCCCC'),
new PaisMundo('Peru',465,220,'#CCCCCC'),
new PaisMundo('Brazil',415,290,'#CCCCCC'),
new PaisMundo('Argentina',565,250,'#CCCCCC'),
new PaisMundo('EastAfrica',440,563,'#CCCCCC'),
new PaisMundo('Congo',485,535,'#CCCCCC'),
new PaisMundo('SouthAfrica',565,535,'#CCCCCC'),
new PaisMundo('Madagascar',610,650,'#CCCCCC'),
new PaisMundo('WesternAustralia',565,880,'#CCCCCC'),
new PaisMundo('EasternAustralia',595,960,'#CCCCCC'),
new PaisMundo('Indonesia',510,830,'#CCCCCC'),
new PaisMundo('NewGuinea',490,950,'#CCCCCC'),
new PaisMundo('GreatBritain',175,400,'#CCCCCC'),
new PaisMundo('WesternEurope',320,430,'#CCCCCC'),
new PaisMundo('NorthernEurope',195,510,'#CCCCCC'),
new PaisMundo('SouthernEurope',302,520,'#CCCCCC'),
new PaisMundo('Scandinavia',145,500,'#CCCCCC'),
new PaisMundo('Ukraine',185,595,'#CCCCCC'),
new PaisMundo('MiddleEast',360,610,'#CCCCCC'),
new PaisMundo('India',365,735,'#CCCCCC'),
new PaisMundo('Slam',395,835,'#CCCCCC'),
new PaisMundo('China',300,835,'#CCCCCC'),
new PaisMundo('Mongolia',245,835,'#CCCCCC'),
new PaisMundo('Japan',215,937,'#CCCCCC'),
new PaisMundo('Irkutsk',180,810,'#CCCCCC'),
new PaisMundo('Afghanistan',260,680,'#CCCCCC'),
new PaisMundo('Ural',180,695,'#CCCCCC'),
new PaisMundo('Siberia',120,750,'#CCCCCC'),
new PaisMundo('Yakutsk',87,820,'#CCCCCC'),
new PaisMundo('kamchatka',90,900,'#CCCCCC'),
new PaisMundo('Egypt',388,563,'#CCCCCC')];

function PaisMundo(nombre,IdX,IdY,color){
    this.nombre = nombre;
    this.IdX = IdX;
    this.IdY = IdY;
    this.color = color;
}

function connect() {
    
    var socket = new SockJS('/stompendpoint');
    stompClient = Stomp.over(socket);
    var estado = sessionStorage.getItem('si');
    if (estado == 1) {
        stompClient.connect({}, function (frame) {
            nombre = sessionStorage.getItem('idLobby');
            $.get("/riska/getLobby." + nombre, function (data) {
                setIdSus(data);
                setId(data);
                createTopic(data);
                sessionStorage.setItem('partida',getIdSus());
                $.get("/riska/ultimo." + data, function (data) {
                    //sessionStorage.setItem();
                    if (data == 1) {
                        sessionStorage.setItem('ready', 1);   
                        sessionStorage.setItem('si',0);
                        sessionStorage.setItem('partida',getIdSus());
                        sessionStorage.setItem('idLobby',nombre);
                        window.open("partida.html", "_self");
                    }
                    
                });
                
                sessionStorage.clear();
            });
            sessionStorage.clear();
            
        }
        );
    }
    if(sessionStorage.getItem('ready') == 1){
        cargar();
        setIdSus(sessionStorage.getItem('partida'));
        stompClient.connect({},function (frame){
            stompClient.subscribe('/topic/partidaTropas.' + getIdSus(), function (data) {
                datos = data.body.split(",");
                if(!inicioPartida){
                    siguienteTurno(datos[0]); 
                }
                console.log("sapo"+datos[2]);
                document.getElementById('identificador'+datos[1]).style.background=colores[datos[2]];
                $('#identificador'+datos[1]+'Num').html(datos[3]);   
                
            });
            stompClient.subscribe('/topic/inicioPartida.' + getIdSus(), function (data) {
                inicioPartida = true;
                $('#JugadorPartidaTurno').html("");  
                $('#turno').html(" ");
                alert("EMPIEZA LA BATALLA!!");
            });
        //stompClient.subscribe('/topic/partidaTropas.' + getIdSus(), function (data) {
        });
        sessionStorage.setItem('ready',0);
    }
    
            
}

function mision(){
    
    activarIdentificador();
    
    $.get("/riska/mision."+partida+"/"+nombre,function(data){
        $("#Mision").html(data);
    });
}

function cargar(){
    partida = sessionStorage.getItem('partida');
    nombre = sessionStorage.getItem('idLobby');
    $("#TarjetaPartidaJugador").html(nombre);
    $.get("/riska/color."+partida+"/"+nombre,function(data){
        $("#TarjetaPartidaColor").html(data);
    });
    siguienteTurno(partida);  
    mision(partida);
}

function siguienteTurno(num){
    $.get("/riska/turno."+num,function(data){
        $("#turno").html(data);
    })
}



function getIdSus(){
    return idSus;
}

function setIdSus(num){
    idSus = num;
    sessionStorage.setItem('partida',idSus);
}

function createTopic(id){
    idSus= id;
        stompClient.subscribe('/topic/lobby.'+getIdSus(),function(data){
               setId(data.body);
               
        });
        stompClient.subscribe('/topic/lobbyPartida.'+getIdSus(),function(data){
               sessionStorage.setItem('ready', 1);   
               sessionStorage.setItem('si',0);
               sessionStorage.setItem('partida',getIdSus());
               sessionStorage.setItem('idLobby',nombre);
               window.open("partida.html","_self"); 
        });
        
    
    
}



function activarIdentificador(){
    for ( x = 0 ; x < countrys.length ; x++){
        
        document.getElementById('identificador'+countrys[x].nombre).style.background=countrys[x].color;
        document.getElementById('identificador'+countrys[x].nombre).style.margin=countrys[x].IdX+"px "+countrys[x].IdY+"px";
        
        document.getElementById('identificador'+countrys[x].nombre+'Num').style.margin=countrys[x].IdX+"px "+countrys[x].IdY+"px";
        
    }
}

function pais(pais){
    if(!inicioPartida){        
        $.ajax({
        url: "/riska/tropas."+partida+"/"+pais,
        type: 'PUT',
        data: JSON.stringify(nombre),
        contentType: "application/json"  
        }).then(siguienteTurno(partida));
    }else{
        clicks[contadorDeClicks] = pais;
        contadorDeClicks += 1;
        if(contadorDeClicks == 2){
            $.ajax({
            url: "/riska/tropas."+partida+"/"+clicks[0]+"/"+clicks[1],
            type: 'PUT',
            data: JSON.stringify(nombre),
            contentType: "application/json"  
            }).then(clicks=["",""]);
            contadorDeClicks = 0;
            
        }     
    }
    
    
    
    
    
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

function getMousePos(canvas, evt) {
    var rect = canvas.getBoundingClientRect();
    return {
        x: evt.clientX - rect.left,
        y: evt.clientY - rect.top
    };
}

var canvas = null;
var context = null;



$(document).ready(
        function () {
            connect();
        }
);
