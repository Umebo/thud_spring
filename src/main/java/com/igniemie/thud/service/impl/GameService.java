package com.igniemie.thud.service.impl;

import com.igniemie.thud.database.IGameDAO;
import com.igniemie.thud.model.Game;
import com.igniemie.thud.model.GameStatus;
import com.igniemie.thud.model.Player;
import com.igniemie.thud.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameService implements IGameService {

    @Autowired
    IGameDAO gameDAO;

    @Override
    public Game createGame(Player player){
        Game game = new Game();
        game.setBoard(new int[3][3]);
        game.setGameId(UUID.randomUUID().toString());
        game.setPlayer1(player);
        game.setStatus(GameStatus.NEW);
        //GameSession.getInstance().setGame(game);
        return game;
    }

    //TODO create a new entry instead of getting an instance of Game
/*
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
*/

}
