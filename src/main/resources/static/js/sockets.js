const url = 'http://localhost:8080';
let stompClient;
let gameUUID;
let playerType;

function connectToSocket(gameUUID) {

    console.log("connecting to the game");
    let socket = new SockJS(url + "/gameplay");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to the frame: " + frame);
        stompClient.subscribe("/topic/game-progress/" + gameUUID, function (response) {
            let data = JSON.stringify(response.body);
            console.log(data);
            displayResponse(data);
        })
    })
}

function createGame(nickname) {
    $.ajax({
        url: url + "/game/start",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "nickname": nickname
        }),
        success: function (data) {
            gameUUID = data.gameUUID;
            playerType = 'X';
            reset();
            connectToSocket(gameUUID);
            alert("Your created a game. Game id is: " + data.gameUUID);
            gameOn = true;
        },
        error: function (error) {
            console.log(error);
        }
    })
}

function connectToSpecificGame(Nickname) {
    let nickname = Nickname;
    let gameUUID = document.getElementById("gameUUID").value;
    if (gameUUID == null || gameUUID === '') {
        alert("Please enter game id");
    }
    $.ajax({
        url: url + "/game/connect",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "player": {
                "nickname": nickname
            },
            "gameUUID": gameUUID
        }),
        success: function (data) {
            gameUUID = data.gameUUID;
            playerType = 'O';
            reset();
            connectToSocket(gameUUID);
            alert("Congrats you're playing with: " + data.player1);
        },
        error: function (error) {
            console.log(error);
        }
    })
}