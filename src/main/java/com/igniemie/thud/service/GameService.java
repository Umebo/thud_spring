package com.igniemie.thud.service;

import com.igniemie.thud.exception.InvalidParamException;
import com.igniemie.thud.model.Game;
import com.igniemie.thud.model.GameStatus;
import com.igniemie.thud.model.Player;
import com.igniemie.thud.session.GameSession;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameService {

    public Game createGame(Player player){
        Game game = new Game();
        game.setBoard(new int[3][3]);
        game.setGameId(UUID.randomUUID().toString());
        game.setPlayer1(player);
        game.setStatus(GameStatus.NEW);
        GameSession.getInstance().setGame(game);
        return game;
    }

    public Game connectToGame(Player player2, String gameId) throws InvalidParamException{
        if (!GameSession.getInstance().getGames().containsKey(gameId)) {
            throw new InvalidParamException("Game with this Id doesn't exist.");
        }
        Game game = GameSession.getInstance().getGames().get(gameId);

        if (game.getPlayer2() != null) {
            throw new InvalidParamException("This game is already occupied.");
        }

        game.setPlayer2(player2);
        game.setStatus(GameStatus.IN_PROGRESS);
        GameSession.getInstance().setGame(game);
        return game;
    }


}
