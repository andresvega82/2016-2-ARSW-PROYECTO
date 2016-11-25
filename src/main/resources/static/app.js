var stompClient = null;
var nombre = "";
var v = 1;
var f = 0;
var lobbyId = 0;
var idSus;
var partida;
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
                siguienteTurno(data.body);
        });
        //stompClient.subscribe('/topic/partidaTropas.' + getIdSus(), function (data) {
        });
        sessionStorage.setItem('ready',0);
    }
    
            
}

function mision(){
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
}

function pais(pais){
    
    activarIdentificador();
    
    
    
    
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
            canvas = document.getElementById('myCanvas');
            context = canvas.getContext('2d');
            canvas.addEventListener('mousedown', function (evt) {
                var mousePos = getMousePos(canvas, evt);
                var message = 'Mouse position: ' + mousePos.x + ',' + mousePos.y;
                alert(message);
            }, false);
        }
);
