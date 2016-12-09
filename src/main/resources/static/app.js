var stompClient = null;
var nombre = "";
var v = 1;
var f = 0;
var lobbyId = 0;
var idSus;
var partida;
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
                siguienteTurno(datos[0]);                
                document.getElementById('identificador'+datos[1]).style.background=colores[datos[2]];
                $('#identificador'+datos[1]+'Num').html(datos[3]);               
            });
            stompClient.subscribe('/topic/inicioPartida.' + getIdSus(), function (data) {                
                $('#JugadorPartidaTurno').html("");   
                $('#turno').html("");               
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
    
    
    /*
     
    document.getElementById('identificadorAlaska').style.background='#CCCCCC';
    document.getElementById('identificadorAlaska').style.margin="115px 35px"; 
    document.getElementById('identificadorNorthwestTerritory').style.background='#CCCCCC';
    document.getElementById('identificadorNorthwestTerritory').style.margin="70px 220px"; 
    document.getElementById('identificadorAlberta').style.background='#CCCCCC';
    document.getElementById('identificadorAlberta').style.margin="130px 120px"; 
    document.getElementById('identificadorGreenland').style.background='#CCCCCC';
    document.getElementById('identificadorGreenland').style.margin="80px 340px";
    document.getElementById('identificadorOntario').style.background='#CCCCCC';
    document.getElementById('identificadorOntario').style.margin="140px 192px";
    document.getElementById('identificadorQuebec').style.background='#CCCCCC';
    document.getElementById('identificadorQuebec').style.margin="140px 270px";
    document.getElementById('identificadorEasternUnitedStates').style.background='#CCCCCC';
    document.getElementById('identificadorEasternUnitedStates').style.margin="240px 245px";
    document.getElementById('identificadorWesternUnitedStates').style.background='#CCCCCC';
    document.getElementById('identificadorWesternUnitedStates').style.margin="250px 140px";
    document.getElementById('identificadorCentralAmerica').style.background='#CCCCCC';
    document.getElementById('identificadorCentralAmerica').style.margin="320px 183px";
    document.getElementById('identificadorVenezuela').style.background='#CCCCCC';
    document.getElementById('identificadorVenezuela').style.margin="395px 205px";
    document.getElementById('identificadorBrazil').style.background='#CCCCCC';
    document.getElementById('identificadorBrazil').style.margin="415px 290px";
    document.getElementById('identificadorPeru').style.background='#CCCCCC';
    document.getElementById('identificadorPeru').style.margin="465px 220px";
    document.getElementById('identificadorArgentina').style.background='#CCCCCC';
    document.getElementById('identificadorArgentina').style.margin="565px 250px";
    document.getElementById('identificadorNorthAfrica').style.background='#CCCCCC';
    document.getElementById('identificadorNorthAfrica').style.margin="405px 450px";
    document.getElementById('identificadorEgypt').style.background='#CCCCCC';
    document.getElementById('identificadorEgypt').style.margin="392px 563px";
    document.getElementById('identificadorEastAfrica').style.background='#CCCCCC';
    document.getElementById('identificadorEastAfrica').style.margin="440px 563px";
    document.getElementById('identificadorCongo').style.background='#CCCCCC';
    document.getElementById('identificadorCongo').style.margin="485px 535px";
    document.getElementById('identificadorSouthAfrica').style.background='#CCCCCC';
    document.getElementById('identificadorSouthAfrica').style.margin="565px 535px";
    document.getElementById('identificadorWesternAustralia').style.background='#CCCCCC';
    document.getElementById('identificadorWesternAustralia').style.margin="565px 880px";
    document.getElementById('identificadorEasternAustralia').style.background='#CCCCCC';
    document.getElementById('identificadorEasternAustralia').style.margin="595px 960px";
    document.getElementById('identificadorNewGuinea').style.background='#CCCCCC';
    document.getElementById('identificadorNewGuinea').style.margin="490px 950px";
    document.getElementById('identificadorIndonesia').style.background='#CCCCCC';
    document.getElementById('identificadorIndonesia').style.margin="510px 830px";
    document.getElementById('identificadorSlam').style.background='#CCCCCC';
    document.getElementById('identificadorSlam').style.margin="395px 835px";
    document.getElementById('identificadorChina').style.background='#CCCCCC';
    document.getElementById('identificadorChina').style.margin="300px 835px";
    document.getElementById('identificadorIndia').style.background='#CCCCCC';
    document.getElementById('identificadorIndia').style.margin="365px 735px";
    document.getElementById('identificadorMiddleEast').style.background='#CCCCCC';
    document.getElementById('identificadorMiddleEast').style.margin="360px 610px";
    document.getElementById('identificadorAfghanistan').style.background='#CCCCCC';
    document.getElementById('identificadorAfghanistan').style.margin="260px 680px";
    document.getElementById('identificadorUral').style.background='#CCCCCC';
    document.getElementById('identificadorUral').style.margin="180px 695px";
    document.getElementById('identificadorUkraine').style.background='#CCCCCC';
    document.getElementById('identificadorUkraine').style.margin="185px 595px";
    document.getElementById('identificadorSiberia').style.background='#CCCCCC';
    document.getElementById('identificadorSiberia').style.margin="120px 750px";
    document.getElementById('identificadorMongolia').style.background='#CCCCCC';
    document.getElementById('identificadorMongolia').style.margin="245px 835px";
    document.getElementById('identificadorIrkutsk').style.background='#CCCCCC';
    document.getElementById('identificadorIrkutsk').style.margin="180px 810px";
    document.getElementById('identificadorSouthernEurope').style.background='#CCCCCC';
    document.getElementById('identificadorSouthernEurope').style.margin="302px 520px";
    document.getElementById('identificadorWesternEurope').style.background='#CCCCCC';
    document.getElementById('identificadorWesternEurope').style.margin="320px 430px";
    document.getElementById('identificadorNorthernEurope').style.background='#CCCCCC';
    document.getElementById('identificadorNorthernEurope').style.margin="195px 510px";
    document.getElementById('identificadorScandinavia').style.background='#CCCCCC';
    document.getElementById('identificadorScandinavia').style.margin="145px 500px";
    document.getElementById('identificadoriceland').style.background='#CCCCCC';
    document.getElementById('identificadoriceland').style.margin="100px 430px";
    document.getElementById('identificadorGreatBritain').style.background='#CCCCCC';
    document.getElementById('identificadorGreatBritain').style.margin="175px 400px";
    document.getElementById('identificadorYakutsk').style.background='#CCCCCC';
    document.getElementById('identificadorYakutsk').style.margin="87px 820px";
    document.getElementById('identificadorkamchatka').style.background='#CCCCCC';
    document.getElementById('identificadorkamchatka').style.margin="90px 900px";
    document.getElementById('identificadorJapan').style.background='#CCCCCC';
    document.getElementById('identificadorJapan').style.margin="215px 937px";
    document.getElementById('identificadorMadagascar').style.background='#CCCCCC';
    document.getElementById('identificadorMadagascar').style.margin="610px 650px";
    */
}

function pais(pais){
    
    
    
    
    
    $.ajax({
        url: "/riska/tropas."+partida+"/"+pais,
        type: 'PUT',
        data: JSON.stringify(nombre),
        contentType: "application/json"  
    }).then(siguienteTurno(partida));
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
