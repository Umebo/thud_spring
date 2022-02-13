package com.igniemie.thud.service;

import com.igniemie.thud.model.Game;
import com.igniemie.thud.model.Player;

public interface IGameService {

    void createGame(String player);

    void getGameById(String gameId);
}
