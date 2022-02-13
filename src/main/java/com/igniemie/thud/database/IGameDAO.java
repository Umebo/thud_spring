package com.igniemie.thud.database;


import com.igniemie.thud.database.impl.GameDAO;
import com.igniemie.thud.model.Game;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IGameDAO {
    void addGame(Game game);

    Optional<Game> getGameById(String gameUUID);

    Optional<Game> getGameByPlayer(String player);

    List<Game> getGames();
}
