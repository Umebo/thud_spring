package com.igniemie.thud.service;

import com.igniemie.thud.database.dto.GamePlayDTO;
import com.igniemie.thud.exception.InvalidParamException;
import com.igniemie.thud.model.Board;
import com.igniemie.thud.model.Game;
import com.igniemie.thud.model.Player;

import java.util.List;

public interface IGameService {

    Game createGame(Player player);

    Game getGameById(String gameId);

    List<Game> getGames();

    Game getGameByPlayer(String player);

    //TODO create a new entry instead of getting an instance of Game
    Game connectToGame(Player player2, String gameId) throws InvalidParamException;

    void gamePlay(GamePlayDTO gamePlay);
}
