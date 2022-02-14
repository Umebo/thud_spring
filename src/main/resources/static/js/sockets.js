const url = 'http://localhost:8080';
let stompClient;
let gameUUID;
let playerType;

function connectToSocket(gameId) {

    console.log("connecting to the game");
    let socket = new SockJS(url + "/gameplay");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to the frame: " + frame);
        stompClient.subscribe("/topic/game-progress/" + gameUUID, function (response) {
            let data = JSON.parse(response.body);
            console.log(data);
            displayResponse(data);
        })
    })
}

function create_game() {
    let nickname = document.getElementById("nickname").value;
    if (nickname == null || nickname === '') {
        alert("Please enter nickname");
    } else {
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
}

function connectToSpecificGame() {
    let nickname = document.getElementById("nickname").value;
    if (nickname == null || nickname === '') {
        alert("Please enter login");
    } else {
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
                alert("Congrats you're playing with: " + data.player1.nickname);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}