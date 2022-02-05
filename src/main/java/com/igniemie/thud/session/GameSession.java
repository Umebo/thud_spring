package com.igniemie.thud.session;

import com.igniemie.thud.model.Game;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;

@Component
@SessionScope
public class GameSession {

    private static Map<String, Game> games;
    private static GameSession instance;

    private GameSession() {
        games = new HashMap<>();
    }

    public static synchronized GameSession getInstance() {
        if (instance == null) {
            instance = new GameSession();
        }
        return instance;
    }

    public static Map<String, Game> getGames() {
        return games;
    }

    public void setGame(Game game) {
        games.put(game.getGameId(), game);
    }
}
