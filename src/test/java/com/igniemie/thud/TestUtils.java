package com.igniemie.thud;

import com.igniemie.thud.model.Game;
import com.igniemie.thud.model.GameStatus;
import com.igniemie.thud.model.Player;

public class TestUtils {

    public static Game generateGame() {
        Game game = new Game();
        game.setGameId(2);
        game.setGameUUID("bde7c065-74c3-41fb-a26b-10a6430d0db0");
        game.setPlayer1("romek");
        game.setPlayer2("janek");
        game.setStatus(GameStatus.NEW);

        return game;
    }

    public static Player generatePlayer() {
        Player player = new Player();
        player.setNickname("kajtek");

        return player;
    }
}
