package com.igniemie.thud.database;


import com.igniemie.thud.database.impl.GameDAO;
import com.igniemie.thud.model.Game;

public interface IGameDAO {
    void addGame(Game game);
}
