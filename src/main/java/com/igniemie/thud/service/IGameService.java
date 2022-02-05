package com.igniemie.thud.service;

import com.igniemie.thud.model.Game;
import com.igniemie.thud.model.Player;

public interface IGameService {

    Game createGame(Player player);
}
